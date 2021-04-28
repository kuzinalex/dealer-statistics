package by.doubleK.common.dao;

import by.doubleK.common.entity.Game;

import java.util.List;

public interface GameDao {
    void save(Game game);

    void delete(Long id);

    Game getById(Long id);

    List<Game> getAll();
}
