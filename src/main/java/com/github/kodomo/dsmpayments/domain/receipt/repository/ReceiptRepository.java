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

    @Query(value = "select r.* from tbl_receipt as r join tbl_user as u join tbl_booth as b " +
            "where u.user_name like ?1 or u.user_number like ?1 or b.booth_id like ?1 or b.booth_name like ?1 " +
            "group by r.id", nativeQuery = true)
    List<Receipt> findAllByQuery(String query);

    List<Receipt> findAllByUser(User user);
    Page<Receipt> findAllByBooth(Booth booth, Pageable pageable);

    @Query(
            value = "select count(r.booth_id) from ( SELECT distinct booth_id from tbl_receipt where user_number = ?1) as r"
            , nativeQuery = true)
    Integer countBoothsUsedByUser(User user);

    @Query(
            value = "select count(r.user_number) from ( SELECT distinct user_number from tbl_receipt where booth_id = ?1) as r"
            , nativeQuery = true)
    Integer countUserByBooth(Booth booth);

    @Query(value = "SELECT sum(requested_value) FROM tbl_receipt group by HOUR(created_at)", nativeQuery = true)
    List<Long> userCoinUseOfHour();

}
