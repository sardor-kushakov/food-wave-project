package sarik.dev.foodwaveproject.xer.imp;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import sarik.dev.foodwaveproject.entity.Otp;
import sarik.dev.foodwaveproject.xer.inter.OtpService;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    private final JavaMailSender mailSender;
    private final OtpRepository otpRepository;

    public OtpServiceImpl(JavaMailSender mailSender, OtpRepository otpRepository) {
        this.mailSender = mailSender;
        this.otpRepository = otpRepository;
    }

    @Override
    public String sendOtp(String email) {
        String otpCode = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);

        otpRepository.deleteByEmail(email);

        Otp otp = new Otp(email, otpCode, expirationTime);
        otpRepository.save(otp);

        sendEmail(email, otpCode);

        return otpCode;
    }

    private void sendEmail(String email, String otpCode) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            String htmlContent = "<h1 style=\"color: red;\">Sizning OTP kodingiz: " + otpCode + "</h1>" +
                    "<p>Kod 5 daqiqa davomida amal qiladi.</p>";

            helper.setTo(email);
            helper.setSubject("OTP Kodingiz");
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateOTP(String email, String otpCode) {
        return otpRepository.findByEmail(email)
                .filter(otp -> otp.getOtpCode().equals(otpCode) && otp.getExpirationTime().isAfter(LocalDateTime.now()))
                .isPresent();
    }
}
