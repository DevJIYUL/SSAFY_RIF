package team.a501.rif.domain.badge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.List;

@Getter
public class Badges {

    private List<Badge> badges;

    public Badges(List<Badge> badges){
        this.badges = badges;
    }

    public Long randomBadgeId(){
        return 0L;
    }
}
