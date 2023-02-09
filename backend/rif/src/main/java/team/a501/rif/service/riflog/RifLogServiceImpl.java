package team.a501.rif.service.riflog;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.member.Member;
import team.a501.rif.domain.riflog.RifLog;
import team.a501.rif.dto.riflog.RifLogInfo;
import team.a501.rif.dto.riflog.RifLogSaveRequest;
import team.a501.rif.exception.ErrorCode;
import team.a501.rif.exception.RifCustomException;
import team.a501.rif.repository.member.MemberRepository;
import team.a501.rif.repository.riflog.RifLogRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RifLogServiceImpl implements RifLogService {

    private final MemberRepository memberRepository;

    private final RifLogRepository rifLogRepository;

    @Override
    public RifLogInfo save(RifLogSaveRequest dto) {
        Member member = memberRepository.findByUid(dto.getUid())
                .orElseThrow(() -> new RifCustomException(ErrorCode.ENTITY_INSTANCE_NOT_FOUND));

        RifLog rifLog = rifLogRepository.save(RifLog.builder()
                .plasticTotal(dto.getPlasticTotal())
                .plasticOk(dto.getPlasticOk())
                .recycleTotal(dto.getRecycleTotal())
                .recycleOk(dto.getRecycleOk())
                .build());
        member.addRifLog(rifLog);

        return RifLogInfo.from(rifLog);
    }

    @Override
    public Slice<RifLogInfo> findByMember(String memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RifCustomException(ErrorCode.ENTITY_INSTANCE_NOT_FOUND));

        Slice<RifLog> rifLogSlice = rifLogRepository.findByMember(member, pageable);

        Slice<RifLogInfo> rifLogInfoSlice = rifLogSlice.map(RifLogInfo::from);

        return rifLogInfoSlice;
    }
}
