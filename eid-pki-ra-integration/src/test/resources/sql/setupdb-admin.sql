-- Clear the database
DELETE FROM CERTIFICATE;
DELETE FROM CONTRACT;
DELETE FROM REGISTRATION;
DELETE FROM CERTIFICATEDOMAIN;
DELETE FROM USER;
DELETE FROM CONFIGURATION;

-- Insert the test user
INSERT INTO USER (USER_ID, LAST_NAME, FIRST_NAME, NATIONAL_REGISTER_NUMBER, IS_ADMIN) VALUES(5001, 'User', 'Test', '99999999999', 1);

-- Certificate domains
INSERT INTO CertificateDomain (CERTIFICATE_DOMAIN_ID, CERTIFICATE_DOMAIN_NAME, DN_EXPRESSION, CLIENTCERT, SERVERCERT, CODECERT) VALUES (1001, 'eHealth Client Certificates', 'c=be,ou=eHealth,uid=*', 1, 0, 0);
INSERT INTO CertificateDomain (CERTIFICATE_DOMAIN_ID, CERTIFICATE_DOMAIN_NAME, DN_EXPRESSION, CLIENTCERT, SERVERCERT, CODECERT) VALUES (1002, 'eHealth Server Certificates', 'c=be,ou=eHealth,cn=*', 0, 1, 0);
INSERT INTO CertificateDomain (CERTIFICATE_DOMAIN_ID, CERTIFICATE_DOMAIN_NAME, DN_EXPRESSION, CLIENTCERT, SERVERCERT, CODECERT) VALUES (1003, 'eHealth Code Signing Certificates', 'c=be,ou=eHealth,uid=*', 0, 0, 1);
INSERT INTO CertificateDomain (CERTIFICATE_DOMAIN_ID, CERTIFICATE_DOMAIN_NAME, DN_EXPRESSION, CLIENTCERT, SERVERCERT, CODECERT) VALUES (1004, 'Test', 'O=ACA,C=BE', 1, 1, 1);

-- Configuration
INSERT INTO CONFIGURATION (ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2001, 'MAIL_SERVER', 'test');
INSERT INTO CONFIGURATION (ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2002, 'MAIL_PORT', '1111');
INSERT INTO CONFIGURATION (ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2003, 'NOTIFICATION_MAIL_DAYS', '14');
INSERT INTO CONFIGURATION (ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2004, 'VALIDITY_PERIODS', '6,12,15,24,36,120');
INSERT INTO CONFIGURATION (ENTRY_ID, ENTRY_KEY, ENTRY_VALUE) VALUES(2005, 'DSS_WS_LOCATION', 'http://localhost:8080/dds');
