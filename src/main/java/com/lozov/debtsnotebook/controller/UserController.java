package com.lozov.debtsnotebook.controller;

import com.lozov.debtsnotebook.entity.User;
import com.lozov.debtsnotebook.repository.MongoUserRepository;
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

        return repository.create(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable String id){
        return repository.getById(id);
    }

    @RequestMapping(value = "/{id}/lenders", method = RequestMethod.GET)
    public List<User> lenders(@PathVariable String id){
        return userService.getLenders(id);
    }

    @RequestMapping(value = "/{id}/debtors", method = RequestMethod.GET)
    public List<User> debtors(@PathVariable String id){
        return userService.getDebtors(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list(){
        return repository.list();
    }
}
