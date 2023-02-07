package team.a501.rif.service.riflog;

import team.a501.rif.domain.riflog.RifLog;
import team.a501.rif.dto.riflog.RifLogSaveRequest;

import java.util.List;

public interface RifLogService {

    RifLog createRifLog(RifLogSaveRequest dto);
    List<RifLog> findByMember(String memberId);
}
