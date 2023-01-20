package team.a501.rif.domain.achievement;

import team.a501.rif.domain.member.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AchievementAcq {

    @Id
    @GeneratedValue
    private Long id;

    private Boolean onDisplay;

    @ManyToOne
    private Member member; // 멤버가 삭제되면 업적획득도 사라져야한다

    // todo 업적이 삭제 되더라도 멤버가 삭제되지 않았다면 Null로 대체 되거나 디폴트 값으로 변경?
    @ManyToOne
    private Achievement achievement;
}
