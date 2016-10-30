package com.adfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by adrianferenc on 10.10.2016.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer orderHeaderId;
    @OneToOne(targetEntity=Perfume.class, fetch= FetchType.EAGER)
    @JoinColumn(name="perfume_id")
    private Perfume perfume;
    private Integer quantity;
}
