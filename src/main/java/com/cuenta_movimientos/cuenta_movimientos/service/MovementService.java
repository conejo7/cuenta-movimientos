package com.cuenta_movimientos.cuenta_movimientos.service;


import com.cuenta_movimientos.cuenta_movimientos.model.pojo.Movement;
import com.cuenta_movimientos.cuenta_movimientos.model.request.MovementRequest;

import java.util.List;

public interface MovementService {


    List<Movement> getMovements();

    Movement getMovement(String accountId);

    Boolean removeMovement(String accountId);

    Movement createMovement(MovementRequest request);

    Movement updateMovement(String clientId, MovementRequest movementRequest);
}
