package team.a501.rif.domain.achievement;

import team.a501.rif.domain.BaseEntity;
import team.a501.rif.domain.member.Member;

import javax.persistence.*;

@Entity
public class AchievementAcq extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Boolean onDisplay;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Achievement achievement;

    public AchievementAcq() {
        onDisplay = false;
    }

    public Long getId() {
        return id;
    }

    public Boolean getOnDisplay() {
        return onDisplay;
    }

    public void setOnDisplay(Boolean onDisplay) {
        this.onDisplay = onDisplay;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    @Override
    public String toString() {
        return "AchievementAcq{" +
                "\n id=" + id +
                ",\n onDisplay=" + onDisplay +
                ",\n member=" + member +
                ",\n achievement=" + achievement +
                ",\n created= " + this.getCreated().toString() +
                ",\n modified= " + this.getLastModified().toString() +
                "\n}";
    }

    public void toggleOnDisplay() {
        this.onDisplay = !this.onDisplay;
    }
}
