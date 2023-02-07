package team.a501.rif.service.riflog;

import team.a501.rif.domain.riflog.RifLog;
import team.a501.rif.dto.riflog.RifLogInfo;
import team.a501.rif.dto.riflog.RifLogSaveRequest;

public interface RifLogService {

    RifLogInfo save(RifLogSaveRequest dto);
}
