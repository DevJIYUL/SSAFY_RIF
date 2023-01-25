package team.a501.rif.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Token {
    private String grantType;
    private String accessToken;
}
