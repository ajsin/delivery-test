package com.delivery.api.domain.delivery.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tblOrder")
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer orderId;

    @Column(length = 25)
    private String uid;

    @Column
    private Integer shopId;

    @Column(length = 1000)
    private String address;

    @Column
    private boolean isModifiable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date orderDate;
}
