package io.core.chat.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "userId")
    private long userId;

    @Column(name = "companyId")
    private long companyId;

    @Column(name = "userEmailId")
    private String userEmailId = "";

    @Column(name = "firstName")
    private String firstName = "";

    @Column(name = "lastName")
    private String lastName = "";

    @Column(name = "designation")
    private String designation = "";

    @Column(name = "userName")
    private String userName = "";

    @Column(name = "dateOfJoining")
    private Date dateOfJoining;

/*	@Column(name = "TRAILENDDATE")
    private Date trailenddate;*/

    @Column(name = "userPhoto")
    private String userPhoto = "";

    @Column(name = "uphone")
    private String uphone = "";

    @Column(name = "password")
    private String password = "";

    @Column(name = "LinkedinUrl")
    private String linkedinUrl = "";

    @Column(name = "address")
    private String address = "";

    @Column(name = "city")
    private String city = "";

    @Column(name = "state")
    private String state = "";

    @Column(name = "country")
    private String country = "";

    @Column(name = "pin")
    private String pin = "";

    @Column(name = "verified")
    private int verified;

    @Column(name = "verifyDate")
    private Date verifyDate;

    @Column(name = "deleted")
    private int deleted;

    //For role based access 1=Enabled, 0=Not Enabled
    @Column(name = "enabled")
    private int enabled;

    @Column(name = "role")
    private String role ="";
}

