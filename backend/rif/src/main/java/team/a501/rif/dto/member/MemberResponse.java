package team.a501.rif.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.member.Member;

@NoArgsConstructor
@Getter
public class MemberResponse {

    String id;
    String uid;
    String name;
    Integer exp;
    Integer point;
    String profileImgPath;

    @Builder
    public MemberResponse(String id, String uid, String name, String profileImgPath,Integer exp,Integer point) {
        this.id = id;
        this.point = point;
        this.uid = uid;
        this.name = name;
        this.exp = exp;
        this.profileImgPath = profileImgPath;
    }

    public static MemberResponse from(Member member){

        return MemberResponse.builder()
                .id(member.getId())
                .uid(member.getUid())
                .name(member.getName())
                .exp(member.getExp())
                .point(member.getPoint())
                .profileImgPath(member.getProfileImgPath())
                .build();
    }

    @Override
    public String toString() {
        return "MemberResponse{" +
                "\n id='" + id + '\'' +
                ",\n uid='" + uid + '\'' +
                ",\n name='" + name + '\'' +
                ",\n point='" + point + '\'' +
                ",\n exp='" + exp + '\'' +
                ",\n imgPath='" + profileImgPath + '\'' +
                "\n}";
    }
}
