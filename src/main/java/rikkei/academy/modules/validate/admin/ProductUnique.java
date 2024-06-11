package rikkei.academy.modules.validate.admin;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = ProductValidator.class
)
public @interface ProductUnique {
    String message() default "Ten da ton tai";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
