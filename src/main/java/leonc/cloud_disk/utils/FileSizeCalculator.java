package leonc.cloud_disk.utils;

public class FileSizeCalculator
{
    public static String calculator (double fileByte)
    {
        String fileSize;
        if (fileByte < 1024)
        {
            fileSize = fileByte + "字节";
        }else if (fileByte < 1024 * 1024)
        {
            fileSize = String.format("%.2f", (fileByte / 1024)) + "KB";
        }else if (fileByte < 1024 * 1024 * 1024)
        {
            fileSize = String.format("%.2f", (fileByte / 1024 / 1024)) + "MB";
        }else if (fileByte / (1024 * 1024) <  1024 * 1024)
        {
            fileSize = String.format("%.2f", (fileByte / 1024 / 1024/ 1024)) + "GB";
        }else
        {
            fileSize = "";
        }

        return fileSize;
    }
}
