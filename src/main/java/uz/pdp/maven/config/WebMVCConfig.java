package uz.pdp.maven.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan("uz.pdp.maven")
@Configuration
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer {

}
