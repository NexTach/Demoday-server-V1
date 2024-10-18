package class4.demoday.global.security.cipher
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.util.*

object EncryptionUtils {
    private const val ALGORITHM = "AES/CBC/PKCS5Padding"
    private const val IV_SIZE = 16

    private fun loadIvFromEnvironment(): IvParameterSpec {
        val base64Iv = System.getenv("ENCRYPTION_IV")
            ?: throw IllegalArgumentException("ENCRYPTION_IV environment variable is not set")
        val decodedIv = Base64.getDecoder().decode(base64Iv)
        if (decodedIv.size != IV_SIZE) {
            throw IllegalArgumentException("IV size is invalid. Expected $IV_SIZE bytes.")
        }
        return IvParameterSpec(decodedIv)
    }

    private fun loadKeyFromEnvironment(): SecretKey {
        val base64Key = System.getenv("ENCRYPTION_KEY")
            ?: throw IllegalArgumentException("ENCRYPTION_KEY environment variable is not set")
        val decodedKey = Base64.getDecoder().decode(base64Key)
        return SecretKeySpec(decodedKey, "AES")
    }

    fun encrypt(data: String): String {
        val secretKey = loadKeyFromEnvironment()
        val cipher = Cipher.getInstance(ALGORITHM)
        val iv = loadIvFromEnvironment()
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)
        val encryptedBytes = cipher.doFinal(data.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    fun decrypt(encryptedData: String): String {
        val secretKey = loadKeyFromEnvironment()
        val cipher = Cipher.getInstance(ALGORITHM)
        val iv = loadIvFromEnvironment()
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
        val decodedBytes = Base64.getDecoder().decode(encryptedData)
        return String(cipher.doFinal(decodedBytes))
    }
}