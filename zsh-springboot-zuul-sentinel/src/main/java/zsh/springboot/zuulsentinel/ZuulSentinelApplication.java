package zsh.springboot.zuulsentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulSentinelApplication.class, args);
    }

}
