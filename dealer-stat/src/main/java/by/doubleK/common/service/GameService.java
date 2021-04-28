package by.doubleK.common.service;

import by.doubleK.common.dao.GameDao;
import by.doubleK.common.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameDao gameDao;

    @Autowired
    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }


    public void save(Game game) {
        gameDao.save(game);
    }


    public Game getById(Long id) {
        return gameDao.getById(id);
    }


    public void delete(Long id) {
        gameDao.delete(id);
    }


    public List<Game> getAll() {
        return gameDao.getAll();
    }
}
