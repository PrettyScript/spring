package com.tekcamp.ulysse.Controller;

import com.tekcamp.ulysse.Model.User;
import com.tekcamp.ulysse.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    protected void createUser(@RequestBody User user) {
        SecureRandom random = new SecureRandom();
        Long id = random.nextLong();
        if(id <= 0) {
            id = id * -1;
        }
        user.setId(id);
        userService.createUser(user);
    }

    @GetMapping(path = "id/{id}")
    public User getUser(@PathVariable long id) {
        User user = userService.getUser(id);
        return user;
    }

    @GetMapping(path = "email/{email}")
    public User getUser(@PathVariable String email) {
        User user = userService.getUser(email);
        return user;
    }

    @GetMapping
    public List<User> getUsers() {
        List<User> users = userService.getUsers();
        return users;
    }

    @PutMapping(path = "/{email}/{id}")
    public User updateUser(@PathVariable String email, @PathVariable long id, @RequestBody User user) {
        User updatedUser = userService.save(user, id);
        return updatedUser;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.delete(id);
    }


}
