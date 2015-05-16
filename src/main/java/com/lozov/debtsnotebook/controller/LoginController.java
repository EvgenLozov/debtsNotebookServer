package com.lozov.debtsnotebook.controller;

import com.lozov.debtsnotebook.entity.User;
import com.lozov.debtsnotebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yevhen on 2015-05-16.
 */
@RestController
@RequestMapping(value = "/login", produces = "application/json")
public class LoginController {

    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public User login(@RequestParam(PARAM_USERNAME) String username,
                      @RequestParam(PARAM_PASSWORD) String password){

        return repository.get(username, password);
    }
}
