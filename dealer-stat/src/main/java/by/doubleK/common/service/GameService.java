package by.doubleK.common.service;

import by.doubleK.common.entity.Game;
import by.doubleK.common.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {
    private GameRepository gameRepository;

    @Autowired
    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Transactional
    public List<Game> getAll() {
        return gameRepository.findAll();
    }


    @Transactional
    public Game getById(Long id) {
        return gameRepository.getById(id);
    }


    @Transactional
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }
}