/*
 * eID PKI RA Project.
 * Copyright (C) 2010 FedICT.
 * 
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version
 * 3.0 as published by the Free Software Foundation.
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, see
 * http://www.gnu.org/licenses/.
 */
package be.fedict.eid.blm.model.eiddss;

import javax.ejb.Local;

import be.fedict.eid.blm.model.contracthandler.ContractHandlerBeanException;

/**
 * @author Jan Van den Bergh
 *
 */
@Local
public interface SignatureVerification {

	/**
	 * Verifies the signature of the message. If this signature is correct, the
	 * identity of the sender is returned.
	 * 
	 * @param requestMessage
	 *            the message that is signed (base64 encoded).
	 * @return the identity (RRN) of the signer of the document when the
	 *         signature is valid.
	 * @throws ContractHandlerBeanException
	 *             when the signature is invalid.
	 */
	String verifySignature(String requestMessage) throws ContractHandlerBeanException;

}