package team.a501.rif.domain.tmp;

import lombok.Builder;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Entity
public class TempMember {

    @Id
    private String id; // 학생증 고유값 = uid

    @Column(unique = true)
    private String studentId; // 학번

    private String password;

    private String name;

    private Integer point;

    private Integer exp;

    private String profileImgPath; // 기본값 /profile/default.png

    @OneToMany(mappedBy = "member")
    private Map<Long, BadgeAcq> badgeAcqs = new HashMap<>(); // key = Badge.id / value = BadgeAcq

    @OneToMany(mappedBy = "member")
    private Map<Long, AchievementAcq> achievementAcqs = new HashMap<>(); // key = Achievement.id / value = AchievementAcq

    public Boolean hasBadge(Badge badge){
        return badgeAcqs.containsKey(badge.getId());
    }

    public Boolean hasAchievement(Achievement achievement){
        return achievementAcqs.containsKey(achievement.getId());
    }

    public void addBadgeAcq(BadgeAcq acq){

        acq.setMember(this);
        badgeAcqs.put(acq.getBadge().getId(), acq);
    }

    public void removeBadgeAcq(BadgeAcq acq){

        acq.setMember(null);
        badgeAcqs.remove(acq.getBadge().getId(), acq);
    }

    public void addAchievementAcq(AchievementAcq acq){

        acq.setMember(this);
        achievementAcqs.put(acq.getAchievement().getId(), acq);
    }

    public void removeAchievementAcq(AchievementAcq acq){

        acq.setMember(null);
        achievementAcqs.remove(acq.getAchievement().getId());
    }

    @Builder
    public TempMember(String id, String studentId, String password, String name, Integer point, Integer exp, String profileImgPath) {

        this.id = id;
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.point = point;
        this.exp = exp;
        this.profileImgPath = profileImgPath;
    }

    @Override
    public String toString() {
        return "TempMember{" +
                "\n id='" + id + '\'' +
                ",\n studentId='" + studentId + '\'' +
                ",\n password='" + password + '\'' +
                ",\n name='" + name + '\'' +
                ",\n point=" + point +
                ",\n exp=" + exp +
                ",\n profileImgPath='" + profileImgPath + '\'' +
                "\n}";
    }
}
