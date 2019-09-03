package com.stone.config.swagger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableSwagger2Doc {

  /**
   * Controls debugging support for Spring Security. Default is false.
   *
   * @return if true, enables debug support with Spring Security
   */
  boolean debug() default false;
}
