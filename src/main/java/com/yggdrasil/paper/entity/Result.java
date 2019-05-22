package com.yggdrasil.paper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/*
* result实体
* */
@Entity
public class Result {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private Type type;

    @Column(length = 4096)
    private String result;

    @Column
    private Date created;

    @Column
    private Date updated;

    public enum Type {
        MOST_VISITED,
        BOY_MOST_VISITED, GIRL_MOST_VISITED,
        MOST_VISITED_BETWEEN, MAN_MOST_VISITED_BETWEEN, GIRL_MOST_VISITED_BETWEEN,
        MOST_VISITED_INTERVAL_10,
        MOST_VISITED_STUNO_BETWEEN,
        MAN_VISITED_SAME_WEBSITE_AT_THE_SAME_TIME,
        GIRL_VISITED_SAME_WEBSITE_AT_THE_SAME_TIME
    }

    public Result(Type type, String result) {
        this.type = type;
        this.result = result;
    }

    public Result() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
