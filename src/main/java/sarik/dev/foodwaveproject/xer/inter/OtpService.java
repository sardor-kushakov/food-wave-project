package sarik.dev.foodwaveproject.xer.inter;

public interface OtpService {
    String sendOtp(String email);

    boolean validateOTP(String email, String otpCode);
}
