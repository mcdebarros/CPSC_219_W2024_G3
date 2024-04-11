# The Regression Application

This java program is an expansion of the previously developed Regression class. It has been expanded to incorporate a unique Matrix object and useful methods thereof, as well as a new GUI for data plotting. The GUI now handles all inputs and outputs including exception handling, file writing and reading, and data plotting.

## Functionality

- GUI to handle all inputs, outputs, plots, and exceptions
- Matrix object with functionality such as transposing, inverting, and multiplication
- Writer/reader classes for Matrix objects and linear models from .txt files
- Linear least squares regression
- Synthetic data generation

## Usage

1. Compile the Java program: `javac GraphingApp.java`
2. Run the program and input a filepath and model order for the data you wish to regress
3. Click 'Regress' and watch the magic happen
4. Close the window, or write your model parameters to a txt file of your choosing

Example:
```bash
java GraphingApp
```
## Requirements

- JDK21 installed on your system
- An input data file with two columns separated by tabs (Regression)

## Data Format

Input data for regression can contain headers but does not have to. Each row should contain two entries separated by a tab
```bash
x      y
1.0    2.1
3.2    4.3
5.4    6.5
```
## Output

The Regression class produces 3 model components in the GUI:

- Phi: The sum of squared residuals for this order of model
- RSQ: The R squared value of the model
- Linear coefficients for the model function


The Matrix object is also compatible with the file writer and reader, and has its own function showMat() to display matrices on the fly

## Future additions

- Eigenvectors and Eigenvalues
- Dot products
- Cross products
- Scalar multiplication
- Spline interpolation
- Iterative solving for transcendental systems 
- Option to store data in another file

## Authors

This program was collectively written by Gage, Gabe, Mo, and Kellen
