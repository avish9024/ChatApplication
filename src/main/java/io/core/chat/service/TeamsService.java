package io.core.chat.service;

import io.core.chat.dto.TeamDetailJson;
import io.core.chat.response.ResponseCodeJson;
import io.core.chat.response.UniversalResponse;

public interface TeamsService {

    UniversalResponse createTeam(TeamDetailJson teamDetailJson);

    ResponseCodeJson updateTeam(TeamDetailJson teamDetailJson);
}
