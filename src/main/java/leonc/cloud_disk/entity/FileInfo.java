package leonc.cloud_disk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileInfo
{
    @Id
    @GeneratedValue
    private Integer fileId;
    private String userId;
    private String folderId;
    private String fileName;
    private double fileSize;


    public Integer getFileId ()
    {
        return fileId;
    }

    public void setFileId (Integer fileId)
    {
        this.fileId = fileId;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public String getFolderId ()
    {
        return folderId;
    }

    public void setFolderId (String folderId)
    {
        this.folderId = folderId;
    }

    public String getFileName ()
    {
        return fileName;
    }

    public void setFileName (String fileName)
    {
        this.fileName = fileName;
    }

    public double getFileSize ()
    {
        return fileSize;
    }

    public void setFileSize (double fileSize)
    {
        this.fileSize = fileSize;
    }
}
