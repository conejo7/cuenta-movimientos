package com.cuenta_movimientos.cuenta_movimientos.service;

import com.cuenta_movimientos.cuenta_movimientos.data.AccountRepository;
import com.cuenta_movimientos.cuenta_movimientos.data.MovementRepository;
import com.cuenta_movimientos.cuenta_movimientos.model.pojo.Account;
import com.cuenta_movimientos.cuenta_movimientos.model.pojo.Movement;
import com.cuenta_movimientos.cuenta_movimientos.model.request.MovementRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MovementServiceImpl implements MovementService {


    private final MovementRepository movementRepository;

    private final AccountRepository accountRepository;


    public MovementServiceImpl(MovementRepository movementRepository, AccountRepository accountRepository) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Movement> getMovements(){
        List<Movement> movements = movementRepository.findAll();
        return movements.isEmpty() ? null : movements;
    }

    @Override
    public Movement getMovement(String movementId) {
        return movementRepository.findById(Long.valueOf(movementId)).orElse(null);
    }

    @Override
    public Boolean removeMovement(String movementId) {

        Movement account = movementRepository.findById(Long.valueOf(movementId)).orElse(null);

        if (account != null) {
            movementRepository.delete(account);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Movement createMovement(MovementRequest request) {

        if (request != null && StringUtils.hasLength(request.getTypeMovement().trim())
                && StringUtils.hasLength(String.valueOf(request.getValue()))
                && StringUtils.hasLength(String.valueOf(request.getBalance()))) {

            Optional<Account> optionalAccount = accountRepository.findByNumeroCuenta(request.getAccountRequest().getNumberAccount());
            Account account;
            if (optionalAccount.isPresent()) {
                account = optionalAccount.get();
            } else {
                account = Account.builder()
                        .numeroCuenta(request.getAccountRequest().getNumberAccount())
                        .tipoCuenta(request.getAccountRequest().getTypeAccount())
                        .saldoInicial(request.getAccountRequest().getInitialBalance())
                        .estado(request.getAccountRequest().isState())
                        .build();
                account = accountRepository.save(account);
            }
            Movement movement = Movement.builder().fecha(request.getDateTime()).tipoMovimiento(request.getTypeMovement())
                    .valor(request.getValue()).saldo(request.getBalance()).account(account).build();

            return movementRepository.save(movement);
        } else {
            return null;
        }
    }

    @Override
    public Movement updateMovement(String movementId, MovementRequest movementRequest) {
        Movement movement = movementRepository.findById(Long.valueOf(movementId)).orElse(null);
        if (movement !=null){
            movement.setFecha(movementRequest.getDateTime());
            movement.setTipoMovimiento(movementRequest.getTypeMovement());
            movement.setValor(movementRequest.getValue());
            movement.setSaldo(movementRequest.getBalance());

            movementRepository.save(movement);
        }
        return movement;
    }


}
