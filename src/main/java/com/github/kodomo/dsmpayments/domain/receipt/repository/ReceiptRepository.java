package com.github.kodomo.dsmpayments.domain.receipt.repository;

import com.github.kodomo.dsmpayments.domain.receipt.entity.Receipt;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

    Page<Receipt> findAll(Pageable pageable);

    @Query(value = "select receipt from tbl_receipt receipt where receipt.user.userNumber like %?1% or receipt.user.userName like %?1%")
    Page<Receipt> findAllByQuery(String query, Pageable pageable);

    Page<Receipt> findAllByUser(User user, Pageable pageable);
    Page<Receipt> findAllByBooth(Booth booth, Pageable pageable);

    @Query(
            value = "select count(r.seller_id) from ( SELECT distinct seller_id from tbl_receipt where user_number = ?1) as r"
            , nativeQuery = true)
    Integer countBoothsUsedByUser(User user);

    @Query(
            value = "select count(r.user_number) from ( SELECT distinct user_number from tbl_receipt where seller_id = ?1) as r"
            , nativeQuery = true)
    Integer countUserByBooth(Booth booth);
}
