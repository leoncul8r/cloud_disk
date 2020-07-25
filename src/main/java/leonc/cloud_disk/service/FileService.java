package leonc.cloud_disk.service;

import leonc.cloud_disk.entity.FileInfo;
import leonc.cloud_disk.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FileService
{
    @Autowired
    private FileRepository fileRepository;

    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    public void save (FileInfo fileInfo)
    {
        fileRepository.save (fileInfo);
    }

    //获取文件夹内的文件
    public List<FileInfo> getFileList (Integer parentFolderId, Integer userId)
    {
        return this.fileRepository.findByFolderIdAndUserId (parentFolderId, userId);
    }


    //写入文件基本数据
    public Integer save(Integer folderId, String fileName, Integer userId, String type) throws  IOException
    {
        FileInfo fileInfo = new FileInfo ();
        fileInfo.setFolderId (folderId);
        fileInfo.setFileName (fileName);
        fileInfo.setUserId (userId);
        fileInfo.setType (type);

        this.fileRepository.save (fileInfo);

        return fileInfo.getFileId ();
    }

    //获取文件信息
    public FileInfo getInfo (Integer fileId, Integer userId)
    {
        try
        {
            FileInfo fileInfo = fileRepository.findByFileId (fileId);
            if (fileInfo.getUserId ().equals (userId))
            {
                return fileInfo;
            }
        }catch (NullPointerException e)
        {
            return null;
        }
        return null;
    }

    //删除文件
    public Integer delete (Integer fileId, Integer userId)
    {
        try
        {
            FileInfo fileInfo = fileRepository.findByFileId (fileId);
            if (fileInfo.getUserId ().equals (userId))
            {
                fileRepository.delete (fileInfo);
                return 1;
            }
        }catch (NullPointerException e)
        {
            return 0;
        }
        return 0;
    }

}