package Util;
import org.apache.commons.codec.digest.DigestUtils;
public class Sign {
    public static void main(String args[]) {
        String AppKey = "ff6a72198dbe85a6638b9a464c8b163d";
        String AppVersion = "80";
        String Duid = "3ipKuUPpDrT3NzwL8gmWaySGXNp1";
        System.out.println("app_key" + AppKey + "app_version" + AppVersion + "duid" + Duid);
        String expectedSign = DigestUtils.md5Hex(
                "app_key" + AppKey + "app_version" + AppVersion + "duid" + Duid);
        System.out.println(expectedSign);
    }
}
