package com.game.repository;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("select player from Player player where " +
            "(:name is null or player.name like concat('%',:name,'%')) and " +
            "(:title is null or player.title like concat('%',:title,'%')) and " +
            "(:race is null or player.race = :race) and " +
            "(:profession is null or player.profession = :profession) and " +
            "(:after is null or player.birthday >= :after) and " +
            "(:before is null or player.birthday <= :before) and " +
            "(:banned is null or player.banned = :banned) and " +
            "(:minExperience is null or player.experience >= :minExperience) and " +
            "(:maxExperience is null or player.experience <= :maxExperience) and " +
            "(:minLevel is null or player.level >= :minLevel) and " +
            "(:maxLevel is null or player.level <= :maxLevel)")
    List<Player> findAllByParams(
            @Param("name") String name,
            @Param("title") String title,
            @Param("race") Race race,
            @Param("profession") Profession profession,
            @Param("after") Date after,
            @Param("before") Date Before,
            @Param("banned") Boolean banned,
            @Param("minExperience") Integer minExperience,
            @Param("maxExperience") Integer maxExperience,
            @Param("minLevel") Integer minLevel,
            @Param("maxLevel") Integer maxLevel,
            Pageable pageable);

}
