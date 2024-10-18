package class4.demoday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories(basePackages = "class4.demoday.global.security.jwt.repository")
public class DemodayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemodayApplication.class, args);
    }

}