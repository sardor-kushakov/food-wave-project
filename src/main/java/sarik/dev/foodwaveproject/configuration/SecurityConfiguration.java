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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import sarik.dev.foodwaveproject.generic.AppErrorDto;

import java.io.OutputStream;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    public static final String[] WHITE_LIST = {
            "/api/auth/verify-otp",
            "/api/auth/register",
            "/api/auth/login",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/api/oauth2/**"
    };
    private final ObjectMapper objectMapper;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final OTPAuthenticationProvider otpAuthenticationProvider;

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
                .oauth2Login(oauth2 -> oauth2
                        .successHandler((request, response, authentication) -> {
                            OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
                            String email = authToken.getPrincipal().getAttribute("email");
                            String name = authToken.getPrincipal().getAttribute("name");

                            String jwtToken = jwtTokenUtil.generateToken(email);

                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            objectMapper.writeValue(response.getWriter(),
                                    new OAuth2LoginResponse(jwtToken, email, name));
                        })
                )
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

        corsConfiguration.setAllowedOriginPatterns(List.of(
                "http://localhost:8080",
                "http://localhost:9090"
        ));

        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            authException.fillInStackTrace();
            String errorPath = request.getRequestURI();
            String errorMessage = authException.getMessage();
            int errorCode = 401;
            AppErrorDto appErrorDTO = new AppErrorDto(errorMessage, errorPath, errorCode);
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
            AppErrorDto appErrorDTO = new AppErrorDto(errorMessage, errorPath, errorCode);
            OutputStream outputStream = response.getOutputStream();
            objectMapper.writeValue(outputStream, appErrorDTO);
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private record OAuth2LoginResponse(String token, String email, String name) {
    }

}
