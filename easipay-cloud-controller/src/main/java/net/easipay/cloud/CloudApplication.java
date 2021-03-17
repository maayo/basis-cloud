package net.easipay.cloud;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "net.easipay.*")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "net.easipay.cloud",
        "net.easipay.support.api"})
@EnableApolloConfig
@MapperScan(basePackages = "net.easipay.cloud.mapper")
public class CloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
    }

}
