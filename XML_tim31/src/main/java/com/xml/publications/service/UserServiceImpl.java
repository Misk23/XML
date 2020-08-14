package com.xml.publications.service;

import com.xml.publications.DTO.UserDTO;
import com.xml.publications.model.User.ObjectFactory;
import com.xml.publications.model.User.User;
import com.xml.publications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public String registerUser(UserDTO userDTO){

        User user = (new ObjectFactory()).createUser();
        user.setUsername(userDTO.getUsername());
        BCryptPasswordEncoder coder = new BCryptPasswordEncoder();
        user.setPassword(coder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirst_name());
        user.setLastName(userDTO.getLast_name());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        return userRepository.saveUser(user);
    }
}
