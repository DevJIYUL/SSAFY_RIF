package team.a501.rif.dto.auth;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenDto {
    private String grantType;
    private String accessToken;
<<<<<<< HEAD:backend/rif/src/main/java/team/a501/rif/domain/auth/Token.java

    private String id;
=======
    private String refreshToken;
>>>>>>> c89f827a97affa60f625d1be003543eb6cc2e001:backend/rif/src/main/java/team/a501/rif/dto/auth/TokenDto.java
}
