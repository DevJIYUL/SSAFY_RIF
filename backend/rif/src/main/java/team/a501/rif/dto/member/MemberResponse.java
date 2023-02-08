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
    String profile_img_path;

    @Builder
    public MemberResponse(String id, String uid, String name, String profileImgPath) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.profile_img_path = profileImgPath;
    }

    public static MemberResponse from(Member member){

        return MemberResponse.builder()
                .id(member.getId())
                .uid(member.getUid())
                .name(member.getName())
                .profileImgPath(member.getProfileImgPath())
                .build();
    }

    @Override
    public String toString() {
        return "MemberResponse{" +
                "\n id='" + id + '\'' +
                ",\n uid='" + uid + '\'' +
                ",\n name='" + name + '\'' +
                ",\n imgPath='" + profile_img_path + '\'' +
                "\n}";
    }
}
