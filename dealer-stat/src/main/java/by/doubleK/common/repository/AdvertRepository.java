package by.doubleK.common.repository;

import by.doubleK.common.entity.Advert;
import by.doubleK.common.entity.Game;
import by.doubleK.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    List<Advert> getAllByUser(User user);


    @Modifying
    @Query("UPDATE Advert a set a.status = ?2 where a.id = ?1")
    void setAdvertApprove(Long id, boolean isApproved);


    List<Advert> getAllByGames(Game game);


    @Query("SELECT a FROM Advert a WHERE a.status = true")
    List<Advert> getAllApprovedAdverts();

}
