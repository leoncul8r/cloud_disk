package leonc.cloud_disk.repository;

import leonc.cloud_disk.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Integer>
{
    Folder findByFolderId (Integer folderId);
    List<Folder> findByParentFolderId (Integer parentFolderId);
}
