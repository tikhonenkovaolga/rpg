package com.game.repository;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
   // List<Player> findAll(Integer pageNumber, Integer pageSize, PlayerOrder order);
}