package com.example.exam.entities;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
//    @Temporal(TemporalType.TIMESTAMP)
//    @UpdateTimestamp
    @Column(name = "Create_date")
    private Date CreateDate;

    @Column(name = "Create_By")
    private String CreateBy;
//    @Temporal(TemporalType.TIMESTAMP)
//    @UpdateTimestamp
    @Column(name = "Modifier_date")
    private Date ModifierDate;

    @Column(name = "Modifier_by")
    private String ModifierBy;

    @PrePersist
    public void prePersist(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            this.setCreateBy(userDetails.getUsername());
        }catch (Exception e){
            this.CreateBy ="UnknowUser";
        }
        this.CreateDate = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            this.setModifierBy(userDetails.getUsername());
        }catch (Exception e){
            this.ModifierBy ="UnknowUser";
        }
        this.ModifierDate = new Date();
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    public Date getModifierDate() {
        return ModifierDate;
    }

    public void setModifierDate(Date modifierDate) {
        ModifierDate = modifierDate;
    }

    public String getModifierBy() {
        return ModifierBy;
    }

    public void setModifierBy(String modifierBy) {
        ModifierBy = modifierBy;
    }
}
