public class DrumSet {

    public static void main(String[] args) {

        // create drum set
        BassDrum bassDrum = new BassDrum(75);
        SnareCymbal snare = new SnareCymbal(100);
        BassDrum highTom = new BassDrum(300);
        BassDrum midTom = new BassDrum(200);
        BassDrum lowTom = new BassDrum(100);
        SnareCymbal closedCymbal = new SnareCymbal(1000);
        SnareCymbal openCymbal = new SnareCymbal(400);

        // the main input loop
        Keyboard keyboard = new Keyboard();
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
}
