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

    private LocalStorageConfig localStorage;

    private DatasourceConfig datasource;

    @Data
    public static class DatasourceConfig {

        private String host;

        private Integer port;

        private String name;

        private String username;

        private String password;

    }


    /**
     * @author fyzzz
     * 2021/8/5 2:35 下午
     */
    @Data
    public static class LocalStorageConfig {

        private String rootPath;

    }
}
