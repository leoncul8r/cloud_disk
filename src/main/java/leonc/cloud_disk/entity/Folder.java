package leonc.cloud_disk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Folder
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer folderId;
    private Integer userId;
    private Integer parentFolderId;
    private String folderName;

    public Integer getFolderId ()
    {
        return folderId;
    }

    public void setFolderId (Integer folderId)
    {
        this.folderId = folderId;
    }

    public Integer getUserId ()
    {
        return userId;
    }

    public void setUserId (Integer userId)
    {
        this.userId = userId;
    }

    public Integer getParentFolderId ()
    {
        return parentFolderId;
    }

    public void setParentFolderId (Integer parentFolderId)
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
