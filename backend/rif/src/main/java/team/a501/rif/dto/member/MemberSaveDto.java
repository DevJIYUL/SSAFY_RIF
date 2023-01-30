package team.a501.rif.dto.member;

import lombok.Data;
import team.a501.rif.domain.member.Member;

import java.util.UUID;

@Data
public class MemberSaveDto {
    private String studentId;

    private String password;

    private String name;

    public Member toEntity() {
        return Member.builder()
                .id(UUID.randomUUID().toString())
                .studentId(this.studentId)
                .password(this.password)
                .name(this.name)
                .exp(0)
                .point(0)
                .profileImgPath("/profile/default.png")
                .build();
    }

}
