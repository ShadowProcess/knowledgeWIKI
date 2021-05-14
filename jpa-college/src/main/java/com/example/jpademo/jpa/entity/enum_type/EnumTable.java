package com.example.jpademo.jpa.entity.enum_type;


import javax.persistence.*;

@Table(name = "enum_table")
@Entity
public class EnumTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private AuditState auditState; //mysql为int类型

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuditState getAuditState() {
        return auditState;
    }

    public void setAuditState(AuditState auditState) {
        this.auditState = auditState;
    }
}
