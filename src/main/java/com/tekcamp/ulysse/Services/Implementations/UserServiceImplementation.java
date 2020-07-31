package com.tekcamp.ulysse.Services.Implementations;

import com.tekcamp.ulysse.DataAccessObject.UserRepository;
import com.tekcamp.ulysse.DataTransferObject.UserDto;
import com.tekcamp.ulysse.Exceptions.NotFoundException;
import com.tekcamp.ulysse.Exceptions.RecordExistsException;
import com.tekcamp.ulysse.Model.Request.UserRequest;
import com.tekcamp.ulysse.Model.User;
import com.tekcamp.ulysse.Services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {


    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        if(userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new RecordExistsException();
        }

        User newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);

        String id = UUID.randomUUID().toString().replace("-", "");
        newUser.setUserId(id);

        String encryptedPassword = UUID.randomUUID().toString().replace("-", "");
        newUser.setEncryptedPassword(encryptedPassword);

        User storedUserDetails = userRepository.save(newUser);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public List<User> getUsers(int page, int limit) {
        List<User> users;

        if(page > 0) page--;
        Pageable pageable = PageRequest.of(page, limit);
        Page<User> userPage = userRepository.findAll(pageable);
        users = userPage.getContent();
        return users;
    }

    @Override
    public UserDto getUser(String email) {

        User user = userRepository.findByEmail(email);
        if(userRepository.findByEmail(email) == null) {
            throw new NotFoundException();
        }
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(user, returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUser(long id) {
        User user = userRepository.findById(id);
        if(userRepository.findById(id) == null) {
            throw new NotFoundException();
        }
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(user, returnValue);

        return returnValue;
    }


    @Override
    public UserDto save(UserRequest user, long id) {
        User updatedUser = userRepository.findById(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setEncryptedPassword(user.getPassword());

        User storedUpdatedUser = userRepository.save(updatedUser);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUpdatedUser, returnValue);

        return returnValue;
    }

    @Override
    public void delete(long id) {
        if(userRepository.findById(id) == null) {
            throw new NotFoundException();
        }
        userRepository.deleteById(id);
    }
}
