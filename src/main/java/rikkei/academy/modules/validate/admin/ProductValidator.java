package rikkei.academy.modules.validate.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rikkei.academy.modules.products.service.IProductService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class ProductValidator implements ConstraintValidator<ProductUnique,String> {
    @Autowired
    private IProductService productService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {


        return  !productService.existByName(value);
    }
}
