package com.twiss.springboot.domain;

import com.twiss.springboot.validator.Age;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * @Author: Twiss
 * @Date: 2021/3/5 12:19 上午
 */
public class User {

    public interface Save {}
    public interface Update {}

    @Null(groups = Save.class)
    @NotNull(groups = Update.class)
    private Long id;
    @Size(min = 2, max = 20, groups = {Save.class, Update.class})
    private String name;
    // @Range(min = 18, max = 70, groups = {Save.class, Update.class})
    @Age(min = 18, max = 70, groups = {Save.class, Update.class})
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
