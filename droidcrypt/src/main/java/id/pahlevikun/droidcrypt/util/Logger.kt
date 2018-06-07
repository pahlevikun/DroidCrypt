package id.pahlevikun.droidcrypt.util

import android.util.Log

class Logger {
    companion object {

        fun d(var0: String, var1: String, var2: String) {
            Log.d("DroidCrypt $var0 $var1", "" + var2)
        }

        fun d(var0: String, var1: String) {
            Log.d("DroidCrypt $var0", "" + var1)
        }

        fun d(var0: String) {
            Log.d("DroidCrypt ", "" + var0)
        }

        fun i(var0: String, var1: String, var2: String) {
            Log.i("DroidCrypt $var0 $var1", "" + var2)
        }

        fun i(var0: String, var1: String) {
            Log.i("DroidCrypt $var0", "" + var1)
        }

        fun i(var0: String) {
            Log.i("DroidCrypt", "" + var0)
        }

        fun e(var0: String, var1: String, var2: String) {
            Log.e("DroidCrypt $var0 $var1", "" + var2)
        }

        fun e(var0: String, var1: String, var2: Throwable) {
            Log.e("DroidCrypt $var0 $var1", "exeption:", var2)
        }

        fun e(var0: String, var1: String) {
            Log.e("DroidCrypt $var0", "" + var1)
        }

        fun e(var0: String, var1: Throwable) {
            Log.e("DroidCrypt $var0", "exeption:", var1)
        }

        fun e(var0: String) {
            Log.e("DroidCrypt", "" + var0)
        }
    }
}