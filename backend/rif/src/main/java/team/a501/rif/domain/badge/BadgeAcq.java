package team.a501.rif.domain.badge;

import team.a501.rif.domain.BaseEntity;
import team.a501.rif.domain.member.Member;

import javax.persistence.*;

@Entity
public class BadgeAcq extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Boolean onDisplay;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Badge badge;

    public BadgeAcq() {

        this.onDisplay = false;
    }

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

    public void toggleOnDisplay() {
        this.onDisplay = !this.onDisplay;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    @Override
    public String toString() {
        return "BadgeAcq{" +
                "\n id=" + id +
                ",\n onDisplay=" + onDisplay +
                ",\n member=" + member +
                ",\n badge=" + badge +
                ",\n created= " + this.getCreated().toString() +
                ",\n modified= " + this.getLastModified().toString() +
                "\n}";
    }
}
