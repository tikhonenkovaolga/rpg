package com.game.controller;


import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class PlayerController {

    private final PlayerServiceImpl playerServiceImpl;

    @Autowired
    public PlayerController(PlayerServiceImpl playerServiceImpl) {
        this.playerServiceImpl = playerServiceImpl;
    }

    //http://localhost:8090/rest/players/
    @PostMapping("rest/players/")
    public ResponseEntity<Player> create(@RequestBody Player player) {
        if (!playerServiceImpl.checkPlayer(player)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        playerServiceImpl.create(player);
        System.out.println("player created");
        return new ResponseEntity<>(player, HttpStatus.OK);

    }

    //Request URL: http://localhost:8090/rest/players?&pageNumber=0&pageSize=1&order=ID
    @GetMapping("rest/players")
    public ResponseEntity<List<Player>> findAllByParams(@RequestParam(value = "name", required = false) String name,
                                                        @RequestParam(value = "title", required = false) String title,
                                                        @RequestParam(value = "race", required = false) Race race,
                                                        @RequestParam(value = "profession", required = false) Profession profession,
                                                        @RequestParam(value = "after", required = false) Long after,
                                                        @RequestParam(value = "before", required = false) Long before,
                                                        @RequestParam(value = "banned", required = false) Boolean banned,
                                                        @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                                        @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                                        @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                                        @RequestParam(value = "maxLevel", required = false) Integer maxLevel,
                                                        @RequestParam(value = "order", required = false, defaultValue = "ID") PlayerOrder order,
                                                        @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize) {


        Date afterDate = playerServiceImpl.convertLong(after);
        Date beforeDate = playerServiceImpl.convertLong(before);

//        if (playerServiceImpl.checkParamsNull(name, title, race, profession, afterDate, beforeDate, banned, minExperience, maxExperience, minLevel, maxLevel)){
//            List<Player> players = playerServiceImpl.findAllPlayers(pageNumber, pageSize, order);
//            return new ResponseEntity<>(players, HttpStatus.OK);
//        }

        if (!playerServiceImpl.checkParamsValid(name, title, afterDate, beforeDate, minExperience, maxExperience)){
            System.out.println("one or a few of parameters is not valid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Player> players = playerServiceImpl.findAllByParams(name, title, race, profession, afterDate, beforeDate, banned, minExperience, maxExperience, minLevel, maxLevel, pageNumber, pageSize, order);

        return new ResponseEntity<>(players, HttpStatus.OK);

    }

    //http://localhost:8090/rest/players/count?&pageNumber=0&pageSize=3&order=ID

    @GetMapping("rest/players/count")
    public ResponseEntity<Long> countOfPlayersByParam(@RequestParam(value = "name", required = false) String name,
                                                         @RequestParam(value = "title", required = false) String title,
                                                         @RequestParam(value = "race", required = false) Race race,
                                                         @RequestParam(value = "profession", required = false) Profession profession,
                                                         @RequestParam(value = "after", required = false) Long after,
                                                         @RequestParam(value = "before", required = false) Long before,
                                                         @RequestParam(value = "banned", required = false) Boolean banned,
                                                         @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                                         @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                                         @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                                         @RequestParam(value = "maxLevel", required = false) Integer maxLevel,
                                                         @RequestParam(value = "order", required = false, defaultValue = "ID") PlayerOrder order,
                                                         @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                         @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize) {

        Date afterDate = playerServiceImpl.convertLong(after);
        Date beforeDate = playerServiceImpl.convertLong(before);

        if (!playerServiceImpl.checkParamsValid(name, title, afterDate, beforeDate, minExperience, maxExperience)){
            System.out.println("one or a few of parameters is not valid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long countOfPlayers = playerServiceImpl.getCountPlayersByParam(name, title, race, profession, afterDate, beforeDate,
                banned, minExperience, maxExperience, minLevel, maxLevel, pageNumber, pageSize, order);

        return new ResponseEntity<>(countOfPlayers, HttpStatus.OK);

    }

    @GetMapping("rest/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") Long id) {
        try {
            if (!playerServiceImpl.checkId(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!playerServiceImpl.isPlayerPresent(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Player findPlayer = playerServiceImpl.getPlayerById(id).get();
        return new ResponseEntity<>(findPlayer, HttpStatus.OK);
    }

    @DeleteMapping("rest/players/{id}")
    public ResponseEntity<HttpStatus> deletePlayerById(@PathVariable("id") Long id) {

        if (!playerServiceImpl.checkId(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!playerServiceImpl.isPlayerPresent(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("rest/players/{id}")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player newPlayer, @PathVariable("id") Long id) {
        if (!playerServiceImpl.checkId(id)) {
            System.out.println("id is not valid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!playerServiceImpl.isPlayerPresent(id)) {
            System.out.println("id is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Player player = playerServiceImpl.update(newPlayer, id);
        if (!playerServiceImpl.checkPlayer(player)){
            System.out.println("one or some parameters of newPlayer is not valid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }


}
