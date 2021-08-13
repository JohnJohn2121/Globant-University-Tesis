package com.john21121.GlobantUniversityTesis.controllers;


import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.repository.UserRepository;
import com.john21121.GlobantUniversityTesis.services.UserService;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user/")
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;


    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/finduser/{userid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUserById(@PathVariable("userid")String userId){
        User user = userService.findById(userId);
        return user;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        userRepository.save(user);
        userService.createNewUser(user);
        return user;
    }

    @PutMapping("/update/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable("userid")String userId,@RequestBody User user){
        Optional<User> user1 = userRepository.findById(userId);
        if (!user1.isPresent()){
            throw new NotFoundException("This User does´nt exist");
        }
        return userService.updateUserById(userId,user);
    }

    @DeleteMapping("delete/{userid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserById(@PathVariable("userid")String userId){
        Optional<User> user1 = userRepository.findById(userId);
        if (!user1.isPresent()){
            throw new NotFoundException("This User does´nt exist");
        }

            userRepository.deleteById(userId);
            userService.deleteById(userId);

    }



}
