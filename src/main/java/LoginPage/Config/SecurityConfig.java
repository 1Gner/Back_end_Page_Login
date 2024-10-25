package LoginPage.Config;

import LoginPage.Security.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private SecurtityDatabase securtityService;


    @Autowired
    public void globalUserDetail(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securtityService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/user").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/save").permitAll()
                        .requestMatchers(HttpMethod.GET,"/user/verify").hasAnyRole("USER","ADMIN")
                        .requestMatchers( "/produto/**").permitAll()
                        .requestMatchers( "/order/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/produto/save").permitAll()
                        .requestMatchers(HttpMethod.POST, "/produto/saveFront").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/produto/findall").permitAll()
                        .requestMatchers(HttpMethod.GET,"/user/all").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/user/{id}").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()

                        .anyRequest().authenticated()

                )
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedOrigin("http://localhost:4200"); // Origem permitida
                    config.addAllowedMethod("*"); // Permite todos os métodos
                    config.addAllowedHeader("*"); // Permite todos os headers
                    config.setAllowCredentials(true); // Permite cookies, etc
                    return config;
                }))
                //.httpBasic(Customizer.withDefaults());
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


}
