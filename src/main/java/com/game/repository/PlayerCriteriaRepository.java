package com.game.repository;


import com.game.entity.Player;
import com.game.entity.PlayerSearchCriteria;
import com.game.service.PageableImpl;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerCriteriaRepository {

    @PersistenceContext
    @Qualifier(value = "entityManager")
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public PlayerCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Player> findAllByParams(PageableImpl pageableImpl, PlayerSearchCriteria playerSearchCriteria) {
        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
        Root<Player> playerRoot = criteriaQuery.from(Player.class);
        Predicate predicate = getPredicate(playerSearchCriteria, playerRoot);
        criteriaQuery.where(predicate);
        setOrder(pageableImpl, criteriaQuery, playerRoot);
        TypedQuery<Player> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageableImpl.getPageNumber() * pageableImpl.getPageSize());
        typedQuery.setMaxResults(pageableImpl.getPageSize());
        Pageable pageable = getPage(pageableImpl);
        Long playersCount = getPlayersCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), pageable, playersCount).getContent();
    }

    public Long countOfAllByParams(PageableImpl pageableImpl, PlayerSearchCriteria playerSearchCriteria) {
        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
        Root<Player> playerRoot = criteriaQuery.from(Player.class);
        Predicate predicate = getPredicate(playerSearchCriteria, playerRoot);
        criteriaQuery.where(predicate);
        setOrder(pageableImpl, criteriaQuery, playerRoot);
        TypedQuery<Player> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageableImpl.getPageNumber() * pageableImpl.getPageSize());
        typedQuery.setMaxResults(pageableImpl.getPageSize());
        return getPlayersCount(predicate);
    }

    private Predicate getPredicate(PlayerSearchCriteria playerSearchCriteria, Root<Player> playerRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (playerSearchCriteria.getName() != null) {
            predicates.add(criteriaBuilder.like(playerRoot.get("name"), "%" + playerSearchCriteria.getName() + "%"));
        }
        if (playerSearchCriteria.getTitle() != null) {
            predicates.add(criteriaBuilder.like(playerRoot.get("title"), "%" + playerSearchCriteria.getTitle() + "%"));
        }
        if (playerSearchCriteria.getRace() != null) {
            predicates.add(criteriaBuilder.equal(playerRoot.get("race"), playerSearchCriteria.getRace()));
        }
        if (playerSearchCriteria.getProfession() != null) {
            predicates.add(criteriaBuilder.equal(playerRoot.get("profession"), playerSearchCriteria.getProfession()));
        }
        if (playerSearchCriteria.getBanned() != null) {
            predicates.add(criteriaBuilder.equal(playerRoot.get("banned"), playerSearchCriteria.getBanned()));
        }
        if (playerSearchCriteria.getAfter() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(playerRoot.get("birthday"), playerSearchCriteria.getAfter()));
        }
        if (playerSearchCriteria.getBefore() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(playerRoot.get("birthday"), playerSearchCriteria.getBefore()));
        }
        if (playerSearchCriteria.getMaxExperience() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(playerRoot.get("experience"), playerSearchCriteria.getMaxExperience()));
        }
        if (playerSearchCriteria.getMinExperience() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(playerRoot.get("experience"), playerSearchCriteria.getMinExperience()));
        }
        if (playerSearchCriteria.getMaxLevel() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(playerRoot.get("level"), playerSearchCriteria.getMaxLevel()));
        }
        if (playerSearchCriteria.getMinLevel() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(playerRoot.get("level"), playerSearchCriteria.getMinLevel()));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }

    private void setOrder(PageableImpl pageable, CriteriaQuery<Player> criteriaQuery, Root<Player> playerRoot) {
        if (pageable.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(playerRoot.get(pageable.getOrder().getFieldName())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(playerRoot.get(pageable.getOrder().getFieldName())));
        }

    }

    private Pageable getPage(PageableImpl pageableImpl) {
        Sort sort = Sort.by(pageableImpl.getSortDirection(), pageableImpl.getOrder().getFieldName());
        return PageRequest.of(pageableImpl.getPageNumber(), pageableImpl.getPageSize(), sort);

    }

    private Long getPlayersCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Player> countRoot = countQuery.from(Player.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }


}
