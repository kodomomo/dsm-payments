package com.github.kodomo.dsmpayments.domain.receipt.entity;

import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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
    private Booth booth;

    @Enumerated(EnumType.STRING)
    private ReceiptSender sender;

    private int value;

    @CreatedDate
    private LocalDateTime createdAt;

    private Receipt(User user, Booth booth, ReceiptSender sender, int value) {
        this.user = user;
        this.booth = booth;
        this.sender = sender;
        this.value = value;
    }

    public static Receipt of(ReceiptDTO receiptDTO) {
        return new Receipt(
                receiptDTO.getUser(),
                receiptDTO.getBooth(),
                receiptDTO.getSender(),
                receiptDTO.getValue()
        );
    }

}
