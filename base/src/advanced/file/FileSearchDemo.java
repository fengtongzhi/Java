package advanced.file;

import java.io.File;

public class FileSearchDemo {
    public static void main(String[] args) {
        File file=new File("D:/");

        fileSearch(file,"Weixin.exe");
    }

    private static void fileSearch(File file,String filename){
        //判断极端情况
        // 修复：原来对目录也直接 return 了，导致传入目录时直接结束，不会遍历下面的文件
        if(file==null || !file.exists()){
            return;
        }

        //获取所有目录下的以及文件或者文件夹对象
        File[] files=file.listFiles();

        //遍历每一个文件或者文件夹对象
        // 修复：应当同时检查 files 非空并且长度大于 0，原来使用 || 会在 files 为 null 时抛出空指针
        if(files!=null && files.length>0){
            for(File f:files){
                //判断是否是文件夹
                if(f.isDirectory()){
                    //递归调用
                    fileSearch(f,filename);
                }else{
                    //判断是否是要查找的文件
                    if(f.getName().equals(filename)){
                        System.out.println("找到了，路径为："+f.getAbsolutePath());
                        return;
                    }
                }
            }
        }
    }
}
