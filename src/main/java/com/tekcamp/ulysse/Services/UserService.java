package com.tekcamp.ulysse.Services;

import com.tekcamp.ulysse.Model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> getUsers();

    User getUser(String email);

    User getUser(long id);

    User save(User user, long id);

    void delete(long id);
}
