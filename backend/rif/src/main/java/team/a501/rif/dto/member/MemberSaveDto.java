package team.a501.rif.dto.member;

import lombok.Data;
import team.a501.rif.domain.member.Member;

import java.util.UUID;

@Data
public class MemberSaveDto {
    private String studentId;
    private String uid;

    private String password;

    private String name;

}
