package uz.pdp.maven.controller;

import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.maven.dto.LoginDto;
import uz.pdp.maven.dto.SignupDto;
import uz.pdp.maven.model.chat.Chat;
import uz.pdp.maven.model.upload.Upload;
import uz.pdp.maven.model.user.User;
import uz.pdp.maven.service.ChatService;
import uz.pdp.maven.service.MessageService;
import uz.pdp.maven.service.UploadService;
import uz.pdp.maven.service.UserService;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private UploadService uploadService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        User user = userService.login(new LoginDto(username, password));
        if (user == null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("auth/signup");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView();

            List<Chat> userChats = chatService.getUserChatsByUserId(user.getId());
            List<User> users = userService.getAll();

            modelAndView.addObject("user", user);
            modelAndView.addObject("userChats", userChats);
            modelAndView.addObject("users", users);
            modelAndView.setViewName("main/telegram");
            return modelAndView;
        }
    }

    @GetMapping("/signup")
    public String signUp() {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signUp(SignupDto signupDto) {
        System.out.println(signupDto);
        Part filePart = signupDto.upload();

        String ownerId = userService.signUp(signupDto);
        Upload upload1 = uploadService.createUpload(filePart, ownerId);
        boolean b = uploadService.save(upload1);
        return b ? "redirect:/auth/login" : "redirect:/auth/signup";
    }

    @GetMapping("/user/*")
    public ModelAndView chat(@RequestParam("userId") String userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/telegram");
        modelAndView.addObject("chat", userId);
        return modelAndView;
    }
}
