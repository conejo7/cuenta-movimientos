package com.cuenta_movimientos.cuenta_movimientos.model.pojo;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cuenta")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuenta_id_gen")
    @SequenceGenerator(name = "cuenta_id_gen", sequenceName = "cuenta_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numero_cuenta", nullable = false, length = 20)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta", nullable = false, length = 50)
    private String tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;

    @Column(name = "estado")
    private Boolean estado;

//    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Movement> movimientos;

}