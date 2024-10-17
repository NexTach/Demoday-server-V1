package class4.demoday.global.security.cipher

import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object EncryptionUtils {
    private const val ALGORITHM = "AES"
    private const val KEY_SIZE = 128

    private fun loadKeyFromEnvironment(): SecretKey {
        val base64Key =
            System.getenv("ENCRYPTION_KEY")
                ?: throw IllegalArgumentException("ENCRYPTION_KEY environment variable is not set")

        val decodedKey = Base64.getDecoder().decode(base64Key)
        return SecretKeySpec(decodedKey, ALGORITHM)
    }

    fun encrypt(data: String): String {
        val secretKey = loadKeyFromEnvironment()
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes = cipher.doFinal(data.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    fun decrypt(encryptedData: String): String {
        val secretKey = loadKeyFromEnvironment()
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decodedBytes = Base64.getDecoder().decode(encryptedData)
        return String(cipher.doFinal(decodedBytes))
    }
}