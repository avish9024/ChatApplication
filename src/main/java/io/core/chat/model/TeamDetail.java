package io.core.chat.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TEAMS", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class TeamDetail {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "teamId")
    private long teamId;

    @Column(name = "teamName")
    private String teamName;

    @Column(name = "teamDescription")
    private String teamDescription;

    @Column(name = "teamPhoto")
    private String teamPhoto;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "createdById")
    private long createdById;

    @Column(name = "lastUpdatedDate")
    private Date lastUpdatedDate;

    @Column(name = "lastUpdatedById")
    private long lastUpdatedById;

    @Column(name = "deleted")
    private int deleted;

    @Column(name = "companyId")
    private long companyId;
}
