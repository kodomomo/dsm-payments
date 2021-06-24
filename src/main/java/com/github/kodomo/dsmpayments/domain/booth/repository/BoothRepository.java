package com.github.kodomo.dsmpayments.domain.booth.repository;

import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import org.springframework.data.repository.CrudRepository;

public interface BoothRepository extends CrudRepository<Booth, String> {
}
