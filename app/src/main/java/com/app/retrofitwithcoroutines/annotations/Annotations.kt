package com.app.daggrhiltdemo.annotations

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class HeaderRetro()

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class SimpleRetro()

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class HeaderRestApi()

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class SimpleRestApi()