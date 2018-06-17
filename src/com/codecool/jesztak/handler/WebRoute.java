package com.codecool.jesztak.handler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //can be read reflectively at run-time
@Target(ElementType.METHOD) //can use in method only.
public @interface WebRoute {

    String path() default "/test";

}
