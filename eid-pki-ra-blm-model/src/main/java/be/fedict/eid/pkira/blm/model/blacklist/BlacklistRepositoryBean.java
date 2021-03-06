/*
 * eID PKI RA Project. 
 * Copyright (C) 2010-2014 FedICT. 
 *
 * This is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License version 
 * 3.0 as published by the Free Software Foundation. 
 *
 * This software is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * Lesser General Public License for more details. 
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this software; if not, see 
 * http://www.gnu.org/licenses/.
 */
package be.fedict.eid.pkira.blm.model.blacklist;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.seam.annotations.Name;

import be.fedict.eid.pkira.blm.model.usermgmt.Registration;

@Stateless
@Name(BlacklistRepository.NAME)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BlacklistRepositoryBean implements BlacklistRepository {

	@PersistenceContext
	private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<BlacklistItem> getAllBlacklistItemsForRegistration(Registration registration) {
        Query query = entityManager.createNamedQuery("FIND_GLOBAL_OR_BYUSER_OR_BYCERTIFICATEDOMAIN");
        query.setParameter("user", registration.getRequester());
        query.setParameter("certificateDomain", registration.getCertificateDomain());

        return query.getResultList();
    }

    /*
     * For testing.
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
