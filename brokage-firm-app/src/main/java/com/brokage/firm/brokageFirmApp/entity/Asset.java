package com.brokage.firm.brokageFirmApp.entity;

import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="asset")
public class Asset {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="asset_name", length=25, nullable=false)
    private String assetName;

    @Column(name="currency", nullable=false)
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;

    @Column(name="size", nullable=false)
    private int size;

    @Column(name="usable_size", nullable=false)
    private int usableSize;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    private Customer customer;

    @OneToMany
    @JsonBackReference
    private Set<Order> order;
}
