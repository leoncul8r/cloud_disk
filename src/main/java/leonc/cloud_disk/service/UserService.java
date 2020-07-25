package leonc.cloud_disk.service;

import leonc.cloud_disk.entity.User;
import leonc.cloud_disk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public User create (String userName, String password)
    {
        if (getUserInfoByUserName (userName) == null)
        {
            User user = new User ();
            user.setUserName (userName);
            user.setPassword (password);

            this.userRepository.save (user);
            return user;
        }

        return null;

    }

    public User getUserInfoByUserName (String userName)
    {
        return this.userRepository.findByUserName (userName);
    }
}
