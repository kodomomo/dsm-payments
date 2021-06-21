package com.github.kodomo.dsmpayments.domain.receipt.entity;

import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.seller.entity.Seller;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "tbl_receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_number")
    private User user;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Enumerated(EnumType.STRING)
    private ReceiptSender sender;

    private int value;

    @CreatedBy
    private LocalDateTime createdAt;

    private Receipt(User user, Seller seller, ReceiptSender sender, int value) {
        this.user = user;
        this.seller = seller;
        this.sender = sender;
        this.value = value;
    }

    public static Receipt of(ReceiptDTO receiptDTO) {
        return new Receipt(
                receiptDTO.getUser(),
                receiptDTO.getSeller(),
                receiptDTO.getSender(),
                receiptDTO.getValue()
        );
    }

}
