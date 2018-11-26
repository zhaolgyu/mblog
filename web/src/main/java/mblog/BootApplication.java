package mblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * SprintBootApplication
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@MapperScan("mblog.mapper")//将项目中对应的mapper类的路径加进来就可以了
public class BootApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootApplication.class);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}