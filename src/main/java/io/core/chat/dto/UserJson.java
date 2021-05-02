package io.core.chat.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class UserJson {
    private long id;
    private long userId;
    private long companyId;
    private String userEmailId = "";
    private String firstName = "";
    private String lastName = "";
    private String designation = "";
    private String userName = "";
    private Date dateOfJoining;
    private String userPhoto = "";
    private String uphone = "";
    private String password = "";
    private String linkedinUrl = "";
    private String address = "";
    private String city = "";
    private String state = "";
    private String country = "";
    private String pin = "";
    private int verified;
    private Date verifyDate;
    private int deleted;
    private int enabled;
    private String role ="";
}
