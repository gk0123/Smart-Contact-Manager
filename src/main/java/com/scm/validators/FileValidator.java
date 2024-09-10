package com.scm.validators;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    @Override
    public void initialize(ValidFile constraintAnnotation) {
        // Initialization logic if needed
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true; // Allow empty files if not required
        }
        
        // Example validation logic
        long maxSize = 2 * 1024 * 1024; // 2MB
        if (file.getSize() > maxSize) {
            context.buildConstraintViolationWithTemplate("File size exceeds the limit of 2MB")
                   .addConstraintViolation()
                   .disableDefaultConstraintViolation();
            return false;
        }
        
        // Additional checks (e.g., file type) can be added here

        return true;
    }
}
