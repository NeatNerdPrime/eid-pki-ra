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
package be.fedict.eid.pkira.privatews;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import be.fedict.eid.pkira.generated.privatews.CertificateWS;
import be.fedict.eid.pkira.generated.privatews.EIDPKIRAPrivatePortType;
import be.fedict.eid.pkira.generated.privatews.FindCertificatesRequest;
import be.fedict.eid.pkira.generated.privatews.FindCertificatesResponse;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Unit test for the web service client.
 * 
 * @author Jan Van den Bergh
 */
public class EIDPKIRAPrivateServiceClientTest {

	private EIDPKIRAPrivatePortType port;
	private EIDPKIRAPrivateServiceClient serviceClient;
	
	@BeforeMethod
	public void setup() {
		port = mock(EIDPKIRAPrivatePortType.class);
		
		serviceClient = new EIDPKIRAPrivateServiceClient();
		serviceClient.setWebservicePort(port);
	}
	
	@Test
	public void testGetCertificates() {
		// Prepare
		FindCertificatesResponse response = new FindCertificatesResponse();
		CertificateWS testCertificate = new CertificateWS();
		response.getCertificates().add(testCertificate);
		String userRRN = "testRRN";
		
		when(port.findCertificates(isA(FindCertificatesRequest.class))).thenReturn(response);
		
		// Call
		List<CertificateWS> result = serviceClient.findCertificates(userRRN, null, null, null);
		
		// Verify
		verify(port).findCertificates(isA(FindCertificatesRequest.class));
		verifyNoMoreInteractions(port);
		
		assertNotNull(result);
		assertEquals(result.size(), 1);
		assertSame(result.get(0), testCertificate);
	}
}
