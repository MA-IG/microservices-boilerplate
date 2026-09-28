package com.mig.product;

import com.mig.product.entity.Product;
import com.mig.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProductRepository repository) {
		return args -> {
			repository.save(new Product(null,"Laptop", 8500));
			repository.save(new Product(null,"Mouse", 250));
			repository.save(new Product(null,"Keyboard", 450));
			repository.save(new Product(null,"Monitor", 2200));
		};
	}
}
