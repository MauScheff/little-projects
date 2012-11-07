import java.util.Scanner;

public class stringGenerator {
    
    public static void main(String[] args) {

	Scanner stdin = new Scanner(System.in);
	String result = "";

	while (true) {
	    System.out.println("Please enter name (exit to finish)");
	    String name = stdin.next();
	    
	    if (name.equals("exit")) break;
	    System.out.println("Please enter number of files");
	    int num = stdin.nextInt();
	    
	    result += "<div class=\"slide\">\n"
		+ "<h1>Insert " + name
		+ "</h1>\n" 
		+ "<p class=\"imgcon\" id=\"anim\">\n";
	    
	    for (int j = 0; j <= num; j++) {
		result += "<img src=\"img/" + name.toLowerCase() 
		    + "0" + j 
		    +".jpg\" id=\"animation\" class=\"incremental\" />\n";
	    }

	    result += "</p>\n</div>\n\n";
	    
	}
	
	System.out.println(result);
    }
    
}