package com.example.ecommerceJava2.Config;


import com.example.ecommerceJava2.Model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable) // co sua (...)
                .authorizeRequests(auth -> auth // bo ..http.. va antMatcher
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .antMatchers("/home/**","/auth/login","/auth/register","/search","/test/**","/userLogin").permitAll()
                        .antMatchers("/admin/**").hasAnyRole(Role.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/auth/login") // Định nghĩa trang đăng nhập
                        .loginProcessingUrl("/userLogin")
                        .defaultSuccessUrl("/home/") // Điều hướng sau khi đăng nhập thành công
                        .permitAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
