package br.com.josef.marvelcharacters.utils

import android.content.Context
import android.net.Uri
import android.util.DisplayMetrics
import br.com.josef.marvelcharacters.model.dataclass.Result
import okhttp3.internal.and
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun md5(ts: String): String {
    val s = ts + PRIVATE_KEY + PUBLIC_KEY
    try {
        val digest = MessageDigest.getInstance("MD5")
        digest.update(s.toByteArray())
        return hexEncode(digest.digest())
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}

private fun hexEncode(bytes: ByteArray): String {
    val result = CharArray(bytes.size * 2)
    var b: Int
    var i = 0
    var j = 0
    while (i < bytes.size) {
        b = bytes[i] and 0xff
        result[j++] = HEXCHARS[b shr 4]
        result[j++] = HEXCHARS[b and 0xf]
        i++
    }
    return String(result)
}

private val HEXCHARS =
    charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

val PUBLIC_KEY: String
    get() = "b975c2aca612a721ed13cf5ba7345f3c"

val PRIVATE_KEY: String
    get() = "4c616f6d7f4ea2cc73ea0ee137a3a4254c0f1324"

fun isTablet(context: Context): Boolean {
    val i =
       context.resources.displayMetrics.widthPixels * DisplayMetrics.DENSITY_DEFAULT / context.resources.displayMetrics.densityDpi
    return i >= 600
}

fun httpsUrlFormater(result: Result): Uri {
    val imagem = result.thumbnail.path + "/portrait_uncanny." + result.thumbnail.extension
    return Uri.parse(imagem.replace("http:", "https:"))
}