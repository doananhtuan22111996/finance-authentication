package finance.authentication.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.OhTeePeeInput
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellConfiguration
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations
import finance.authentication.presentation.EMPTY_STRING
import finance.authentication.presentation.OTP_LENGTH

@Composable
fun OtpComponent(
    otp: String = EMPTY_STRING,
    cellsCount: Int = OTP_LENGTH,
    isValueInvalid: Boolean = false,
    onValueChange: (otp: String, isValid: Boolean) -> Unit
) {
    // this config will be used for each cell
    val emptyCellConfig = OhTeePeeCellConfiguration.withDefaults(
        borderColor = MaterialTheme.colorScheme.onSurface,
        backgroundColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(16.dp),
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface)
    )
    val defaultCellConfig = OhTeePeeCellConfiguration.withDefaults(
        borderColor = MaterialTheme.colorScheme.primary,
        backgroundColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(16.dp),
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface)

    )
    val errorCellConfig = OhTeePeeCellConfiguration.withDefaults(
        borderColor = MaterialTheme.colorScheme.error,
        backgroundColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(16.dp),
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.error)
    )

    OhTeePeeInput(
        value = otp,
        onValueChange = { newValue, newIsValid ->
            onValueChange(newValue, newIsValid)
        },
        isValueInvalid = isValueInvalid,
        configurations = OhTeePeeConfigurations.withDefaults(
            cellsCount = cellsCount,
            emptyCellConfig = emptyCellConfig,
            cellModifier = Modifier
                .padding(horizontal = 4.dp)
                .size(48.dp),
            activeCellConfig = defaultCellConfig,
            filledCellConfig = defaultCellConfig,
            errorCellConfig = errorCellConfig
        ),
    )
}