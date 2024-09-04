package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
    @Autowired
    private ApplicationContext context;
    public static  ApplicationContext myContext;
    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if(context==null) return true; //returns true if product "context" is valid.
        if(context!=null)myContext=context; //myContext now holds the reference
        ProductService repo = myContext.getBean(ProductServiceImpl.class);//Retrieves "ProductService" bean from application context
        if (product.getId() != 0) {//Checks to see if product exists.
            Product myProduct = repo.findById((int) product.getId()); // Gets existing product based on ID
            for (Part pair : myProduct.getParts()) {//loop through each part associated with Product.
                if (pair.getInv()<(product.getInv()-myProduct.getInv())){ //checks if there are enough parts to meet the needs of the updated product inventory.
                    constraintValidatorContext.disableDefaultConstraintViolation();//allows custom error messages
                    constraintValidatorContext.buildConstraintViolationWithTemplate("Error! Insufficient inventory for requested part: " + pair.getName()).addConstraintViolation();
                    return false;//Product is not valid because not enough inventory.
                }
            }
            return true; //Closes the loop indicating there is enough parts for the product
        }
        return false; // New product without an ID. Can't be validated.

    }
}
