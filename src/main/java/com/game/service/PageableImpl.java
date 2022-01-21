package com.game.service;

import com.game.controller.PlayerOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableImpl implements Pageable {
    private Integer pageNumber = 0;
    private Integer pageSize = 3;
    private PlayerOrder order;
    private Sort.Direction sortDirection = Sort.Direction.ASC;



    public PageableImpl(Integer pageNumber, Integer pageSize, PlayerOrder order) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.order = order;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    public PlayerOrder getOrder() {
        return order;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setOrder(PlayerOrder order) {
        this.order = order;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
