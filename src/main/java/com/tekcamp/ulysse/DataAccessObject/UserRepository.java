package com.tekcamp.ulysse.DataAccessObject;

import com.tekcamp.ulysse.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Page findAll(Pageable pageable);

    @Override
    Iterable<User> findAll(Sort sort);

    User findByEmail(String email);
    User findById(long id);

}
