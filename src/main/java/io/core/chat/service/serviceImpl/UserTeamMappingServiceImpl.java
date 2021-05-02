package io.core.chat.service.serviceImpl;

import io.core.chat.response.ResponseCodeJson;
import io.core.chat.service.UserTeamMappingService;
import org.springframework.stereotype.Service;

@Service
public class UserTeamMappingServiceImpl implements UserTeamMappingService {
    @Override
    public ResponseCodeJson addMemberInTeam(long teamId, long userId, long companyId) {
        return null;
    }
}
