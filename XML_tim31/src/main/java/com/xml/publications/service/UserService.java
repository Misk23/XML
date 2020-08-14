package com.xml.publications.service;

import com.xml.publications.DTO.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public String registerUser(UserDTO userDTO);
}
