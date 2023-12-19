package com.example.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.comparator.SizeFileComparator;
import org.apache.commons.io.filefilter.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.regex.Pattern;


/**
 * @ClassName: FileUtil
 * @Description: 文件操作工具类
 */
public class FileUtil {
    private static Pattern FILE_PATTERN = Pattern
            .compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");

    private static String MESSAGE = "";

    private FileUtil() {
    }

    /**
     * @auth:dongchen @Title: getFile @Description: 获取文件 @param @param
     * path @param @return 设定文件 @return File 返回类型 @throws
     */
    public static File getFile(String path) {
        return FileUtils.getFile(path);
    }

    /**
     * @auth:dongchen @Title: getFullPath @Description: 获取全路径 @param @param
     * path @param @return 设定文件 @return String 返回类型 @throws
     */
    public static String getFullPath(String path) {
        return FilenameUtils.getFullPath(path);
    }

    /**
     * @auth:dongchen @Title: getName @Description: 获取文件名 @param @param
     * path @param @return 设定文件 @return String 返回类型 @throws
     */
    public static String getName(String path) {
        return FilenameUtils.getName(path);
    }

    /**
     * @auth:dongchen @Title: getExtension @Description: 获取文件后缀 @param @param
     * path @param @return 设定文件 @return String 返回类型 @throws
     */
    public static String getExtension(String path) {
        return FilenameUtils.getExtension(path);
    }

    /**
     * @auth:dongchen @Title: getBaseName @Description: 获取文件名不带路径
     * (例如aa.txt,返回结果为aa) @param @param path @param @return
     * 设定文件 @return String 返回类型 @throws
     */
    public static String getBaseName(String path) {
        return FilenameUtils.getBaseName(path);
    }

    /**
     * @auth:dongchen @Title: mkdir @Description: 创建文件夹 @param @param
     * path @param @return 设定文件 @return boolean 返回类型 @throws
     */
    public static boolean mkdirs(String path) {
        return getFile(path).mkdirs();
    }

    /**
     * @auth:dongchen @Title: mkdir @Description: 创建文件 @param @param path @param
     */
    public static void mkdirsFile(String path) throws IOException {
        getFile(path).createNewFile();
    }

    /**
     * @throws IOException
     * @auth:dongchen @Title: cleanDirectory @Description:
     * 清空目录，但不删除目录 @param @param path 设定文件 @return void
     * 返回类型 @throws
     */
    public static void cleanDirectory(String path) throws IOException {
        FileUtils.cleanDirectory(getFile(path));
    }

    /**
     * @auth:dongchen @Title: deleteDirectory @Description: 删除目录 @param @param
     * path @param @throws IOException 设定文件 @return void
     * 返回类型 @throws
     */
    public static void deleteDirectory(String path) throws IOException {
        FileUtils.deleteDirectory(getFile(path));
    }

    /**
     * @auth:dongchen @Title: isFile @Description: 判断是否为文件 @param @param
     * path @param @return 设定文件 @return boolean 返回类型 @throws
     */
    public static boolean isFile(String path) {
        return getFile(path).isFile();
    }

    /**
     * @auth:dongchen @Title: isDirectory @Description: 判断是否为文件夹 @param @param
     * path @param @return 设定文件 @return boolean 返回类型 @throws
     */
    public static boolean isDirectory(String path) {
        return getFile(path).isDirectory();
    }

    /**
     * @auth:dongchen @Title: exists @Description:判断文件是否存在 @param @param
     * path @param @return 设定文件 @return boolean 返回类型 @throws
     */
    public static boolean isExists(String path) {
        return getFile(path).exists();
    }

    /**
     * @auth:dongchen @Title: copyFile @Description:
     * 复制文件或者目录,复制前后文件完全一样 @param @param
     * resFilePath @param @param distFolder @param @throws
     * IOException 设定文件 @return void 返回类型 @throws
     */
    public static void copyFile(String resFilePath, String distFolder) throws IOException {
        if (!FileUtil.isExists(distFolder)) {
            FileUtil.mkdirs(distFolder);
        }
        File resFile = getFile(resFilePath);
        File distFile = getFile(distFolder);
        if (isDirectory(resFilePath)) {
            FileUtils.copyDirectoryToDirectory(resFile, distFile);
        } else if (isFile(resFilePath)) {
            FileUtils.copyFileToDirectory(resFile, distFile, true);
        }
    }

    /**
     * url复制到指定路径 dongchen
     *
     * @param urlPath
     * @param distPath
     * @throws IOException
     */
    public static void copyURLToFile(String urlPath, String distPath) throws IOException {
        URL url = new URL(urlPath);
        File file = new File(distPath);
        FileUtils.copyURLToFile(url, file);
    }

    /**
     * @auth:dongchen @Title: copyDirectory @Description:
     * 将一个目录内容拷贝到另一个目录 @param @param resFilePath @param @param
     * distFolder @param @throws IOException 设定文件 @return void
     * 返回类型 @throws
     */
    public static void copyDirectory(String resFilePath, String distFolder) throws IOException {
        File resFile = getFile(resFilePath);
        File distFile = getFile(distFolder);
        if (isDirectory(resFilePath)) {
            FileUtils.copyDirectory(resFile, distFile);
        } else if (isFile(resFilePath)) {
            FileUtils.copyFileToDirectory(resFile, distFile, true);
        }
    }

    /**
     * @auth:dongchen @Title: deleteFile @Description: 删除一个文件或者目录 @param @param
     * targetPath @param @throws IOException 设定文件 @return void
     * 返回类型 @throws
     */
    public static void deleteFile(String targetPath) throws IOException {
        File targetFile = getFile(targetPath);
        if (isDirectory(targetPath)) {
            FileUtils.deleteDirectory(targetFile);
        } else if (isFile(targetPath)) {
            FileUtils.deleteQuietly(targetFile);
        }
    }

    /**
     * @auth:dongchen @Title: moveFile @Description:
     * 移动文件或者目录,移动前后文件完全一样,如果目标文件夹不存在则创建 @param @param
     * resFilePath @param @param distFolder @param @throws
     * IOException 设定文件 @return void 返回类型 @throws
     */
    public static void moveFile(String resFilePath, String distFolder) throws IOException {
        File resFile = getFile(resFilePath);
        File distFile = getFile(distFolder);
        if (isDirectory(resFilePath)) {
            FileUtils.moveDirectoryToDirectory(resFile, distFile, true);
        } else if (isFile(resFilePath)) {
            FileUtils.moveFileToDirectory(resFile, distFile, true);
        }
    }

    /**
     * @auth:dongchen @Title: renameFile @Description: 重命名文件或文件夹 @param @param
     * resFilePath @param @param newFileName @param @return
     * 设定文件 @return boolean 返回类型 @throws
     */
    public static boolean renameFile(String resFilePath, String newFileName) {
        File resFile = getFile(resFilePath);
        File newFile = getFile(newFileName);
        return resFile.renameTo(newFile);
    }

    /**
     * @auth:dongchen @Title: listSuffixFileFilterFilesName @Description:
     * 某个目录下的文件列表 @param @param folder 文件目录 @param @param suffix
     * 文件的后缀名(.xml,.pdf) @param @return 设定文件 @return String[]
     * 返回类型 @throws
     */
    public static String[] listSuffixFileFilterFilesName(String path, String suffix) {
        IOFileFilter fileFilter1 = new SuffixFileFilter(suffix);
        IOFileFilter fileFilter2 = new NotFileFilter(DirectoryFileFilter.INSTANCE);
        FilenameFilter filenameFilter = new AndFileFilter(fileFilter1, fileFilter2);
        return getFile(path).list(filenameFilter);
    }

    /**
     * @auth:dongchen @Title: listsuffixFileFilterFiles @Description:
     * 列出指定目录下指定后缀的所有文件 @param @param path @param @param
     * suffix @param @return 设定文件 @return Collection
     * <File> 返回类型 @throws
     */
    public static Collection<File> listsuffixFileFilterFiles(String path, String suffix) {
        return FileUtils.listFiles(getFile(path), FileFilterUtils.suffixFileFilter(suffix, IOCase.INSENSITIVE),
                DirectoryFileFilter.INSTANCE);
    }

    /**
     * @auth:dongchen @Title: listNameFileFifterFiles @Description:
     * 通过名字过滤指定路径下的文件 @param @param path @param @param
     * acceptedNames @param @return 设定文件 @return String[]
     * 返回类型 @throws
     */
    public static String[] listNameFileFifterFiles(String path, String[] acceptedNames) {
        return getFile(path).list(new NameFileFilter(acceptedNames, IOCase.SENSITIVE));
    }

    /**
     * @auth:dongchen @Title: listNameFileFifterFiles @Description:
     * 通过指定名字过滤指定路径下的文件 @param @param path @param @param
     * acceptedNames @param @return 设定文件 @return Collection
     * <File> 返回类型 @throws
     */
    public static Collection<File> listNameFileFifterFiles(String path, String acceptedNames) {
        return FileUtils.listFiles(getFile(path), FileFilterUtils.nameFileFilter(acceptedNames, IOCase.SENSITIVE),
                DirectoryFileFilter.INSTANCE);
    }

    /**
     * @auth:dongchen @Title: listWildcardFileFilterFilesNames @Description:
     * 通过指定关键字通配过滤指定路径下的文件 @param @param path @param @param
     * wild @param @return 设定文件 @return String[] 返回类型 @throws
     */
    public static String[] listWildcardFileFilterFilesNames(String path, String wild) {
        return getFile(path)
                .list(new WildcardFileFilter(new StringBuffer().append("*").append(wild).append("*").toString()));
    }

    /**
     * @auth:dongchen @Title: listWildcardFileFilterFiles @Description:
     * 通过指定关键字通配过滤指定路径下的文件 @param @param path @param @param
     * wild @param @return 设定文件 @return Collection
     * <File> 返回类型 @throws
     */
    public static Collection<File> listWildcardFileFilterFiles(String path, String wild) {
        return FileUtils.listFiles(getFile(path),
                FileFilterUtils.and(
                        new WildcardFileFilter(new StringBuffer().append("*").append(wild).append("*").toString())),
                DirectoryFileFilter.INSTANCE);
    }

    /**
     * @auth:dongchen @Title: listPrefixFileFilterFilesNames @Description:
     * 通过前缀名过滤文件 @param @param path @param @param
     * prefix @param @return 设定文件 @return String[] 返回类型 @throws
     */
    public static String[] listPrefixFileFilterFilesNames(String path, String prefix) {
        return getFile(path).list(new PrefixFileFilter(prefix));
    }

    /**
     * @auth:dongchen @Title: listPrefixFileFilterFiles @Description:
     * 通过前缀过滤文件 @param @param path @param @param
     * prefix @param @return 设定文件 @return Collection
     * <File> 返回类型 @throws
     */
    public static Collection<File> listPrefixFileFilterFiles(String path, String prefix) {
        return FileUtils.listFiles(getFile(path), FileFilterUtils.prefixFileFilter(prefix, IOCase.SENSITIVE),
                DirectoryFileFilter.INSTANCE);
    }

    /**
     * @auth:dongchen @Title: nameFileComparator @Description:
     * 通过文件名排序 @param @param path @param @return 设定文件 @return
     * File[] 返回类型 @throws
     */
    public static File[] nameFileComparator(String path) {
        if (isDirectory(path)) {
            NameFileComparator comparator = new NameFileComparator(IOCase.SENSITIVE);
            return comparator.sort(getFile(path).listFiles());
        }
        return null;
    }

    /**
     * @auth:dongchen @Title: sizeFileComparator @Description:
     * 通过文件大小排序 @param @param path @param @return 设定文件 @return
     * File[] 返回类型 @throws
     */
    public static File[] sizeFileComparator(String path) {
        if (isDirectory(path)) {
            SizeFileComparator sizeComparator = new SizeFileComparator(true);
            return sizeComparator.sort(getFile(path).listFiles());
        }
        return null;
    }

    /**
     * @auth:dongchen @Title: lastModifiedFileComparator @Description:
     * 通过修改时间进行排序 @param @param path @param @return 设定文件 @return
     * File[] 返回类型 @throws
     */
    public static File[] lastModifiedFileComparator(String path) {
        if (isDirectory(path)) {
            LastModifiedFileComparator lastModified = new LastModifiedFileComparator();
            return lastModified.sort(getFile(path).listFiles());
        }
        return null;
    }

    /**
     * @auth:dongchen @Title: getSize @Description: 获取文件或者目录大小 @param @param
     * path @param @return 设定文件 @return long 返回类型 @throws
     */
    public static long getSize(String path) {
        File file = getFile(path);
        if (isDirectory(path)) {
            return FileUtils.sizeOfDirectory(file);
        } else {
            return FileUtils.sizeOf(file);
        }
    }

    /**
     * @auth:dongchen @Title: filenameFilter @Description:
     * 文件名过滤特殊字符 @param @param str @param @return 设定文件 @return
     * String 返回类型 @throws
     */
    public static String filenameFilter(String str) {
        return str == null ? null : FILE_PATTERN.matcher(str).replaceAll("");
    }

    /**
     * 判断文件名后缀是否等于suffix dongchen
     *
     * @param fileName 文件名
     * @param suffix   后缀 例如.zip .rar等等
     * @return
     */
    public static boolean isEndsWith(String fileName, String suffix) {
        boolean flag = false;
        if (fileName != null && !"".equals(fileName.trim()) && suffix != null && !"".equals(suffix.trim())) {
            return fileName.toLowerCase().endsWith(suffix);
        }

        return flag;
    }

    /**
     * 获取文件大小 dongchen
     *
     * @param file
     * @return
     */
    public static String getFileSize(File file) {
        String size;
        if (file.exists() && file.isFile()) {
            long fileS = file.length();
            size = getFileSize(fileS);
        } else if (file.exists() && file.isDirectory()) {
            size = "";
        } else {
            size = "0BT";
        }
        return size;
    }

    /**
     * 获取文件大小 dongchen
     *
     * @param fileSize
     * @return
     */
    public static String getFileSize(long fileSize) {
        String size;
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileSize < 1024) {
            size = df.format((double) fileSize) + "BT";
        } else if (fileSize < 1048576) {
            size = df.format((double) fileSize / 1024) + "KB";
        } else if (fileSize < 1073741824) {
            size = df.format((double) fileSize / 1048576) + "MB";
        } else {
            size = df.format((double) fileSize / 1073741824) + "GB";
        }
        return size;
    }

    /**
     * 拼凑文件目录 dongChen
     *
     * @param args
     * @return
     * @throws IOException
     */
    public static String getFilePath(Object... args) throws IOException {
        StringBuilder stringBuffer = new StringBuilder(60);
        for (Object object : args) {
            stringBuffer.append(object);
        }
        File file = FileUtil.getFile(stringBuffer.toString());
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        } else {
            FileUtil.cleanDirectory(stringBuffer.toString());
        }
        return stringBuffer.toString();
    }

    /**
     * 预览 pdf 文件
     *
     * @param response http 响应
     * @param file     要被写出的文件
     * @throws IOException
     */
    public static void viewPdf(HttpServletResponse response, File file) throws IOException {
        InputStream input = FileUtils.openInputStream(file);
        byte[] data = IOUtils.toByteArray(input);
        response.setContentType("application/pdf;charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
        IOUtils.closeQuietly(input);
    }

    /**
     * 根据网络url 获取字节流
     *
     * @param networkUrl
     * @return
     * @throws IOException
     */
    public static byte[] getByteByURL(String networkUrl) throws IOException {
        //new一个URL对象
        URL url = new URL(networkUrl);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        return readInputStream(inStream);
    }

    /**
     * 转换字节
     *
     * @param inStream
     * @return
     * @throws IOException
     */
    private static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    private static void writeToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }


    public static void main(String[] args) {
//        try (FileInputStream in = new FileInputStream("/Users/finup/Desktop/test/test.jpg");
//             FileOutputStream out = new FileOutputStream("/Users/finup/Desktop/test/test1.jpg")) {
//            byte[] b = new byte[1024];
//            int l;
//            while ((l = in.read(b)) != -1) {
//                out.write(b, 0, l);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//                  scc_gsl_sync_data_student_log_202353
//                scc_gsl_sync_data_teacher_log_202401
//                  lesson_live_student_log_202356
//                lesson_live_teacher_log_202312

        String filePath = "/Users/finup/Desktop/java/test/TOL_learning_Y.txt";
        String toFilePath = "/Users/finup/Desktop/java/test/TOL_Y.txt";

        StringBuilder builder = new StringBuilder();


        for (int i = 1; i < 13; i++) {
            // 从文件读取并打印内容
            String fileContent = readFromFile(filePath);
            String num = i < 10 ? "0" + i : String.valueOf(i);
            fileContent = fileContent.replaceAll("#NUM", num);
            builder.append(fileContent);
            builder.append("\n");
        }
        // 写入文件
        writeToFile(toFilePath, builder.toString());
    }

}
