BoozeLogger
===========

Data logger and storage API engine for fermentation and distillation

- Requires:
-   PostgreSQL 9.x (tested with 9.3)
-   Maven 3
-   OpenLDAP
-   Tomcat 7.0.x (Tested with 7.0.53)

All builds and testing conducted under Ubuntu 14.04 LTS 64-bit

Installation procedures:

1) Install all prerequisities and configure for your environment
2) Configure the GrandUnification prerequisites (LDAP, etc.)
3) Create a postgres database named 'boozelogger' and configure a role to own the schema
with full permissions
4) Load entity/src/main/sql/boozelogger.sql into the database (this creates the schema)
5) Build and deploy the Maven artifacts
6) Deploy the WAR file into tomcat

NOTE: The following parameters are loaded at runtime from the system environment, and must be
populated for the system to operate:
-Dhibernate.dialect=org.hibernate.dialect.PostgreSQL82Dialect
-Djavax.persistence.jdbc.driver=org.postgresql.Driver
-Djavax.persistence.jdbc.url=jdbc:postgresql://localhost:5432/boozelogger
-Djavax.persistence.jdbc.user=boozelogger
-Djavax.persistence.jdbc.password=boozelogger
-Djpa.persistence.unit=boozelogger
-DLDAP_URL=ldap://localhost:389
-DLDAP_ADMIN_DN="cn=admin,dc=javaunification,dc=org"
-DLDAP_ADMIN_PW=secret
-DAPI_VERSION=1.0

Set them in the launch configuration for Tomcat and use values that match your environmetn.
This is designed to allow for easy integration into Elastic Beanstalk or other
automated deployment environments.

