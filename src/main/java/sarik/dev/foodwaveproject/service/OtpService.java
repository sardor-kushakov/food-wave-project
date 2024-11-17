package sarik.dev.foodwaveproject.service;

public interface OtpService {
    String sendOtp(String email);

    boolean validateOTP(String email, String otpCode);
}
