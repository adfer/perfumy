package com.adfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by adrianferenc on 07.10.2016.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderHeader {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne(targetEntity = Customer.class, fetch= FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;
}
