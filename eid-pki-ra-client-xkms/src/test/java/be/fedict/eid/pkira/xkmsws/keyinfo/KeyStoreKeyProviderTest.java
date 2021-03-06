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
package be.fedict.eid.pkira.xkmsws.keyinfo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import be.fedict.eid.pkira.xkmsws.XKMSClientException;

import static org.testng.Assert.assertNotNull;

public class KeyStoreKeyProviderTest {

	@Test
	public void testGetCertificateAndPrivateKey() throws XKMSClientException {
		KeyStoreKeyProvider info = new KeyStoreKeyProvider();

		Map<String, String> parameters = new HashMap<String, String>();
		String url = KeyStoreKeyProviderTest.class.getResource("/test.jks").toExternalForm();
		parameters.put(KeyStoreKeyProvider.PARAMETER_KEYSTORE_TYPE, "JKS");
		parameters.put(KeyStoreKeyProvider.PARAMETER_KEYSTORE_URL, url);
		parameters.put(KeyStoreKeyProvider.PARAMETER_KEYSTORE_ENTRYNAME, "test");
		parameters.put(KeyStoreKeyProvider.PARAMETER_KEYSTORE_PASSWORD, "changeit");
		parameters.put(KeyStoreKeyProvider.PARAMETER_KEYSTORE_ENTRY_PASSWORD, "changeit");
		info.setParameters(parameters);

		assertNotNull(info.getCertificate());
		assertNotNull(info.getPrivateKey());
	}
}
