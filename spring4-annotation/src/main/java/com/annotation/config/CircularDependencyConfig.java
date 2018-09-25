package com.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@ComponentScan("com.annotation.circulardependency")
public class CircularDependencyConfig {
}
