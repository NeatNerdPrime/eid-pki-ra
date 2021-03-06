package be.fedict.eid.pkira.integration.xkms;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import be.fedict.eid.pkira.crypto.certificate.CertificateInfo;
import be.fedict.eid.pkira.crypto.certificate.CertificateParserImpl;
import be.fedict.eid.pkira.crypto.csr.CSRInfo;
import be.fedict.eid.pkira.crypto.exception.CryptoException;
import be.fedict.eid.pkira.xkmsws.XKMSClient;
import be.fedict.eid.pkira.xkmsws.XKMSClientException;

public class XKMSIntegrationTest {

	// private static class KeyValueKeySelector extends KeySelector {
	//
	// @Override
	// public KeySelectorResult select(KeyInfo keyInfo, KeySelector.Purpose
	// purpose, AlgorithmMethod method,
	// XMLCryptoContext context) throws KeySelectorException {
	// return new KeySelectorResult() {
	//
	// @Override
	// public Key getKey() {
	// try {
	// KeyStore keystore = KeyStore.getInstance("pkcs12");
	// keystore.load(new FileInputStream("src/test/resources/gov-test.p12"),
	// "G0vT3st".toCharArray());
	//
	// String alias = keystore.aliases().nextElement();
	// return keystore.getCertificate(alias).getPublicKey();
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// }
	// }
	// };
	// }
	// }

	private static final String PARAMETER_XKMS_URL = "xkms.url";

	private static BigInteger serialNumber;
	private static String DATE_STR = new SimpleDateFormat("yyyyMMdd-HHmmss-").format(new Date());

	private CertificateParserImpl certificateParser;
	private XKMSClient xkmsClient;
	private Map<String, String> xkmsClientParameters;

	@BeforeMethod
	public void setup() throws IOException {
		// Create the mocks
		MockitoAnnotations.initMocks(this);

		// Create parsers
		certificateParser = new CertificateParserImpl();

		// Load the properties
		Properties properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream("xkms-integration.properties"));

		// Set http.proxy properties on System
		for (Object propertyKey : properties.keySet()) {
			String propertyName = propertyKey.toString();
			if (propertyName.startsWith("http.proxy")) {
				System.setProperty(propertyName, properties.getProperty(propertyName));
			}
		}

		// Create the XKMS client
		xkmsClientParameters = new HashMap<String, String>();
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			xkmsClientParameters.put((String) entry.getKey(), (String) entry.getValue());
		}
		xkmsClient = new XKMSClient(properties.getProperty(PARAMETER_XKMS_URL), xkmsClientParameters);
	}

	@Test(enabled = false)
	public void testXKMSCreateCertificate() throws XKMSClientException, CryptoException {
		// Generate a CSR and validate it
		CSRInfo csrInfo = Util.generateCSR();

		assertNotNull(csrInfo);
		assertTrue(csrInfo.getSubject().startsWith(Util.DN_PREFIX));
		assertTrue(csrInfo.getSubject().endsWith(Util.DN_SUFFIX));

		new File("tests").mkdir();
		xkmsClientParameters.put(XKMSClient.PARAMETER_LOG_PREFIX, "tests/" + DATE_STR + "certificate-request");

		// Generate the certificate
		byte[] certificate = xkmsClient.createCertificate(csrInfo.getDerEncoded(), 15, "persons", 5);
		assertNotNull(certificate);

		CertificateInfo certificateInfo = certificateParser.parseCertificate(certificate);
		System.out.println("DN: " + certificateInfo.getDistinguishedName());
		System.out.println("Issuer: " + certificateInfo.getIssuer());
		System.out.println("Serial number: " + certificateInfo.getSerialNumber());

		// assertEquals(certificateInfo.getDistinguishedName(),
		// csrInfo.getSubject());
		serialNumber = certificateInfo.getSerialNumber();
	}

	// @Test(dependsOnMethods = "testXKMSCreateCertificate", alwaysRun = true)
	// public void validateXKMSCreateCertificateSignature() throws Exception {
	// validateSignature("tests/" + DATE_STR +
	// "certificate-request-signed.xml");
	// }

	@Test(enabled = false, dependsOnMethods = "testXKMSCreateCertificate")
	public void testXKMSRevokeCertificateRequest() throws XKMSClientException, CryptoException {
		// Revoke the certificate
		xkmsClientParameters.put(XKMSClient.PARAMETER_LOG_PREFIX, "tests/" + DATE_STR + "certificate-revocation");
		xkmsClient.revokeCertificate(serialNumber, "client");
	}

	// @Test(dependsOnMethods = "testXKMSRevokeCertificateRequest", alwaysRun =
	// true)
	// public void validateXKMSRevokeCertificateSignature() throws Exception {
	// validateSignature("tests/" + DATE_STR + "revocation-request-signed.xml");
	// }

	// private void validateSignature(String xmlFileName) throws Exception {
	// System.err.println("Validating " + xmlFileName + "...");
	//
	// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	// dbf.setNamespaceAware(true);
	// DocumentBuilder builder = dbf.newDocumentBuilder();
	// Document doc = builder.parse(new File(xmlFileName));
	//
	// NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS,
	// "Signature");
	// if (nl.getLength() == 0) {
	// throw new Exception("Cannot find Signature element");
	// }
	//
	// DOMValidateContext valContext = new DOMValidateContext(new
	// KeyValueKeySelector(), nl.item(0));
	// XMLSignatureFactory factory = XMLSignatureFactory.getInstance("DOM");
	// XMLSignature signature = factory.unmarshalXMLSignature(valContext);
	//
	// boolean sv = signature.getSignatureValue().validate(valContext);
	// System.err.println("Signature validation status: " + sv);
	//
	// @SuppressWarnings("rawtypes")
	// Iterator i = signature.getSignedInfo().getReferences().iterator();
	// for (int j = 0; i.hasNext(); j++) {
	// Reference reference = (Reference) i.next();
	// boolean refValid = reference.validate(valContext);
	// System.err.println("Reference " + j + ", " + reference.getURI() +
	// ": validity status: " + refValid);
	// }
	//
	// assertTrue(signature.validate(valContext));
	// }
}
