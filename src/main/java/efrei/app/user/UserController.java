package efrei.app.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }
    @PostMapping
    public void createUser(@Valid @RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId,
                                        @RequestBody User user){
        return userService.findAndUpdateUser(userId, user);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable Integer userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        return userService.deleteUserById(userId);
    }
}
