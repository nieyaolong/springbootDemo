package com.example.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseModel implements Comparable<BaseModel>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private Date updateTime;

    @PrePersist
    public void prePersist() {
        createTime = updateTime = new Date();
    }

    @PreUpdate
    public void preUpdated() {
        updateTime = new Date();
    }


    @Override
    public int compareTo(BaseModel o) {
        return this.getId().compareTo(o.getId());
    }
}
