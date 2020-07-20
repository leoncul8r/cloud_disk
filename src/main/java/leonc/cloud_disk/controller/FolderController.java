package leonc.cloud_disk.controller;

import io.swagger.annotations.ApiOperation;
import leonc.cloud_disk.entity.Folder;
import leonc.cloud_disk.service.FolderService;
import leonc.cloud_disk.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value="/folder")
public class FolderController
{
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FolderService folderService;

    @PostMapping ("/create")
    @ResponseBody
    @ApiOperation (notes = "返回值补充说明：Data内容为该文件夹信息", value = "创建文件夹")
    public Result create (@RequestParam ("folderName") String folderName,
                          @RequestParam ("parentFolderId") Integer parentFolderId,
                          @RequestParam ("userId") Integer userId)
    {
        Integer folderId;
        folderId = folderService.createFolder (folderName, parentFolderId, userId);

        Folder folder = folderService.getFolderById (folderId);
        Result res = new Result ();
        res.setData (folder);
        res.setMessageAndCode ("新建文件夹成功", 1);

        return res;
    }

    @GetMapping ("/getList")
    @ResponseBody
    @ApiOperation (notes = "返回值补充说明：Data内容为若干个文件夹信息（List<Folder>）", value = "获取文件夹内的文件夹")
    public Result getList (@RequestParam ("parentFolderId") Integer parentFolderId)
    {
        List<Folder> folders = folderService.getFoldersByParent (parentFolderId);
        Result res = new Result ();
        res.setData (folders);
        res.setMessageAndCode ("获取该父文件夹下的文件夹列表成功", 1);

        return res;
    }

    //TODO delete

}
