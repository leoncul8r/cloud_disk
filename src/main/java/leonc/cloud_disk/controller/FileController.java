package leonc.cloud_disk.controller;

import io.swagger.annotations.ApiOperation;
import leonc.cloud_disk.entity.FileInfo;
import leonc.cloud_disk.service.FileService;
import leonc.cloud_disk.utils.RenameFile;
import leonc.cloud_disk.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@EnableAsync
@RequestMapping(value="/file")
public class FileController
{
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

//    private static String path = FileService.getFilePath ();

    @Autowired
    private FileService fileService;

    @PostMapping ("/upload")
    @ResponseBody
    @ApiOperation (notes = "返回值补充说明：Data内容为新建的文件夹的信息", value = "上传文件")
    public Result upload(@RequestParam("file") MultipartFile file,
                         @RequestParam ("userId") Integer userId,
                         @RequestParam ("folderId") Integer folderId)
    {
        Result res = new Result ();
        if (file.isEmpty()) {
            res.setMessageAndCode ("上传失败，请选择文件", 0);
            return res;
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/Library/Storage/2020/";

        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            Integer fileId;
            fileId = fileService.save(folderId, fileName, userId);
            RenameFile.rename (filePath + fileName, filePath + fileId);
            log.info("上传成功");
            res.setData (file);
            res.setMessageAndCode ("上传成功", 1);
            return res;
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        res.setMessageAndCode ("上传失败", 0);
        return res;
    }


//

    //TODO Download file
    @GetMapping ("/download")
    @ResponseBody
    @ApiOperation (notes = "", value = "下载文件（未完成）")
    public Result download (@RequestParam ("fileId") Integer fileId,
                            @RequestParam ("userId") Integer userId)
    {
        Result res = new Result ();
        res.setMessageAndCode ("你成功请求了这个接口，但这个接口的内容为还没有写", 1);

        return res;
    }


    //TODO delete
    @PostMapping ("/delete")
    @ResponseBody
    @ApiOperation (notes = "", value = "删除文件（未完成）")
    public Result delete (@RequestParam ("fileId") Integer fileId,
                            @RequestParam ("userId") Integer userId)
    {
        Result res = new Result ();
        res.setMessageAndCode ("你成功请求了这个接口，但这个接口的内容为还没有写", 1);

        return res;
    }


    @GetMapping ("/getList")
    @ResponseBody
    @ApiOperation (notes = "最外层文件夹parentFolderId = 0。\n返回值补充说明：Data内容为若干个文件的信息（List<Folder>）", value = "获取文件夹内的文件")
    public Result getList (@RequestParam ("parentFolderId") Integer parentFolderId,
                           @RequestParam ("userId") Integer userId)
    {
        List<FileInfo> fileInfos = fileService.getFileInfos (parentFolderId, userId);
        Result res = new Result ();
        res.setData (fileInfos);
        res.setMessageAndCode ("获取该父文件夹下的文件列表成功", 1);

        return res;
    }


    //    @GetMapping ("/multiUpload")
    //    public String multiUpload() {
    //        return "multiUpload";
    //    }
    //
    //    @PostMapping ("/multiUpload")
    //    @ResponseBody
    //    public Result multiUpload(HttpServletRequest request) {
    //        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
    //        String filePath = "/Library/Storage/2020/";     //文件存储在服务器的本地路径
    //        Result res = new Result ();
    //        for (int i = 0; i < files.size(); i++) {
    //            MultipartFile file = files.get(i);
    //            if (file.isEmpty()) {
    //                res.setMessageAndCode ("上传第" + (i++) + "个文件失败", 0);
    //                return res;
    //            }
    //            String fileName = file.getOriginalFilename();
    //
    //            File dest = new File(filePath + fileName);
    //            try {
    //                file.transferTo(dest);
    //                log.info("第" + (i + 1) + "个文件上传成功");
    //            } catch (IOException e) {
    //                log.error(e.toString(), e);
    //                res.setMessageAndCode ("上传第" + (i++) + "个文件失败", 0);
    //                return res;
    //            }
    //        }
    //        res.setMessageAndCode ("上传成功", 1);
    //        return res;
    //    }


//    @ResponseBody
//    @RequestMapping ("/test")
//    public Result test()
//    {
//        Result res = new Result ();
//        res.setMessageAndCode ("test successfully", 1);
//        return res;
//    }

}
