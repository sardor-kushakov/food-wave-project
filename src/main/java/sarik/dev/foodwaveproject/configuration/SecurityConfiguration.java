package sarik.dev.foodwaveproject.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import sarik.dev.foodwaveproject.generic.AppErrorDTO;

import java.io.OutputStream;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final ObjectMapper objectMapper;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final OTPAuthenticationProvider otpAuthenticationProvider;

    public static final String[] WHITE_LIST = {
            "/api/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    public SecurityConfiguration(ObjectMapper objectMapper, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, OTPAuthenticationProvider otpAuthenticationProvider) {
        this.objectMapper = objectMapper;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.otpAuthenticationProvider = otpAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Enable CORS configuration
                .authorizeHttpRequests(httpReqConf ->
                        httpReqConf.requestMatchers(WHITE_LIST).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionConf -> sessionConf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exHanConfig -> exHanConfig.authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler()))
                .addFilterBefore(new JwtTokenFilter(jwtTokenUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.authenticationProvider(authenticationProvider());
        authBuilder.authenticationProvider(otpAuthenticationProvider);
        return authBuilder.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Allow specific origins (frontend URLs)
        corsConfiguration.setAllowedOriginPatterns(List.of(
                "http://localhost:8080",  // Frontend
                "http://localhost:9090"   // Backend
        ));

        corsConfiguration.setAllowedHeaders(List.of("*")); // Allow all headers
        corsConfiguration.setAllowedMethods(List.of("*")); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        corsConfiguration.setAllowCredentials(true);        // Allow cookies/authorization headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);  // Apply to all endpoints

        return source;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            authException.fillInStackTrace();
            String errorPath = request.getRequestURI();
            String errorMessage = authException.getMessage();
            int errorCode = 401;
            AppErrorDTO appErrorDTO = new AppErrorDTO(errorMessage, errorPath, errorCode);
            response.setStatus(errorCode);
            OutputStream outputStream = response.getOutputStream();
            objectMapper.writeValue(outputStream, appErrorDTO);
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return ((request, response, accessDeniedException) -> {
            accessDeniedException.fillInStackTrace();
            String errorPath = request.getRequestURI();
            String errorMessage = accessDeniedException.getMessage();
            int errorCode = 403;
            AppErrorDTO appErrorDTO = new AppErrorDTO(errorMessage, errorPath, errorCode);
            OutputStream outputStream = response.getOutputStream();
            objectMapper.writeValue(outputStream, appErrorDTO);
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
