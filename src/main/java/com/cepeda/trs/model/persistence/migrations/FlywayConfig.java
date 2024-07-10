package com.cepeda.trs.model.persistence.migrations;

import org.flywaydb.core.Flyway;

/**
 *
 * @author CyborgK27
 */
public class FlywayConfig {
    public void migrateDatabase() {
        Flyway flyway = Flyway.configure()
            .dataSource("jdbc:sqlite:trs.ddb", null, null)
            .locations("classpath:db/migration")  // Ruta donde se encuentran los scripts SQL
            .load();
        
        // Ejecuta las migraciones
        flyway.migrate();
    }
}
