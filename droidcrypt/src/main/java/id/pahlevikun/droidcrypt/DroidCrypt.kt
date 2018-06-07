package id.pahlevikun.droidcrypt

import android.content.Context
import android.util.Base64
import id.pahlevikun.droidcrypt.util.Logger
import id.pahlevikun.droidcrypt.util.StoringManager
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import android.R.attr.password




class DroidCrypt(private val context: Context) {

    private val storingManager = StoringManager(context)

    fun startEncryptWithoutBase64(key: String, algorithm: String, plainText: String): Data {
        return try {
            val keyInByteArray = key.toByteArray()
            val plainTextInByteArray = plainText.toByteArray(Charsets.UTF_8)

            val messageDigest = MessageDigest.getInstance(algorithm)
            val digestOfPassword = messageDigest.digest(keyInByteArray)

            val secretKeySpec = SecretKeySpec(digestOfPassword, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)

            val cipherBytes = cipher.doFinal(plainTextInByteArray)

            Data(String(cipherBytes, Charsets.UTF_8), cipherBytes)
        } catch (e: Exception) {
            Logger.e("Encrypt", e)
            Data(context.getString(R.string.droidcrypt_fail_encrypt), context.getString(R.string.droidcrypt_fail_encrypt).toByteArray())
        }
    }

    fun startDecryptWithoutBase64(key: String, algorithm: String, data: Data): Data {
        Logger.d("Input", data.textInString)
        return try {
            val keyInByteArray = key.toByteArray()

            val messageDigest = MessageDigest.getInstance(algorithm)
            val digestOfPassword = messageDigest.digest(keyInByteArray)

            val secretKeySpec = SecretKeySpec(digestOfPassword, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)

            val decryptedBytes = cipher.doFinal(data.textInByteArray)
            Data(String(decryptedBytes, Charsets.UTF_8), data.textInByteArray)
        } catch (e: Exception) {
            Logger.e("Decrypt", e)
            Data(context.getString(R.string.droidcrypt_fail_decrypt), context.getString(R.string.droidcrypt_fail_encrypt).toByteArray())
        }
    }

    fun startEncryptWithBase64(key: String, algorithm: String, plainText: String): Data {
        return try {
            val keyInByteArray = key.toByteArray()
            val plainTextInByteArray = plainText.toByteArray(Charsets.UTF_8)

            val messageDigest = MessageDigest.getInstance(algorithm)
            val digestOfPassword = messageDigest.digest(keyInByteArray)

            val secretKeySpec = SecretKeySpec(digestOfPassword, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)

            val cipherBytes = cipher.doFinal(plainTextInByteArray)

            val encryptedValueInByteArray = Base64.encodeToString(cipherBytes, Base64.DEFAULT)
            val encryptedValueInString = encryptedValueInByteArray.toString()

            Data(encryptedValueInString, cipherBytes)
        } catch (e: Exception) {
            Logger.e("Encrypt", e)
            Data(context.getString(R.string.droidcrypt_fail_encrypt), context.getString(R.string.droidcrypt_fail_encrypt).toByteArray())
        }
    }

    fun startDecryptWithBase64(key: String, algorithm: String, data: Data): Data {
        Logger.d("Input", data.textInString)
        return try {
            val keyInByteArray = key.toByteArray()

            val messageDigest = MessageDigest.getInstance(algorithm)
            val digestOfPassword = messageDigest.digest(keyInByteArray)

            val secretKeySpec = SecretKeySpec(digestOfPassword, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)

            val decodedValue = Base64.decode(data.textInString, Base64.DEFAULT)
            val decryptedVal = cipher.doFinal(decodedValue)

            Data(String(decryptedVal, Charsets.UTF_8), data.textInByteArray)
        } catch (e: Exception) {
            Logger.e("Decrypt", e)
            Data(context.getString(R.string.droidcrypt_fail_decrypt), context.getString(R.string.droidcrypt_fail_encrypt).toByteArray())
        }
    }

    fun saveEncryptedToPreferences(data: Data) {
        storingManager.saveEncryptedData(data)
    }

    fun getEncryptedFromPreferences(): Data {
        return storingManager.getStoredData()
    }

    fun deleteEncryptedFromPreferences() {
        storingManager.clearStoredData()
    }

    fun getDecryptedFromPreferences(key: String, algorithm: String, isWithBase64: Boolean): Data {
        val data = storingManager.getStoredData()
        return if (isWithBase64) {
            startDecryptWithBase64(key, algorithm, data)
        } else {
            startDecryptWithoutBase64(key, algorithm, data)
        }
    }

    fun isDataStoreEmpty(): Boolean {
        return storingManager.isDataStoredEmpty()
    }
}