package Spring;

import Spring.security.auth.AuthenticationService;
import Spring.security.auth.RegisterRequest;
import Spring.service.user.Role;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration
@EnableScheduling
@SpringBootApplication()
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class TPTradingStart {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(TPTradingStart.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var felixCelesty = RegisterRequest.builder()
                    .firstname("Mr")
                    .lastname("Felix")
                    .email("felix@mail.com")
                    .password("felix1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("Felix token: " + service.register(felixCelesty).getAccessToken());
            var felix0 = RegisterRequest.builder()
                    .firstname("Mr")
                    .lastname("Felix0")
                    .email("felix0mail.com")
                    .password("felix1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("Felix0 token: " + service.register(felix0).getAccessToken());

            var felixNew = RegisterRequest.builder()
                    .firstname("Mr")
                    .lastname("FelixNew")
                    .email("felix1@mail.com")
                    .password("felix1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("Felix1 token: " + service.register(felixNew).getAccessToken());

            var felixClaire = RegisterRequest.builder()
                    .firstname("Mr")
                    .lastname("FelixClaire")
                    .email("felix2@mail.com")
                    .password("felix1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("Felix2 token: " + service.register(felixClaire).getAccessToken());

            var felixDev0 = RegisterRequest.builder()
                    .firstname("Mr")
                    .lastname("FelixDev0")
                    .email("felixdev0@mail.com")
                    .password("felix1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("FelixDev0 token: " + service.register(felixDev0).getAccessToken());

            var felixDev1 = RegisterRequest.builder()
                    .firstname("Mr")
                    .lastname("FelixDev1")
                    .email("felixdev1@mail.com")
                    .password("felix1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("FelixDev1 token: " + service.register(felixDev1).getAccessToken());

            var felixDev2 = RegisterRequest.builder()
                    .firstname("Mr")
                    .lastname("FelixDev2")
                    .email("felixdev2@mail.com")
                    .password("felix1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("FelixDev2 token: " + service.register(felixDev2).getAccessToken());


            var prem = RegisterRequest.builder()
                    .firstname("Mr")
                    .lastname("Prem")
                    .email("prem@mail.com")
                    .password("prem1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("Prem token: " + service.register(prem).getAccessToken());

            var william = RegisterRequest.builder()
                    .firstname("Mr")
                    .lastname("William")
                    .email("william@mail.com")
                    .password("william@#1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("William token: " + service.register(william).getAccessToken());


            var narendra = RegisterRequest.builder()
                    .firstname("Mr")
                    .lastname("Narendra")
                    .email("mauryanarendra2003@gmail.com")
                    .password("narendra@#1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("Narendra token: " + service.register(narendra).getAccessToken());
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        CorsFilter filter = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<Filter> disableCSRF() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                CsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
                csrfTokenRepository.saveToken(null, request, response);
                filterChain.doFilter(request, response);
            }
        });
        bean.setOrder(Ordered.LOWEST_PRECEDENCE);
        return bean;
    }

}
