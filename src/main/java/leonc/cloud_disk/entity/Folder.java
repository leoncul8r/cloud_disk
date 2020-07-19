package leonc.cloud_disk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Folder
{
    @Id
    @GeneratedValue
    private Integer folderId;
    private String parentFolderId;
    private String folderName;

    public Integer getFolderId ()
    {
        return folderId;
    }

    public void setFolderId (Integer folderId)
    {
        this.folderId = folderId;
    }
    
    public String getParentFolderId ()
    {
        return parentFolderId;
    }

    public void setParentFolderId (String parentFolderId)
    {
        this.parentFolderId = parentFolderId;
    }

    public String getFolderName ()
    {
        return folderName;
    }

    public void setFolderName (String folderName)
    {
        this.folderName = folderName;
    }
}
