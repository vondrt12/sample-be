package eu.greyson.sample.shared.validators;

import eu.greyson.sample.general.model.Transaction;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class TransactionValidator implements ConstraintValidator<ValidTransaction, Transaction> {
    @Override
    public void initialize(ValidTransaction constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext constraintValidatorContext) {
        return !transaction.getCreditor().equals(transaction.getDebtor());
    }
}
