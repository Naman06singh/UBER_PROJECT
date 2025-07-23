package com.codingshuttle.project.uber.uberApplication.services;

import com.codingshuttle.project.uber.uberApplication.dto.WalletTransactionDto;
import com.codingshuttle.project.uber.uberApplication.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
