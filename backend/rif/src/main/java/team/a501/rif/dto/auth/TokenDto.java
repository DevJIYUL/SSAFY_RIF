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

    private String refreshToken;
}
