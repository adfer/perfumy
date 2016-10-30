package com.adfer.repository;

import com.adfer.domain.OrderDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by adrianferenc on 10.10.2016.
 */
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderHeaderId(Integer orderHeaderId);

    void deleteByOrderHeaderId(Integer orderHeaderId);

    int countByOrderHeaderId(Integer orderHeaderId);
}
