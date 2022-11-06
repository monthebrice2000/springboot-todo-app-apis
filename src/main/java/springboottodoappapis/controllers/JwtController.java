package springboottodoappapis.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springboottodoappapis.models.JwtRequest;
import springboottodoappapis.models.JwtResponse;
import springboottodoappapis.models.UserModel;
import springboottodoappapis.services.CustomUserDetailsService;
import springboottodoappapis.utils.JwtUtil;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class JwtController {

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<?> register( @RequestBody UserModel userModel){
        try{
            UserDetails userDetails = this.userDetailsService.register( userModel );
            return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
        }catch (Exception err){
            return new ResponseEntity<Exception>(err, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(Principal principal){
        try{
            UserDetails userDetails = this.userDetailsService.loadUserByUsername( principal.getName() );
            return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK );
        }catch (Exception err){
            return new ResponseEntity<Exception>(err, HttpStatus.OK );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
        try{
            System.out.println( jwtRequest );
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword() );
            auth.authenticate( upat );
            UserDetails userDetails = this.userDetailsService.loadUserByUsername( jwtRequest.getUsername() );
            JwtResponse jwtResponse = new JwtResponse( jwtUtil.generateToken(userDetails) );
            return new ResponseEntity<JwtResponse>(jwtResponse,HttpStatus.OK);
        }catch (Exception err){
            return new ResponseEntity<Exception>(err, HttpStatus.BAD_REQUEST);
        }
    }
}
