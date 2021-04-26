package by.doubleK.common.controller;

import by.doubleK.common.entity.User;
import by.doubleK.common.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class TestController {

//    UserRepository userRepository;
//
//    @Autowired
//    public TestController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    private UserDao userdao;

    @Autowired
    public TestController(UserDao userDao) {
        this.userdao = userDao;
    }

    @GetMapping("/home")
    public String homePage() {
        User user=new User();
        user.setFirstName("Alexey");
        userdao.save(user);
        return "index";
    }
}
