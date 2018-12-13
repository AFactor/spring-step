package com.rbs.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DemoApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
    JdbcTemplate jdbcTemplate;

	@Override
    public void run(String... strings) throws Exception {

        

        jdbcTemplate.execute("DROP TABLE Sample_Response IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE Sample_Response(" +
                "id INT, name VARCHAR(255))");
	}
}
