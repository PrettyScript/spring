package com.tekcamp.ulysse.DataAccessObject;

import com.tekcamp.ulysse.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    User findById(long id);

}
