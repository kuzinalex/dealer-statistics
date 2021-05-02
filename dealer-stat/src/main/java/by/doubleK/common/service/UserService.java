package by.doubleK.common.service;

import by.doubleK.common.entity.User;
import by.doubleK.common.repository.RoleRepository;
import by.doubleK.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;


    @Autowired
    public void setUserRepository(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public User getById(Long id) {
        return userRepository.getById(id);
    }


    @Transactional
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }


    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(User::isActivated)
                .filter(user -> !user.getRoles().
                        contains(roleRepository.getByName("ROLE_ADMIN")))
                .collect(Collectors.toList());
    }


    @Transactional
    public void setUserActivationStatus(Long id, boolean isActive) {
        userRepository.setUserActivationStatus(id, isActive);
    }


    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }


    @Transactional
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

}
