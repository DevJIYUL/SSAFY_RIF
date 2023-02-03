package team.a501.rif.service.riflog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.member.Member;
import team.a501.rif.domain.riflog.RifLog;
import team.a501.rif.dto.riflog.RifLogSaveRequest;
import team.a501.rif.repository.member.MemberRepository;
import team.a501.rif.repository.riflog.RifLogRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class RifLogServiceImpl implements RifLogService {

    private final MemberRepository memberRepository;

    private final RifLogRepository rifLogRepository;

    @Override
    @Transactional
    public RifLog createRifLog(RifLogSaveRequest dto) {
        Member member = memberRepository.findByUid(dto.getMemberUid())
                .orElseThrow(() -> new NoSuchElementException());

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
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());

        return member.getRifLogs();
    }
}
