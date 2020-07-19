package leonc.cloud_disk.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping ("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping ("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "/Library/Storage/2020/";

        String folderId = request.getParameter("folderId");
        log.info("From request: folderId" + folderId + "; File name: " + fileName);

        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            Integer fileId;
            fileId = fileService.save(folderId, fileName, "0");
            RenameFile.rename (filePath + fileName, filePath + fileId);
            log.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return "上传失败！";
    }


    @GetMapping ("/multiUpload")
    public String multiUpload() {
        return "multiUpload";
    }

    @PostMapping ("/multiUpload")
    @ResponseBody
    public String multiUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "/Library/Storage/2020/";
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                return "上传第" + (i++) + "个文件失败";
            }
            String fileName = file.getOriginalFilename();

            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                log.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                log.error(e.toString(), e);
                return "上传第" + (i++) + "个文件失败";
            }
        }

        return "上传成功";

    }

    //TODO Download file

    @PostMapping ("/getFiles")
    @ResponseBody
    public Result getFiles (HttpServletRequest request)
    {
        String parentFolderId= request.getParameter("parentFolderId");
        List<FileInfo> fileInfos = fileService.getFileInfos (parentFolderId);
        Result res = new Result ();
        res.setData (fileInfos);
        res.setMessage ("获取该父文件夹下的文件列表成功");
        res.setCode (1);

        return res;
    }

    @ResponseBody
    @RequestMapping ("/test")
    public Result test()
    {
        Result res = new Result ();
        res.setMessage ("test successfully");
        res.setCode (1);
        return res;
    }



}
