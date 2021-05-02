package by.doubleK.common.service;

import by.doubleK.common.entity.Advert;
import by.doubleK.common.entity.Game;
import by.doubleK.common.repository.AdvertRepository;
import by.doubleK.common.repository.GameRepository;
import by.doubleK.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;


@Service
public class AdvertService {

    private AdvertRepository advertRepository;
    private GameRepository gameRepository;
    private UserRepository userRepository;

    @Autowired
    public void setAdvertRepository(UserRepository userRepository,
                                    GameRepository gameRepository, AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public void saveAdvert(Advert advert, Principal principal) {
        advert.setUser(userRepository.getByUsername(principal.getName()));
        saveGames(advert);
    }

    @Transactional
    public void updateAdvert(Advert advert, Long advertId, Principal principal) {
        advert.setId(advertId);
        saveAdvert(advert, principal);
    }

    @Transactional
    public Advert getById(Long id) {
        return advertRepository.getById(id);
    }


    @Transactional
    public void saveGames(Advert advert) {
        ListIterator<Game> iter = advert.getGames().listIterator();
        while (iter.hasNext()) {
            Game game = iter.next();
            Game currentGame = gameRepository.getByName(game.getName());
            if (currentGame == null) {
                gameRepository.save(game);
            } else {
                iter.set(currentGame);
            }
        }
        advertRepository.save(advert);
    }


    @Transactional
    public List<Advert> getUserAdverts(String username) {
        return advertRepository.getAllByUser(userRepository.getByUsername(username))
                .stream().filter(Advert::isStatus).collect(Collectors.toList());
    }


    public List<Advert> allAdvertsByGame(Long id) {
        Game game = gameRepository.getById(id);
        return advertRepository.getAllByGames(game).stream()
                .filter(Advert::isStatus)
                .filter(advert -> advert.getUser().isActivated())
                .collect(Collectors.toList());
    }


    @Transactional
    public boolean setAdvertApprove(Long id, boolean isApproved, Principal principal) {
        Advert advert = advertRepository.getById(id);
        if (principal.getName().equals(advert.getUser().getUsername())) {
            advertRepository.setAdvertApprove(id, isApproved);
            return true;
        }
        return false;
    }


    @Transactional
    public void setAdvertApprove(Long id) {
        Advert advert = advertRepository.getById(id);
        advert.setStatus(!advert.isStatus());
        advertRepository.save(advert);
    }


    @Transactional
    public List<Advert> getAll() {
        return advertRepository.getAllApprovedAdverts();
    }
}