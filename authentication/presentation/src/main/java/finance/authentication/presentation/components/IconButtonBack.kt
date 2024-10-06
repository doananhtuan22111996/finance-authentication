package finance.authentication.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import vn.finance.authentication.R

@Composable
fun IconButtonBack(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Filled.ArrowBackIosNew, contentDescription = stringResource(
                R.string.navigation_back
            )
        )
    }
}