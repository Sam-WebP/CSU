# Running Guide

## Prerequisites

- Java Development Kit (JDK) installed
- JavaFX installed

## Steps

1. Open the terminal or command prompt.

2. Navigate to the `A2` folder containing the task folders.

3. Compile the program:

Debian/ubuntu:
`javac --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml task1/*.java`

Windows:
`javac --module-path "%PATH_TO_FX%" --add-modules javafx.controls,javafx.fxml task1/*.java`

4. Run the program:

Debian/ubuntu:
`java --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml task1.XYZSalesSummary`

Windows:
`java --module-path "%PATH_TO_FX%" --add-modules javafx.controls,javafx.fxml task1.XYZSalesSummary`

## Notes

- `$PATH_TO_FX` is an environment variable that should be set to the path where your JavaFX SDK library files are located.
- `--module-path` specifies the location of the modules that the application depends on.
- `--add-modules` specifies which modules should be added to the module graph.
- `javafx.controls` is the module that contains the JavaFX UI controls.
- `javafx.fxml` is the module that uses FXML for the UI to be defined.
