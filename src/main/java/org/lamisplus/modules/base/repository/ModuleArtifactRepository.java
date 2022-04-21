package org.lamisplus.modules.base.repository;

import org.lamisplus.modules.base.domain.entity.ModuleArtifact;
import org.lamisplus.modules.base.domain.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModuleArtifactRepository extends JpaRepository<ModuleArtifact, Long> {
    Optional<ModuleArtifact> findByModule(Module module);
}
