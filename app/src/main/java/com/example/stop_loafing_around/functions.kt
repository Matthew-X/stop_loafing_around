package com.example.stop_loafing_around

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import androidx.core.net.toUri
import java.io.*
import java.util.*


fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
fun getImageUri(bitmap:Bitmap,context: Context): Uri {
    val file = File(context.cacheDir,"${UUID.randomUUID()}") //Get Access to a local file.
    file.delete() // Delete the File, just in Case, that there was still another File
    file.createNewFile()
    val fileOutputStream = file.outputStream()
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream)
    val bytearray = byteArrayOutputStream.toByteArray()
    fileOutputStream.write(bytearray)
    fileOutputStream.flush()
    fileOutputStream.close()
    byteArrayOutputStream.close()

    val URI = file.toUri()
    return URI
}
fun Uri?.toDrawable(context:Context): Drawable {
    val inputStream = context?.contentResolver?.openInputStream(this!!)
    var step_image = Drawable.createFromStream(inputStream, this!!.toString())
    return step_image
}

fun trimCache(context: Context) {
    try {
        val dir: File = context.getCacheDir()
        if (dir != null && dir.isDirectory()) {
            deleteDir(dir)
        }
    } catch (e: Exception) {
    }
}

fun deleteDir(dir: File?): Boolean? {
    if (dir != null && dir.isDirectory()) {
        val children: Array<String> = dir.list()
        for (i in children.indices) {
            val success = deleteDir(File(dir, children[i]))
            if (!success!!) {
                return false
            }
        }
    }

    // The directory is now empty so delete it
    return dir?.delete()
}