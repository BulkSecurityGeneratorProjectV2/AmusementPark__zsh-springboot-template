package zsh.springboot.mybatisplus.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("multi-ds")
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = "zsh.springboot.mybatisplus.ds0.dao*", sqlSessionFactoryRef = "ds0SqlSessionFactory")
//这个注解，作用相当于下面的@Bean MapperScannerConfigurer，2者配置1份即可
public class Ds0DataSourceConfig {

}
