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

    public void delete (FileInfo fileInfo)
    {
        fileRepository.delete (fileInfo);
    }


    public void save (FileInfo fileInfo)
    {
        fileRepository.save (fileInfo);
    }

    public List<FileInfo> getFileInfos (Integer parentFolderId)
    {
        return this.fileRepository.findByFolderId (parentFolderId);
    }


    //写入文件基本数据
    public Integer save(Integer folderId, String fileName, Integer userId) throws  IOException
    {
        FileInfo fileInfo = new FileInfo ();
        fileInfo.setFolderId (folderId);
        fileInfo.setFileName (fileName);
        fileInfo.setUserId (userId);

        this.fileRepository.save (fileInfo);

        return fileInfo.getFileId ();
    }

    //删除信息
    public void deleteFileInfoByName (Integer fileId)
    {
        fileRepository.delete (fileRepository.findByFileId (fileId));
    }

}