package team.a501.rif.config.Jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import team.a501.rif.exception.ErrorCode;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

//@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {

        return ((request, response, accessDeniedException) -> {
            String fail = "Login Require";

            ObjectMapper ob = new ObjectMapper();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            String json = ob.writeValueAsString(Map.of("msg",fail));
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        });
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return ((request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("UNAUTHORIZED");
            response.getWriter().flush();
            response.getWriter().close();
        });
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/assets/**", "/h2-console/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                .antMatchers("/api/v/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
