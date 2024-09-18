package com.cuenta_movimientos.cuenta_movimientos.data;


import com.cuenta_movimientos.cuenta_movimientos.model.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByNumeroCuenta(String numeroCuenta);
}
