import java.util.*;

public class Main {
	
	private static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args){
		nInsignificant = stdin.nextInt();
		if (nInsignificant != 0){ 
			String[] insignificant = new String[nInsignificant];
			
			//Find Insigificants
			for (int j=0;j<insignificant.length();j++){
				insignificant[j] = stdin.nextLine();
			}
		
			while (true){
				String str = stdin.nextLine();
				if(str.equals("LAST CASE")) break;
				char[] abbrev = stdin.next().toCharArray();
				
				
			}
		}	
	}
	
}