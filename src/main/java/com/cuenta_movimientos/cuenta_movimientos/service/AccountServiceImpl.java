package com.cuenta_movimientos.cuenta_movimientos.service;

import com.cuenta_movimientos.cuenta_movimientos.data.AccountRepository;
import com.cuenta_movimientos.cuenta_movimientos.model.pojo.Account;
import com.cuenta_movimientos.cuenta_movimientos.model.request.CreateAccountRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }


    @Override
    public List<Account> getAccounts(){
        List<Account> accounts = accountRepository.findAll();
        return accounts.isEmpty() ? null : accounts;
    }

    @Override
    public Account getAccount(String accountId) {

        return accountRepository.findById(Long.valueOf(accountId)).orElse(null);
    }

    @Override
    public Boolean removeAccount(String accountId) {

        Account account = accountRepository.findById(Long.valueOf(accountId)).orElse(null);

        if (account != null) {
            accountRepository.delete(account);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Account createAccount(CreateAccountRequest request) {

        if (request != null && StringUtils.hasLength(request.getNumberAccount().trim())
                && StringUtils.hasLength(request.getTypeAccount().trim())
                && StringUtils.hasLength(String.valueOf(request.getInitialBalance()))) {

            Account account = Account.builder().numeroCuenta(request.getNumberAccount()).tipoCuenta(request.getTypeAccount())
                    .saldoInicial(request.getInitialBalance()).estado(request.isState()).build();

            return accountRepository.save(account);
        } else {
            return null;
        }
    }

    @Override
    public Account updateAccount(String accountId, CreateAccountRequest clientRequest) {
        Account account = accountRepository.findById(Long.valueOf(accountId)).orElse(null);
        if (account !=null){
            account.setNumeroCuenta(clientRequest.getNumberAccount());
            account.setTipoCuenta(clientRequest.getTypeAccount());
            account.setSaldoInicial(clientRequest.getInitialBalance());
            account.setEstado(clientRequest.isState());

            accountRepository.save(account);
        }
        return account;
    }


}
