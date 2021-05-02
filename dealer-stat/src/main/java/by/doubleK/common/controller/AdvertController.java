package by.doubleK.common.controller;

import by.doubleK.common.entity.Advert;
import by.doubleK.common.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class AdvertController {

    private AdvertService advertService;

    @Autowired
    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }


    @GetMapping("/adverts")
    public ResponseEntity<List<Advert>> allAdverts() {
        return ResponseEntity.ok(advertService.getAll());
    }

    @PostMapping("/advert")
    public ResponseEntity<Advert> addAdvert(@RequestBody Advert advert, Principal principal) {
        advertService.saveAdvert(advert, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/my")
    public ResponseEntity<List<Advert>> getUserAdverts(Principal principal) {
        return ResponseEntity.ok(advertService.getUserAdverts(principal.getName()));
    }


    @PutMapping("/advert/{id}")
    public ResponseEntity<Advert> updateAdvert(@RequestBody Advert advert, Principal principal, @PathVariable Long id) {
        advertService.updateAdvert(advert, id, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/adverts/approve/{id}")
    public ResponseEntity<Advert> approveAdvert(@PathVariable Long id) {
        advertService.setAdvertApprove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/advert/{id}")
    public ResponseEntity<Advert> deleteAdvert(@PathVariable Long id, Principal principal) {
        if (advertService.setAdvertApprove(id, false, principal)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}