package com.github.kodomo.dsmpayments.domain.booth.repository;

import com.github.kodomo.dsmpayments.domain.booth.entity.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends CrudRepository<Menu, Integer> {
    List<Menu> findAllByBoothId(String boothId);
}
