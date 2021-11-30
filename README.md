# JDBCTest

A small, utility Java class to test JDBC connections to Postgresql with SSL enabled.

## Installation and Use

1. Save/Reformat SSL Keys/Certificates
2. Clone git repository
3. Ensure access to (non-ancient) Java JDK
4. Download PostgreSQL JDBC driver to project directory
5. Update config values in `example.conf` and save into `connection.conf` (**Name Change Important**)
6. Compile the program
7. Run the program

### 1. Save/Reformat SSL Keys/Certificates

Current versions of the SSL keys/certificates should have been provided in PEM format.

This Java class has been tested to work with the PKCS-8 DER format generated with the following command:

`openssl pkcs8 -topk8 -inform PEM -in postgresql.key -outform DER -out postgresql.pk8 -v1 PBE-MD5-DES`

This resource (https://techmonger.github.io/77/odbc-ssl-postgres/) walks through the conversion process in-depth from an ODBC perspective but for PKCS12 format.

### 2. Clone git repository

```
git clone https://github.com/CUCentralAdvancement/jdbc-test.git
cd jdbc-test
```

### 3. Ensure access to (non-ancient) Java JDK

- The program was initially developed using AdoptOpenJDK11, however the code should be backwards compatible to any JDK version 8+.
- Inspect the Java versions with `java -version` and `javac -version`.
- Update if necessary.

### 4. Download PostgreSQL JDBC driver to project directory

From project directory:

```
wget https://jdbc.postgresql.org/download/postgresql-42.3.1.jar
```

### 5. Update config values in `example.conf` and save into `connection.conf` (**Name Change Important**)

- `example.conf` provides boilerplate for the configuration file. The program expects to find a `connection.conf` file in the directory from which it is run.
- Fill in the blanks with Heroku database credentials and the absolute file paths to your certificates/keys.
- Make sure it's saved as `connection.conf`
  - If you really need to be special, you can use some other name but you'll also want to change the hard-coded filename in `JDBCTest.java` (and `connection.conf` is included in `.gitignore` to prevent leaking credentials).

### 6. Compile the program

Run `javac JDBCTest.java` to compile the program.

### 7. Run the program

Assuming the program compiles, you should be able to run it with `java JDBCTest`.

Note: you'll need to include the PostgreSQL JDBC driver jar in the classpath
