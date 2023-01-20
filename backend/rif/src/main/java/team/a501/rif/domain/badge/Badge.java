package team.a501.rif.domain.badge;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Badge {

    @Id
    @GeneratedValue
    private Long id;

    private Integer tier;

    private String description;

    private String badgeImgPath;

    @OneToMany(mappedBy = "badge")
    private List<BadgeAcq> badgeAcqs;

    public void addBadgeAcqs(BadgeAcq acq) {

        acq.setBadge(this);
        badgeAcqs.add(acq);
    }
}
