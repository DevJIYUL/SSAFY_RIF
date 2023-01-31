package team.a501.rif.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.a501.rif.domain.role.Role;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor

public class Member implements UserDetails {


    @Id
    private String id;
    private String studentId; // 학번

    private String password;

    private String name;
    private Integer point;

    private Integer exp;

    private String profileImgPath; // 기본값 /profile/default.png
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Role> roles;
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
        return this.password;
    }

    @Builder
    public Member(String id, String studentId, String password, String name, Integer point, Integer exp, String profileImgPath, List<Role> roles) {
        this.id = id;
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.point = point;
        this.exp = exp;
        this.profileImgPath = profileImgPath;
        this.roles = new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return studentId;
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
//    todo 필드 유효성 검사


    public void setPassword(String password) {
        this.password = password;
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
