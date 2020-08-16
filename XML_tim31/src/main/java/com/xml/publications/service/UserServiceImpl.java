package com.xml.publications.service;

import com.xml.publications.DTO.UserDTO;
import com.xml.publications.model.User.ObjectFactory;
import com.xml.publications.model.User.User;
import com.xml.publications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public String registerUser(UserDTO userDTO){

        System.out.println(userDTO.getUsername());
        System.out.println(userDTO.getEmail());
        System.out.println(userDTO.getPassword());
        System.out.println(userDTO.getRole());
        System.out.println(userDTO.getFirst_name());
        System.out.println(userDTO.getLast_name());

        User user = (new ObjectFactory()).createUser();
        user.setUsername(userDTO.getUsername());
        BCryptPasswordEncoder coder = new BCryptPasswordEncoder();
        user.setPassword(coder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirst_name());
        user.setLastName(userDTO.getLast_name());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        user.setOwnPublications((new ObjectFactory()).createTPublications());
        user.setReviewPublications((new ObjectFactory()).createTPublications());

        return userRepository.saveUser(user);
    }

    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}
