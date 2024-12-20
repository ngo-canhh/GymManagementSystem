package com.example.gymmanagement.service;

 import com.example.gymmanagement.entity.User;
 import com.example.gymmanagement.repository.UserRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 @Service
   public class AuthService
   {
      @Autowired
 UserRepository userRepository;


  public User register(User user)
   {
      return  userRepository.save(user);

  }
 }
