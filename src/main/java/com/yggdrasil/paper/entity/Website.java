package com.yggdrasil.paper.entity;

import javax.persistence.*;

/*
* website实体
* */

@Entity
public class Website {
    @Id
    private String url;

    @Column
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Type type;

    private enum Type {
        NORMAL
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

