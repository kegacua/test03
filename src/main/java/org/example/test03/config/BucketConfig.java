package org.example.test03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.time.Duration;

@Configuration
public class BucketConfig {

    @Bean
    public Bucket addMoneyBucket() {
        return Bucket.builder()
                .addLimit(
                        Bandwidth.classic(5, Refill.greedy(5, Duration.ofSeconds(10))))
                .build();
    }

    @Bean
    public Bucket transferMoneyBucket() {
        return Bucket.builder()
                .addLimit(
                        Bandwidth.classic(5, Refill.greedy(5, Duration.ofSeconds(10)))) // Tái tạo yêu cầu mỗi phút
                .build();
    }

}
