package com.delivery.api.domain.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tblUser")
public class User {

    @Id
    @Column(length = 25)
    private String uid;

    @Column(length = 50)
    private String userId;

    @Column(length = 50)
    private String name;

    @Column(length = 256)
    private String password;
}
