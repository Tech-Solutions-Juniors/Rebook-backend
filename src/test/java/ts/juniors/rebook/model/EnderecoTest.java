package ts.juniors.rebook.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnderecoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidEndereco() {
        Endereco endereco = new Endereco(null, "Rua das Flores", "São Paulo", "SP", "12345-678", "100", "Apto 1");

        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);

        assertTrue(violations.isEmpty(), "A entidade Endereco não deve ter violações de validação");
    }

    @Test
    public void testInvalidEndereco() {
        Endereco endereco = new Endereco(null, "", "", "", "", "", "");

        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);

        assertEquals(6, violations.size(), "A entidade Endereco deve ter 6 violações de validação");
    }
}
