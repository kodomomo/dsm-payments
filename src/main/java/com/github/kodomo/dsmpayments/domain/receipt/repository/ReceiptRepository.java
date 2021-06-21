package com.github.kodomo.dsmpayments.domain.receipt.repository;

import com.github.kodomo.dsmpayments.domain.receipt.entity.Receipt;
import com.github.kodomo.dsmpayments.domain.seller.entity.Seller;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
    Page<Receipt> findAll(Pageable pageable);
    Page<Receipt> findAllByUser(User user, Pageable pageable);
    Page<Receipt> findAllBySeller(Seller seller, Pageable pageable);
}
