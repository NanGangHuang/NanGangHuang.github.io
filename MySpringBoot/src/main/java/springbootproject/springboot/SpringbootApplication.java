package springbootproject.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springbootproject.springboot.initializer.SecondInitializer;

import java.util.Properties;

@SpringBootApplication
@PropertySource({"demo.properties"})
public class SpringbootApplication {

    public static void main(String[] args) {
        /*SpringApplication.run(Sb2Application.class, args);*/
        SpringApplication springApplication = new SpringApplication(SpringbootApplication.class);
        springApplication.addInitializers(new SecondInitializer());
        Properties properties = new Properties();
        properties.setProperty("com.statestreet.url","statestreet_application");
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
    }

}
