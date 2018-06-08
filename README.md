# DroidCrypt
A simple Android Library. Very easy for use this Android library for performing encryption to String with AES encryption, it can used for save to SharedPreferences too.

## Support 
Support from Android 4.4 KitKat / Minimum API 19

## Permission 
No needed special permission in AndroidManifest

## Installing 
Add repository in root build.gradle
```gradle
repositories {
    maven { url "https://jitpack.io" }
}
```
And add dependency to module build.gradle:
```gradle
dependencies {
    implementation 'com.github.pahlevikun:droidcrypt:0.0.1'
}
```

## Sample Code 
For starting encryption, you have to put 16-byte Key, a String, and Algorithm to encrypt or decrypt plain-text, 
it will return result as an object. You can custom your algorithm with MD5 or SHA256. 
The SDK already provide it with enum class called Algorithm, just call it and put as parameter. When you use encryption,
there are 2 variant of method, you can encrypt with Base64 encoding or without Base64 encoding.
<br>Here's sample for encrypting :
```kotlin
// Please use try-catch to prevent exception or force close
try {
      // Without Base64
      val encrypted = DroidCrypt(this).startEncryptWithoutBase64("@12d", Algorithm.MD5.type, "MAKAN")
      print(encrypted.textInString)

      // With Base64
      val encryptedBase64 = DroidCrypt(this).startEncryptWithBase64("@12d", Algorithm.MD5.type, "MAKAN")
      print(encryptedBase64.textInString)
} catch (e: Exception) {
      e.printStackTrace()
}
```
<br>For decrypting, you only put the 16-byte Key and a object. The object contains String for decryption and it String as a Byte Array.
<br>For example, look at this :
```kotlin
try {
      // Without Base64
      val decrypted = DroidCrypt(this).startDecryptWithoutBase64("@12d", Algorithm.MD5.type, encrypted)
      print(decrypted.textInString)

      // With Base64
      val decryptedBase64 = DroidCrypt(this).startDecryptWithBase64("@12d", Algorithm.MD5.type, encryptedBase64)
      print(decryptedBase64.textInString)
} catch (e: Exception) {
      e.printStackTrace()
}
```
<br>For use sharedpreferences, please use like this :
```kotlin
// Save it to SharedPreferences
DroidCrypt(this).saveEncryptedToPreferences(encrypted)
// Get it from SharedPreferences
val data = DroidCrypt(this).getEncryptedFromPreferences()
// Delete it from SharedPreferences
DroidCrypt(this).deleteEncryptedFromPreferences()
```
## Changelog
```changelog
Version 0.0.1
- Encrypt and Decrypt
- Encryption with Base64 encoding or without Base64 encoding.
- Save encrypted data to SharedPreferences. You can get, store, or delete it.
```

## License
```
Copyright 2018 Farhan Yuda Pahlevi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
