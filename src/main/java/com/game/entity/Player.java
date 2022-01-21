package com.game.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID игрока

    @Column(name = "name", length = 12)
    private String name; //Имя персонажа (до 12 знаков включительно)

    @Column(name = "title", length = 30)
    private String title; // Титул персонажа (до 30 знаков включительно)

    @Enumerated(EnumType.STRING)
    @Column(name = "race")
    private Race race; // Раса персонажа

    @Enumerated(EnumType.STRING)
    @Column(name = "profession")
    private Profession profession; // Профессия персонажа

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

    public Player(String name, String title, Race race, Profession profession, Date birthday, Boolean banned, Integer experience, Integer level, Integer untilNextLevel) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
        this.birthday = birthday;
        this.banned = banned;
    }

//  //  public void setId(Long id) {
//            this.id = id;
//    }

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
        this.birthday = birthday;
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
