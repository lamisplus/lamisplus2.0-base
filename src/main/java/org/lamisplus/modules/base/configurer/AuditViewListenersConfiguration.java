package org.lamisplus.modules.base.configurer;

import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.PrePersistListener;
import com.blazebit.persistence.view.PreRemoveListener;
import com.blazebit.persistence.view.PreUpdateListener;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.base.domain.views.AuditableView;
import org.lamisplus.modules.base.security.SecurityUtils;

import javax.persistence.EntityManager;
import java.util.Date;

@Slf4j
public class AuditViewListenersConfiguration<T> implements PrePersistListener<T>, PreUpdateListener<T>, PreRemoveListener<T> {

    @Override
    public void preUpdate(EntityViewManager entityViewManager, EntityManager entityManager, Object view) {

        if (AuditableView.class.isAssignableFrom(view.getClass())) {
            AuditableView auditable = (AuditableView) view;
            auditable.setLastModifiedDate(new Date());
            String currentUser = SecurityUtils.getCurrentUserLogin ().orElse ("");
            auditable.setLastModifiedBy(currentUser);
        }
    }

    @Override
    public void prePersist(EntityViewManager entityViewManager, EntityManager entityManager, Object view) {

        if (AuditableView.class.isAssignableFrom(view.getClass())) {
            String currentUser = SecurityUtils.getCurrentUserLogin ().orElse ("");
            AuditableView auditable = (AuditableView) view;
            Date date = new Date();
            auditable.setCreatedDate(date);
            auditable.setLastModifiedDate(date);
            auditable.setLastModifiedBy(currentUser);
            auditable.setCreatedBy(currentUser);
            auditable.setArchived(false);
        }
    }

    @Override
    public boolean preRemove(EntityViewManager entityViewManager, EntityManager entityManager, Object view) {
        String currentUser = SecurityUtils.getCurrentUserLogin ().orElse ("");
        if (AuditableView.class.isAssignableFrom(view.getClass())) {
            AuditableView auditable = (AuditableView) view;
            auditable.setLastModifiedDate(new Date());
            auditable.setLastModifiedBy(currentUser);
            auditable.setArchived(true);
        }
        return false;
    }
}
