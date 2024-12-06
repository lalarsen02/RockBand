import java.util.Arrays;

public class Compose {
    private static void playback(Record tracks) {
        // If there are no tracks, they are sent back
        if (tracks.size() == 0) {
            StdOut.println("There are no tracks for you to listen to yet.");
            return;
        }

        // Otherwise, they are asked what track to playback
        StdOut.println(
                "What track do you want to playback? Please type the name of a track.");
        StdOut.println("Otherwise, type ` to go back.");
        StdOut.println(tracks);
        String track = StdIn.readString();
        if (track.equals("`")) return;
        while (!tracks.contain(track)) {
            StdOut.println(
                    "That is not the name of a track. Please type the name of a track.");
            track = StdIn.readString();
        }
        double[] audio = tracks.get(track);
        StdAudio.play(audio);
    }

    private static void remove(Record tracks) {
        // If there are no tracks, they are sent back.
        if (tracks.size() == 0) {
            StdOut.println("There are no tracks for you to remove yet.");
            return;
        }

        // Otherwise, they are asked what track to remove.
        StdOut.println(
                "What track do you want to remove? Please type the name of the track.");
        StdOut.println("Otherwise, type ` to go back.");
        StdOut.println(tracks);
        String track = StdIn.readString();
        if (track.equals("`")) return;
        while (!tracks.contain(track)) {
            StdOut.println(
                    "That is not the name of a track. Please type the name of a track.");
            track = StdIn.readString();
        }
        tracks.remove(track);
    }

    private static void addEffects(Record tracks, int tempo) {
        // If there are no tracks, they are sent back.
        if (tracks.size() == 0) {
            StdOut.println("There are no tracks for you to add effects to yet.");
            return;
        }

        // Otherwise, they are asked what tracks would like effects.
        StdOut.println(
                "What track would you like to add an effect to? Please type the name of the track.");
        StdOut.println("Otherwise, type ` to go back");
        StdOut.println(tracks);
        String track = StdIn.readString();
        if (track.equals("`")) return;
        while (!tracks.contain(track)) {
            StdOut.println(
                    "That is not the name of a track. Please type the name of a track.");
            track = StdIn.readString();
        }
        double[] audio = tracks.get(track);

        // Then, they are asked which effect to put on the track.
        for (String s : Arrays
                .asList("What effect would you like to add. Type 1, 2, 3, 4, 5, 6, or 7.",
                        "1. Amplify/Volume", "2: Echo", "3: Reverb", "4: Overdrive",
                        "5. Distortion", "6. Reverse", "7. Go Back")) {
            StdOut.println(s);
        }
        String selection1 = StdIn.readString();

        while (!(selection1.equals("1") || selection1.equals("2") || selection1
                .equals("3") || selection1.equals("4") || selection1.equals("5")
                || selection1.equals("6") || selection1.equals("7"))) {
            StdOut.println(
                    "That is not a vaid option. Please type 1, 2, 3, 4, 5, 6, or 7.");
            selection1 = StdIn.readString();
        }

        // If they select 1, they get volume control.
        if (selection1.equals("1")) {
            StdOut.println(
                    "How much would you like to increase or decrease the volume?"
                            + " Please write a positive number");
            double amplify = StdIn.readDouble();
            tracks.remove(track);
            double[] newAudio = Effects.amplify(audio, amplify);
            tracks.insert(track, newAudio);
            StdAudio.play(newAudio);
        }

        // If they select 2, they get echo.
        else if (selection1.equals("2")) {
            StdOut.println("What type of delay would you like? Please type 1, 2, 3, or 4.");
            StdOut.println("1. Eighth Note");
            StdOut.println("2. Quarter Note");
            StdOut.println("3. Dotted Quarter Note");
            StdOut.println("4. Half Note");
            String selection2 = StdIn.readString();

            while (!(selection2.equals("1") || selection2.equals("2") || selection2
                    .equals("3") || selection2.equals("4"))) {
                StdOut.println(
                        "That is not a vaid option. Please type 1, 2, 3, or 4.");
                selection2 = StdIn.readString();
            }

            // Eighth note delay
            if (selection2.equals("1")) {
                tracks.remove(track);
                double[] newAudio = Effects.echo(audio, tempo, 1);
                tracks.insert(track, newAudio);
                StdAudio.play(newAudio);
            }

            // Quarter note delay
            else if (selection2.equals("2")) {
                tracks.remove(track);
                double[] newAudio = Effects.echo(audio, tempo, 2);
                tracks.insert(track, newAudio);
                StdAudio.play(newAudio);
            }

            // Dotted quarter note delay
            else if (selection2.equals("3")) {
                tracks.remove(track);
                double[] newAudio = Effects.echo(audio, tempo, 3);
                tracks.insert(track, newAudio);
                StdAudio.play(newAudio);
            }

            // Half Note Delay
            else {
                tracks.remove(track);
                double[] newAudio = Effects.echo(audio, tempo, 4);
                tracks.insert(track, newAudio);
                StdAudio.play(newAudio);
            }
        }

        // If they select 3, they get reverb.
        else if (selection1.equals("3")) {
            tracks.remove(track);
            double[] newAudio = Effects.reverb(audio);
            tracks.insert(track, newAudio);
            StdAudio.play(newAudio);
        }

        // If they select 4, they get overdrive.
        else if (selection1.equals("4")) {
            tracks.remove(track);
            double[] newAudio = Effects.overdrive(audio);
            tracks.insert(track, newAudio);
            StdAudio.play(newAudio);
        }

        // If they select 5, they get distortion or fuzz.
        else if (selection1.equals("5")) {
            StdOut.println(
                    "How much distortion would you like? Please provide a positive number.");
            double gain = StdIn.readDouble();
            while (gain < 0) {
                StdOut.println("Please input a positive number");
                gain = StdIn.readDouble();
            }
            StdOut.println("What is your preferred mix between the distorted "
                                   + "and normal version? Please write a number"
                                   + " between 0 and 1, with 1 being only the "
                                   + "distored signal.");
            double mix = StdIn.readDouble();
            while ((mix < 0) || (mix > 1)) {
                StdOut.println("Please write a number between 0 and 1");
                mix = StdIn.readDouble();
            }
            tracks.remove(track);
            double[] newAudio = Effects.distortion(audio, gain, mix);
            tracks.insert(track, newAudio);
            StdAudio.play(newAudio);
        }

        // If they select 6, they can reverse the track.
        else if (selection1.equals("6")) {
            tracks.remove(track);
            double[] newAudio = Effects.reverse(audio);
            tracks.insert(track, newAudio);
            StdAudio.play(newAudio);
        }
    }

    public static void main(String[] args) {
        // Starting sequence
        StdOut.println(
                "How many bars is your composition? Please use Integers like 8, 12, 16, etc.");
        int bars = StdIn
                .readInt(); // The user enters how many bars they want the composition to be.
        StdOut.println("What is the tempo of your composition? Please use Integers.");
        int tempo = StdIn.readInt(); // The user enters the tempo of their composition.
        int numberOfSamples = (int) Math.floor(60.0 / tempo * 4.0 * bars * 44100.0);
        Record tracks = new Record();

        // the main input loop / The Piano opens
        Keyboard keyboard = new Keyboard();
        String piano =
                "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        char[] pianoKeys = new char[piano.length()];
        for (
                int i = 0; i < piano.length(); i++) {
            pianoKeys[i] = piano.charAt(i);
        }

        // A while loop is created to allow the user to create a composition as long as the program is open
        while (true) {
            // A list of the tracks the user has made appears
            StdOut.println("These are your tracks so far." + "-----------------------------");
            StdOut.println(tracks);

            // The user decides what they want to do
            for (String s : Arrays.asList("What would you like to do? Type 1, 2, 3, 4, 5, or 6.",
                                          "1. Add a new Track", "2. Playback a Track",
                                          "3. Remove a Track",
                                          "4. Add effects to a Track", "5. Playback all Tracks",
                                          "6. Exit the Program")) {
                StdOut.println(s);
            }
            String selection = StdIn.readString();

            if (!(selection.equals("1") || selection.equals("2") || selection
                    .equals("3") || selection.equals("4") || selection.equals("5") || selection
                    .equals("6"))) {
                do {
                    StdOut.println("That is not a vaid option. Please type 1, 2, 3, 4, 5, or 6");
                    selection = StdIn.readString();
                } while (!(selection.equals("1") || selection.equals("2") || selection
                        .equals("3") || selection.equals("4") || selection.equals("5") || selection
                        .equals("6")));
            }

            // If they select 1, they can record a track.
            if (selection.equals("1")) {
                double[] samples
                        = new double[numberOfSamples]; // A double array is created to store the data
                int b = 0;
                StdOut.println("What is the Name of this Track?");
                String trackName = StdIn.readString();

                // The list of instruments is printed out
                for (String s : Arrays.asList("What instrument would you like to play?",
                                              "1: Acoustic Guitar", "2: Bass Guitar",
                                              "3: Synth Piano", "4: Drum Set",
                                              "5: Go Back")) {
                    StdOut.println(s);
                }
                String selection1 = StdIn.readString();  // The user decides what to play.

                if (!(selection1.equals("1") || selection1.equals("2") || selection1
                        .equals("3") || selection1.equals("4") || selection1.equals("5"))) {
                    do {
                        StdOut.println(
                                "That is not a vaid option. Please type 1, 2, 3, 4, or 5");
                        selection1 = StdIn.readString();
                    } while (!(selection1.equals("1") || selection1.equals("2") || selection1
                            .equals("3") || selection1.equals("4") || selection1.equals("5")));
                }

                // If they select 1, the guitar patch will open
                if (selection1.equals("1")) {
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
                            if (key == '`') break;
                        }

                        // compute the superposition of the samples
                        double sample = 0;
                        for (int k = 0; k < piano.length(); k++) {
                            sample += guitar[k].sample();
                        }

                        // log the sample
                        samples[b] = sample;
                        b++;
                        if (b == numberOfSamples) break;

                        // play the sample on standard audio
                        StdAudio.play(sample);

                        // advance the simulation of each guitar string by one step
                        for (int m = 0; m < piano.length(); m++) {
                            guitar[m].tic();
                        }
                    }
                    // Afterward, the track is inserted.
                    tracks.insert(trackName, samples);
                }

                // If they select 2, the bass patch opens.
                else if (selection1.equals("2")) {
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

                        // log the sample
                        samples[b] = sample;
                        b++;
                        if (b == numberOfSamples) break;

                        // play the sample on standard audio
                        StdAudio.play(sample);

                        // advance the simulation of each guitar string by one step
                        for (int m = 0; m < piano.length(); m++) {
                            bass[m].tic();
                        }
                    }

                    // Afterward, the track is inserted.
                    tracks.insert(trackName, samples);
                }

                // If the user selects 3, the piano patch opens.
                else if (selection1.equals("3")) {
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

                        // log the sample
                        samples[b] = sample;
                        b++;
                        if (b == numberOfSamples) break;

                        // play the sample on standard audio
                        StdAudio.play(sample);

                        // advance the simulation of each guitar string by one step
                        for (int m = 0; m < piano.length(); m++) {
                            synthKeys[m].tic();
                        }
                    }

                    // Afterward, the tracks are inserted.
                    tracks.insert(trackName, samples);
                }

                // If the user selects 4, the Drum Set patch will open.
                else if (selection1.equals("4")) {
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

                        // log the sample
                        samples[b] = sample;
                        b++;
                        if (b == numberOfSamples) break;

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

                    // Afterward, the sample is logged.
                    tracks.insert(trackName, samples);
                }
            }

            // If they select 2, they can playback a track.
            else if (selection.equals("2")) playback(tracks);

                // If they select 3, they can remove a track.
            else if (selection.equals("3")) remove(tracks);

                // If they select 4, they can add effects.
            else if (selection.equals("4")) addEffects(tracks, tempo);

                // If they select 5, they can playback all the tracks at once.
            else if (selection.equals("5")) {
                if (tracks.size() == 0) {
                    StdOut.println("There are no tracks to playback yet.");
                    continue;
                }
                double[] playback = Effects.normalize(tracks.playback());
                StdAudio.play(playback);
            }

            // Otherwise, they could choose to end the program
            // https://www.geeksforgeeks.org/system-exit-in-java/
            else System.exit(0);
        }
    }
}
