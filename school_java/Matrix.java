/*
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab06
 */

public class Matrix implements DivideConquerMatrix {

    public double[][] matrix;
    int extraRows = 0;
    int extraCols = 0;

    public Matrix(double[][] array) {
	this.matrix = array; 
    }

    public Matrix(int rows, int cols) {
	this.matrix = new double[rows][cols];
    }

    /*
     * Builds a Matrix of Matrices (Assuming that they are all of size
     * n^2).
     * @param C1 Upper Left BlockMatrix
     * @param C2 Upper Right BlockMatrix
     * @param C3 Lower Left BlockMatrix
     * @param C4 Lower Right BlockMatrix
     */
    public Matrix(Matrix C1, Matrix C2, Matrix C3, Matrix C4) {

	int n = (C1.matrix.length + C2.matrix.length);

	this.matrix = new double[n][n];
	
	// Upper left
	for (int j = 0; j < n/2; j++) {
	    for (int k = 0; k < n/2; k++) {
		this.matrix[j][k] = C1.matrix[j][k];
	    }
	}

	// Upper right
	for (int j = 0,  m = 0; j < n/2; j++, m++) {
	    for (int k = n/2, p = 0; k < n; k++, p++) {
		this.matrix[j][k] = C2.matrix[m][p];
	    }
	}

	// Lower left
	for (int j = n/2,  m = 0; j < n; j++, m++) {
	    for (int k = 0, p = 0; k < n/2; k++, p++) {
		this.matrix[j][k] = C3.matrix[m][p];
	    }
	}

	// Lower Right
	for (int j = n/2,  m = 0; j < n; j++, m++) {
	    for (int k = n/2, p = 0; k < n; k++, p++) {
		this.matrix[j][k] = C4.matrix[m][p];
	    }
	}

    }

    /* Add a matrix, input, to this matrix (without changing the internal data)
     * and return the resulting matrix.
     * @param The matrix to be added to this matrix
     * @return The resultant matrix from this addition
     * @throw UnsupportedOperationException when the two matrices cannot be added
     */
    public DivideConquerMatrix add(DivideConquerMatrix input) {
	Matrix inputM = (Matrix) input;
	if (this.matrix.length != inputM.matrix.length ||
	    this.matrix[0].length != inputM.matrix[0].length){
	    throw new UnsupportedOperationException();
	}

	Matrix result = new Matrix(inputM.matrix.length, inputM.matrix[0].length);
	for (int j = 0; j < result.matrix.length; j++) {
	    for (int k = 0; k < result.matrix[j].length; k++) {
		result.matrix[j][k] = this.matrix[j][k] + inputM.matrix[j][k];
	    }
	}
	return result;
    }

    public DivideConquerMatrix negate(DivideConquerMatrix input) {
	
	Matrix result = new Matrix(((Matrix) input).matrix.length, ((Matrix) input).matrix[0].length);
	for (int j = 0; j < result.matrix.length; j++) {
	    for (int k = 0; k < result.matrix[j].length; k++) {
		result.matrix[j][k] = ((Matrix) input).matrix[j][k] * -1;
	    }
	}

	return (DivideConquerMatrix) result;
    }
    
    /* Multiply a matrix, input, against this matrix (without changing the
     * internal data) and return the resulting matrix. Use the Divide-
     * and-Conquer approach that uses eight multiplications per recursive step.
     * @param The right-side matrix to be multiplied against this matrix
     * @return The resultant matrix from this multiplication
     * @throw UnsupportedOperationException when the two matrices cannot be multiplied
     */
    public DivideConquerMatrix multiply(DivideConquerMatrix input) {
    	//If this number of rows are different than that number of columns
	if (((Matrix)this).matrix[0].length != ((Matrix) input).matrix.length) {
	    throw new UnsupportedOperationException();
	}
	
	Matrix fixedThis = ((Matrix) this).fixSize();
	Matrix fixedInput = ((Matrix)input).fixSize();
	Matrix result = new Matrix(2, 2);

	// If fixSize did it's work correctly then we can pick any length
	// to be n
	int n = fixedThis.matrix.length;

	if (n > 2) {

	    // Upper Left
	    Matrix C1 = (Matrix) fixedThis.blockMatrix(0, 0).multiply(fixedInput.blockMatrix(0, 0))
		.add(fixedThis.blockMatrix(0, n/2).multiply(fixedInput.blockMatrix(n/2,0)));
	    // Upper Right
	    Matrix C2 = (Matrix) fixedThis.blockMatrix(0, 0).multiply(fixedInput.blockMatrix(0, n/2))
		.add(fixedThis.blockMatrix(0, n/2).multiply(fixedInput.blockMatrix(n/2, n/2)));
	    // Lower Left
	    Matrix C3 = (Matrix) fixedThis.blockMatrix(n/2, 0).multiply(fixedInput.blockMatrix(0, 0))
		.add(fixedThis.blockMatrix(n/2,n/2).multiply(fixedInput.blockMatrix(n/2,0)));
	    // Lower Right
	    Matrix C4 = (Matrix) fixedThis.blockMatrix(n/2, 0).multiply(fixedInput.blockMatrix(0, n/2))
		.add(fixedThis.blockMatrix(n/2,n/2).multiply(fixedInput.blockMatrix(n/2,n/2)));
	    
	    return new Matrix(C1, C2, C3, C4);

	} else {    

	    result.matrix[0][0] = fixedThis.matrix[0][0] * fixedInput.matrix[0][0]
		                  + fixedThis.matrix[0][1] * fixedInput.matrix[1][0];
	    result.matrix[0][1] = fixedThis.matrix[0][0] * fixedInput.matrix[0][1]
		                  + fixedThis.matrix[0][1] * fixedInput.matrix[1][1];
	    result.matrix[1][0] = fixedThis.matrix[1][0] * fixedInput.matrix[0][0]
		                  + fixedThis.matrix[1][1] * fixedInput.matrix[1][0];
	    result.matrix[1][1] = fixedThis.matrix[1][0] * fixedInput.matrix[0][1]
		                  + fixedThis.matrix[1][1] * fixedInput.matrix[1][1];
	}
	
	return result;
    }

    public DivideConquerMatrix blockMatrix(int selectRow, int selectCol) {

	int length = this.matrix.length / 2;
	Matrix result = new Matrix(length, length);

	for (int j = 0, m = selectRow; j < length; j++, m++) {

	    for (int k = 0, n = selectCol; k < length; k++, n++) {
		result.matrix[j][k] = this.matrix[m][n];
	    }
	}

	return (DivideConquerMatrix) result;
    }
    
    /* Multiply a matrix, input, against this matrix (without changing the
     * internal data) and return the resulting matrix. Use Strassen's
     * algorithm to multiply.
     * @param The right-side matrix to be multiplied against this matrix
     * @return The resultant matrix from this multiplication
     * @throw UnsupportedOperationException when the two matrices cannot be multiplied
     */
    public DivideConquerMatrix strassen(DivideConquerMatrix input) {
	
	//If this number of rows are different than that number of columns
	if (((Matrix)this).matrix[0].length != ((Matrix) input).matrix.length) {
	    throw new UnsupportedOperationException();
	}
	
	Matrix fixedThis = ((Matrix) this).fixSize();
	Matrix fixedInput = ((Matrix)input).fixSize();
	Matrix result = new Matrix(2, 2);

	// If fixSize did it's work correctly then we can pick any length
	// to be n
	int n = fixedThis.matrix.length;

	if (n > 2) {

	    Matrix M1 = (Matrix) fixedThis.blockMatrix(0, 0)
		.add(fixedThis.blockMatrix(n/2, n/2))
		.multiply(fixedInput.blockMatrix(0, 0)
			  . add(fixedInput.blockMatrix(n/2, n/2)));
	    Matrix M2 = (Matrix) fixedThis.blockMatrix(n/2, 0)
		.add(fixedThis.blockMatrix(n/2, n/2))
		.multiply(fixedInput.blockMatrix(0, 0));
	    Matrix M3 = (Matrix) fixedThis.blockMatrix(0,0)
		.multiply(fixedInput.blockMatrix(0, n/2)
			  .add(negate(fixedInput.blockMatrix(n/2, n/2))));
	    Matrix M4 = (Matrix) fixedThis.blockMatrix(n/2, n/2)
		.multiply(fixedInput.blockMatrix(n/2, 0)
			  .add(negate(fixedInput.blockMatrix(0, 0))));
	    Matrix M5 = (Matrix) fixedThis.blockMatrix(0, 0)
		.add(fixedThis.blockMatrix(0, n/2))
		.multiply(fixedInput.blockMatrix(n/2, n/2));
	    Matrix M6 = (Matrix) fixedThis.blockMatrix(n/2, 0)
		.add(negate(fixedThis.blockMatrix(0,0)))
		.multiply(fixedInput.blockMatrix(0, 0)
			  .add(fixedInput.blockMatrix(0, n/2)));
	    Matrix M7 = (Matrix) fixedThis.blockMatrix(0, n/2)
		.add(negate(fixedThis.blockMatrix(n/2, n/2)))
		.multiply(fixedInput.blockMatrix(n/2, 0)
			  .add(fixedInput.blockMatrix(n/2, n/2)));
	    
	    Matrix C1 = (Matrix) M1.add(M4).add(negate(M5)).add(M7);
	    Matrix C2 = (Matrix) M3.add(M5);
	    Matrix C3 = (Matrix) M2.add(M4);
	    Matrix C4 = (Matrix) M1.add(negate(M2)).add(M3).add(M6);

	    return new Matrix(C1, C2, C3, C4);

	} else {    

	    result.matrix[0][0] = fixedThis.matrix[0][0] * fixedInput.matrix[0][0]
		                  + fixedThis.matrix[0][1] * fixedInput.matrix[1][0];
	    result.matrix[0][1] = fixedThis.matrix[0][0] * fixedInput.matrix[0][1]
		                  + fixedThis.matrix[0][1] * fixedInput.matrix[1][1];
	    result.matrix[1][0] = fixedThis.matrix[1][0] * fixedInput.matrix[0][0]
		                  + fixedThis.matrix[1][1] * fixedInput.matrix[1][0];
	    result.matrix[1][1] = fixedThis.matrix[1][0] * fixedInput.matrix[0][1]
		                  + fixedThis.matrix[1][1] * fixedInput.matrix[1][1];
	}
	
	return result;
    }
    
    /* Return the string representation of this DivideConquerMatrix
     * @return The string representation of this matrix
     */
    public String toString() {
	String result = "";
	for (int j = 0; j < this.matrix.length; j++) {
	    for (int k = 0; k < this.matrix[j].length; k++) {
		result += " " + this.matrix[j][k];
	    }
	    result.trim();
	    result += "\n";
	}
	return result;
    }

    /*
     * Call resize with the appropiate values.
     */
    public Matrix fixSize() {	
	int rows  = this.matrix.length;
	int cols = this.matrix[0].length;
	int powerOfTwo = nextPowerOfTwo(Math.max(rows, cols));
	return this.resize(powerOfTwo - rows, powerOfTwo - cols);
    }

    /*
     * Calculates next power of two.
     */
    private int nextPowerOfTwo(int number) {
	return (int) Math.pow(2, Math.ceil(Math.log(number)/Math.log(2))); 
    }
    
    /*
     * Copies a 2d array to a bigger one
     * @param extraRows Number of extra rows filled with zeroes
     * @param extraCols Extra columns filled with zeroes
     */
    private Matrix resize(int extraRows, int extraCols) {
	if (extraRows == 0 && extraCols == 0) return this;
	Matrix result = new Matrix(this.matrix.length + extraRows, this.matrix[0].length + extraCols);
	result.fill(this);
	return result;
    }

    /*
     * Fills this Matrix with a smaller Matrix
     * The remaining spaces will be zeroes
     */
    private void fill(Matrix smallerMatrix) {
	for (int j = 0; j < smallerMatrix.matrix.length; j++) {
	    for (int k = 0; k < smallerMatrix.matrix[j].length; k++) {
		this.matrix[j][k] = smallerMatrix.matrix[j][k];
	    }
	}
    }
    
}