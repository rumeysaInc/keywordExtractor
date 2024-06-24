package projects.keywordextractor.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import projects.keywordextractor.core.dataAccess.RoleDao;
import projects.keywordextractor.core.dataAccess.UserDao;
import projects.keywordextractor.core.entities.Role;
import projects.keywordextractor.dto.AuthResponseDTO;
import projects.keywordextractor.dto.LoginDto;
import projects.keywordextractor.dto.RegisterDto;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.HttpStatus;
import projects.keywordextractor.security.JWTGenerator;

import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private UsersController usersController;
    private AuthenticationManager authenticationManager;
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @BeforeEach
    public void setUp() {
        authenticationManager = mock(AuthenticationManager.class);
        userDao = mock(UserDao.class);
        roleDao = mock(RoleDao.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtGenerator = mock(JWTGenerator.class);

        usersController = new UsersController(authenticationManager, userDao, roleDao, passwordEncoder, jwtGenerator);
    }


    @Test
    void testLogin() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("esra12@gmail.com");
        loginDto.setPassword("1616");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginDto)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        AuthResponseDTO responseDTO = objectMapper.readValue(content, AuthResponseDTO.class);


        assertThat(responseDTO.getAccessToken()).isNotNull();
        assertThat(responseDTO.getUserId()).isNotNull();
    }

    @Test
    public void testRegister_Success() {
        // Arrange
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("test@example.com");
        registerDto.setPassword("password");
        registerDto.setUsername("username");
        registerDto.setSurname("surname");

        Role role = new Role();
        role.setId(14);
        role.setName("USER");
        when(roleDao.findByName("USER")).thenReturn(Optional.of(role));

        // Act
        ResponseEntity<String> responseEntity = usersController.register(registerDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User registered success!", responseEntity.getBody());
    }

    @Test
    public void testRegister_Failure_EmailAlreadyExists() {
        // Arrange
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("existing@example.com");

        when(userDao.existsByEmail(registerDto.getEmail())).thenReturn(true);

        // Act
        ResponseEntity<String> responseEntity = usersController.register(registerDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Email is already in use!", responseEntity.getBody());
    }


    // Bu metot, bir nesneyi JSON formatına dönüştürmek için kullanılır
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
