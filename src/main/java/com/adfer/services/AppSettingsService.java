package com.adfer.services;

import com.adfer.domain.AppSettings;

/**
 * Created by adrianferenc on 29.10.2016.
 */
public interface AppSettingsService {

    boolean isAvailable();

    void setDefaultSettings();

    boolean updateSettings(AppSettings appSettings);
}
