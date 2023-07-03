package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/*
查找指定目录下的所有文件
TODO：目前只能查找一层，需要换成递归的
 */
public class ListFilesInDir {
    List<String> fileNameList =  new ArrayList<>();
    String folderPath = "";  // 给定一个默认路径
    public ListFilesInDir(String path) {
        folderPath = path; //目标文件夹路径
        File folder = new File(folderPath);
        if(folder.exists() && folder.isDirectory()) {
            File[] fileList = folder.listFiles();
            for(File file : fileList) {
                if(file.isFile()) {
                    fileNameList.add(file.getName());
                }
            }
        }
    }
    public List<String> getFileNameList(){
        return fileNameList;
        // 返回一个由所有文件名构成的列表
    }
    public String getFolderPath(){
        return folderPath;
        // 返回目录文件夹所在路径
    }
}

//public class ListFilesInDir {
//    List<String> fileNameList = new ArrayList<>();
//    String dirPath = "";
//    public ListFilesInDir(String folderPath) {
//        this.dirPath = folderPath;
//        listFiles();
//    }
//
//    public void listFiles() {
//        File folder = new File(this.dirPath);
//        File[] files = folder.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                if (file.isFile()) {
//                    // 是文件，添加到文件名列表中
//                    this.fileNameList.add(file.getName());
//                } else if (file.isDirectory()) {
//                    // 是文件夹，递归查找文件
//                    ListFilesInDir subFolder = new ListFilesInDir(file.getAbsolutePath());
//                    subFolder.listFiles();
//                    this.fileNameList.addAll(subFolder.getFileNameList());
//                }
//            }
//        }
//    }
//
//    public List<String> getFileNameList() {
//        return this.fileNameList;
//    }
//}