package int222.backend.controllers;

import int222.backend.auth.JwtAuthenticationRequest;
import int222.backend.auth.JwtAuthenticationResponse;
import int222.backend.models.entities.Authority;
import int222.backend.models.entities.User;
import int222.backend.models.exceptions.EntityAlreadyExistsException;
import int222.backend.models.services.UserService;
import int222.backend.repositories.AuthorityRepository;
import int222.backend.repositories.UserRepository;
import int222.backend.utilities.CustomUserDetailsService;
import int222.backend.utilities.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private TokenHelper jwtToken;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping(value = "/login",consumes = "application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping(value = "/signup",consumes = "application/json")
    public ResponseEntity<String> createNewUser(@RequestBody User user) {
        if (userService.checkUserNameIsAlreadyExists(user.getUsername())){
            throw new EntityAlreadyExistsException("Username is already taken");
        }
        if(userService.checkFullNameIsAlreadyExists(user.getFullname())){
            throw new EntityAlreadyExistsException("Firstname and lastname is already taken , change neither one of them");
        }
        Authority roleUser= authorityRepository.findById(2);
        String encryptedPassword= passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(roleUser);
        userRepository.save(user);
        return ResponseEntity.ok().body("Sign up successfully");
    }


}
