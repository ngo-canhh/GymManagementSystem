     package com.example.GymManagementSystem.controller;

  import com.example.GymManagementSystem.entity.User;
  import com.example.GymManagementSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

 import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")

 public class AuthController {


     @Autowired
    AuthService authService;


  @PostMapping("/register")

 public String register(@ModelAttribute("user") User user,
                      @RequestParam("confirmPassword") String confirmPassword,Model model) {
     if (!user.getPassword().equals(confirmPassword)){
       model.addAttribute("error","Password mismatch. Please re-enter again!");
         return "StaffViewsHtml/register";
    }
      
      authService.register(user);

   return "redirect:/auth/login";
   }

     @GetMapping("/login")
 public String viewLogin(){
       return "StaffViewsHtml/login";
   }

    @GetMapping("/register")
  public String viewRegister(){
     return "StaffViewsHtml/register";
   }

   
  }
