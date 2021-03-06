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

package be.fedict.eid.pkira.portal.certificate;

/**
 * @author Bram Baeyens
 *
 */
public enum CertificateType {
	
	SERVER("Server", "certificate.type.server"), 
	CLIENT("Client", "certificate.type.client"), 
	CODE("Code", "certificate.type.code"),
	PERSONS("Persons", "certificate.type.persons");
	
	private final String type;
	private final String messageKey;
	
	private CertificateType(String type, String messageKey){
		this.type = type;
		this.messageKey = messageKey;
	}
	
	@Override
	public String toString(){
		return type;
	}
	
	public String getMessageKey() {
		return messageKey;
	}
	
}
