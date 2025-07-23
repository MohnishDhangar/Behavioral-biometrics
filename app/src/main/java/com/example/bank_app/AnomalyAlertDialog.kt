package com.example.bank_app

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

@Composable
@Preview(showBackground = true)
fun AnomalyAlertDialogPreview() {
    AnomalyAlertDialog(
        hazeState = HazeState(),
        showDialog = true,
        dialogMessage = "Critical Anomaly Detected!",
        onDismiss = {}
    )
}

@Composable
fun AnomalyAlertDialog(
    hazeState: HazeState,
    showDialog: Boolean,
    dialogMessage: String,
    onDismiss: () -> Unit // Lambda to handle dismissal
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss() // Call the lambda when dismissal is requested
            },
            title = {
                Text(
                    text = "Warning",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.LightGray
                )
            },
            text = {
                Text(text = dialogMessage, color = Color.White)
            },
            confirmButton = {
                TextButton(
                    modifier = Modifier.size(50.dp).offset(0.dp, 15.dp),
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("OK")
                }
            },
            modifier = Modifier
                .size(400.dp, 240.dp)
                .padding(6.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 2.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.6f),
                            Color.White.copy(alpha = 0.2f),
                        ),
                    ),
                    shape = RoundedCornerShape(20.dp)
                ),
            shape = MaterialTheme.shapes.medium,
            containerColor = Color(15, 2, 69, 255),
        )
    }
}
