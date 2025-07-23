package com.codingshuttle.project.uber.uberApplication.dto;

import com.codingshuttle.project.uber.uberApplication.entities.Ride;
import com.codingshuttle.project.uber.uberApplication.entities.Wallet;
import com.codingshuttle.project.uber.uberApplication.entities.enums.TransactionMethod;
import com.codingshuttle.project.uber.uberApplication.entities.enums.TransactionType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDto {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDto ride;

    private String transactionId;

    private WalletDto wallet;

    private LocalDateTime timeStamp;

}
