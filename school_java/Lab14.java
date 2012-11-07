import java.io.*;
import java.util.*;

public class Lab14 {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Error: Filename I/O parameters needed");
			System.exit(0);
		}
		FileReader inputText = null;
		ObjectOutputStream objectWriter = null;
		Scanner scan = null;
		
		System.out.println("CSE 1002 - Lab 14 - Mauricio Zepeda\n");
		try {
			printTabbed("Reading from: " + args[0] + "- writing to: " + args[1] + "\n");
			inputText = new FileReader(new File(args[0]));
			scan = new Scanner(inputText);
			objectWriter = new ObjectOutputStream(new FileOutputStream(args[1]));
		} catch (IOException e) {
			printTabbed("Error opening files");
			System.exit(0);
		}
		
		/*
		 * Read and split it into the array, then write an object and on the
		 * fly create the object to be written with the array arguments.
		 */
		while(scan.hasNextLine()){
			String[] sentence = scan.nextLine().split(",");
			try {
			objectWriter.writeObject(new President(sentence[0], Integer.parseInt(sentence[1]), Integer.parseInt(sentence[2]), Integer.parseInt(sentence[3])));
			} catch (IOException e) {
				printTabbed("Error writing output file\n");
			}
		}
		try{
		inputText.close();
		objectWriter.close();
		printTabbed("Closing input and output files.\n");
		} catch (IOException e ){
			printTabbed("Error closing files\n");
		}
		
		printTabbed("Re-opening: " + args[1]);
		ObjectInputStream objectInput = null;
		
		try {
			objectInput = new ObjectInputStream(new FileInputStream(args[1]));
		} catch (IOException e) {
			printTabbed("Error reading object file: " + args[1]);
		}
		try {
			while(true){
				President object = (President) objectInput.readObject();
				object.printPresident();
			}
		} catch (EOFException e){
			printTabbed("\nAll objects have been read from the file\n");
		} catch (ClassNotFoundException e){
			printTabbed("President class not found\n");
		} catch (IOException e){
			printTabbed("Error reading objects from file: " + args[1] + "\n");
		} catch (Exception e){
			printTabbed("Unknown exception\n\t" + e + "\n");
		}
	}

	private static void printTabbed(String string) {
		System.out.println("\t" + string);
	}
}
