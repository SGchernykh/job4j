package ru.job4j.config;

/**
 * Configuration.
 * SpringRootConfig.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("ru.job4j")
@ImportResource("classpath*:*spring-context.xml")
public class SpringRootConfig {
}
