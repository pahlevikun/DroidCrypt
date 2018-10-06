package id.pahlevikun.droidcrypt.util

import android.content.Context
import android.content.SharedPreferences
import id.pahlevikun.droidcrypt.model.Data
import java.util.*

class StoringManager(private val context: Context) {

    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor

    companion object {
        const val PRIVATE_MODE = 0
        const val SHARED_PREFERENCES = "droidcryptSharedPref"
        const val PREF_STRING = "droidcryptSharedPrefTextInString"
        const val PREF_BYTEARRAY = "droidcryptSharedPrefTextInByteArray"
    }

    init {
        pref = context.getSharedPreferences(SHARED_PREFERENCES,
            PRIVATE_MODE)
        editor = pref.edit()
    }

    fun saveEncryptedData(data: Data) {
        editor.putString(PREF_STRING, data.textInString)
        editor.putString(PREF_BYTEARRAY, Arrays.toString(data.textInByteArray))
        editor.commit()
    }

    fun getStoredData(): Data {
        val textInString = pref.getString(PREF_STRING, "")
        val textInByteArrayAsString = pref.getString(PREF_BYTEARRAY, "")

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

    fun isDataStoredEmpty(): Boolean {
        val textInString = pref.getString(PREF_STRING, null)
        val textInByteArrayAsString = pref.getString(PREF_BYTEARRAY, null)

        return !(textInString != null || textInByteArrayAsString != null)
    }
}