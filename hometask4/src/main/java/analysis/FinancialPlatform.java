package analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FinancialPlatform {

    public static void main(String[] args) {
        SpringApplication.run(FinancialPlatform.class, args);
    }
}
