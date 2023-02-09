package team.a501.rif.service.riflog;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import team.a501.rif.dto.riflog.RifLogInfo;
import team.a501.rif.dto.riflog.RifLogSaveRequest;

public interface RifLogService {

    RifLogInfo save(RifLogSaveRequest dto);

    Slice<RifLogInfo> findByMember(String memberId, Pageable pageable);
}
