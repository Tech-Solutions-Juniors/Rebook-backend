package ts.juniors.rebook.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EnderecoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testEnderecoValido() {
        Endereco endereco = new Endereco(1L, "Rua Teste", "Cidade Teste", "Estado Teste", "12345-678", "123", "Apto 1", null);
        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testEnderecoInvalido() {
        Endereco endereco = new Endereco();
        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);
        assertFalse(violations.isEmpty());
        assertEquals(6, violations.size());
    }

    @Test
    void testEnderecoSemRua() {
        Endereco endereco = new Endereco(1L, "", "Cidade Teste", "Estado Teste", "12345-678", "123", "Apto 1", null);
        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("rua")));
    }

    @Test
    void testEnderecoSemCidade() {
        Endereco endereco = new Endereco(1L, "Rua Teste", "", "Estado Teste", "12345-678", "123", "Apto 1", null);
        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("cidade")));
    }

    @Test
    void testEnderecoSemEstado() {
        Endereco endereco = new Endereco(1L, "Rua Teste", "Cidade Teste", "", "12345-678", "123", "Apto 1", null);
        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("estado")));
    }

    @Test
    void testEnderecoSemCep() {
        Endereco endereco = new Endereco(1L, "Rua Teste", "Cidade Teste", "Estado Teste", "", "123", "Apto 1", null);
        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("cep")));
    }

    @Test
    void testEnderecoSemNumero() {
        Endereco endereco = new Endereco(1L, "Rua Teste", "Cidade Teste", "Estado Teste", "12345-678", "", "Apto 1", null);
        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("numero")));
    }

    @Test
    void testEnderecoSemComplemento() {
        Endereco endereco = new Endereco(1L, "Rua Teste", "Cidade Teste", "Estado Teste", "12345-678", "123", "", null);
        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("complemento")));
    }
}
