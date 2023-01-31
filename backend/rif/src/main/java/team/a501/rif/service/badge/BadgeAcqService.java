package team.a501.rif.service.badge;

import team.a501.rif.domain.badge.BadgeAcq;

import java.util.List;

public interface BadgeAcqService {

    BadgeAcq save(String memberId, Long badgeId) ;

    List<BadgeAcq> findByMemberUid(String memberUid);
}
