package uz.pdp.maven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.maven.dto.LoginDto;
import uz.pdp.maven.dto.SignupDto;
import uz.pdp.maven.model.user.User;
import uz.pdp.maven.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService implements BaseService<User> {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(LoginDto loginDto) {
        User login = userRepository.login(loginDto);
        return login;
    }

    public String signUp(SignupDto signupDto) {

        User user = User.builder()
                .username(signupDto.username())
                .name(signupDto.name())
                .password(signupDto.password())
                .age(signupDto.age())
                .email(signupDto.email())
                .build();

        System.out.println("user1 = " + user);
        userRepository.save(user);

        return user.getId();
    }

    @Override
    public User get(String id) {
        return userRepository.get(id);
    }

    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean delete(String id) {
        return userRepository.delete(id);
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    public List<User> getUsersByUsername(String searchUsername) {
        return userRepository.getUsersByUsername(searchUsername);
    }
}
