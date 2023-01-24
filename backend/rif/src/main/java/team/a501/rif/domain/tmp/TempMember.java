package team.a501.rif.domain.tmp;

import lombok.Builder;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.badge.BadgeAcq;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class TempMember {

    @Id
    private String uid;

    private String id; // 학번

    private String password;

    private String name;

    private Integer point;

    private Integer exp;

    private String profileImgPath; // 기본값 /profile/default.png

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<BadgeAcq> badgeAcqs = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<AchievementAcq> achievementAcqs = new ArrayList<>();

    @Builder
    public TempMember(String uid, String id, String password, String name, Integer point, Integer exp, String profileImgPath) {

        this.uid = uid;
        this.id = id;
        this.password = password;
        this.name = name;
        this.point = point;
        this.exp = exp;
        this.profileImgPath = profileImgPath;
    }
}
