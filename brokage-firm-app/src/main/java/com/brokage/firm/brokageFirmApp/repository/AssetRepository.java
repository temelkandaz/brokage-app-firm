package com.brokage.firm.brokageFirmApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brokage.firm.brokageFirmApp.entity.Asset;

@Repository
public interface AssetRepository extends CrudRepository<Asset, Long> {
    List<Asset> findAll(); 

    Asset findById(long id);
}
