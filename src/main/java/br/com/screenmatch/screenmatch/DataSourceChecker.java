package br.com.screenmatch.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@Component
public class DataSourceChecker implements CommandLineRunner {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public DataSourceChecker(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== DataSourceChecker starting ===");
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData md = conn.getMetaData();
            System.out.println("Driver name: " + md.getDriverName());
            System.out.println("Driver version: " + md.getDriverVersion());
            System.out.println("Database product: " + md.getDatabaseProductName());
            System.out.println("Database version: " + md.getDatabaseProductVersion());
        } catch (Exception e) {
            System.err.println("Erro ao abrir conexão:");
            e.printStackTrace();
        }

        try {
            Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            String version = jdbcTemplate.queryForObject("SELECT version()", String.class);
            System.out.println("SELECT 1 -> " + one);
            System.out.println("PostgreSQL version -> " + version);
        } catch (Exception e) {
            System.err.println("Erro ao executar query:");
            e.printStackTrace();
        }
        System.out.println("=== DataSourceChecker finished ===");
    }
}
