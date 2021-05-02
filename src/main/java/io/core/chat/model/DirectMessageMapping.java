package io.core.chat.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "DIRECT_MESSAGE_MAPPING", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class DirectMessageMapping {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "mappingId")
    private long mappingId;

    @Column(name = "userId1")
    private long userId1;

    @Column(name = "userId2")
    private long userId2;

    @Column(name = "companyId")
    private long companyId;

    @Column(name = "deleted")
    private int deleted;

    @Column(name = "createdDate")
    private Date createdDate;
}
