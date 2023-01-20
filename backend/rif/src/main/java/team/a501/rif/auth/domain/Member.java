package team.a501.rif.auth.domain;

import team.a501.rif.acq.domain.AchievementAcq;
import team.a501.rif.acq.domain.BadgeAcq;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Member {

    @Id
    private String uid;
    
    private String id; // 학번
    
    private String password;
    
    private String name;
    
    private Integer point;
    
    private Integer exp;
    
    private String profileImgPath; // 기본값 /profile/default.png
//    todo 필드 유효성 검사
}
