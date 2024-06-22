package projects.keywordextractor.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private int userId;

    public AuthResponseDTO(String accessToken,int userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }
}
