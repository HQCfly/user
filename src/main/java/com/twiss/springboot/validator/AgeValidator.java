package com.twiss.springboot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author: Twiss
 * @Date: 2021/3/6 4:49 下午
 */
public class AgeValidator implements ConstraintValidator<Age,Integer> {
    private Age age;
    private Integer max;
    private Integer min;

    public void initialize(Age age) {
        this.age = age;
        this.max = age.max();
        this.min = age.min();
    }

    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value < max && value>min;
    }

}
