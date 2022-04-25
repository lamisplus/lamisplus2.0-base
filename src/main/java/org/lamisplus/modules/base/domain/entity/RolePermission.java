package org.lamisplus.modules.base.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "role_permission")
@IdClass(RolePermissionPK.class)
public class RolePermission{
    @Column(name = "role_id")
    @Id
    private Long roleId;

    @Column(name = "permission_id")
    @Id
    private Long permissionId;
}