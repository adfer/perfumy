package com.adfer.controllers;

import com.adfer.domain.Perfume;
import com.adfer.domain.UserSettings;
import com.adfer.enums.PerfumeCategory;
import com.adfer.services.PerfumeService;
import com.adfer.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@Controller
@RequestMapping("perfumes")
public class PerfumesController {

    private final UserSettings settings;
    private PerfumeService perfumeService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public PerfumesController(PerfumeService perfumeService, UserSettings settings, ShoppingCartService shoppingCartService) {
        this.perfumeService = perfumeService;
        this.settings = settings;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping()
    public String getAll(){
        return "redirect:/perfumes/women";
    }

    @RequestMapping("{category}")
    public String getAllOfCategory(@PathVariable String category, Pageable pageable, Model model) {
        PerfumeCategory perfumeCategory = PerfumeCategory.WOMEN;
        if(!StringUtils.isEmpty(category)){
            perfumeCategory = getPerfumeCategory(category);
        }

        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name");

        Page<Perfume> perfumes = perfumeService.getAll(perfumeCategory, pageRequest);
        long allPerfumesCount = perfumes.getTotalElements();

        model.addAttribute("perfumes", perfumes);
        model.addAttribute("category", perfumeCategory.toString());
        model.addAttribute("settings", settings);
        model.addAttribute("shoppingCart", shoppingCartService);
        model.addAttribute("pageCount", allPerfumesCount / pageable.getPageSize());
        model.addAttribute("pageable", pageable);
        return "perfumes";
    }

    @RequestMapping("{category}/{name}")
    public String findByNameContaining(@PathVariable String category, @PathVariable String name, Pageable pageable, Model model) {
        PerfumeCategory perfumeCategory = getPerfumeCategory(category);

        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name");

        Page<Perfume> perfumes = perfumeService.findByNameContaining(perfumeCategory, name, pageRequest);
        long allPerfumesCount = perfumes.getTotalElements();

        model.addAttribute("perfumes", perfumes);
        model.addAttribute("category", perfumeCategory.toString());
        model.addAttribute("settings", settings);
        model.addAttribute("shoppingCart", shoppingCartService);
        model.addAttribute("pageCount", allPerfumesCount / pageable.getPageSize());
        model.addAttribute("pageable", pageable);
        return "perfumes";
    }

    private PerfumeCategory getPerfumeCategory(@PathVariable String category) {
        PerfumeCategory perfumeCategory;
        switch (category.toLowerCase()) {
            case "women":
                perfumeCategory = PerfumeCategory.WOMEN;
                break;
            case "men":
                perfumeCategory = PerfumeCategory.MEN;
                break;
            default:
                throw new RuntimeException("Not found category " + category);
        }
        return perfumeCategory;
    }

}
