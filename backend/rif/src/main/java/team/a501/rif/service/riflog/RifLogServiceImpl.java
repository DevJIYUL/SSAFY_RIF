package team.a501.rif.service.riflog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.member.Member;
import team.a501.rif.domain.riflog.RifLog;
import team.a501.rif.dto.riflog.RifLogInfo;
import team.a501.rif.dto.riflog.RifLogSaveRequest;
import team.a501.rif.exception.ExceptionCode;
import team.a501.rif.exception.RifCustomException;
import team.a501.rif.repository.member.MemberRepository;
import team.a501.rif.repository.riflog.RifLogRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RifLogServiceImpl implements RifLogService {

    private final MemberRepository memberRepository;

    private final RifLogRepository rifLogRepository;

    @Override
    @Transactional
    public RifLogInfo save(RifLogSaveRequest dto) {
        Member member = memberRepository.findByUid(dto.getUid())
                .orElseThrow(() -> new RifCustomException(ExceptionCode.ENTITY_INSTANCE_NOT_FOUND));

        RifLog rifLog = rifLogRepository.save(RifLog.builder()
                .plasticTotal(dto.getPlasticTotal())
                .plasticOk(dto.getPlasticOk())
                .recycleTotal(dto.getRecycleTotal())
                .recycleOk(dto.getRecycleOk())
                .adviceIgnored(dto.getAdviceIgnored())
                .build());
        member.addRifLog(rifLog);

        return RifLogInfo.from(rifLog);
    }
}
