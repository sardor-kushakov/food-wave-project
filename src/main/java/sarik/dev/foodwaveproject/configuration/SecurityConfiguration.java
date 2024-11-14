//package sarik.dev.foodwaveproject.configuration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import sarik.dev.foodwaveproject.dto.AppErrorDTO;
//
//import java.io.OutputStream;
//import java.util.List;
//
////@Configuration
////@EnableWebSecurity
////@EnableMethodSecurity
//public class SecurityConfiguration {
//
////    private final ObjectMapper objectMapper;
////    private final UserDetailsService userDetailsService;
////    private final JwtTokenUtil jwtTokenUtil;
////
////    public static final String[] WHITE_LIST = {
////            "/api/**",
////            "/swagger-ui/**",
////            "/swagger-resources/**",
////            "/v3/api-docs/**",
////    };
////
////    public SecurityConfiguration(ObjectMapper objectMapper, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
////        this.objectMapper = objectMapper;
////        this.userDetailsService = userDetailsService;
////        this.jwtTokenUtil = jwtTokenUtil;
////    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        http.
////                csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(httpReqConf ->
////                        httpReqConf.requestMatchers(WHITE_LIST).permitAll()
////                                .anyRequest()
////                                .fullyAuthenticated()
////                )
////                .sessionManagement(sessionConf -> sessionConf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .exceptionHandling(exHanConfig -> exHanConfig.authenticationEntryPoint(authenticationEntryPoint())
////                        .accessDeniedHandler(accessDeniedHandler()))
////                .addFilterBefore(new JwtTokenFilter(jwtTokenUtil,userDetailsService), UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////
////
////    }
////
////    @Bean
////    public CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration corsConfiguration = new CorsConfiguration();
////        corsConfiguration.setAllowedOriginPatterns(List.of(
////                "http://localhost:8080",
////                "http://localhost:9090"
////        ));
////
////        corsConfiguration.setAllowedHeaders(List.of("*"
////                /*"Accept",
////                "Content-Type",
////                "Authorization"*/
////        ));
////
////        corsConfiguration.setAllowedMethods(List.of(
////                "PUT,DELETE", "POST", "PUT"/*  "*"  */
////        ));
////
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", corsConfiguration);
////         /*source.registerCorsConfiguration("/api/v2/**", configuration2);
////        source.registerCorsConfiguration("/api/v3/**", configuration3);*/
////        return source;
////    }
////
////    @Bean
////    public AuthenticationEntryPoint authenticationEntryPoint() {
////        return (request, response, authException) -> {
////            authException.fillInStackTrace();
////            String errorPath = request.getRequestURI();
////            String errorMessage = authException.getMessage();
////            int errorCode = 401;
////            AppErrorDTO appErrorDTO = new AppErrorDTO(errorMessage, errorPath, errorCode);
////            response.setStatus(errorCode);
////            OutputStream outputStream = response.getOutputStream();
////            objectMapper.writeValue(outputStream, appErrorDTO);
////        };
////    }
////
////    @Bean
////    public AccessDeniedHandler accessDeniedHandler() {
////        return ((request, response, accessDeniedException) -> {
////            accessDeniedException.fillInStackTrace();
////            String errorPath = request.getRequestURI();
////            String errorMessage = accessDeniedException.getMessage();
////            int errorCode = 403;
////            AppErrorDTO appErrorDTO = new AppErrorDTO(errorMessage, errorPath, errorCode);
////            OutputStream outputStream = response.getOutputStream();
////            objectMapper.writeValue(outputStream, appErrorDTO);
////        });
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////
////    @Bean
////    public AuthenticationProvider authenticationProvider(){
////        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setPasswordEncoder(passwordEncoder());
////        authenticationProvider.setUserDetailsService(userDetailsService);
////        return authenticationProvider;
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(){
////        return new ProviderManager(authenticationProvider());
////    }
//
//}
