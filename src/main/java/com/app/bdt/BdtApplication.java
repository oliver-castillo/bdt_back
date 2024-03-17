package com.app.bdt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BdtApplication {

  public static void main(String[] args) {
    SpringApplication.run(BdtApplication.class, args);
  }

  /*@Bean
  public WebMvcConfigurer corsMappingConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS", "HEAD")
                .maxAge(3600)
                .allowedHeaders("*")
                .exposedHeaders("X-Get-Header");
      }
    };
  }*/

}
