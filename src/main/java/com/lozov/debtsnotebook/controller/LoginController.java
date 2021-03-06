package com.lozov.debtsnotebook.controller;

import com.lozov.debtsnotebook.entity.User;
import com.lozov.debtsnotebook.repository.MongoUserRepository;
import com.lozov.debtsnotebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Yevhen on 2015-05-16.
 */
@RestController
@RequestMapping(value = "/login", produces = "application/json")
public class LoginController {

    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    @Resource(name = "userRepository")
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public User login(@RequestBody Map<String, String> body){

        return repository.get(body.get(PARAM_USERNAME), body.get(PARAM_PASSWORD));
    }
}
