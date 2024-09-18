package com.cuenta_movimientos.cuenta_movimientos.data;


import com.cuenta_movimientos.cuenta_movimientos.model.pojo.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementsRepository extends JpaRepository<Movement,Long> {


}
