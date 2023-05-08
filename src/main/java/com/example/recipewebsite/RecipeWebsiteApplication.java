package com.example.recipewebsite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@EnableJpaRepositories(basePackages = {"com.example.recipewebsite.repository"})
//@SpringBootApplication()
//@ComponentScan({"com.example.recipewebsite.repository", "com.example.recipewebsite.controller",
//        "com.example.recipewebsite.service", "com.example.recipewebsite.security"})
@SpringBootApplication
public class RecipeWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeWebsiteApplication.class, args);
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("*");
//            }
//        };
//    }

}