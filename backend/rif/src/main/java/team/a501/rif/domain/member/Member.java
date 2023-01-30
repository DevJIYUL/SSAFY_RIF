package team.a501.rif.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.role.Role;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Entity
public class Member implements UserDetails {

    @Id
    private String id;

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
    private Map<Long, BadgeAcq> badgeAcqs;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Map<Long, AchievementAcq> achievementAcqs;

    @OneToMany(mappedBy = "member")
    private List<Role> roles;

    @Builder
    public Member(String id, String studentId, String password, String name, Integer point, Integer exp, String profileImgPath) {

        this.id = id;
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.point = point;
        this.exp = exp;
        this.profileImgPath = profileImgPath;

        this.badgeAcqs = new HashMap<>();
        this.achievementAcqs = new HashMap<>();
        this.roles = new ArrayList<>();
    }

    // implement UserDetails
    @JsonIgnore
    @Override
    public List<GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(t->new SimpleGrantedAuthority(t.getType()))
                .collect(Collectors.toList());
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return id;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    // implement UserDetails end

    // getter setter
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

    public Map<Long, BadgeAcq> getBadgeAcqs() {
        return badgeAcqs;
    }

    public Map<Long, AchievementAcq> getAchievementAcqs() {
        return achievementAcqs;
    }

    public void setProfileImgPath(String profileImgPath) {
        this.profileImgPath = profileImgPath;
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

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", point=" + point +
                ", exp=" + exp +
                ", profileImgPath='" + profileImgPath + '\'' +
                '}';
    }
}
