package com.xml.publications.service;

import com.xml.publications.DTO.UserDTO;
import com.xml.publications.model.User.User;


public interface UserService {

    String registerUser(UserDTO userDTO);
    User findByUsername(String username);
}
