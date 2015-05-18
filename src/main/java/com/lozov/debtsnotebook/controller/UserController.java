package com.lozov.debtsnotebook.controller;

import com.lozov.debtsnotebook.entity.User;
import com.lozov.debtsnotebook.repository.UserRepository;
import com.lozov.debtsnotebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lozov on 12.05.15.
 */
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User user){
        String id = (String) repository.save(user).getId();
        user.setId(id);

        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable String id){
        return repository.getById(id);
    }

    @RequestMapping(value = "/{id}/borrowers", method = RequestMethod.GET)
    public List<User> borrowers(@PathVariable String id){
        return userService.getBorrowers(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list(){
        return repository.find().asList();
    }
}
