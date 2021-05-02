package io.core.chat.service.serviceImpl;

import io.core.chat.constants.Constants;
import io.core.chat.dto.TeamDetailJson;
import io.core.chat.model.TeamDetail;
import io.core.chat.model.UserTeamMapping;
import io.core.chat.repository.TeamsRepository;
import io.core.chat.repository.UserTeamMappingRepository;
import io.core.chat.response.ResponseCodeJson;
import io.core.chat.response.UniversalResponse;
import io.core.chat.service.TeamsService;
import io.core.chat.utils.AtomicIdCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TeamsServiceImpl implements TeamsService, Constants {

    @Autowired
    TeamsRepository teamsRepository;
    @Autowired
    UserTeamMappingRepository utmp;

    @Override
    public UniversalResponse createTeam(TeamDetailJson teamDetailJson) {
        UniversalResponse ur = new UniversalResponse();
        Optional<TeamDetail> optionalTeamDetail = teamsRepository.findByTeamNameAndDeleted(teamDetailJson.getTeamName(), NonDeleted);
        if (optionalTeamDetail.isPresent()) {
            ur.setResponseCodeJson(new ResponseCodeJson("team already present", 400));
            return ur;
        }
        long uniqueId = AtomicIdCounter.getUniqueID();
        TeamDetail teamDetail = new TeamDetail();
        teamDetail.setTeamId(uniqueId);
        teamDetailJson.setTeamId(uniqueId);
        teamDetail.setCompanyId(teamDetailJson.getCompanyId());
        teamDetail.setCreatedById(teamDetailJson.getCreatedById());
        teamDetail.setCreatedDate(new Date(System.currentTimeMillis()));
        teamDetail.setDeleted(NonDeleted);
        teamDetail.setLastUpdatedById(teamDetailJson.getCreatedById());
        teamDetail.setLastUpdatedDate(new Date(System.currentTimeMillis()));
        teamDetail.setTeamDescription(teamDetailJson.getTeamDescription());
        teamDetail.setTeamName(teamDetailJson.getTeamName());
        teamDetail.setTeamPhoto(teamDetailJson.getTeamPhoto());
        teamsRepository.save(teamDetail);
        ur.setObject(teamDetailJson);
        ur.setResponseCodeJson(new ResponseCodeJson("team successfully created", 200));
        return ur;
    }

    @Override
    public ResponseCodeJson updateTeam(TeamDetailJson teamDetailJson) {
        UniversalResponse ur = new UniversalResponse();
        Optional<TeamDetail> optionalTeamDetail = teamsRepository.findByTeamNameAndDeleted(teamDetailJson.getTeamName(), NonDeleted);
        if (optionalTeamDetail.isPresent()) {
            return new ResponseCodeJson("team already present", 400);
        }
        TeamDetail teamDetail = optionalTeamDetail.get();
        teamDetail.setDeleted(teamDetailJson.getDeleted());
        teamDetail.setLastUpdatedById(teamDetailJson.getCreatedById());
        teamDetail.setLastUpdatedDate(new Date(System.currentTimeMillis()));
        teamDetail.setTeamDescription(teamDetailJson.getTeamDescription());
        teamDetail.setTeamName(teamDetailJson.getTeamName());
        teamDetail.setTeamPhoto(teamDetailJson.getTeamPhoto());
        teamsRepository.save(teamDetail);
        return new ResponseCodeJson("team updated successfully", 200);
    }
}
