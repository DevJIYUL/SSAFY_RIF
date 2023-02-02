package team.a501.rif.service.riflog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.member.Member;
import team.a501.rif.domain.riflog.RifLog;
import team.a501.rif.dto.riflog.RifLogSaveRequest;
import team.a501.rif.repository.riflog.RifLogRepository;
import team.a501.rif.service.member.MemberService;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RifLogServiceImpl implements RifLogService{

    private final MemberService memberService;

    private final RifLogRepository rifLogRepository;

    @Override
    @Transactional
    public RifLog createRifLog(RifLogSaveRequest dto) {
        Member member = memberService.findByUid(dto.getMemberUid());
        RifLog rifLog = rifLogRepository.save(RifLog.builder()
                .plasticTotal(dto.getPlasticTotal())
                .plasticOk(dto.getPlasticOk())
                .recycleTotal(dto.getRecycleTotal())
                .recycleOk(dto.getRecycleOk())
                .adviseIgnored(dto.getAdviseIgnored())
                .build());
        member.addRifLog(rifLog);
        return rifLog;
    }

    @Override
    @Transactional
    public List<RifLog> findByMember(String memberId) {
        Member member = memberService.findById(memberId);
        return member.getRifLogs();
    }
}
