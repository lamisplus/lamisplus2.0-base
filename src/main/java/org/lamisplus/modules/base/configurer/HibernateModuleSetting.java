package org.lamisplus.modules.base.configurer;

import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModuleSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HibernateModuleSetting extends AcrossHibernateJpaModuleSettings {
    @Override
        public String getPersistenceUnitName() {
            String persistenceUnitName = super.getPersistenceUnitName ();
            log.info ("persistenceUnitName {} " , persistenceUnitName);
            return persistenceUnitName;
        }

    @Override
    public Boolean getPrimary() {
        Boolean primary = super.getPrimary ();
        log.info ("primary {}", primary );
        return primary;
    }
}
