package efrei.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
    public  ResponseEntity<?> findAndUpdateUser(Integer id, User user){
        User userAModifier = userRepository.findById(id).orElse(null);
        if(userAModifier != null){
            if(user.getUserName() != null){
                userAModifier.setUserName(user.getUserName());
            }
            if(user.getEmail() != null){
                userAModifier.setEmail(user.getEmail());
            }
            if(user.getPassword() != null){
                userAModifier.setPassword(user.getPassword());
            }
            userRepository.save(userAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public void saveUser(User user){
        userRepository.save(user);
    }

    public ResponseEntity<?> deleteUserById(Integer id){
        if(!userRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
