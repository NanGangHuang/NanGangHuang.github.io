package springbootproject.springboot.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

@Order(3)
public class ThirdInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        //environment.setActiveProfiles("uat");
        //Map<String, Object> map = new HashMap<>();
        //map.put("spring.profiles.active", "uat");
        //MapPropertySource mapPropertySource = new MapPropertySource("thirdInitializer", map);
       // environment.getPropertySources().addLast(mapPropertySource);
        //environment.getPropertySources().addFirst(mapPropertySource);
        System.out.println("run thirdInitializer");
    }
}
