package com.codingshuttle.project.uber.uberApplication.services.impl;

import com.codingshuttle.project.uber.uberApplication.dto.WalletTransactionDto;
import com.codingshuttle.project.uber.uberApplication.entities.WalletTransaction;
import com.codingshuttle.project.uber.uberApplication.repositories.WalletTransactionRepository;
import com.codingshuttle.project.uber.uberApplication.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction){
        walletTransactionRepository.save(walletTransaction);
    }
}
