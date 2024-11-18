package sarik.dev.foodwaveproject.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sarik.dev.foodwaveproject.configuration.JwtTokenUtil;
import sarik.dev.foodwaveproject.dto.authUserDto.AuthUserResponseDTO;
import sarik.dev.foodwaveproject.dto.authUserDto.CreateAuthUserDTO;
import sarik.dev.foodwaveproject.dto.authUserDto.UpdateAuthUserDTO;
import sarik.dev.foodwaveproject.dto.requestDto.LoginRequest;
import sarik.dev.foodwaveproject.dto.requestDto.OtpRequest;
import sarik.dev.foodwaveproject.service.authUser.AuthUserServiceImpl;
import sarik.dev.foodwaveproject.service.otp.OtpService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUserServiceImpl authUserServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final OtpService otpService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthUserServiceImpl authUserServiceImpl, AuthenticationManager authenticationManager, OtpService otpService, JwtTokenUtil jwtTokenUtil) {
        this.authUserServiceImpl = authUserServiceImpl;
        this.authenticationManager = authenticationManager;
        this.otpService = otpService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CreateAuthUserDTO dto) {
        authUserServiceImpl.registerUser(dto);
        otpService.sendOtp(dto.email());
        return ResponseEntity.ok("OTP yuborildi. Iltimos, tekshiring.");

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password());
        authenticationManager.authenticate(authentication);
        otpService.sendOtp(loginRequest.email());
        return ResponseEntity.ok("OTP yuborildi. Iltimos, tekshiring.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpRequest otpRequest) {
        boolean isOtpValid = otpService.validateOTP(otpRequest.getEmail(), otpRequest.getOtpCode());
        if (isOtpValid) {
            String token = jwtTokenUtil.generateToken(otpRequest.getEmail());
            return ResponseEntity.ok("Tasdiqlash muvaffaqiyatli. Token: " + token);
        } else {
            return ResponseEntity.status(401).body("Noto'g'ri yoki muddati o'tgan OTP.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUserResponseDTO> createUser(@Valid @RequestBody CreateAuthUserDTO dto){
        AuthUserResponseDTO user = authUserServiceImpl.registerUser(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthUserResponseDTO>> getUsers(){
        List<AuthUserResponseDTO> allUsers = authUserServiceImpl.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthUserResponseDTO> getUserById(@PathVariable Long id){
        AuthUserResponseDTO user = authUserServiceImpl.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<AuthUserResponseDTO> updateUser(@PathVariable Long id,@Valid @RequestBody UpdateAuthUserDTO updateAuthUserDTO){
        AuthUserResponseDTO user = authUserServiceImpl.updateUser(id, updateAuthUserDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        authUserServiceImpl.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


}
