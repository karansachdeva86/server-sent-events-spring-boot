package com.hexor.reandroid.persistence.dialect;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by karan on 23/4/15.
 */
@Configuration
@Profile("MySQLInnoDB")
public class MySQLInnoDBDialect implements Dialect{
    @Override
    public String getDialect() {
        return "org.hibernate.dialect.MySQLInnoDBDialect";
    }
}
