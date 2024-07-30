package uz.pdp.maven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.maven.dto.SignupDto;
import uz.pdp.maven.dto.UserDto;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username);
        System.out.println(password);
        UserDto user = new UserDto(username, "Asror", "asror@gmail.com", 18);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("main/home");
        return modelAndView;
    }

    @GetMapping("/signup")
    public String signUp() {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signUp(SignupDto signupDto) {
        System.out.println(signupDto);
        return "/main/about";
    }
}
