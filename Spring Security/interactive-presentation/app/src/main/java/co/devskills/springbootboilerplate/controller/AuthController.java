package co.devskills.springbootboilerplate.controller;

import co.devskills.springbootboilerplate.dto.AuthRequest;
import co.devskills.springbootboilerplate.dto.PingResponse;
import co.devskills.springbootboilerplate.model.AppUser;
import co.devskills.springbootboilerplate.service.CustomUserDetailsService;
import co.devskills.springbootboilerplate.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/public")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(), authRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(Collections.singletonMap("jwtToken", token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        AppUser appUserCreated = userDetailsService.signUp(appUser);
        return ResponseEntity.ok(appUserCreated);
    }

    @GetMapping(value = "/ping")
    @ResponseStatus(HttpStatus.OK)
    public PingResponse healthCheck() {
        return new PingResponse("The service is up and running");
    }
}
