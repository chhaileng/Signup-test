package info.chhaileng.app.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfiguration {
	@Bean
	public DataSource localDb() {
		System.out.println("TEST TEST");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/my_db");
		dataSource.setUsername("postgres");
		dataSource.setPassword("toor");
		return dataSource;
	}
}
