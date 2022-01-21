package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<Player> findAllByParams(String name, String title, Race race, Profession profession, Date after, Date before,
                                 Boolean banned, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel, Integer pageNumber, Integer PageSize, PlayerOrder order);
    Long getCountPlayersByParam(String name, String title, Race race, Profession profession, Date after, Date before, Boolean banned, Integer minExperience,
                               Integer maxExperience, Integer minLevel, Integer maxLevel,Integer pageNumber, Integer pageSize, PlayerOrder order);
    Player create(Player player);
    Optional<Player> getPlayerById(Long id);
    Player update(Player newPlayer, Long id);
    void delete(Long id);


}
