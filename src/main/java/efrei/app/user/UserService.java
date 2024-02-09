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
        return userRepository.findById(id).orElseGet(null);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
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
