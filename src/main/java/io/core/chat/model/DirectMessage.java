package io.core.chat.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "DIRECT_MESSAGE", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class DirectMessage {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "messageId")
    private long messageId;

    @Column(name = "mappingId")
    private long mappingId;

    @Column(name = "messageType")
    private String messageType;

    @Column(name = "message")
    private String message;

    @Column(name = "messageById")
    private long messageById;

    @Column(name = "companyId")
    private long companyId;

    @Column(name = "sentDate")
    private Date sentDate;

    @Column(name = "deleted")
    private int deleted;
}
