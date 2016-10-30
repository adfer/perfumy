package com.adfer.repository;

import com.adfer.domain.Perfume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@Repository
public interface PerfumeRepository extends CrudRepository<Perfume, Integer> {
    Page<Perfume> findAllByCategory(String category, Pageable pageable);

    Long countByCategory(String perfumeCategory);

    Page<Perfume> findByCategoryAndNameContaining(String category, String name, Pageable pageable);
}
