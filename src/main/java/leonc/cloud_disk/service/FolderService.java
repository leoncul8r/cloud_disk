package leonc.cloud_disk.service;

import leonc.cloud_disk.entity.Folder;
import leonc.cloud_disk.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService
{
    @Autowired
    private FolderRepository folderRepository;

    public Integer createFolder (String folderName, Integer parentFolderId, Integer userId)
    {
        Folder folder = new Folder ();
        folder.setFolderName (folderName);
        folder.setParentFolderId (parentFolderId);
        folder.setUserId (userId);

        this.folderRepository.save (folder);

        return folder.getFolderId ();
    }

    public Folder getFolderById (Integer folderId)
    {
        return this.folderRepository.findByFolderId (folderId);
    }

    public List<Folder> getFoldersByParent (Integer parentFolderId, Integer userId)
    {
        return this.folderRepository.findByParentFolderIdAndUserId (parentFolderId, userId);
    }

    //删除文件夹
    public Integer delete (Integer folderId, Integer userId)
    {
        try
        {
            Folder folder = this.getFolderById (folderId);
            if (folder.getUserId ().equals (userId))
            {
                folderRepository.delete (folder);
                return 1;
            }
        }catch (NullPointerException e)
        {
            return 0;
        }
            return 0;
    }
}
