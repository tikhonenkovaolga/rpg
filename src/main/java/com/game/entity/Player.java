package com.game.entity;

import com.game.entity.Profession;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "player")
public class Player {

    @Id
    private Long id; // ID игрока

    @Column(name = "name", length = 12)
    private String name; //Имя персонажа (до 12 знаков включительно)

    @Column(name = "title", length = 30)
    private String title; // Титул персонажа (до 30 знаков включительно)

    @Column(name = "race")
    private Race race; // Расса персонажа

    @Column(name = "profession")
    private com.game.entity.Profession profession; // Профессия персонажа

    @Column(name = "experience", length = 10000000)
    private Integer experience; // Опыт персонажа. Диапазон значений 0..10,000,000

    @Column(name = "level")
    private Integer level; // Уровень персонажа

    @Column(name = "untilNextLevel")
    private Integer untilNextLevel; // Остаток опыта до следующего уровня

    @Column(name = "birthday")
    private Date birthday; // Дата регистрации
    //Диапазон значений года 2000..3000 включительно

    @Column(name = "banned")
    private Boolean banned; // Забанен / не забанен

    public Player() {
    }

    public void setId(Long id) {
        if (id > 0) {
            this.id = id;
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public void setBirthday(Date birthday) {
        if (birthday.after(new GregorianCalendar(2000, 1, 1).getTime()) && birthday.before(new GregorianCalendar(3000, 1, 1).getTime())) {
            this.birthday = birthday;
        }

    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", experience=" + experience +
                ", level=" + level +
                ", untilNextLevel=" + untilNextLevel +
                ", birthday=" + birthday +
                ", banned=" + banned +
                '}';
    }
}
