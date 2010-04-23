-- Clear the database
DELETE FROM REPORT_ENTRY;
DELETE FROM CERTIFICATE;
DELETE FROM CONTRACT;
DELETE FROM REGISTRATION;
DELETE FROM CERTIFICATEDOMAIN;
DELETE FROM USER;
DELETE FROM CONFIGURATION;
DELETE FROM CA_PARAMETERS;
DELETE FROM CA;

-- Configuration
INSERT INTO CONFIGURATION(ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2001, 'MAIL_SERVER', 'mail.aca-it.be');
INSERT INTO CONFIGURATION(ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2002, 'MAIL_PORT', '25');
INSERT INTO CONFIGURATION(ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2003, 'NOTIFICATION_MAIL_DAYS', 7);
INSERT INTO CONFIGURATION(ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2004, 'VALIDITY_PERIODS', 15);
INSERT INTO CONFIGURATION(ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2005, 'DSS_SERVLET', 'https://www.e-contract.be/eid-dss/dss-post-entry');
INSERT INTO CONFIGURATION(ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2006, 'DSS_WS_CLIENT', 'http://www.e-contract.be/eid-dss-ws/dss');
INSERT INTO CONFIGURATION(ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2007, 'IDP_SERVLET', 'https://www.e-contract.be/eid-idp-sp/saml-request');
INSERT INTO CONFIGURATION(ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2008, 'IDP_DESTINATION', 'https://www.e-contract.be/eid-idp/protocol/saml2');

-- Certificate authorities
INSERT INTO CA(CA_ID, NAME, XKMS_URL, LEGAL_NOTICE) VALUES (5001, 'Test CA', 'http://localhost:8080/xkms/xkms', 'Test Legal Notice');
INSERT INTO CA_PARAMETERS(CA_CA_ID, mapkey, element) VALUES (5001, 'buc', '8047651269');
INSERT INTO CA_PARAMETERS(CA_CA_ID, mapkey, element) VALUES (5001, 'signing.provider', 'be.fedict.eid.pkira.xkmsws.keyinfo.SigningKeyKeyStoreProvider');
INSERT INTO CA_PARAMETERS(CA_CA_ID, mapkey, element) VALUES (5001, 'signing.keystore.url', 'file:/C:/Dev/Fedict/eid-pki-ra/eid-pki-ra/eid-pki-ra-client-xkms/target/test-classes/test.jks');
INSERT INTO CA_PARAMETERS(CA_CA_ID, mapkey, element) VALUES (5001, 'signing.keystore.entry', 'test');
INSERT INTO CA_PARAMETERS(CA_CA_ID, mapkey, element) VALUES (5001, 'signing.keystore.password', 'changeit');
INSERT INTO CA_PARAMETERS(CA_CA_ID, mapkey, element) VALUES (5001, 'signing.keystore.entry.password', 'changeit');

-- Insert the test user
INSERT INTO USER (USER_ID, LAST_NAME, FIRST_NAME, NATIONAL_REGISTER_NUMBER, IS_ADMIN) VALUES(5001, 'Puk', 'Pietje', '99123129212', 0);
