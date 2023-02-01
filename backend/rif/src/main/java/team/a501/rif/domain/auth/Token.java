package team.a501.rif.domain.auth;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Token {
    private String grantType;
    private String accessToken;
}
