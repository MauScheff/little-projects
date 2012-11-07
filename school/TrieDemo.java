import java.util.Scanner;

public class TrieDemo {
    
    public static void main(String[] args) {
	
	Scanner stdin = new Scanner(System.in);
	
	System.out.println("Insert Everyone\n");
	
	Trie myTrie = new Trie();
	myTrie.insert("Asmita");
	myTrie.insert("Joshua");
	myTrie.insert("Tyler");
	myTrie.insert("Luis");
	myTrie.insert("Michael");
	myTrie.insert("Ryan");
	myTrie.insert("John");
	myTrie.insert("Ronald");
	myTrie.insert("James");
	myTrie.insert("Chris");
	myTrie.insert("Miles");
	myTrie.insert("Thomas");	
	myTrie.insert("Kevin");
	myTrie.insert("Hamad");
	myTrie.insert("James");
	myTrie.insert("Nicholas");
	myTrie.insert("Ronaldo");
	myTrie.insert("Kevin");
	myTrie.insert("Deborah");
	myTrie.insert("Mauricio");
	myTrie.insert("Sandra");
	myTrie.insert("Eric");
	myTrie.insert("Bradley");
	myTrie.insert("Shriram");
	
	System.out.println(myTrie);

	System.out.println("Delete Sandra: " + (myTrie.delete("Sandra") ? "Deleted!": "Not Found"));
	System.out.println("Delete Mauricio: " + (myTrie.delete("Mauricio") ? "Deleted!": "Not Found"));
	System.out.println("Delete Asmita: " + (myTrie.delete("Asmita") ? "Deleted!": "Not Found"));
	System.out.println("Delete Kyle: " + (myTrie.delete("Kyle") ? "Deleted!": "Not Found"));
	System.out.println("Delete James: " + (myTrie.delete("James") ? "Deleted!": "Not Found"));
	System.out.println("Delete June: " + (myTrie.delete("June") ? "Deleted!": "Not Found"));
	System.out.println("");

	System.out.println(myTrie);

	System.out.println("Find James: " + myTrie.find("James"));
	System.out.println("Find Ronaldo: " + myTrie.find("Ronaldo"));
	System.out.println("Find Nicolas: " + myTrie.find("Nicolas"));
	System.out.println("Find Nicholas: " + myTrie.find("Nicholas"));
	System.out.println("Find Kevin: " + myTrie.find("Kevin"));
	
	System.out.println("\nGet Children of \"Mi\": ");

	String[] results = myTrie.getChildren("Mi");
	for (String str : results) {
	    System.out.println(str);
	}

	System.out.println("\nGet Children of \"Ronal\": ");

	String[] results2 = myTrie.getChildren("Ronal");
	for (String str : results2) {
	    System.out.println(str);
	}

	System.out.println("\nGet Children of \"\": ");

	String[] results3 = myTrie.getChildren("");
	for (String str : results3) {
	    System.out.println(str);
	}

	myTrie.insert("Mauricio");

	System.out.println("\nGet Children of \"M\": ");

	String[] results4 = myTrie.getChildren("M");
	for (String str : results4) {
	    System.out.println(str);
	}
    }
}