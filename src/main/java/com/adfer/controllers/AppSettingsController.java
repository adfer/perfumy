package com.adfer.controllers;

import com.adfer.domain.AppSettings;
import com.adfer.services.AppSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by adrianferenc on 29.10.2016.
 */
@RestController
public class AppSettingsController {

    @Autowired
    private AppSettingsService appSettingsService;

    @RequestMapping(value = "/admin/settings/available", method = RequestMethod.GET)
    public ResponseEntity<Boolean> updateAppSettings() {
        return ResponseEntity.ok(appSettingsService.isAvailable());
    }

    @RequestMapping(value = "/admin/settings/available", method = RequestMethod.POST)
    public ResponseEntity<Boolean> updateAppSettings(Boolean available) {
        AppSettings appSettings = new AppSettings();
        appSettings.setAvailable(available);
        boolean isAvailable = appSettingsService.updateSettings(appSettings);
        return ResponseEntity.ok(isAvailable);
    }


}
