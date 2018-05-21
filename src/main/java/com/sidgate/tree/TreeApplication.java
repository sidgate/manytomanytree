package com.sidgate.tree;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TreeApplication {
  public static void main(String[] args) {
    SpringApplication.run(TreeApplication.class);
  }


  @Bean
  public CommandLineRunner demo(final TreeNodeRepository repository) {
    return args -> {
      Node node = new Node();
      node.setId("123");
      repository.save(node);
    };
  }
}
