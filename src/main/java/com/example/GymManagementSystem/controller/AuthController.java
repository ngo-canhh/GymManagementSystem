      package com.example.GymManagementSystem.controller;
     

  import com.example.GymManagementSystem.entity.User;
      import com.example.GymManagementSystem.service.AuthService;
       import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.http.HttpStatus;
      import org.springframework.http.ResponseEntity;
     import org.springframework.web.bind.annotation.*;

 @RestController
@RequestMapping("/auth")

    public class AuthController
      {

    @Autowired
  AuthService authService;


  @PostMapping("/register")

    public ResponseEntity<String> register(@RequestBody User user)
         {

   User u=  authService.register(user);
   
      if(u.getId() != null)

        return new ResponseEntity<String>("user is registered", HttpStatus.CREATED);

          return new ResponseEntity<>("cannot create user",HttpStatus.EXPECTATION_FAILED);
         }

  
   }
 
