package class4.demoday.global.component

import org.springframework.stereotype.Component

@Component
class PhoneNumberFormatter {
    private fun format(phoneNumber: String): String {
        return phoneNumber.replace(Regex("[^0-9+]"), "")
    }

    fun e164Format(phoneNumber: String): String {
        val formattedNumber = format(phoneNumber)
        return when {
            formattedNumber.startsWith("+82") -> formattedNumber
            formattedNumber.startsWith("0") -> "+82" + formattedNumber.substring(1)
            else -> formattedNumber
        }
    }

    fun formatCheck(phoneNumber: String): Boolean {
        val formattedNumber = format(phoneNumber)
        return when {
            formattedNumber.startsWith("+82") -> true
            else -> false
        }
    }
}