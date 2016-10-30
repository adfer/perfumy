package com.adfer.services.impl;

import com.adfer.domain.AppSettings;
import com.adfer.repository.AppSettingsRepository;
import com.adfer.services.AppSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.stereotype.Service;


/**
 * Created by adrianferenc on 29.10.2016.
 */
@Service
public class AppSettingsServiceImpl implements AppSettingsService {

    @Autowired
    private AppSettingsRepository appSettingsRepository;

    @Override
    public boolean isAvailable() {
        return appSettingsRepository.findFirstByAvailable(Boolean.TRUE).isPresent();
    }

    @Override
    public void setDefaultSettings() {
        AppSettings defaultSettings = new AppSettings(AppSettings.DEFAULT_SETTINGS_ID, Boolean.FALSE);
        appSettingsRepository.save(defaultSettings);
    }

    public boolean updateSettings(AppSettings appSettings){
        appSettingsRepository.save(appSettings);
        return appSettings.getAvailable();
    }
}
