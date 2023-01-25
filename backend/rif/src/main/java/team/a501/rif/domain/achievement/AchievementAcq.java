package team.a501.rif.domain.achievement;

import lombok.NoArgsConstructor;
import team.a501.rif.domain.BaseEntity;
import team.a501.rif.domain.member.Member;
import team.a501.rif.domain.tmp.TempMember;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class AchievementAcq extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Boolean onDisplay;

//    @ManyToOne
//    private Member member; // 멤버가 삭제되면 업적획득도 사라져야한다

    @ManyToOne
    private TempMember member; // todo TempMember로 테스트 후 다시 Member로 변경하기

    // todo 업적이 삭제 되더라도 멤버가 삭제되지 않았다면 Null로 대체 되거나 디폴트 값으로 변경?
    @ManyToOne
    private Achievement achievement;

    public Long getId() {
        return id;
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
}
