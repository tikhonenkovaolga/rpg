package com.game.entity;

import java.util.Date;

public class PlayerSearchCriteria {

 //   private Long id;
    private String name; //Имя персонажа (до 12 знаков включительно)
    private String title; // Титул персонажа (до 30 знаков включительно)
    private Race race; // Раса персонажа
    private Profession profession;
    private Date after;
    private Date before;
    private Boolean banned;
    private Integer minExperience;
    private Integer maxExperience;
    private Integer minLevel;
    private Integer maxLevel;

    public PlayerSearchCriteria(String name, String title, Race race, Profession profession,
                                Date after, Date before, Boolean banned, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.after = after;
        this.before = before;
        this.banned = banned;
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    //    public void setId(Long id) {
//        this.id = id;
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

    public void setBanned(Boolean banned) {
        this.banned = banned;
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

    public Integer getMinExperience() {
        return minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public Date getAfter() {
        return after;
    }

    public Date getBefore() {
        return before;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public void setAfter(Date after) {
        this.after = after;
    }

    public void setBefore(Date before) {
        this.before = before;
    }

    public Boolean getBanned() {
        return banned;
    }
//    public Long getId() {
//        return id;
//    }


}
