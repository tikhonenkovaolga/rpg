package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {


    private final PlayerRepository playerRepository;


    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public List<Player> getAllPlayers(Integer pageNumber, Integer pageSize, PlayerOrder order) {
        return playerRepository.findAll();
    }

    @Override
    public int getPlayersCount(Integer pageNumber, Integer pageSize, PlayerOrder order) {
        return getAllPlayers(pageNumber, pageSize, order).size();
    }

    @Override
    public void create(Player player) {
        playerRepository.saveAndFlush(currentLevel(player));
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.getOne(id);
    }

    @Override
    public void update(Long id) {
        Player player = getPlayer(id);
        playerRepository.saveAndFlush(currentLevel(player));
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    public Player currentLevel(Player player) {
        int currentLevel = (int) (((Math.sqrt(2500 + 200 * player.getLevel())) - 50) / 100);
        int untilNextLevel = 50 * (currentLevel + 1) * (currentLevel + 2) - player.getLevel();
        player.setLevel(currentLevel);
        player.setUntilNextLevel(untilNextLevel);
        return player;
    }
}
