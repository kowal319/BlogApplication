package com.example.BlogApplication.controller;


import com.example.BlogApplication.dto.RegistrationDto;
import com.example.BlogApplication.entity.User;
import com.example.BlogApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //handler method to handle login page request
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    //handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        //this object holds form data
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    //handle method to handle user registration form submit request
    @PostMapping("register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result,
                           Model model){
        User existingUser = userService.findByEmail(user.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
        result.rejectValue("email", null, "There is already a user with the same email");
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }
}
