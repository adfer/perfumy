package com.adfer.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by adrianferenc on 29.10.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppSettings {

    public static final Integer DEFAULT_SETTINGS_ID = 1;

    @Id
    @Setter(AccessLevel.NONE)
    private Integer id=DEFAULT_SETTINGS_ID;
    private Boolean available;

}
