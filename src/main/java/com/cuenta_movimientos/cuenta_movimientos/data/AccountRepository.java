package com.cuenta_movimientos.cuenta_movimientos.data;


import com.cuenta_movimientos.cuenta_movimientos.model.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {


}
