package com.adfer.services;

import com.adfer.domain.Perfume;
import com.adfer.enums.PerfumeCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by adrianferenc on 05.10.2016.
 */
public interface PerfumeService {
    Page<Perfume> getAll(PerfumeCategory category, Pageable pageable);

    Perfume add(Perfume perfume);

    Perfume update(Perfume perfume);

    Perfume find(Integer perfumeId);

    Page<Perfume> findByNameContaining(PerfumeCategory perfumeCategory, String name, Pageable pageable);

    void loadPerfumes(MultipartFile file);

    long countByCategory(String perfumeCategory);
}
