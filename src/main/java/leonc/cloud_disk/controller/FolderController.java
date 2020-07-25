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
        log.info("新建" + folderId +"号文件夹成功");

        return res;
    }

    @GetMapping ("/getList")
    @ResponseBody
    @ApiOperation (notes = "最外层文件夹parentFolderId = 0。\n返回值补充说明：Data内容为若干个文件夹信息（List<Folder>）", value = "获取文件夹内的文件夹")
    public Result getList (@RequestParam ("parentFolderId") Integer parentFolderId,
                           @RequestParam ("userId") Integer userId)
    {
        List<Folder> folders = folderService.getFoldersByParent (parentFolderId, userId);
        Result res = new Result ();
        res.setData (folders);
        res.setMessageAndCode ("获取该父文件夹下的文件夹列表成功", 1);
        log.info("获取" + parentFolderId +"号文件夹内的文件夹列表成功");

        return res;
    }

    @PostMapping ("/delete")
    @ResponseBody
    @ApiOperation (notes = "建议前端在执行此操作时弹出警告，文件夹内的文件夹和文件都会被删除\n返回值补充说明：Data内容为空", value = "删除文件夹")
    public Result delete (@RequestParam ("folderId") Integer folderId,
                          @RequestParam ("userId") Integer userId)
    {
        Result res = new Result ();
        Integer i = folderService.delete (folderId, userId);
        if (i == 1)
        {
            res.setMessageAndCode ("删除文件夹成功", 1);
            log.info("删除" + folderId +"号文件夹成功");
        }else
        {
            res.setMessageAndCode ("删除文件夹失败，请检查请求参数", 0);
            log.info("删除" + folderId +"号文件夹失败");
        }

        return res;
    }
}
