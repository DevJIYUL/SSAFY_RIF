package team.a501.rif.domain.tmp;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Entity
public class TempMember {

    @Id
    private String id; // 학생증 고유값 = uid

    @Column(unique = true, length = 10)
    private String studentId; // 학번

    @Column(length = 100)
    private String password;

    @Column(length = 20)
    private String name;

    private Integer point;

    private Integer exp;

    @Column(length = 40)
    private String profileImgPath; // 기본값 /profile/default.png

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Map<Long, BadgeAcq> badgeAcqs; // key = Badge.id / value = BadgeAcq

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Map<Long, AchievementAcq> achievementAcqs; // key = Achievement.id / value = AchievementAcq

    @Builder
    public TempMember(String id, String studentId, String password, String name, Integer point, Integer exp, String profileImgPath) {

        this.id = id;
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.point = point;
        this.exp = exp;
        this.profileImgPath = profileImgPath;

        this.badgeAcqs = new HashMap<>();
        this.achievementAcqs = new HashMap<>();
    }

    public Boolean hasBadge(@NotNull Badge badge) {
        return badgeAcqs.containsKey(badge.getId());
    }

    public Boolean hasAchievement(@NotNull Achievement achievement) {
        return achievementAcqs.containsKey(achievement.getId());
    }

    public void addBadgeAcq(@NotNull BadgeAcq acq) {

        acq.setMember(this);
        badgeAcqs.put(acq.getBadge().getId(), acq);
    }

    public void removeBadgeAcq(@NotNull BadgeAcq acq) {

        acq.setMember(null);
        badgeAcqs.remove(acq.getBadge().getId(), acq);
    }

    public void addAchievementAcq(@NotNull AchievementAcq acq) {

        acq.setMember(this);
        achievementAcqs.put(acq.getAchievement().getId(), acq);
    }

    public void removeAchievementAcq(@NotNull AchievementAcq acq) {

        acq.setMember(null);
        achievementAcqs.remove(acq.getAchievement().getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getProfileImgPath() {
        return profileImgPath;
    }

    public void setProfileImgPath(String profileImgPath) {
        this.profileImgPath = profileImgPath;
    }

    public Map<Long, BadgeAcq> getBadgeAcqs() {
        return badgeAcqs;
    }

    public Map<Long, AchievementAcq> getAchievementAcqs() {
        return achievementAcqs;
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
