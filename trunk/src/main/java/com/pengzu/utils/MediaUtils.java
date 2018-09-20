package com.pengzu.utils;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by jackie on 2017/9/30.
 */
public class MediaUtils {

    private final static Logger logger = Logger.getLogger(MediaUtils.class);

    /**
     * 得到文件的类型。
     * 实际上就是得到文件名中最后一个“.”后面的部分。
     *
     * @param fileName 文件名
     * @return 文件名中的类型部分
     * @since 1.0
     */
    public static String getTypePart(String fileName) {
        int point = fileName.lastIndexOf('.');
        int length = fileName.length();
        if (point == -1 || point == length - 1) {
            return "";
        } else {
            return fileName.substring(point + 1, length);
        }
    }

    /**
     * 读取文件的内容
     * 读取指定文件的内容
     *
     * @param path 为要读取文件的绝对路径
     * @return 以行读取文件后的内容
     * @throws IOException
     */
    public static final String getFileContent(String orderFileRoot, String path) throws IOException {
        String filecontent = "";
        path = orderFileRoot + path;
        try {
            File f = new File(path);
            if (f.exists()) {
                FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr); //建立BufferedReader对象，并实例化为br
                String line = br.readLine(); //从文件读取一行字符串
                //判断读取到的字符串是否不为空
                while (line != null) {
                    filecontent += line + "\n";
                    line = br.readLine(); //从文件中继续读取一行数据
                }
                br.close(); //关闭BufferedReader对象
                fr.close(); //关闭文件
            }

        } catch (IOException e) {
            logger.error("读取文件内容出错 " + e);
            throw e;
        }
        return filecontent;
    }

    /**
     * 通过ServletInputStream 输入流保存文件
     *
     * @param servletInputStream 文件输入流
     * @param baseInfo           基础信息
     * @param orderFileRoot      绝对路径
     * @return 文件相对路径
     * @throws IOException
     */
    public static String saveIO(ServletInputStream servletInputStream, String baseInfo, String orderFileRoot) throws IOException {
        String path = baseInfo;
        BufferedOutputStream stream = null;
        try {
            byte[] bytes = new byte[1024];
            File file = new File(orderFileRoot + path);
            if (!file.exists()) {
                file.mkdirs();
            }
            path = path + UUID.randomUUID().toString() + ".zip";
            FileOutputStream fstream = new FileOutputStream(new File(orderFileRoot + path));
            stream = new BufferedOutputStream(fstream);
            int length = servletInputStream.read(bytes, 0, 1024);
            while (length != -1) {
                stream.write(bytes);
                length = servletInputStream.read(bytes, 0, 1024);
            }
        } catch (Exception e) {
            logger.error("保存离线订单文件出错 " + e);
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    logger.error(" 关闭文件流出错 " + e1);
                    e1.printStackTrace();
                }
            }
        }
        return path;
    }

    /**
     * 通过ServletInputStream 输入流保存文件
     *
     * @param servletInputStream 文件输入流
     * @param fileName           文件名
     * @param fileRootPath       绝对路径
     * @return 文件相对路径
     * @throws IOException
     */
    public static Boolean saveIO2(ServletInputStream servletInputStream, String fileName, String fileRootPath) throws IOException {
        logger.info("\n执行方法[MediaUtils.saveIO2]...\n参数[" + servletInputStream + " , " + fileName + " , " + fileRootPath + " ]");
        BufferedOutputStream stream = null;
        Boolean success = false;
        try {
            byte[] bytes = new byte[1024];
            File file = new File(fileRootPath);
            if (!file.exists()) {
                logger.info("文件夹不存在,尝试创建...");
                if (file.mkdirs()) {
                    logger.info("创建文件夹[" + fileRootPath + "]成功...");
                    success = true;
                } else {
                    logger.info("创建文件夹[" + fileRootPath + "]失败...");
                }
            }
            FileOutputStream fstream = new FileOutputStream(new File(fileRootPath + fileName));
            stream = new BufferedOutputStream(fstream);
            int length = servletInputStream.read(bytes, 0, 1024);
            while (length != -1) {
                stream.write(bytes);
                length = servletInputStream.read(bytes, 0, 1024);
            }
            success = true;
        } catch (Exception e) {
            logger.error("保存文件出错 " + e);
            e.printStackTrace();
            success = false;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    logger.error(" 关闭文件流出错 " + e1);
                    e1.printStackTrace();
                    success = false;
                }
            }
        }
        return success;
    }


    /**
     * 压缩文件-由于out要在递归调用外,所以封装一个方法用来
     * 调用ZipFiles(ZipOutputStream out,String path,File... srcFiles)
     *
     * @param zip
     * @param path
     * @param srcFiles
     * @throws IOException
     */
    public static void zipFiles(File zip, String path, File... srcFiles) throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
        MediaUtils.zipFiles(out, path, srcFiles);
        out.close();
    }

    /**
     * 压缩文件-File
     *
     * @param out      zip文件
     * @param srcFiles 被压缩源文件
     */
    public static void zipFiles(ZipOutputStream out, String path, File... srcFiles) {
        path = path.replaceAll("\\*", "/");
        if (!path.endsWith("/")) {
            path += "/";
        }
        byte[] buf = new byte[1024];
        try {
            for (int i = 0; i < srcFiles.length; i++) {
                if (srcFiles[i].isDirectory()) {
                    File[] files = srcFiles[i].listFiles();
                    String srcPath = srcFiles[i].getName();
                    srcPath = srcPath.replaceAll("\\*", "/");
                    if (!srcPath.endsWith("/")) {
                        srcPath += "/";
                    }
                    out.putNextEntry(new ZipEntry(path + srcPath));
                    zipFiles(out, path + srcPath, files);
                } else {
                    FileInputStream in = new FileInputStream(srcFiles[i]);
                    out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.closeEntry();
                    in.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压到指定目录
     *
     * @param zipPath
     * @param descDir
     */
    public static void unZipFiles(String zipPath, String descDir) throws IOException {
        unZipFiles(new File(zipPath), descDir);
    }

    /**
     * 解压文件到指定目录
     *
     * @param zipFile
     * @param descDir
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        logger.info("开始解压文件 zipFile[" + zipFile + "] 解压路径 descDir [" + descDir + "]");
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile);
        for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = descDir;
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String path = outPath + File.separator + zipEntryName;
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(path).isDirectory()) {
                continue;
            }
            OutputStream out = new FileOutputStream(path);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        zip.close();
        logger.info("解压文件完成 zipFile[" + zipFile + "] 解压路径 descDir [" + descDir + "]");
    }


    /**
     * 递归获取文件夹里的所有文件
     *
     * @param path 文件夹路径
     * @return 文件夹下所有文件
     */
    public static List<File> traverseFolder2(String path) {
        //logger.info("获取目录["+path+"]下的所有文件");
        File file = new File(path);
        List<File> fileList = new ArrayList<File>();
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                return fileList;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        fileList.addAll(traverseFolder2(file2.getAbsolutePath()));
                    } else {
                        fileList.add(file2);
                    }
                }
            }
        }
        return fileList;
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                logger.info("删除解析文件[" + fileName + "]成功");
                return true;
            } else {
                logger.info("删除解析文件[" + fileName + "]失败");
                return false;
            }
        } else {
            logger.info("文件[" + fileName + "]不存在");
            return false;
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        //删除当前目录
        if (dirFile.delete()) {
            logger.info("删除目录 [" + sPath + "]成功");
            return true;
        } else {
            logger.info("删除目录 [" + sPath + "]失败");
            return false;
        }
    }

    /**
     * @param multipartFile
     * @return
     */
    public static String save(MultipartFile multipartFile, String root, String fileFodlerPrefix) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMM/dd");
        String path = "/uploads/" + fileFodlerPrefix + "/" + formater.format(new Date()) + "/"
                + UUID.randomUUID().toString().replaceAll("-", "") + "."
                + getTypePart(multipartFile.getOriginalFilename());
        File file = new File(root + "/" + path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * @param multipartFile
     * @return
     */
    public static String save(MultipartFile multipartFile, String root) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        String path = "/download/" + formater.format(new Date()) + "/" + new Random().nextInt(100) + multipartFile.getOriginalFilename();
        File file = new File(root + "/" + path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static void main(String[] args) throws IOException {

        List<File> files = traverseFolder2("E:\\aaa");
        for (File file : files) {
            String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
            String extName = getTypePart(file.getName());
            if ("txt".equals(extName)) {
                String name = file.getName().substring(0, file.getName().indexOf(extName));
//                System.out.println(getFileContent(path,file.getName()));
                try {
                    FileOutputStream fos = new FileOutputStream(path + name + "java");
                    fos.write(getFileContent(path, file.getName()).getBytes());
                    deleteFile(file.getPath());
                    System.out.println("文件[" + path + name + "java" + "]写入成功..");
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            System.out.println();
        }

    }

}
