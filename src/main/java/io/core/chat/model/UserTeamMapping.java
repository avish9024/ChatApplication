package io.core.chat.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "USER_TEAM_MAPPING", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class UserTeamMapping {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "mappingId")
    private long mappingId;

    @Column(name = "teamId")
    private long teamId;

    @Column(name = "userId")
    private long userId;

    @Column(name = "companyId")
    private long companyId;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "deleted")
    private int deleted;
}
