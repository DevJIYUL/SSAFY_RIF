package team.a501.rif.acq.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Achievement {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private String achievementImgPath;

    @OneToMany(mappedBy = "achievement")
    private List<AchievementAcq> achievementAcqs;
}
