import java.util.*;

public class lab02 {

	public static void main(String args[]){
		Scanner keyboard = new Scanner(System.in);
		int Nvowels = 0, Nconsonants = 0, Nspaces = 0;
		String phrase;
		
		while (true){
			System.out.print("Enter a phrase");
			phrase = keyboard.nextLine();
			if(phrase.equals(""))
				break;
			
			phrase = phrase.toLowerCase();
			char[] phraseArray = new char[phrase.length()];
			phraseArray = phrase.toCharArray();
			
			for (int i=0;i<phraseArray.length;i++){
				switch (phraseArray[i]){
				case 'a': Nvowels++; break;
				case 'e': Nvowels++; break;
				case 'i': Nvowels++; break;
				case 'o': Nvowels++; break;
				case 'u': Nvowels++; break;
				case ' ': Nspaces++; break;
				default: Nconsonants++; break;
				}
			}
			
			System.out.println("Your input contains:");
			System.out.println(phrase.length());
			System.out.println("including:");
			System.out.println(Nvowels + " vowels");
			System.out.println(Nconsonants + " consonants");
			System.out.println(Nspaces + " spaces");
			
			Nvowels = 0;
			Nconsonants = 0;
			Nspaces = 0;
			
		} 
	}
}
