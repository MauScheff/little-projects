import java.io.*;

public class Lab13 {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out
					.println("You must enter both the input and output file names!");
			System.exit(0);
		}
		System.out.println("CSE 1002 - Lab 13 - Mauricio Zepeda\n");

		int entries = 0; // Counted in Pairs
		String Employee1 = "";
		String Employee2 = "";
		int ID1 = 0;
		int ID2 = 0;
		double pay1 = 0;
		double pay2 = 0;

		DataInputStream input = null;
		DataOutputStream output = null;

		try {
			printTabbed("Reading from: " + args[0] + "\n");
			printTabbed("Writing to: " + args[1] + "\n");
			input = new DataInputStream(new FileInputStream(args[0]));

			output = new DataOutputStream(new FileOutputStream(args[1]));
		} catch (IOException e) {
			printTabbed("Error opening files.");
			System.exit(0);
		}

		try {
			Employee1 = input.readUTF();
			Employee2 = input.readUTF();
			entries = input.readInt() - 1;

			ID1 = input.readInt();
			pay1 = input.readDouble();

			for (int i = 1; i <= entries; i++) {
				int ID = input.readInt();
				double amount = input.readDouble();
				if (ID == ID1)
					pay1 += amount;
				else {
					ID2 = ID;
					pay2 += amount;
				}
			}
			input.close();
			printTabbed("Read " + (entries + 1) + " employee records.\n");
		} catch (IOException e) {
			printTabbed("Error reading input file.");
		}

		try {

			if (Math.min(ID1, ID2) == ID1) {
				output.writeInt(ID1);
				output.writeUTF(Employee1);
				output.writeDouble(pay1);
				output.writeInt(ID2);
				output.writeUTF(Employee2);
				output.writeDouble(pay2);
			} else {
				output.writeInt(ID2);
				output.writeUTF(Employee1);
				output.writeDouble(pay2);
				output.writeInt(ID1);
				output.writeUTF(Employee2);
				output.writeDouble(pay1);
			}

			output.close();
			printTabbed("Totals were written to the output file.");
		} catch (IOException e) {
			printTabbed("Error writing output file.");
		}

	}

	public static void printTabbed(String string) {
		System.out.println("\t" + string);
	}
}