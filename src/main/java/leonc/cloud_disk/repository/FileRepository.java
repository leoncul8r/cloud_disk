package leonc.cloud_disk.repository;

import leonc.cloud_disk.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileInfo, Integer>

{
    List<FileInfo>  findByFolderId (String folderId);
    FileInfo findByFileId (Integer fileId);
}
