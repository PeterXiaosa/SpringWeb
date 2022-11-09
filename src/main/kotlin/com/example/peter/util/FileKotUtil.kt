package com.example.peter.util

import com.github.promeg.pinyinhelper.Pinyin
import java.io.File
import java.util.*

class FileKotUtil {

    companion object {

        @JvmStatic
        fun getAllFileName(path:String, listFileName: ArrayList<String>) {
            val file = File(path)
            val files = file.listFiles()
            val names = file.list()
            if (names != null) {
                val completNames = arrayOfNulls<String>(names.size)
                for (i in 0 until names.size) {
                    var temp = path + names[i]

                    completNames[i] = temp
                }

                completNames.forEach {
                    it?.let { it1 -> listFileName.add(it1) }
                }
            }
            files.forEach {
                if (it.isDirectory) {
                    getAllFileName(it.absolutePath+ "\\", listFileName)
                }
            }
        }

        @JvmStatic
        fun renameFile(fileName:String, nameMap:MutableMap<String, String>) {
            val oldFileName = fileName
            val oldFile = File(oldFileName)
            val newFileName = Pinyin.toPinyin(oldFileName, "")
                    .lowercase(Locale.getDefault())

            val newFile = File(newFileName)
            if (oldFile.exists() && oldFile.isFile) {
                oldFile.renameTo(newFile)

                nameMap.put(oldFileName, newFileName)
            }
        }
    }
}