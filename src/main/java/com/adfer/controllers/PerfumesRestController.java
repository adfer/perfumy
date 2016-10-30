package com.adfer.controllers;

import com.adfer.domain.Perfume;
import com.adfer.domain.UserSettings;
import com.adfer.services.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@RestController
@RequestMapping("admin/perfumes")
public class PerfumesRestController {

    private final UserSettings settings;
    private PerfumeService perfumeService;

    @Autowired
    public PerfumesRestController(PerfumeService perfumeService, UserSettings settings) {
        this.perfumeService = perfumeService;
        this.settings = settings;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Perfume add(@RequestBody Perfume perfume) {
        return perfumeService.add(perfume);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Perfume update(@RequestBody Perfume perfume) {
        return perfumeService.update(perfume);
    }

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public void loadPerfumes(@RequestParam("file") MultipartFile file) {
        perfumeService.loadPerfumes(file);
    }


}
