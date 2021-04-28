package by.doubleK.common.controller;


//import by.doubleK.common.repository.UserRepository;

import by.doubleK.common.entity.Advert;
import by.doubleK.common.entity.Game;
import by.doubleK.common.entity.Role;
import by.doubleK.common.entity.User;
import by.doubleK.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class TestController {

    @PersistenceContext
    private EntityManager entityManager;

    private UserService userService;
    private AdvertService advertService;
    private CommentService commentService;
    private GameService gameService;
    private RoleService roleService;

    @Autowired
    public TestController(GameService gameService, UserService userService, AdvertService advertService, CommentService commentService, RoleService roleService) {
        this.userService = userService;
        this.advertService = advertService;
        this.commentService = commentService;
        this.roleService = roleService;
        this.gameService = gameService;
    }
    @GetMapping("/home")
    public String testPage(){
        gameService.delete(8L);
        return "hi";
    }

    @GetMapping("/")
    public String homePage() {
//        for (int i = 0; i <10 ; i++) {
//            advertService.save(new Advert());
//            gameService.save(new Game());
//        }
//        Advert advert =  advertService.getById(4L);
//        Game game = gameService.getById(8L);
//        advert.getGames().add(game);
//        advertService.save(advert);
////


//        Advert advert = new Advert();
//        advert.setUser(userService.getById(15L));
//        advertService.save(advert);
        //user.getAdverts().add(advert);
        // advert.setUser(user);
        //user.setFirstName("Alexey");
        //advert.setUser(user);
        // advertService.save(advert);
        //advertService.delete(3L);
        //userService.delete(23L);


        return "index";
    }
}
