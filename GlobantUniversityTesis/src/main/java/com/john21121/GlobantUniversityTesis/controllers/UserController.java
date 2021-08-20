package com.john21121.GlobantUniversityTesis.controllers;


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


    //TODO Update the whole class

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

    @GetMapping("/finduser/{userid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUserById(@PathVariable("userid")String userId){
        return null;//userService.findById(userId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        userRepository.save(user);
      //  userService.createNewUser(user);
        return user;
    }

    @PutMapping("/update/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> updateUser(@PathVariable("userid")Long userId, @RequestBody User user){
        Optional<User> user1 = userRepository.findById(userId);
        if (user1.isEmpty()){
            throw new NotFoundException("This User does´nt exist");
        }
        return null;//userService.updateUserById(userId,user);
    }

    @DeleteMapping("delete/{userid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserById(@PathVariable("userid")Long userId){
        Optional<User> user1 = userRepository.findById(userId);
        if (user1.isEmpty()){
            throw new NotFoundException("This User does´nt exist");
        }

            userRepository.deleteById(userId);
            userService.deleteById(userId);

    }



}
