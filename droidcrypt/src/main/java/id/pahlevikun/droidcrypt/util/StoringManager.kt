package id.pahlevikun.droidcrypt.util

import android.content.Context
import android.content.SharedPreferences
import id.pahlevikun.droidcrypt.Data
import id.pahlevikun.droidcrypt.R
import java.util.*

class StoringManager(private val context: Context) {

    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor
    private val PRIVATE_MODE = 0

    init {
        pref = context.getSharedPreferences(context.getString(R.string.droidcrypt_sharedpref),
                PRIVATE_MODE)
        editor = pref.edit()
    }

    fun saveEncryptedData(data: Data) {
        editor.putString(context.getString(R.string.droidcrypt_text_in_string), data.textInString)
        editor.putString(context.getString(R.string.droidcrypt_text_in_bytearray), Arrays.toString(data.textInByteArray))
        editor.commit()
    }

    fun getStoredData(): Data {
        val textInString = pref.getString(context.getString(R.string.droidcrypt_text_in_string), "")
        val textInByteArrayAsString = pref.getString(context.getString(R.string.droidcrypt_text_in_bytearray), "")

        val split = textInByteArrayAsString.substring(1, textInByteArrayAsString.length - 1).split(", ")
        val textInByteArray = ByteArray(split.size)
        for (i in split.indices) {
            textInByteArray[i] = java.lang.Byte.parseByte(split[i])
        }

        return Data(textInString, textInByteArray)
    }

    fun clearStoredData() {
        editor.clear()
        editor.commit()
    }

    fun isDataStoredEmpty() : Boolean {
        val textInString = pref.getString(context.getString(R.string.droidcrypt_text_in_string), null)
        val textInByteArrayAsString = pref.getString(context.getString(R.string.droidcrypt_text_in_bytearray), null)

        return !(textInString!=null || textInByteArrayAsString != null)
    }
}