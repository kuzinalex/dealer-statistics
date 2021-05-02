package by.doubleK.common.controller;

import by.doubleK.common.entity.Advert;
import by.doubleK.common.entity.Game;
import by.doubleK.common.service.AdvertService;
import by.doubleK.common.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    private GameService gameService;
    private AdvertService advertService;


    @Autowired
    public GameController(GameService gameService, AdvertService advertService) {
        this.gameService = gameService;
        this.advertService = advertService;
    }


    @GetMapping("/games")
    public ResponseEntity<List<Game>> allGames() {
        return ResponseEntity.ok(gameService.getAll());
    }


    @GetMapping("/games/{id}/advert")
    public ResponseEntity<List<Advert>> allAdvertsByGame(@PathVariable Long id) {

        return ResponseEntity.ok(advertService.allAdvertsByGame(id));
    }

}
