package sarik.dev.foodwaveproject.controller;


import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sarik.dev.foodwaveproject.configuration.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/oauth2")
public class OAuth2Controller {

    private final JwtTokenUtil jwtTokenProvider;

    public OAuth2Controller(JwtTokenUtil jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/callback/google")
    public Map<String, Object> handleGoogleLogin(OAuth2AuthenticationToken authToken) {
        return generateResponse(authToken);
    }

    @GetMapping("/callback/facebook")
    public Map<String, Object> handleFacebookLogin(OAuth2AuthenticationToken authToken) {
        return generateResponse(authToken);
    }

    private Map<String, Object> generateResponse(OAuth2AuthenticationToken authToken) {
        OAuth2User user = authToken.getPrincipal();

        String email = user.getAttribute("email");
        String name = user.getAttribute("name");

        // JWT token yaratish
        String token = jwtTokenProvider.generateToken(email);

        // Ma’lumotlarni qaytarish
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("email", email);
        response.put("name", name);
        return response;
    }
}
