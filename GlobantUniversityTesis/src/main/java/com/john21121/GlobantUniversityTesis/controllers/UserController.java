package com.john21121.GlobantUniversityTesis.controllers;


import com.john21121.GlobantUniversityTesis.dto.UserDto;
import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.security.AuthRequest;
import com.john21121.GlobantUniversityTesis.security.AuthResponse;
import com.john21121.GlobantUniversityTesis.security.JwtUtil;
import com.john21121.GlobantUniversityTesis.security.MyUserDetailsService;
import com.john21121.GlobantUniversityTesis.services.UserService;
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


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value="/login/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
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

    @GetMapping("/login/finduser/{username}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto getUserByUsername(@PathVariable("username")String username){
        return userService.findUserByUsername(username);
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

    @PutMapping("login/{username}/update/")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUserByName(@PathVariable("username")String userId, @RequestBody UserDto user){
        UserDto user1 = userService.findUserByUsername(userId);
        if (user1 == null){
            throw new NotFoundException("This User does´nt exist");
        }
        return userService.updateUserById(user1.getId(),user);
    }


    @DeleteMapping("delete/{userid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserById(@PathVariable("userid")Long userId){
        userService.deleteById(userId);
    }

    @DeleteMapping("delete/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserByUsername(@PathVariable("username")String username){
        userService.deleteUserByUsername(username);
    }


}
