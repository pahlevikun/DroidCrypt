package id.pahlevikun.androidencryption

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.pahlevikun.droidcrypt.DroidCrypt
import id.pahlevikun.droidcrypt.type.Algorithm
import id.pahlevikun.droidcrypt.util.Logger


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            val encrypted = DroidCrypt(this).startEncryptWithoutBase64("@12d", Algorithm.MD5.type, "MAKAN")
            Logger.d("HASIL ENCRYPT", MainActivity::class.java.simpleName, encrypted.textInString)
            val decrypted = DroidCrypt(this).startDecryptWithoutBase64("@12d", Algorithm.MD5.type, encrypted)
            Logger.d("HASIL DECRYPT", MainActivity::class.java.simpleName, decrypted.textInString)


            val encryptedBase64 = DroidCrypt(this).startEncryptWithBase64("@12d", Algorithm.MD5.type, "MAKAN")
            Logger.d("HASIL ENCRYPT", MainActivity::class.java.simpleName, encryptedBase64.textInString)
            val decryptedBase64 = DroidCrypt(this).startDecryptWithBase64("@12d", Algorithm.MD5.type, encryptedBase64)
            Logger.d("HASIL DECRYPT", MainActivity::class.java.simpleName, decryptedBase64.textInString)

            DroidCrypt(this).saveEncryptedToPreferences(encrypted)
            val data = DroidCrypt(this).getEncryptedFromPreferences()
            Logger.d("HASIL STORE", MainActivity::class.java.simpleName, data.textInString)
            Logger.d("HASIL STORE", MainActivity::class.java.simpleName, data.textInByteArray.toString())

            DroidCrypt(this).deleteEncryptedFromPreferences()
        } catch (e: Exception) {
            Logger.e("HASIL", MainActivity::class.java.simpleName, e)
            e.printStackTrace()
        }

    }
}
