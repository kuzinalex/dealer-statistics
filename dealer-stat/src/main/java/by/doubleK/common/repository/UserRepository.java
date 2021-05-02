package by.doubleK.common.repository;

import by.doubleK.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);


    User getByEmail(String email);


    @Override
    User getById(Long aLong);


    @Modifying
    @Query("UPDATE User u set u.isActivated = ?2 where u.id = ?1")
    void setUserActivationStatus(Long id, boolean isActive);
}
