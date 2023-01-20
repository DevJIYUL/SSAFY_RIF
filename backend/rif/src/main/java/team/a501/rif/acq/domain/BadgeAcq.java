package team.a501.rif.acq.domain;

import team.a501.rif.auth.domain.Member;

import javax.persistence.*;

@Entity
public class BadgeAcq {

    @Id @GeneratedValue
    private Long id;

    private Boolean onDisplay;

    @ManyToOne
    private Member member;

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
