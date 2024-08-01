package uz.pdp.maven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.maven.model.chat.Chat;
import uz.pdp.maven.model.message.Message;
import uz.pdp.maven.model.user.User;
import uz.pdp.maven.service.ChatService;
import uz.pdp.maven.service.MessageService;
import uz.pdp.maven.service.UploadService;
import uz.pdp.maven.service.UserService;

import java.util.List;

@Controller
public class TelegramController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private UploadService uploadService;

    @GetMapping("main/telegram")
    public String telegram() {
        return "main/telegram";
    }

    @PostMapping("main/telegram")
    public ModelAndView telegram(@RequestParam String userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/telegram");

        User user = userService.get(userId);
        List<Message> userMessages = messageService.getMessageByUserID(userId);
        List<Chat> userChats = chatService.getUserChatsByUserId(userId);

        modelAndView.addObject("userMessages", userMessages);
        modelAndView.addObject("user", user);
        modelAndView.addObject("userChats", userChats);

        return modelAndView;
    }

    @GetMapping("/chat/create/{userId}")
    public ModelAndView chatCreate(@PathVariable(value = "userId") String userId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/telegram");
        User user = userService.get(userId);

        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/chat/create")
    public ModelAndView chatCreate(@RequestParam(value = "user1Id") String user1Id,
                                   @RequestParam(value = "user2Id") String user2Id)
    {
        ModelAndView modelAndView = new ModelAndView();

        Chat chat = chatService.getChat(user1Id, user2Id);
        List<Message> chatMessages = messageService.getChatMessagesByChatId(chat.getId());

        modelAndView.addObject("chat", chat);
        modelAndView.addObject("messages", chatMessages);
        modelAndView.setViewName("main/telegram");

        return modelAndView;
    }

    @PostMapping("main/telegram/searchContacts")
    public ModelAndView searchContacts(@RequestParam(value = "searchUsername") String searchUsername){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/telegram");

        List<User> usersByUsername = userService.getUsersByUsername(searchUsername);

        modelAndView.addObject("usersByUsername", usersByUsername);
        return modelAndView;
    }
}
