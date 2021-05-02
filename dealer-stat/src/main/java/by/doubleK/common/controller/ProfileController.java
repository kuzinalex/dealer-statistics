package by.doubleK.common.controller;

import by.doubleK.common.entity.Profile;
import by.doubleK.common.entity.User;
import by.doubleK.common.service.ProfileService;
import by.doubleK.common.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {
    private ProfileService profileService;
    private UserService userService;


    public ProfileController(UserService userService, ProfileService profileService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<Profile>> getAllProfiles(){
        List<User> users=userService.getAllUsers();
        return ResponseEntity.ok(profileService.getAllUsersProfile(users));
    }


    @GetMapping("/users/{username}/profile")
    public ResponseEntity<Profile> getUserProfile(@PathVariable String username) {
        User user = userService.getByUsername(username);
        if (user != null && user.isActivated()) {
            return ResponseEntity.ok(profileService.getUserProfile(user));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
