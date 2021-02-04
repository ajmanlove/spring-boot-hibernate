package com.ajmanlove.spring.controllers;

import com.ajmanlove.spring.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UsersService usersService;

  @GetMapping
  public ResponseEntity<List<Object>> getUsers() {
    return ResponseEntity.ok(
      usersService.getUsers()
    );
  }
}
