package com.adfer.repository;

import com.adfer.domain.AppSettings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by adrianferenc on 29.10.2016.
 */
public interface AppSettingsRepository extends CrudRepository<AppSettings, Integer>{


    Optional<AppSettings> findFirstByAvailable(Boolean available);

}
