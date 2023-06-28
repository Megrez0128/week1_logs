package org.example;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListFilesInFolder {
    List<String> fileNameList =  new ArrayList<>();
    String folderPath = "";  // 给定一个默认路径
    public ListFilesInFolder(String path) {
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