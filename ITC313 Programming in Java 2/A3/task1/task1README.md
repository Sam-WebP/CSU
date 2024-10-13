# Running Guide

## Prerequisites

- Java Development Kit (JDK) installed
- JavaFX installed
- MySQL

## How to Setup the `TaxManagementSystem` Database

### 1. Create the database

`CREATE DATABASE TaxManagementSystem;`

### 2. Use the database you just created

`USE TaxManagementSystem;`

### 3. Create the `TaxResult` Table

```
CREATE TABLE TaxResult (
    ID VARCHAR(10) PRIMARY KEY,
    FinancialYear VARCHAR(10) NOT NULL,
    TaxableIncome DOUBLE NOT NULL,
    Tax DOUBLE NOT NULL
);
```

### 4. User Privileges

```
CREATE USER 'your_mysql_username'@'localhost' IDENTIFIED BY 'your_mysql_password';
GRANT ALL PRIVILEGES ON TaxManagementSystem.* TO 'your_mysql_username'@'localhost';
FLUSH PRIVILEGES;
```

Replace `your_mysql_username` with the username you want to use, and `your_mysql_password` with a password of your choice.

## Steps

1. Open the terminal or command prompt.

2. Ensure your MySQL database is running

3. Navigate to the `A2` folder containing the task folders.

4. Compile the program:

Debian/ubuntu:
`javac --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml task1/*.java`

4. Run the program:

Debian/ubuntu:
`java --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -cp .:"$MYSQL_CONNECTOR_JAR" task1.DatabaseConnection`

## Notes

- `$PATH_TO_FX` is an environment variable that should be set to the path where your JavaFX SDK library files are located.
- `--module-path` specifies the location of the modules that the application depends on.
- `--add-modules` specifies which modules should be added to the module graph.
- `javafx.controls` is the module that contains the JavaFX UI controls.
- `javafx.fxml` is the module that uses FXML for the UI to be defined.
- `$MYSQL_CONNECTOR_JAR` is an environment variable that should be set to the path of your MySQL Connector/J JAR file
