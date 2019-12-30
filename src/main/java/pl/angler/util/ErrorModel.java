package pl.angler.util;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorModel {

    private Map<String, String> errors = new HashMap<>();

    public ErrorModel(BindingResult bindingResult) {
        bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();

                this.errors.put(fieldName, errorMessage);
        });
    }

}
