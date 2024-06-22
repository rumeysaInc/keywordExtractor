package projects.keywordextractor.dto;


import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String surname;
    private String email;
    private String password;
}
