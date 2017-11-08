package guo.ping.db2pojo.tools;

import java.io.File;

public class ClearFiles {

    /**
     * 删除目录下的所有文件以及目录
     * @param path 文件路径
     * @return
     */
    public static boolean clearPathFiles(String path) {
        File dir = new File(path);
        return clearFilesInOutputPath(dir);
    }

    /**
     * 清除文件
     * @param dir 文件目录
     */
    public static boolean clearFilesInOutputPath(File dir) {
        //boolean success = false;
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = clearFilesInOutputPath(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
