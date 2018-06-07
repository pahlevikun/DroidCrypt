package id.pahlevikun.droidcrypt.model

import java.util.*

data class Data(val textInString: String, val textInByteArray: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Data

        if (!Arrays.equals(textInByteArray, other.textInByteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(textInByteArray)
    }
}