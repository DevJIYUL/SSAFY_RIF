package team.a501.rif.acq.domain;

import team.a501.rif.auth.domain.Member;

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
    private Member member;

    @ManyToOne
    private Achievement achievement;
}
