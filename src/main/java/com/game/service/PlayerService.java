package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers(Integer pageNumber, Integer pageSize, PlayerOrder order);
    int getPlayersCount(Integer pageNumber, Integer pageSize, PlayerOrder order);
    void create(Player player);
    Player getPlayer(Long id);
    void update(Long id);
    void delete(Long id);
}
