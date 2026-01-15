package sammycalle.taco_cloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/design","/orders").hasRole("USER")
            .requestMatchers("/","/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
        )
        .formLogin(form ->form
            .loginPage("/login")
            .defaultSuccessUrl("/design", true)
            .permitAll()
        )
        .logout(logoutCustomizer -> logoutCustomizer
            .logoutSuccessUrl("/")
            .permitAll()
        )
        .build();
    }
}
