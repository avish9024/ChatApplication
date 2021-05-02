package io.core.chat.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_TEAM_MESSAGE", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class UserTeamMessage {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "messageId")
    private long messageId;

    @Column(name = "messageType")
    private String messageType;

    @Column(name = "message")
    private String message;

    @Column(name = "teamId")
    private long teamId;

    @Column(name = "userId")
    private long userId;

    @Column(name = "companyId")
    private long companyId;

    @Column(name = "deleted")
    private int deleted;
}
