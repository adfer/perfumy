package com.adfer.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@Getter
@Setter
@ToString
@Entity
public class Perfume {
    @Id
    @GeneratedValue
    private Integer id;
    private String category;
    private String brand;
    private String name;
    private String capacity;
    private String price;
    private String picture;

    public Perfume() {
        this.picture = "http://image.flaticon.com/icons/svg/27/27092.svg";
    }
}
