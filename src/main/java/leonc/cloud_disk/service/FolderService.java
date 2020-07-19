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

    public Integer createFolder (String folderName, String parentFolderId)
    {
        Folder folder = new Folder ();
        folder.setFolderName (folderName);
        folder.setParentFolderId (parentFolderId);

        this.folderRepository.save (folder);

        return folder.getFolderId ();
    }

    public Folder getFolderById (Integer folderId)
    {
        return this.folderRepository.findByFolderId (folderId);
    }

    public List<Folder> getFoldersByParent (String parentFolderId)
    {
        return this.folderRepository.findByParentFolderId (parentFolderId);
    }
}
