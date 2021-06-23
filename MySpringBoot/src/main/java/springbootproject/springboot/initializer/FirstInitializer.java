package springbootproject.springboot.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@Order(1)
public class FirstInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void  initialize(ConfigurableApplicationContext configurableApplicationContext) {
        Map<String,String> envMap = System.getenv();
        //System.getProperties();
        //envMap.forEach((k,v) -> System.out.println("key : "+ k + ",value " + v ));
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        Map<String,Object> map = new HashMap<>();
        map.put("server.port","8010");
        MapPropertySource mapPropertySource = new MapPropertySource("firstInitializer",map);
        environment.getPropertySources().addLast(mapPropertySource);
        environment.getProperty("server.port");
        System.out.println(environment.getProperty("server.port"));
        System.out.println("run firstInitializer");
    }
}
