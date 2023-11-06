package com.capitole.ecommerce.infrastructure.adapter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
@NoArgsConstructor
public class Price {
    @Id
    @Column(name = "PRICE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "BRAND_ID", nullable = false)
    private Brand brand;
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    @Column(name = "PRICE_LIST")
    private Long priceList;
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "PRIORITY")
    private Integer priority;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "CURR")
    private String currency;

}
