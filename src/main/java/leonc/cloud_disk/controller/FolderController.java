package leonc.cloud_disk.controller;

import leonc.cloud_disk.entity.Folder;
import leonc.cloud_disk.service.FolderService;
import leonc.cloud_disk.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public Result create (HttpServletRequest request)
    {
        String folderName= request.getParameter("folderName");
        String parentFolderId= request.getParameter("parentFolderId");
        Integer folderId;
        folderId = folderService.createFolder (folderName, parentFolderId);

        Folder folder = folderService.getFolderById (folderId);
        Result res = new Result ();
        res.setData (folder);

        return res;
    }

    @PostMapping ("/getFolders")
    @ResponseBody
    public Result getFolders (HttpServletRequest request)
    {
        String parentFolderId= request.getParameter("parentFolderId");
        List<Folder> folders = folderService.getFoldersByParent (parentFolderId);
        Result res = new Result ();
        res.setData (folders);

        return res;
    }

}
