public class testDrive {
    public static void main(String[] args) {
        double[][] m1 = {{3.,1.},
                         {2.,3.},
                         {4.,2.},
                         {6.,7.}};
    
        double[][] m2 = {{7.,1.,4.,3.},
                         {2.,6.,4.,8.}};

        DivideConquerMatrix left  = new Matrix(m1);
        DivideConquerMatrix right = new Matrix(m2);

	DivideConquerMatrix result = left.multiply(right);
	
	System.out.println(left);
	System.out.println("\n");
	System.out.println(right);
	System.out.println("\n");
	System.out.println(result);
	
        // "Normal" Divide and Conquer approach to multiplying matrices m1 and m2
	// DivideConquerMatrix dc_result = left.multiply(right);
        // System.out.println(dc_result);
        
        // "Strassen" Divide and Conquer approach to multiplying matrices m1 and m2
        // DivideConquerMatrix st_result = left.strassen(right);
        // System.out.println(st_result);
    }
}