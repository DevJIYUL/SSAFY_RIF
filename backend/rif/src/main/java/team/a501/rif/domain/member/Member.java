package team.a501.rif.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
    @JsonIgnore
    public static final String DEFAULT_PROFILE_IMG = "/profile/default.png";

    @Id
    private String id; // 학번

    @Column(unique = true, length = 100)
    private String uid;

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
    private Set<Role> roles;

    @Builder
    public Member(String id, String uid, String password, String name, Integer point, Integer exp, String profileImgPath) {

        this.id = id;
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.point = point;
        this.exp = exp;
        this.profileImgPath = profileImgPath;

        this.badgeAcqs = new HashMap<>();
        this.achievementAcqs = new HashMap<>();
        this.roles = new HashSet<>();
    }

    // implement UserDetails

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getType()))
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    @JsonIgnore
    public Map<Long, BadgeAcq> getBadgeAcqs() {
        return badgeAcqs;
    }

    @JsonIgnore
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
                ", studentId='" + uid + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", point=" + point +
                ", exp=" + exp +
                ", profileImgPath='" + profileImgPath + '\'' +
                '}';
    }
}
