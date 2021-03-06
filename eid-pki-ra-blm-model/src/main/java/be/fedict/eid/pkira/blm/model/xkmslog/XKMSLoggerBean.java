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

package be.fedict.eid.pkira.blm.model.xkmslog;

import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.Name;

import be.fedict.eid.pkira.xkmsws.XKMSLogger;

@Stateless
@Name(XKMSLogger.NAME)
public class XKMSLoggerBean implements XKMSLogger {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void logError(XKMSMessageType messageType, String requestMessage, byte[] responseMessage, Throwable t) {
		XKMSStatus status;
		if (requestMessage == null) {
			status=XKMSStatus.MARSHAL_ERROR;
		} else if (responseMessage == null) {
			status=XKMSStatus.XKMS_SERVICE_ERROR;
		} else {
			status=XKMSStatus.RESULT_ERROR;
		}
		
		saveLogEntry(messageType, requestMessage, responseMessage, t, status);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void logSuccesfulInteraction(XKMSMessageType messageType, String requestMessage, byte[] responseMessage) {
		saveLogEntry(messageType, requestMessage, responseMessage, null, XKMSStatus.SUCCESS);
	}

	private void saveLogEntry(XKMSMessageType messageType, String requestMessage, byte[] responseMessage, Throwable t, XKMSStatus status) {
		XKMSLogEntry logEntry = new XKMSLogEntry();
		logEntry.setMessageType(messageType);
		logEntry.setCreationDate(new Date());
		logEntry.setRequestMessage(requestMessage);
		logEntry.setResponseMessage(responseMessage == null ? null : new String(responseMessage));
		logEntry.setStatus(status);
		logEntry.setErrorMessage(t==null ? null : t.getMessage());
		
		entityManager.persist(logEntry);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
