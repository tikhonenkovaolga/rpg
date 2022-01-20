package com.game.controller;


import com.game.entity.Player;
import com.game.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerServiceImpl playerService;




//    @PostMapping()
//    public ResponseEntity<Player> create (@RequestBody Player player){
//        if (player.getName() == null || player.getName().equals("") || player.getName().length() > 12){
//            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
//        }
//        if (player.getTitle() == null || player.getTitle().equals("") || player.getTitle().length() > 30){
//            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
//        }
//        if (player.getRace() == null || player.getProfession() == null){
//            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
//        }
//        //if (player.getBirthday())
//        if (player.getBanned() == null){
//            player.setBanned(false);
//        }
//
//        playerService.create(player);
//        return player != null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
//    }

    //Request URL: http://localhost:8090/rest/players?&pageNumber=0&pageSize=1&order=ID
    @GetMapping("/rest/players")
    public ResponseEntity<List<Player>> findAll(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, @RequestParam PlayerOrder order) {
        System.out.println("method call: ResponseEntity<List<Player>> findAll");
        System.out.println("pageNumber = " + pageNumber + " pageSize = " + pageSize + "playerORDER = " + order);
        try {
            if (pageNumber == null) {
                pageNumber = 0;
            }
            if (pageSize == null) {
                pageSize = 3;
            }
            if (order == null) {
                order = PlayerOrder.ID;
            }
            List<Player> players = playerService.getAllPlayers(pageNumber, pageSize, order);

            if (players.isEmpty()){
                System.out.println("empty");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }
            return new ResponseEntity<>(players, HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println("error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


}
