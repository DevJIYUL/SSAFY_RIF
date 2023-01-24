package team.a501.rif.domain.badge;

import team.a501.rif.domain.member.Member;
import team.a501.rif.domain.tmp.TempMember;

import javax.persistence.*;

@Entity
public class BadgeAcq {

    @Id
    @GeneratedValue
    private Long id;

    private Boolean onDisplay;

//    @ManyToOne
//    private Member member; // 멤버가 삭제되면 뱃지획득 역시 삭제되어야한다

    @ManyToOne
    private TempMember member; // todo 테스트용

    // todo 뱃지가 삭제되어도 멤버가 남아있다면 뱃지획득이 삭제되어선 안된다. 뱃지가 삭제되면 기본 뱃지로 대체되는 것도 고려해보자
    @ManyToOne
    private Badge badge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOnDisplay() {
        return onDisplay;
    }

    public void setOnDisplay(Boolean onDisplay) {
        this.onDisplay = onDisplay;
    }

    public TempMember getMember() {
        return member;
    }

    public void setMember(TempMember member) {
        this.member = member;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
}
