package app.gcp.pubsub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class PubsubApplication  {
    @Value("${spring.cloud.gcp.pubsub.credentials.location}")
    private Resource resource;

    public static void main(String[] args) {
        SpringApplication.run(PubsubApplication.class, args);
    }

}
