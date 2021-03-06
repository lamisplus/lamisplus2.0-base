package org.lamisplus.modules.base.repository;

import org.lamisplus.modules.base.domain.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    Optional<RolePermission> findByRoleIdAndPermissionId(Long roleId, Long permissionId);

    List<RolePermission> findAllByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);
}