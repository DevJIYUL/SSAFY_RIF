package team.a501.rif.dto.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class MemberRegisterRequest {
    private String id; // student id

    private String password;

    private String uid;

    private String name;

    @Builder
    public MemberRegisterRequest(String id, String password, String uid, String name) {
        this.id = id;
        this.password = password;
        this.uid = uid;
        this.name = name;
    }
}
