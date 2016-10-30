package com.adfer.repository;

import com.adfer.domain.OrderHeader;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by adrianferenc on 07.10.2016.
 */
public interface OrderHeaderRepository extends CrudRepository<OrderHeader, Integer> {
}
