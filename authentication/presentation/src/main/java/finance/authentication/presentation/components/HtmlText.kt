package finance.authentication.presentation.components

import android.text.Html
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun AppHtmlText(html: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        }
    })
}