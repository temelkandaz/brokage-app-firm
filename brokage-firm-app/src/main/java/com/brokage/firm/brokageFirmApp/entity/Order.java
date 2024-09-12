package com.brokage.firm.brokageFirmApp.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="asset_name", length=25, nullable=false)
    private String assetName;

    @Column(name="order_side", nullable=false)
    @Enumerated(EnumType.ORDINAL)
    private OrderSide orderSide;

    @Column(name="size", nullable=false)
    private int size;

    @Column(name="price", nullable=false)
    private int price;

    @Column(name="currency", nullable=false)
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;

    @Column(name="order_status", nullable=false)
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_id", referencedColumnName = "id")
    @JsonManagedReference
    private Asset asset;
}
