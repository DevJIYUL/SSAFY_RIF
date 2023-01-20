package team.a501.rif.domain.badge;

import team.a501.rif.domain.member.Member;

import javax.persistence.*;

@Entity
public class BadgeAcq {

    @Id
    @GeneratedValue
    private Long id;

    private Boolean onDisplay;

    @ManyToOne
    private Member member; // 멤버가 삭제되면 뱃지획득 역시 삭제되어야한다

    // todo 뱃지가 삭제되어도 멤버가 남아있다면 뱃지획득이 삭제되어선 안된다. 뱃지가 삭제되면 기본 뱃지로 대체되는 것도 고려해보자
    @ManyToOne
    private Badge badge;

    public Member getMember() {
        return member;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
}
