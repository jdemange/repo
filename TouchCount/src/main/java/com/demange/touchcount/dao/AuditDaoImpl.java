/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.dao;

import com.demange.touchcount.mapper.AuditMapper;
import com.demange.touchcount.model.Audit;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jocel
 */
public class AuditDaoImpl implements AuditDao {

    private JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_INSERT_AUDIT
            = "insert into audits (loc_name, user_id) "
            + "values (?, ?)";

    private static final String SQL_UPDATE_AUDIT
            = "update audits set "
            + "loc_name = ?, user_id = ? "
            + "where audit_id = ?";

    private static final String SQL_DELETE_AUDIT
            = "delete from audits where audit_id = ?";

    private static final String SQL_GET_ALL_AUDITS
            = "select * from audits";

    private static final String SQL_GET_AUDIT_BY_ID
            = "select * from audits where audit_id = ?";

    private static final String SQL_GET_AUDIT_BY_USER_ID
            = "select * from audits where user_id = ? "
            + "order by loc_name ASC";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Audit addAudit(Audit audit) {
        jt.update(SQL_INSERT_AUDIT,
                audit.getLocName(),
                audit.getUser().getUserId());
        int id = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);
        return getAuditById(id);
    }

    @Override
    public Audit updateAudit(Audit audit) {
        jt.update(SQL_UPDATE_AUDIT,
                audit.getLocName(),
                audit.getUser().getUserId(),
                audit.getAuditId());

        return getAuditById(audit.getAuditId());
    }

    @Override
    public void deleteAudit(int auditId) {
        jt.update(SQL_DELETE_AUDIT, auditId);
    }

    @Override
    public List<Audit> getAllAudits() {
        return jt.query(SQL_GET_ALL_AUDITS, new AuditMapper());
    }

    @Override
    public Audit getAuditById(int auditId) {
        return jt.queryForObject(SQL_GET_AUDIT_BY_ID, new AuditMapper(), auditId);
    }

    @Override
    public List<Audit> getAuditsByUser(int userId) {
        return jt.query(SQL_GET_AUDIT_BY_USER_ID, new AuditMapper(), userId);
    }
}
