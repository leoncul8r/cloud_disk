package leonc.cloud_disk.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadUtil
{
    public static void download (String pathAndName, HttpServletResponse res) throws IOException
    {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream ();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream (new FileInputStream (new File (pathAndName)));
        int i = bis.read (buff);
        while (i != - 1)
        {
            outputStream.write (buff, 0, buff.length);
            outputStream.flush ();
            i = bis.read (buff);
        }
    }
}