package com.mysite.suggestion.user;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.suggestion.DataNotFoundException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String userID, String username, String email, String password) {
        SiteUser user = new SiteUser();
        if (userID.contains("admin")) {
        	throw new UsernameNotFoundException("사용할 수 없는 문자를 포함하고 있습니다.");
        } else {
	        user.setUserID(userID);
	        user.setUsername(username);
	        user.setEmail(email);
	        user.setPassword(passwordEncoder.encode(password));
	        this.userRepository.save(user);
	        return user;
        }
    }
    
    public SiteUser getUser(String userID) {
    	Optional<SiteUser> siteUser = this.userRepository.findByUserID(userID);
    	if (siteUser.isPresent()) {
    		return siteUser.get();
    	} else {
    		throw new DataNotFoundException("siteuser not found");
    	}
    }
}