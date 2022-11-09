package com.example.peter.util;

import com.github.promeg.pinyinhelper.Pinyin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class FileUtil {

    public static void getAllFileName(String path, ArrayList<String> listFileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] names = file.list();
        if (names != null) {
            String[] completNames = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                String temp = path + names[i];
                temp = temp.replace("D:/Temp/yibu/", "");
                completNames[i] = temp;
            }
            listFileName.addAll(Arrays.asList(completNames));
        }
        for (File a : files) {
            if (a.isDirectory()) {
                getAllFileName(a.getAbsolutePath() + "\\", listFileName);
            }
        }
    }

    public static void renameFile(String fileName, Map<String, String> nameMap) {
        String oldFileName = fileName;

        File oldFile = new File(oldFileName);
        String newFileName = Pinyin.toPinyin(oldFileName, "").toLowerCase();

        File newFile = new File(newFileName);
        if (oldFile.exists() && oldFile.isFile()) {
//            oldFile.renameTo(newFile);
            nameMap.put(oldFileName, newFileName);
        }
        System.out.println(newFileName);
    }

}
