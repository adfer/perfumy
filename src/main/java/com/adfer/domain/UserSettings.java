package com.adfer.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@Getter
@Component
public class UserSettings {

    @Value("${user.settings.showPrices}")
    private boolean showPrices;
}
