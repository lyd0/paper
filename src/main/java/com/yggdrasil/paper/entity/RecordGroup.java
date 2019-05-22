package com.yggdrasil.paper.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/*
* record group实体
* */
@Entity
public class RecordGroup {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private Status Status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="record_group_id")
    private List<Record> records;

    @Column
    @UpdateTimestamp
    private Date updated;

    @Column
    @CreationTimestamp
    private Date created;

    public enum Status {
        GENERATING, DONE
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RecordGroup.Status getStatus() {
        return Status;
    }

    public void setStatus(RecordGroup.Status status) {
        Status = status;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
