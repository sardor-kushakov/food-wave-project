//package sarik.dev.foodwaveproject.configuration;
//
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import sarik.dev.foodwaveproject.entity.auth.AuthRole;
//import sarik.dev.foodwaveproject.entity.auth.AuthUser;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Getter
//public class CustomUserDetails implements UserDetails {
//
//    private transient final AuthUser authUser;
//
//    public CustomUserDetails(AuthUser authUser) {
//        this.authUser = authUser;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<AuthRole> roles = authUser.getRoles();
//
//        if(roles == null){
//            return Collections.emptyList();
//        }
//        Set<GrantedAuthority> authorities = new HashSet<>();
//
//        for (AuthRole role : roles) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
//        }
//        return authorities;
//    }
//    public AuthUser getAuthUser() {
//        return authUser;
//    }
//
//    @Override
//    public String getPassword() {
//        return authUser.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return authUser.getEmail();
//    }
//}
