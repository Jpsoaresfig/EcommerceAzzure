package com.jp.azzure.infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CorsConfigurationSource corsConfiguration;

    @Autowired
    private SecurityFilter securityFilter;

    SecurityConfig(CorsConfigurationSource corsConfiguration) {
        this.corsConfiguration = corsConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpsSecurity) throws Exception {

        return httpsSecurity
                .cors(cors -> cors.configurationSource(corsConfiguration))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// session
                                                                                                             // stateless
                .authorizeHttpRequests(auth -> auth
                        // .requestMatchers("/v3/api-docs/**", "/swagger-ui/**",
                        // "/swagger-ui.html").permitAll()//REMOVER DEPOS SE NECESSÁRIO
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // login
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // register
                        .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN") // only admin can create
                                                                                        // products

                        .anyRequest().authenticated() // any request

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // add filter
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {// encrypt password
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSources() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000/login"); // front-end que vai acessar
        configuration.addAllowedMethod("*"); // permitir todos os métodos (GET, POST, etc)
        configuration.addAllowedHeader("*"); // permitir todos os headers
        configuration.setAllowCredentials(true); // se precisar enviar cookies, tokens etc

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // para todas as rotas

        return source;
    }

}
