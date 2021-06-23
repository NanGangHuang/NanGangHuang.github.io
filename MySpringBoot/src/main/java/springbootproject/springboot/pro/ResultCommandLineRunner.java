package springbootproject.springboot.pro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ResultCommandLineRunner implements CommandLineRunner, EnvironmentAware {

    private Environment env;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(env.getProperty("com.statestreet.url"));
        System.out.println(env.getProperty("com.statestreet.url2"));
        System.out.println(env.getProperty("com.statestreet.url3"));
        System.out.println(env.getProperty("com.statestreet.url4"));
        System.out.println(env.getProperty("com.statestreet.url5"));
        System.out.println(env.getProperty("statestreet.avg.age"));
        System.out.println(env.getProperty("statestreet.path"));
    }

    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }
}
