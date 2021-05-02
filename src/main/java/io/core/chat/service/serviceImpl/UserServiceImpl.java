package io.core.chat.service.serviceImpl;

import io.core.chat.constants.Constants;
import io.core.chat.dto.UserJson;
import io.core.chat.model.Users;
import io.core.chat.repository.UserRepository;
import io.core.chat.response.ResponseCodeJson;
import io.core.chat.response.UniversalResponse;
import io.core.chat.service.UserService;
import io.core.chat.utils.AtomicIdCounter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, Constants {

    @Autowired
    UserRepository userRepository;

    @Override
    public UniversalResponse addUser(UserJson userJson) {
        UniversalResponse ur = new UniversalResponse();
        if (userJson == null) {
            ur.setResponseCodeJson(new ResponseCodeJson("empty request", 400));
            return ur;
        }
        Optional<Users> optionalUsers = userRepository.findByUserEmailIdAndDeleted(userJson.getUserEmailId(), NonDeleted);
        if (optionalUsers.isPresent()) {
            ur.setResponseCodeJson(new ResponseCodeJson("user already present", 401));
            return ur;
        }
        Users users = new Users();
        users.setUserEmailId(userJson.getUserEmailId());
        long uniqueId = AtomicIdCounter.getUniqueID();
        users.setUserId(uniqueId);
        userJson.setUserId(uniqueId);
        users.setCompanyId(userJson.getCompanyId());
        users.setAddress(userJson.getAddress());
        users.setCity(userJson.getCity());
        users.setCountry(userJson.getCountry());
        users.setDateOfJoining(new Date(System.currentTimeMillis()));
        users.setDeleted(NonDeleted);
        users.setDesignation(userJson.getDesignation());
        users.setEnabled(NonDeleted);
        users.setFirstName(userJson.getFirstName());
        users.setLastName(userJson.getLastName());
        users.setLinkedinUrl(userJson.getLinkedinUrl());
        users.setPassword(userJson.getPassword());
        users.setPin(userJson.getPin());
        users.setRole(userJson.getRole());
        users.setState(userJson.getState());
        users.setUphone(userJson.getUphone());
        users.setUserName(userJson.getUserName());
        users.setUserPhoto(userJson.getUserPhoto());
        users.setVerified(NOT_VERIFIED);
        users.setVerifyDate(null);
        userRepository.save(users);
        ur.setResponseCodeJson(new ResponseCodeJson("user added", 200));
        ur.setObject(userJson);
        return ur;
    }

    @Override
    public ResponseCodeJson updateUser(UserJson userJson) {
        Optional<Users> optionalUsers = userRepository.findByUserEmailIdAndDeleted(userJson.getUserEmailId(), NonDeleted);
        if (!optionalUsers.isPresent()) {
            return new ResponseCodeJson("user not present", 401);
        }
        Users users = optionalUsers.get();
        users.setAddress(userJson.getAddress());
        users.setCity(userJson.getCity());
        users.setCountry(userJson.getCountry());
        users.setDateOfJoining(new Date(System.currentTimeMillis()));
        users.setDeleted(NonDeleted);
        users.setDesignation(userJson.getDesignation());
        users.setEnabled(NonDeleted);
        users.setFirstName(userJson.getFirstName());
        users.setLastName(userJson.getLastName());
        users.setLinkedinUrl(userJson.getLinkedinUrl());
        users.setPassword(userJson.getPassword());
        users.setPin(userJson.getPin());
        users.setRole(userJson.getRole());
        users.setState(userJson.getState());
        users.setUphone(userJson.getUphone());
        users.setUserName(userJson.getUserName());
        users.setUserPhoto(userJson.getUserPhoto());
        users.setVerified(userJson.getVerified());
        users.setVerifyDate(new Date(System.currentTimeMillis()));
        userRepository.save(users);
        return new ResponseCodeJson("user details updated", 401);
    }

    @Override
    public UniversalResponse getUserDetail(long userId, long companyId) {
        UniversalResponse ur = new UniversalResponse();
        Optional<Users> optionalUsers = userRepository.findByUserIdAndCompanyIdAndDeleted(userId, companyId, NonDeleted);
        if (!optionalUsers.isPresent()) {
            ur.setResponseCodeJson(new ResponseCodeJson("user not present", 401));
            return ur;
        }
        Users users = optionalUsers.get();
        UserJson userJson = new UserJson();
        userJson.setUserEmailId(users.getUserEmailId());
        userJson.setUserId(users.getUserId());
        userJson.setCompanyId(users.getCompanyId());
        userJson.setAddress(users.getAddress());
        userJson.setCity(users.getCity());
        userJson.setCountry(users.getCountry());
        userJson.setDateOfJoining(users.getDateOfJoining());
        userJson.setDeleted(users.getDeleted());
        userJson.setDesignation(users.getDesignation());
        userJson.setEnabled(users.getEnabled());
        userJson.setFirstName(users.getFirstName());
        userJson.setLastName(users.getLastName());
        userJson.setLinkedinUrl(users.getLinkedinUrl());
        userJson.setPassword(users.getPassword());
        userJson.setPin(users.getPin());
        userJson.setRole(users.getRole());
        userJson.setState(users.getState());
        userJson.setUphone(users.getUphone());
        userJson.setUserName(users.getUserName());
        userJson.setUserPhoto(users.getUserPhoto());
        userJson.setVerified(users.getVerified());
        userJson.setVerifyDate(users.getVerifyDate());
        ur.setObject(userJson);
        ur.setResponseCodeJson(new ResponseCodeJson("successfully fetched details", 200));
        return ur;
    }
}
