package cn.fyzzz.panserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.fyzzz.panserver.mapper")
@SpringBootApplication
public class PanServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanServerApplication.class, args);
    }

}
