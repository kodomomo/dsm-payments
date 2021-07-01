package com.github.kodomo.dsmpayments.domain.booth.repository;

import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoothRepository extends CrudRepository<Booth, String> {

    List<Booth> findAll();

    @Query("select booth from tbl_booth booth where booth.boothName like %?1%")
    List<Booth> findAllBoothStatus(String searchWord);

    @Query(value = "SELECT (sum(coin) / count(*)) as value FROM tbl_booth", nativeQuery = true)
    double allBoothCoinAverage();

}
