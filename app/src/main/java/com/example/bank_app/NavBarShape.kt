package com.example.bank_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class BottomNavBarShape(
    private val cornerRadius: Dp = 20.dp,
    private val dipHeight: Dp = 40.dp,
    private val dipWidth: Dp = 60.dp,
    private val dipControlOffset: Dp = 15.dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val dipHeightPx = with(density) { dipHeight.toPx() }
        val dipWidthPx = with(density) { dipWidth.toPx() }
        val dipControlOffsetPx = with(density) { dipControlOffset.toPx() }

        val halfDipWidth = dipWidthPx / 2
        val midX = size.width / 2

        val path = Path().apply {
            // Start from top-left, after the rounded corner
            moveTo(cornerRadiusPx, 0f)

            // Top edge left part leading to the pre-dip curve
            lineTo(midX - halfDipWidth - dipControlOffsetPx , 0f)

            // --- Curve into the dip (left side) ---
            cubicTo(
                x1 = midX - halfDipWidth - (dipControlOffsetPx / 2), // Control point 1: slightly towards the dip
                y1 = 0f,
                x2 = midX - halfDipWidth, // Control point 2: start of the main dip curve, but slightly pulled up
                y2 = dipHeightPx * 0.2f,
                x3 = midX - halfDipWidth - dipControlOffsetPx,
                y3 = 0f, // Start curving down slightly before the main dip

            )

            // Reset and restart path definition for clarity with new curve logic
            reset() // Clear previous path commands

            // Start from top-left, after the rounded corner
            moveTo(cornerRadiusPx, 0f)

            // Line to where the pre-dip curve starts
            val preDipCurveStartX = midX - halfDipWidth - dipControlOffsetPx
            lineTo(preDipCurveStartX, 0f)

            // Smooth curve from flat top edge INTO the start of the main dip (left side)
            // This curve will transition from (preDipCurveStartX, 0f)
            // to (midX - halfDipWidth, 0f) but with a downward curve
            quadraticBezierTo(
                x1 = midX - halfDipWidth - (dipControlOffsetPx / 2), // Control point, pulling the curve start downwards
                y1 = 0f, // Keep y on the top edge initially for the control point
                x2 = midX - halfDipWidth, // End of this pre-dip curve, start of main dip
                y2 = dipHeightPx * 0.1f // Start the main dip slightly lower
            )


            // Main Dip - upside down bell curve (quadratic Bezier curve)
            quadraticBezierTo(
                x1 = midX,                             // Control point for the main dip (center bottom of dip)
                y1 = dipHeightPx,
                x2 = midX + halfDipWidth,              // End of main dip on the right
                y2 = dipHeightPx * 0.1f                // Mirror the y-start of the main dip
            )

            // Smooth curve FROM the end of the main dip back to the flat top edge (right side)
            quadraticBezierTo(
                x1 = midX + halfDipWidth + (dipControlOffsetPx / 2), // Control point, pulling the curve end downwards
                y1 = 0f, // Keep y on the top edge initially
                x2 = midX + halfDipWidth + dipControlOffsetPx, // End of this pre-dip curve
                y2 = 0f
            )

            // Top edge right part, after the pre-dip curve
            lineTo(size.width - cornerRadiusPx, 0f)

            // Top-right corner
            arcTo(
                rect = Rect(
                    Offset(size.width - 2 * cornerRadiusPx, 0f),
                    Size(2 * cornerRadiusPx, 2 * cornerRadiusPx)
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Right edge
            lineTo(size.width, size.height) // Corrected to account for bottom radius

            // Bottom edge
            lineTo(0f, size.height)

            // Left edge
            lineTo(0f, cornerRadiusPx) // Corrected to account for top radius

            // Top-left corner
            arcTo(
                rect = Rect(
                    Offset(0f, 0f),
                    Size(2 * cornerRadiusPx, 2 * cornerRadiusPx)
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            close()
        }
        return Outline.Generic(path)
    }
}


@Composable
@Preview(showBackground = true)
fun BottomNavBarShapePreviewWithDipCurves() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .size(width = 360.dp, height = 70.dp) // Adjusted preview size
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = BottomNavBarShape(
                        cornerRadius = 15.dp,
                        dipHeight = 70.dp,
                        dipWidth = 90.dp,
                        dipControlOffset = 13.dp // Sets the beginning of the dip curve
                    )
                )
        )
    }
}