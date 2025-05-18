import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thezayin.dadjokes.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun DynamicResizableTextField(
    descriptionText: TextFieldValue,
    onJokeChange: (TextFieldValue) -> Unit,
    jokePlaceholder: String,
    minHeight: Dp = 50.sdp,
    maxHeight: Dp = 120.sdp,
    maxLines: Int = 5
) {
    val lines = descriptionText.text.lines().size
    val dynamicHeight = (minHeight.value + (lines * 24)).coerceAtMost(maxHeight.value)

    TextField(
        value = descriptionText,
        onValueChange = {
            onJokeChange(it)
        },
        textStyle = TextStyle(
            color = colorResource(R.color.text_color),
            fontSize = 8.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_medium)),
        ),
        placeholder = {
            Text(
                text = jokePlaceholder,
                fontSize = 8.ssp,
                color = colorResource(R.color.text_color),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = R.color.background),
            unfocusedContainerColor = colorResource(id = R.color.background),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedTextColor = colorResource(id = R.color.black)
        ),
        shape = RoundedCornerShape(8.sdp),
        modifier = Modifier
            .fillMaxWidth()
            .height(dynamicHeight.dp),
        maxLines = maxLines,
        minLines = 2
    )
}
