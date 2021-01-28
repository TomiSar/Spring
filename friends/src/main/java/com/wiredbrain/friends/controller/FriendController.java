package com.wiredbrain.friends.controller;

import com.wiredbrain.friends.model.Friend;
import com.wiredbrain.friends.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping("/friend")
    Iterable<Friend> getFriends() {
        return friendService.findAll();
    }

    @GetMapping("/friend/{id}")
    Optional<Friend> findById(@PathVariable Integer id) {
        return friendService.findById(id);
    }

    //Postman http://localhost:8080/friend/search?first={firstName}&last={lastName}
    //Finding options with firstName, lastName, firstname && lastName else return all names
    @GetMapping("/friend/search")
    Iterable<Friend> findByQuery(
        @RequestParam(value = "first", required = false) String firstName, @RequestParam(value = "last", required = false) String lastName) {

        if (firstName != null) {
            return friendService.findByFirstName(firstName);
        } else if (lastName != null) {
            return friendService.findByLastName(lastName);
        } else if (firstName != null && lastName != null) {
            return friendService.findByFirstNameAndLastName(firstName, lastName);
        } else {
            return friendService.findAll();
        }
    }

    @PostMapping("/friend")
    Friend createFriend(@RequestBody Friend friend) {
        return friendService.save(friend);
    }

    @PutMapping("/friend")
    Friend updateFriend(@RequestBody Friend friend) {
        return friendService.save(friend);
    }

    //The Url mapping here delete
    @DeleteMapping("/friend/{id}")
    public void deleteFriend(@PathVariable Integer id) {
        friendService.deleteById(id);
    }
}
