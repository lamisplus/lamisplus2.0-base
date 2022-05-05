package org.lamisplus.modules.base.configurer;

import com.foreach.across.core.transformers.BeanPrefixingTransformer;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;

public class JPAConfig extends AcrossHibernateJpaModule {

    public static final String NAME = "JPAConfig";

    public JPAConfig() {
        setPropertiesPrefix( "jpa" );
        setExposeTransformer( new BeanPrefixingTransformer ("lamis" ) );
        setPrimary( true );
    }

    @Override
    public String getName() {
         return NAME;
    }
}
