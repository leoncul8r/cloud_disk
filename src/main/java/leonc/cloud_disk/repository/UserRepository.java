package leonc.cloud_disk.repository;

import leonc.cloud_disk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUserName (String userName);
}
