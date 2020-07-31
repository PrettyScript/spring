package com.tekcamp.ulysse.Services;

import com.tekcamp.ulysse.DataTransferObject.UserDto;
import com.tekcamp.ulysse.Model.Request.UserRequest;
import com.tekcamp.ulysse.Model.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<User> getUsers(int page, int limit);

    UserDto getUser(String email);

    UserDto getUser(long id);

    UserDto save(UserRequest user, long id);

    void delete(long id);
}
