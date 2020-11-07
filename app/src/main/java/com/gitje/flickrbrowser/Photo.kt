package com.gitje.flickrbrowser

import android.util.Log
import java.io.IOException
import java.io.ObjectStreamException
import java.io.Serializable

class Photo(var title: String, var author: String, var authorId: String, var link: String, var tags: String, var image: String)
    : Serializable {

    companion object {
        private const val serialversionUID = 1L
    }

    override fun toString(): String {
        return "Photo(title='$title', author='$author', authorId='$authorId', link='$link', tags='$tags', image='$image')"
    }

    @Throws(IOException::class)
    private fun writeObject(out: java.io.ObjectOutputStream) {
        Log.d("Photo", "writeObject called")
        out.writeUTF(title)
        out.writeUTF(author)
        out.writeUTF(authorId)
        out.writeUTF(link)
        out.writeUTF(tags)
        out.writeUTF(image)
    }

    @Throws(IOException::class)
    private fun readObject(instream: java.io.ObjectInputStream) {
        Log.d("Photo", "readObject called")
        title = instream.readUTF()
        author = instream.readUTF()
        authorId = instream.readUTF()
        link = instream.readUTF()
        tags = instream.readUTF()
        image = instream.readUTF()
    }

    @Throws(ObjectStreamException::class)
    private fun readObjectNoData(){
        Log.d("Photo", "readObjectNoData called")
    }
}