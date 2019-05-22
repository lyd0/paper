package com.yggdrasil.paper.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/*
* result group实体
* */
@Entity
public class ResultGroup {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private Status Status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="result_group_id")
    private List<Result> result;

    @Column
    @UpdateTimestamp
    private Date updated;

    @Column
    @CreationTimestamp
    private Date created;

    private enum Status {
        CALCULATING, DONE
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResultGroup.Status getStatus() {
        return Status;
    }

    public void setStatus(ResultGroup.Status status) {
        Status = status;
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
