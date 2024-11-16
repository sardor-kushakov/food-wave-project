package sarik.dev.foodwaveproject.security;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class UserSession {

    public static Long requireUserId() {
        return ThreadLocalRandom.current().nextLong(10, 30);
    }

}
