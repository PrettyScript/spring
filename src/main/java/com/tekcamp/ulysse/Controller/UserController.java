package com.tekcamp.ulysse.Controller;

import com.tekcamp.ulysse.DataTransferObject.UserDto;
import com.tekcamp.ulysse.Model.Request.UserRequest;
import com.tekcamp.ulysse.Model.Response.UserResponse;
import com.tekcamp.ulysse.Model.User;
import com.tekcamp.ulysse.Services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    protected UserResponse createUser(@RequestBody UserRequest userRequest) {

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);

        UserDto updatedUser = userService.createUser(userDto);

        UserResponse userResponse = new UserResponse();
        userResponse.setHttpStatus(HttpStatus.CREATED);
        BeanUtils.copyProperties(updatedUser, userResponse);

        return userResponse;
    }

    @GetMapping(path = "id/{id}")
    public UserResponse getUser(@PathVariable long id) {

        UserResponse userResponse = new UserResponse();
        userResponse.setHttpStatus(HttpStatus.FOUND);

        UserDto userDto = userService.getUser(id);
        BeanUtils.copyProperties(userDto, userResponse);

        return userResponse;
    }

    @GetMapping(path = "email/{email}")
    public UserResponse getUser(@PathVariable String email) {
        UserResponse userResponse = new UserResponse();
        userResponse.setHttpStatus(HttpStatus.FOUND);

        UserDto userDto = userService.getUser(email);
        BeanUtils.copyProperties(userDto, userResponse);
        return userResponse;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<User> users = userService.getUsers(page, limit);
        return users;
    }

    @PutMapping(path = "/{email}/{id}")
    public UserResponse updateUser(@PathVariable String email, @PathVariable long id, @RequestBody UserRequest userRequest) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);

        UserDto updatedUser = userService.save(userRequest, id);

        UserResponse userResponse = new UserResponse();
        userResponse.setHttpStatus(HttpStatus.ACCEPTED);
        BeanUtils.copyProperties(updatedUser, userResponse);

        return userResponse;
    }

    @DeleteMapping(path = "/{id}")
    public UserResponse deleteUser(@PathVariable long id) {
       UserResponse userResponse = new UserResponse();
       userResponse.setMessage("The account with the id: " + id + " was deleted");
       userService.delete(id);
       return userResponse;
    }


}
