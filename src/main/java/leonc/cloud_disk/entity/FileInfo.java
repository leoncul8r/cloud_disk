package leonc.cloud_disk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FileInfo
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer fileId;
    private Integer userId;
    private Integer folderId;
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

    public Integer getUserId ()
    {
        return userId;
    }

    public void setUserId (Integer userId)
    {
        this.userId = userId;
    }

    public Integer getFolderId ()
    {
        return folderId;
    }

    public void setFolderId (Integer folderId)
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
