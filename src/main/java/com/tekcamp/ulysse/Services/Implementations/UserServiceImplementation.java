package com.tekcamp.ulysse.Services.Implementations;

import com.tekcamp.ulysse.DataAccessObject.UserRepository;
import com.tekcamp.ulysse.Model.User;
import com.tekcamp.ulysse.Services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users = (List<User>) userRepository.findAll();
        return users;
    }

    @Override
    public User getUser(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User getUser(long id) {
        User user = userRepository.findById(id);
        return user;
    }


    @Override
    public User save(User user, long id) {
        User updatedUser = userRepository.findById(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        userRepository.save(updatedUser);
        return updatedUser;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
