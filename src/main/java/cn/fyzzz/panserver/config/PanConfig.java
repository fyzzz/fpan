package cn.fyzzz.panserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author fyzzz
 * 2021/8/5 11:51 上午
 */
@Data
@Component
@ConfigurationProperties(prefix = "pan")
public class PanConfig {

    private String storageType;

    private LocalStorageConfig localStorage;

    private DatabaseConfig database;

    @Data
    static class DatabaseConfig {

        private String host;

        private Integer port;

        private String name;

        private String username;

        private String password;

    }


}
