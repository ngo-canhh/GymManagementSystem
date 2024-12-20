package com.example.gymmanagement.controller;

import com.example.gymmanagement.entity.User;
import com.example.gymmanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")

public class AuthController
   {

@Autowired
