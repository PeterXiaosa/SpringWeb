package com.example.peter.util;

import com.github.promeg.pinyinhelper.Pinyin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class FileUtil {

    public static void getAllFileName(String path, ArrayList<String> listFileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] names = file.list();
        if (names != null) {
            String[] completNames = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                completNames[i] = path + names[i];
            }
            listFileName.addAll(Arrays.asList(completNames));
        }
        for (File a : files) {
            if (a.isDirectory()) {
                getAllFileName(a.getAbsolutePath() + "\\", listFileName);
            }
        }
    }

    public static void renameFile(String fileName) {
        String oldFileName = fileName;

        File oldFile = new File(oldFileName);
        String newFileName = Pinyin.toPinyin(oldFileName, "").toLowerCase();
//        String newFileName = filePath+File.separator+fmdate.format(new Date())+"."+fileName.split("\\.")[1];
        File newFile = new File(newFileName);
        if (oldFile.exists() && oldFile.isFile()) {
//            oldFile.renameTo(newFile);
        }
        System.out.println(newFileName);
    }

}
