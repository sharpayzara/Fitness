package com.nick.bb.fitness.injector.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by sharpay on 17-3-22.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplication {
}

