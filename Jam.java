import java.util.Arrays;

public class Jam {
    public static void main(String[] args) {
        // the main input loop / Opens up piano
        Keyboard keyboard = new Keyboard();
        String piano =
                "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        char[] pianoKeys = new char[piano.length()];
        for (int i = 0; i < piano.length(); i++) {
            pianoKeys[i] = piano.charAt(i);
        }

        // Opens up a while loop so that the user can gather ideas on different
        // instruments until they decide to close the program
        while (true) {
            for (String s : Arrays.asList("What instrument would you like to play?",
                                          "1: Acoustic Guitar", "2: Bass Guitar",
                                          "3: Synth Piano", "4: Drum Set",
                                          "5: Exit the Program")) {
                StdOut.println(s);
            }
            // The user decides what they want to play.
            String selection = StdIn.readString();

            if (!(selection.equals("1") || selection.equals("2") || selection
                    .equals("3") || selection.equals("4") || selection.equals("5"))) {
                do {
                    StdOut.println("That is not a vaid option. Please type 1, 2, 3, 4, or 5");
                    selection = StdIn.readString();
                } while (!(selection.equals("1") || selection.equals("2") || selection
                        .equals("3") || selection.equals("4") || selection.equals("5")));
            }

            // If they choose 1, the guitar patch opens up on the keyboard.
            if (selection.equals("1")) {
                // Create 37 Guitar Strings
                Guitar[] guitar = new Guitar[37];
                for (int i = 0; i < 37; i++) {
                    guitar[i] = new Guitar(440.0 * Math.pow(2, (i - 24) / 12.0));
                }

                while (true) {

                    // check if the user has played a key; if so, process it
                    if (keyboard.hasNextKeyPlayed()) {

                        // the key the user played
                        char key = keyboard.nextKeyPlayed();

                        // pluck the corresponding string
                        for (int j = 0; j < piano.length(); j++) {
                            if (key == pianoKeys[j]) {
                                guitar[j].pluck();
                            }
                        }
                        if (key == '`') {
                            break;
                        }
                    }

                    // compute the superposition of the samples
                    double sample = 0;
                    for (int k = 0; k < piano.length(); k++) {
                        sample += guitar[k].sample();
                    }

                    // play the sample on standard audio
                    StdAudio.play(sample);

                    // advance the simulation of each guitar string by one step
                    for (int m = 0; m < piano.length(); m++) {
                        guitar[m].tic();
                    }
                }
            }

            // If they choose 2, the bass patch opens.
            else if (selection.equals("2")) {
                // Create 37 Bass Strings
                Bass[] bass = new Bass[37];
                for (int i = 0; i < 37; i++) {
                    bass[i] = new Bass(220.0 * Math.pow(2, (i - 24) / 12.0));
                }

                while (true) {

                    // check if the user has played a key; if so, process it
                    if (keyboard.hasNextKeyPlayed()) {

                        // the key the user played
                        char key = keyboard.nextKeyPlayed();

                        // pluck the corresponding string
                        for (int j = 0; j < piano.length(); j++) {
                            if (key == pianoKeys[j]) {
                                bass[j].pluck();
                            }
                        }

                        if (key == '`') break;
                    }

                    // compute the superposition of the samples
                    double sample = 0;
                    for (int k = 0; k < piano.length(); k++) {
                        sample += bass[k].sample();
                    }

                    // play the sample on standard audio
                    StdAudio.play(sample);

                    // advance the simulation of each guitar string by one step
                    for (int m = 0; m < piano.length(); m++) {
                        bass[m].tic();
                    }
                }
            }

            // If they choose 3, the piano opens
            else if (selection.equals("3")) {
                // Create 37 Piano Keys
                Synth[] synthKeys = new Synth[37];
                for (int i = 0; i < 37; i++) {
                    synthKeys[i] = new Synth(440.0 * Math.pow(2, (i - 24) / 12.0));
                }

                while (true) {

                    // check if the user has played a key; if so, process it
                    if (keyboard.hasNextKeyPlayed()) {

                        // the key the user played
                        char key = keyboard.nextKeyPlayed();

                        // pluck the corresponding string
                        for (int j = 0; j < piano.length(); j++) {
                            if (key == pianoKeys[j]) {
                                synthKeys[j].pluck();
                            }
                        }

                        if (key == '`') break;
                    }

                    // compute the superposition of the samples
                    double sample = 0;
                    for (int k = 0; k < piano.length(); k++) {
                        sample += synthKeys[k].sample();
                    }

                    // play the sample on standard audio
                    StdAudio.play(sample);

                    // advance the simulation of each guitar string by one step
                    for (int m = 0; m < piano.length(); m++) {
                        synthKeys[m].tic();
                    }
                }
            }

            // If they choose 4, the drums open.
            if (selection.equals("4")) {
                // create drum set
                BassDrum bassDrum = new BassDrum(75);
                SnareCymbal snare = new SnareCymbal(100);
                BassDrum highTom = new BassDrum(300);
                BassDrum midTom = new BassDrum(200);
                BassDrum lowTom = new BassDrum(100);
                SnareCymbal closedCymbal = new SnareCymbal(1000);
                SnareCymbal openCymbal = new SnareCymbal(400);

                while (true) {

                    // check if the user has played a key; if so, process it
                    if (keyboard.hasNextKeyPlayed()) {

                        // the key the user played
                        char key = keyboard.nextKeyPlayed();

                        // pluck the corresponding string
                        if (key == 'e') bassDrum.pluck();
                        if (key == 'r') snare.pluck();
                        if (key == 't') highTom.pluck();
                        if (key == 'y') midTom.pluck();
                        if (key == 'u') lowTom.pluck();
                        if (key == 'i') closedCymbal.pluck();
                        if (key == 'o') openCymbal.pluck();
                        if (key == '`') break;
                    }

                    // compute the superposition of the samples
                    double sample = bassDrum.sample() + snare.sample() + highTom.sample()
                            + midTom.sample() + lowTom.sample() + closedCymbal.sample() +
                            openCymbal.sample();

                    // play the sample on standard audio
                    StdAudio.play(sample);

                    // advance the simulation of each guitar string by one step
                    bassDrum.tic();
                    snare.tic();
                    highTom.tic();
                    midTom.tic();
                    lowTom.tic();
                    closedCymbal.tic();
                    openCymbal.tic();
                }
            }
            // Otherwise, they could choose to exit the program.
            // https://www.geeksforgeeks.org/system-exit-in-java/
            else System.exit(0);
        }
    }
}
