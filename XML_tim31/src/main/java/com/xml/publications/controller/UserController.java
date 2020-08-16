package com.xml.publications.controller;

import com.xml.publications.DTO.LoginDTO;
import com.xml.publications.DTO.TokenDTO;
import com.xml.publications.DTO.UserDTO;
import com.xml.publications.model.User.User;
import com.xml.publications.security.ForbiddenException;
import com.xml.publications.security.TokenUtils;
import com.xml.publications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        String response;
        try{
            response = userService.registerUser(userDTO);
        }catch (Exception e){
            return new ResponseEntity<String>("Invalid registration request" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response , HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@Valid @RequestBody LoginDTO loginDTO, BindingResult errors) {
        try {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            User user = this.userService.findByUsername(loginDTO.getUsername());
            if(user == null) throw new UsernameNotFoundException("No user found");
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            authenticationManager.authenticate(token);
            Long id = Long.parseLong(user.getUsername().toString(),Character.MAX_RADIX);
            TokenDTO userToken = new TokenDTO(jwtUtils.generateToken(details, id));
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch(ForbiddenException ex) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        catch (Exception ex) {
            //ex.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
