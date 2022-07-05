package com.teamc.bioskop.Service;

import com.teamc.bioskop.Model.Seats;
import com.teamc.bioskop.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();
    User createUser(User user);
    Optional<User> getUserById(Long users_Id);
    void deleteUserById(Long users_Id);
    User updateUser(User user, Long userId);
    User getReferenceById(Long Id);
    Optional<User> findbyid(Long id);
}

