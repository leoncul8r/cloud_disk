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
}
