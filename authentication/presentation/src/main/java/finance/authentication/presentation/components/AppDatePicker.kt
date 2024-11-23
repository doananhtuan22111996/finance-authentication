package finance.authentication.presentation.components

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import finance.authentication.presentation.DOB_FORMAT
import finance.authentication.presentation.EMPTY_STRING
import finance.authentication.presentation.convertMillisToDate
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import vn.finance.authentication.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePicker(
    label: String,
    placeHolder: String,
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)?,
    onValueChange: (String) -> Unit,
    onValidator: ((value: String) -> String?)? = null
) {
    var value by rememberSaveable { mutableStateOf(EMPTY_STRING) }
    var isError by rememberSaveable { mutableStateOf(false) }
    var isPicker by rememberSaveable { mutableStateOf(false) }
    val dateState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
    val interactionSource = remember {
        object : MutableInteractionSource {
            override val interactions = MutableSharedFlow<Interaction>(
                extraBufferCapacity = 16,
                onBufferOverflow = BufferOverflow.DROP_OLDEST,
            )

            override suspend fun emit(interaction: Interaction) {
                if (interaction is PressInteraction.Release) {
                    isPicker = true
                }
                interactions.emit(interaction)
            }

            override fun tryEmit(interaction: Interaction): Boolean {
                return interactions.tryEmit(interaction)
            }
        }
    }

    LaunchedEffect(value) {
        onValueChange(value)
        val mError = onValidator?.invoke(value)
        isError = !mError.isNullOrEmpty()
    }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = {
            Text(
                text = label, style = MaterialTheme.typography.labelMedium
            )
        },
        onValueChange = { text ->
            value = text
        },
        isError = isError,
        readOnly = true,
        singleLine = true,
        maxLines = 1,
        interactionSource = interactionSource,
        placeholder = { Text(text = placeHolder) },
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        ),
        supportingText = {
            if (isError) {
                Text(onValidator?.invoke(value) ?: EMPTY_STRING)
            }
        },
    )
    if (isPicker) {
        DatePickerDialog(onDismissRequest = { isPicker = false }, confirmButton = {
            Button(onClick = {
                isPicker = false
                value = dateState.selectedDateMillis.convertMillisToDate()
            }, enabled = dateState.selectedDateMillis.convertMillisToDate() != value) {
                Text(stringResource(R.string.accept))
            }
        }, dismissButton = {
            OutlinedButton(onClick = { isPicker = false }) { Text(stringResource(R.string.cancel)) }
        }) {
            DatePicker(
                state = dateState,
                dateFormatter = remember { DatePickerDefaults.dateFormatter(selectedDateSkeleton = DOB_FORMAT) },
                showModeToggle = false,
                title = title
            )
        }
    }
}