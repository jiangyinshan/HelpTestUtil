import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static void main(String[] args) {
        String fileMD5 = getFilePartMD5(args[0]);
        System.out.println(fileMD5);
    }

    public static String getMD5(String val) {
        if (val != null && !val.isEmpty()) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(val.getBytes());
                byte[] m = md5.digest();
                return md5ToHex(m);
            } catch (NoSuchAlgorithmException e) {

            }
        }
        return "";

    }

    /**
     * 获取字节数组的MD5
     *
     * @param bytes 要获取MD5值的字符串
     * @return byte数组的32位小写MD5字符串, 出现异常则返回空字符串
     */
    public static String getByteArrayMD5(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(bytes);
            return md5ToHex(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取字符串的MD5
     *
     * @param str 要获取MD5值的字符串
     * @return 字符串的32位小写MD5字符串, 出现异常则返回空字符串
     */
    public static String getStringMD5(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return getByteArrayMD5(str.getBytes());
    }

    /**
     * 获取文件的MD5
     *
     * @param file 要获取MD5值的文件
     * @return 文件的32位小写MD5字符串, 出现异常则返回空字符串
     */
    public static String getFileMD5(File file) {
        if (file == null) {
            return "";
        }
        FileInputStream fis = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            byte[] result = digest.digest();
            return md5ToHex(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(fis);
        }
        return "";
    }

    /**
     * 获取某个文件的MD5
     *
     * @param filePath 要获取MD5值的文件路径
     * @return 文件的32位小写MD5字符串, 出现异常则返回空字符串
     */
    public static String getFileMD5(String filePath) {
        if (isEmpty(filePath)) {
            return "";
        }
        return getFileMD5(new File(filePath));
    }

    /**
     * 获取某个文件部分内容的MD5值 具体算法: 如果文件长度小于4096字节, 直接计算整个文件的MD5, 如果文件长度大于4096字节,
     * 取其前1024字节和后1024字节, 计算这部分的MD5
     *
     * @param file 要获取部分内容MD5值的文件
     * @return 文件部分内容的32位小写MD5字符串, 出现异常则返回空字符串
     */
    public static String getFilePartMD5(File file) {
        if (file == null) {
            return "";
        }
        if (file.length() < 4096) {
            return getFileMD5(file);
        } else {
            RandomAccessFile raf = null;
            try {
                raf = new RandomAccessFile(file, "r");
                byte[] headAndTail = new byte[2048];
                raf.seek(0);
                raf.read(headAndTail, 0, 1024);
                raf.seek(raf.length() - 1024);
                raf.read(headAndTail, 1024, 1024);
                return getByteArrayMD5(headAndTail);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeQuietly(raf);
            }
            return "";
        }
    }

    /**
     * 获取某个文件部分内容的MD5值 具体算法: 如果文件长度小于4096字节, 直接计算整个文件的MD5, 如果文件长度大于4096字节,
     * 取其前1024字节和后1024字节, 计算这部分的MD5
     *
     * @param filePath 要获取部分内容MD5值的文件路径
     * @return 文件部分内容的32位小写MD5字符串, 出现异常则返回空字符串
     */
    public static String getFilePartMD5(String filePath) {
        if (isEmpty(filePath)) {
            return "";
        }
        return getFilePartMD5(new File(filePath));
    }

    /**
     * 获取字节数组部分内容的MD5值 具体算法: 如果字节数组长度小于4096字节, 直接计算整个字节数组的MD5, 如果长度大于4096字节,
     * 取其前1024字节和后1024字节, 计算这部分的MD5
     *
     * @param bytes 要获取部分内容MD5值得字节数组
     * @return 字节数组部分内容的32位小写MD5字符串, 出现异常则返回空字符串
     */
    public static String getByteArrayPartMD5(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        if (bytes.length < 4096) {
            return getByteArrayMD5(bytes);
        } else {
            byte[] headAndTail = new byte[2048];
            System.arraycopy(bytes, 0, headAndTail, 0, 1024);
            System.arraycopy(bytes, bytes.length - 1024, headAndTail, 1024, 1024);
            return getByteArrayMD5(headAndTail);
        }
    }

    private static String md5ToHex(byte[] md5) {
        if (md5 == null || md5.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int offset = 0; offset < md5.length; offset++) {
            byte b = md5[offset];
            if ((b & 0xFF) < 0x10) sb.append("0");
            sb.append(Integer.toHexString(b & 0xFF));
        }
        return sb.toString();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
