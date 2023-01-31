package team.a501.rif.dto.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.member.Member;


@NoArgsConstructor
@Data
public class MemberRegister {
    private String id; // student id

    private String password;

    private String uid;

    private String name;

    @Builder
    public MemberRegister(String id, String password, String uid, String name) {
        this.id = id;
        this.password = password;
        this.uid = uid;
        this.name = name;
    }
}
