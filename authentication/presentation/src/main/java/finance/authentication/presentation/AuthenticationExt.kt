package finance.authentication.presentation

import android.content.Context
import android.content.ContextWrapper
import android.icu.text.SimpleDateFormat
import android.util.Patterns
import androidx.activity.ComponentActivity
import java.util.Date
import java.util.Locale

const val EMPTY_STRING = ""
const val OTP_LENGTH = 6
const val DOB_FORMAT = "dd/MM/yyyy"

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


fun CharSequence.isValidPassword(): Boolean {
    return isNotEmpty() && this.length >= 8 && this.any { it.isUpperCase() } && this.any { !it.isLetterOrDigit() }
}

fun CharSequence?.isValidPhoneNumber() = !isNullOrEmpty() && Patterns.PHONE.matcher(this).matches()

fun Long?.convertMillisToDate(): String {
    if (this == null) return EMPTY_STRING
    val formatter = SimpleDateFormat(DOB_FORMAT, Locale.getDefault())
    return formatter.format(Date(this))
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}