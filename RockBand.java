import java.util.Arrays;

public class RockBand {
    public static void main(String[] args) {
        // Starting Sequence
        for (String s : Arrays.asList("What would you like to do? Type 1, 2, or 3.",
                                      "1: Jam out on an instrument of your choice",
                                      "2: Record parts on an instrument of your choice",
                                      "3: Exit the Program")) {
            StdOut.println(s);
        }
        // The User decides what program they would like to see.
        String selection = StdIn.readString();

        while (!((selection.equals("1")) || (selection.equals("2")) || (selection
                .equals("3")))) {
            StdOut.println("That is not a vaid option. Please type 1, 2, or 3.");
            selection = StdIn.readString();
        }

        if (selection.equals("1")) Jam.main(args); // Just play with the instruments to get ideas
        if (selection.equals("2")) Compose.main(args); // Record a song
        else if (selection.equals("3")) System.exit(0); // Leave the program
        // https://www.geeksforgeeks.org/system-exit-in-java/
    }
}
