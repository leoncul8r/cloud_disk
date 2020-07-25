package leonc.cloud_disk.controller;

import io.swagger.annotations.ApiOperation;
import leonc.cloud_disk.entity.FileInfo;
import leonc.cloud_disk.service.FileService;
import leonc.cloud_disk.utils.DownloadUtil;
import leonc.cloud_disk.utils.RenameFile;
import leonc.cloud_disk.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@RestController
@EnableAsync
@RequestMapping(value="/file")
public class FileController
{
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @Value ("${file.path}")
    private String filePath;

    @PostMapping ("/upload")
    @ResponseBody
    @ApiOperation (notes = "返回值补充说明：Data内容为上传文件的id（fileId）", value = "上传文件")
    public Result upload(@RequestParam("file") MultipartFile file,
                         @RequestParam ("userId") Integer userId,
                         @RequestParam ("folderId") Integer folderId,
                         @RequestParam ("type") String type)

    {
        Result res = new Result ();
        if (file.isEmpty()) {
            res.setMessageAndCode ("上传失败，请选择文件", 0);
            return res;
        }
        String fileName = file.getOriginalFilename();

        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            Integer fileId;
            fileId = fileService.save(folderId, fileName, userId, type);
            RenameFile.rename (filePath + fileName, filePath + fileId);
            log.info("上传" + fileId +"号文件成功");
            res.setData (fileId);
            res.setMessageAndCode ("上传成功", 1);
            return res;
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        res.setMessageAndCode ("上传失败", 0);
        log.info("上传失败");

        return res;
    }


//

    //TODO Download file
    @GetMapping ("/download")
    @ResponseBody
    @ApiOperation (notes = "", value = "下载文件")
    public Result download (@RequestParam ("fileId") Integer fileId,
                            @RequestParam ("userId") Integer userId) throws IOException
    {
        Result res = new Result ();
        FileInfo fileInfo = fileService.getInfo (fileId, userId);
        if (fileInfo == null)
        {
            res.setMessageAndCode ("下载" + fileId +"号文件失败，请检查请求参数", 0);
            log.info("下载" + fileId +"号文件失败");
        }
        else
        {
            String fileName = fileInfo.getFileName ();
            ServletRequestAttributes requestAttributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes ();
            HttpServletResponse response = Objects.requireNonNull (requestAttributes).getResponse ();                   // 设置信息给客户端不解析
            String type = new MimetypesFileTypeMap ().getContentType (fileName);                                        // 设置contentType，即告诉客户端所发送的数据属于什么类型
            Objects.requireNonNull (response).setHeader ("Content-type", type);                                      // 设置编码
            String header = new String (fileName.getBytes (StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);       // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
            response.setHeader ("Content-Disposition", "attachment;filename=" + header);
            DownloadUtil.download (filePath + fileName, response);


            res.setMessageAndCode ("下载" + fileId +"号文件成功", 1);
            log.info("下载" + fileId +"号文件成功");
        }
        return res;
    }


    @PostMapping ("/delete")
    @ResponseBody
    @ApiOperation (notes = "返回值补充说明：Data内容为空", value = "删除文件")
    public Result delete (@RequestParam ("fileId") Integer fileId,
                            @RequestParam ("userId") Integer userId)
    {
        Result res = new Result ();
        Integer i = fileService.delete (fileId, userId);
        if (i == 1)
        {
            res.setMessageAndCode ("删除文件成功", 1);
            log.info("删除" + fileId +"号文件成功");
        }else
        {
            res.setMessageAndCode ("删除文件失败，请检查请求参数", 0);
            log.info("删除" + fileId +"号文件失败");
        }
        return res;
    }


    @GetMapping ("/getList")
    @ResponseBody
    @ApiOperation (notes = "最外层文件夹parentFolderId = 0。\n" +
            "返回值补充说明：Data内容为若干个文件的信息（List<Folder>）", value = "获取文件夹内的文件")
    public Result getList (@RequestParam ("parentFolderId") Integer parentFolderId,
                           @RequestParam ("userId") Integer userId)
    {
        List<FileInfo> fileInfos = fileService.getFileList (parentFolderId, userId);
        Result res = new Result ();
        res.setData (fileInfos);
        res.setMessageAndCode ("获取该父文件夹下的文件列表成功", 1);
        log.info("请求" + parentFolderId +"号文件夹内的文件列表成功");

        return res;
    }


//    @ResponseBody
//    @RequestMapping ("/test")
//    public Result test()
//    {
//        Result res = new Result ();
//        res.setMessageAndCode ("test successfully", 1);
//        return res;
//    }

}
