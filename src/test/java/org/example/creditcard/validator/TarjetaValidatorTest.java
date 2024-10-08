package org.example.creditcard.validator;

import io.vavr.control.Either;
import org.example.creditcard.errors.TarjetaErrors;
import org.example.models.TarjetaCredito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TarjetaValidatorTest {
    TarjetaValidator tarjetaValidator = new TarjetaValidator();
    TarjetaCredito tarjeta = TarjetaCredito.builder()
            .id(UUID.fromString("3ec260b3-820c-496a-8620-9fe3b0b016b3"))
            .numero("4257 4600 0460 1637")
            .nombreTitular("John Doe")
            .clientID(UUID.fromString("3ec260b3-820c-496a-8620-9fe3b0b016b3"))
            .fechaCaducidad("12/25")
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .isDeleted(false)
            .build();
    @Test
    void validarTarjetaCreditoOk() {
        Either<TarjetaErrors, TarjetaCredito> resultado = tarjetaValidator.validarTarjetaCredito(tarjeta);
        assertTrue(resultado.isRight());
        assertEquals(tarjeta, resultado.get());
    }

    @Test
    void validarTarjetaCreditoNumeroInvalido() {
        tarjeta.setNumero("4257 4600 0460 1638");
        Either<TarjetaErrors, TarjetaCredito> resultado = tarjetaValidator.validarTarjetaCredito(tarjeta);
        assertTrue(resultado.isLeft());
        assertEquals("Número de tarjeta inválido", resultado.getLeft().getMessage());
    }

    @Test
    void validarTarjetaCreditoNumeroNotDigit(){
        tarjeta.setNumero("lalalalala");
        Either<TarjetaErrors, TarjetaCredito> resultado = tarjetaValidator.validarTarjetaCredito(tarjeta);
        assertTrue(resultado.isLeft());
        assertEquals("Número de tarjeta inválido", resultado.getLeft().getMessage());
    }

    @Test
    void validarTarjetaCreditofechaCaducidadInvalida() {
        tarjeta.setFechaCaducidad("12/20");
        Either<TarjetaErrors, TarjetaCredito> resultado = tarjetaValidator.validarTarjetaCredito(tarjeta);
        assertTrue(resultado.isLeft());
        assertEquals("Fecha de caducidad inválida", resultado.getLeft().getMessage());
    }

    @Test
    void validarTarjetaCreditofechaCaducidadFormatoIncorrecto() {
        tarjeta.setFechaCaducidad("12-20");
        Either<TarjetaErrors, TarjetaCredito> resultado = tarjetaValidator.validarTarjetaCredito(tarjeta);
        assertTrue(resultado.isLeft());
        assertEquals("Fecha de caducidad inválida", resultado.getLeft().getMessage());
    }


}