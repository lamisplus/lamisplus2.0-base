package org.lamisplus.modules.base.configurer;

import com.foreach.across.core.annotations.ModuleConfiguration;
import com.foreach.across.modules.hibernate.provider.HibernatePackageConfigurer;
import com.foreach.across.modules.hibernate.provider.HibernatePackageRegistry;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.base.domain.entity.BaseDomain;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * The AcrossHibernateJpaModule sets up a shared EntityManager that multiple modules can use (with Hibernate).
 * Using the shared EntityManager in this case simplifies transaction management on the same database,
 * but there is nothing against a module defining its own EntityManager however.
 * <p/>
 * If a module wants to map its entities on the shared EntityManager, it must tell the AcrossHibernateJpaModule
 * where to scan for additional entities.  That's the purpose of this class.
 */
@ModuleConfiguration("BaseJpaModule")
@EntityScan(basePackages = {"org.lamisplus.modules.base.domain.entity"})
@Slf4j
public class BaseEntityScanConfiguration implements HibernatePackageConfigurer {
    public BaseEntityScanConfiguration() {
    }

    public void configureHibernatePackage(HibernatePackageRegistry hibernatePackageRegistry) {
        hibernatePackageRegistry.addPackageToScan(BaseDomain.class);
    }
}
