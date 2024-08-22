package ts.juniors.rebook.service.validation;

import ts.juniors.rebook.controller.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ts.juniors.rebook.dto.UsuarioInsertDto;
import ts.juniors.rebook.model.Usuario;
import ts.juniors.rebook.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsertValid, UsuarioInsertDto> {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public void initialize(UsuarioInsertValid ann) {
	}

	@Override
	public boolean isValid(UsuarioInsertDto dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Usuario user = repository.findByEmail(dto.getEmail());
		if (user != null) {
			list.add(new FieldMessage("email", "Email já existe"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
