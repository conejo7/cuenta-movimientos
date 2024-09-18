package com.cuenta_movimientos.cuenta_movimientos.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {

    private String numberAccount;

    private String typeAccount;

    private Double initialBalance;

    private boolean state;





}
