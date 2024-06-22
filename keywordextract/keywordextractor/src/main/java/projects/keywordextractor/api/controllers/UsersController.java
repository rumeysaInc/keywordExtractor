package projects.keywordextractor.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import projects.keywordextractor.business.abstracts.UserService;
import projects.keywordextractor.core.dataAccess.RoleDao;
import projects.keywordextractor.core.dataAccess.UserDao;
import projects.keywordextractor.core.entities.Role;
import projects.keywordextractor.core.entities.UserEntity;
import projects.keywordextractor.core.utilities.results.ErrorDataResult;
import projects.keywordextractor.dto.AuthResponseDTO;
import projects.keywordextractor.dto.LoginDto;
import projects.keywordextractor.dto.RegisterDto;
import projects.keywordextractor.security.JWTGenerator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/api/auth")
public class UsersController {

    private AuthenticationManager authenticationManager;
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    private UserService userService;

    @Autowired
    public UsersController(AuthenticationManager authenticationManager, UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder,JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    public UsersController(UserService userService) {
        this.userService = userService;
    }

   @PostMapping("login")
   public ResponseEntity<AuthResponseDTO > login(@RequestBody LoginDto loginDto){
       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
               loginDto.getPassword()));
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String token = jwtGenerator.generateToken(authentication);
       // Kullanıcıyı e-posta ile bul
       UserEntity user = userDao.findByEmail(loginDto.getEmail()).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

       // AuthResponseDTO oluştur ve kullanıcı ID'sini ekle
       AuthResponseDTO response = new AuthResponseDTO(token, user.getId());
       return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @PostMapping("/register")
   public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
       if  (userDao.existsByEmail(registerDto.getEmail())) {
           return new ResponseEntity<>("Email is already in use!", HttpStatus.BAD_REQUEST);
       }

       UserEntity user = new UserEntity();
       user.setUsername(registerDto.getUsername());
       user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
       user.setSurname(registerDto.getSurname());
       user.setEmail(registerDto.getEmail());

       Role roles = roleDao.findByName("USER").get();
       user.setRoles(Collections.singletonList(roles));

       userDao.save(user);

       return new ResponseEntity<>("User registered success!", HttpStatus.OK);
   }

    //hata yakalama AOP
    @ExceptionHandler(MethodArgumentNotValidException.class) //bu türde bir hata olursa sistemi devreye sok
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){ //hataları parametre olarak geç
        Map<String,String> validationErrors= new HashMap<String,String>();
        for(FieldError fieldError :exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors= new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
        return errors;
    }


}
