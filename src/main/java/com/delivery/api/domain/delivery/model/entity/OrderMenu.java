package com.delivery.api.domain.delivery.model.entity;

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
@Table(name = "tblOrderMenu")
public class OrderMenu {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer orderMenuId;

    @Column
    private Integer orderId;

    @Column
    private Integer menuId;
}
