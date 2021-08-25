package com.john21121.GlobantUniversityTesis.controllers;


import com.john21121.GlobantUniversityTesis.dto.UserDto;
import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.repository.UserRepository;
import com.john21121.GlobantUniversityTesis.security.AuthRequest;
import com.john21121.GlobantUniversityTesis.security.AuthResponse;
import com.john21121.GlobantUniversityTesis.security.JwtUtil;
import com.john21121.GlobantUniversityTesis.security.MyUserDetailsService;
import com.john21121.GlobantUniversityTesis.services.UserService;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/user/")
public class UserController {



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    private final UserService userService;
    private final UserRepository userRepository;


    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @RequestMapping(value="/login/",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @GetMapping("/login/finduser/{userid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto getUserById(@PathVariable("userid")Long userId){
        return userService.findById(userId);
    }


    @PostMapping("/singup/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto user){
        return userService.createNewUser(user);
    }

    @PutMapping("login/update/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable("userid")Long userId, @RequestBody UserDto user){
        UserDto user1 = userService.findById(userId);
        if (user1 == null){
            throw new NotFoundException("This User does´nt exist");
        }
        return userService.updateUserById(userId,user);
    }

    @DeleteMapping("delete/{userid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserById(@PathVariable("userid")Long userId){
        userRepository.deleteById(userId);
    }



}
