package com.david.wowStockCalculator.dao;

import com.david.wowStockCalculator.domain.Resource;

import java.util.Optional;

public interface ResourceDao {

    void create(Resource resource);

    Optional<Resource> findOne(long l);
}
