package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.PlayerSearchCriteria;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerCriteriaRepository;
import com.game.repository.PlayerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerServiceImpl implements PlayerService {


    private final PlayerRepository playerRepository;
    private final PlayerCriteriaRepository playerCriteriaRepository;


    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerCriteriaRepository playerCriteriaRepository) {
        this.playerRepository = playerRepository;
        this.playerCriteriaRepository = playerCriteriaRepository;
    }

    @Override
    public List<Player> findAllByParams(String name, String title, Race race, Profession profession, Date after, Date before, Boolean banned, Integer minExperience,
                                        Integer maxExperience, Integer minLevel, Integer maxLevel, Integer pageNumber, Integer pageSize, PlayerOrder order) {
        Pageable pageable = new PageableImpl(pageNumber, pageSize, order);
        PlayerSearchCriteria playerSearchCriteria = new PlayerSearchCriteria(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel);

        return playerCriteriaRepository.findAllByParams((PageableImpl) pageable, playerSearchCriteria);
    }

    @Override
    public Long getCountPlayersByParam(String name, String title, Race race, Profession profession, Date after, Date before, Boolean banned, Integer minExperience,
                                      Integer maxExperience, Integer minLevel, Integer maxLevel, Integer pageNumber, Integer pageSize, PlayerOrder order) {
        Pageable pageable = new PageableImpl(pageNumber, pageSize, order);
        PlayerSearchCriteria playerSearchCriteria = new PlayerSearchCriteria(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel);
        Long countOfPlayers = playerCriteriaRepository.countOfAllByParams((PageableImpl) pageable, playerSearchCriteria);
        return countOfPlayers;
    }

    @Override
    public Player create(Player player) {
        player.setLevel(currentLevel(player));
        player.setUntilNextLevel(untilNextLevel(player));

        playerRepository.saveAndFlush(player);
        return player;
    }

    @Override
    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player update(Player newPlayer, Long id) {
        Player player = playerRepository.findById(id).get();
        updateData(player, newPlayer);

        playerRepository.save(player);
        return player;
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }


    public Integer currentLevel(Player player) {
        return (int) (((Math.round(Math.sqrt(2500 + 200 * player.getExperience()))) - 50) / 100);

    }

    public Integer untilNextLevel(Player player) {
        return (50 * (player.getLevel() + 1) * (player.getLevel() + 2)) - player.getExperience();
    }

    public boolean checkPlayer(Player player) {
        if (player == null) {
            return false;
        }

        if (player.getName() == null || badName(player.getName())) {
            System.out.println("The name is not corrected");
            return false;
        }
        if (player.getTitle() == null || badTitle(player.getTitle())) {
            System.out.println("The title is not corrected");
            return false;
        }
        if (player.getRace() == null || player.getProfession() == null) {
            System.out.println("Enter race or(and) profession");
            return false;
        }
        if (isDateNotValid(player.getBirthday())) {
            System.out.println("The birthday is not corrected");
            return false;
        }
        if (badExperience(player.getExperience())) {
            System.out.println("The experience is not corrected");
            return false;
        }
        if (player.getBanned() == null) {
            player.setBanned(false);
        }
        return true;
    }

    public boolean checkId(Long id) {
        if (id == null || id < 0L || id == 0L) {
            System.out.println("id == null or id = 0 or id < 0");
            return false;
        }
        if (id % 1 != 0L) {
            System.out.println("id is not Long");
            return false;
        }
        if (!String.valueOf(id).matches("^-?\\d+$")) {
            System.out.println("id is not digit");
            return false;
        }
        return true;
    }

    public boolean isPlayerPresent(Long id) {
        return playerRepository.findById(id).isPresent();
    }

    public Date convertLong(Long millis) {
        if (millis == null || millis == 0L) {
            return null;
        }
        return new Date(millis);
    }

    public void updateData(Player player, Player newPlayer) {
        if (newPlayer == null) {
            return;
        }
        if (newPlayer.getName() != null) {
            player.setName(newPlayer.getName());
        }
        if (newPlayer.getTitle() != null) {
            player.setTitle(newPlayer.getTitle());
        }
        if (newPlayer.getRace() != null) {
            player.setRace(newPlayer.getRace());
        }
        if (newPlayer.getProfession() != null) {
            player.setProfession(newPlayer.getProfession());
        }
        if (newPlayer.getExperience() != null) {
            player.setExperience(newPlayer.getExperience());
            player.setLevel(currentLevel(player));
            player.setUntilNextLevel(untilNextLevel(player));
        }
        if (newPlayer.getBirthday() != null) {
            player.setBirthday(newPlayer.getBirthday());
        }
        if (newPlayer.getBanned() != null) {
            player.setBanned(newPlayer.getBanned());
        }

    }

//    public boolean checkParamsNull(String name, String title, Race race, Profession profession, Date after, Date before, Boolean banned, Integer minExperience,
//                                   Integer maxExperience, Integer minLevel, Integer maxLevel) {
//        return (name == null && title == null && race == null && profession == null && after == null && before == null
//                && banned == null && minExperience == null && maxExperience == null && minLevel == null && maxLevel == null);
//    }

    public boolean checkParamsValid(String name, String title, Date after, Date before, Integer minExperience,
                                    Integer maxExperience) {
        if (name != null && badName(name)) {
            return false;
        }

        if (title != null && badTitle(title)) {
            return false;
        }

        if (after != null && isDateNotValid(after)) {
            return false;
        }

        if (before != null && isDateNotValid(before)) {
            return false;
        }

        if (minExperience != null && badExperience(minExperience)) {
            return false;
        }

        if (maxExperience != null && badExperience(maxExperience)) {
            return false;
        }

        return true;
    }

    private boolean badName(String name) {
        return name.equals("") || name.length() > 12;
    }

    private boolean badTitle(String title) {
        return title.equals("") || title.length() > 30;
    }

    private boolean isDateNotValid(Date birthDate) {
        Calendar date = Calendar.getInstance();
        date.setTime(birthDate);
        return birthDate.getTime() <= 0 || date.get(Calendar.YEAR) < 2_000
                || date.get(Calendar.YEAR) > 3_000;
    }

    private boolean badExperience(Integer exp) {
        return exp <= 0 || exp >= 10000000;
    }


}
