package team.a501.rif.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberResponse {

    String id;
    String uid;
    String name;
    String imgPath;

    @Builder
    public MemberResponse(String id, String uid, String name, String imgPath) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "MemberResponse{" +
                "\n id='" + id + '\'' +
                ",\n uid='" + uid + '\'' +
                ",\n name='" + name + '\'' +
                ",\n imgPath='" + imgPath + '\'' +
                "\n}";
    }
}
