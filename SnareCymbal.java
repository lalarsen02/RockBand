public class SnareCymbal {

    // Instance Variables
    private RingBuffer string; // The Guitar String
    private int n; // The Capacity and Size of the RingBuffer
    private final int SAMPLING_RATE = 44100;

    // Creates a guitar string of the specified frequency,
    // using a sampling rate of 44,100.
    public SnareCymbal(double frequency) {
        n = (int) Math.ceil(SAMPLING_RATE / frequency);
        string = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            string.enqueue(0);
        }
    }

    // Creates a guitar string whose length and initial values
    // are given by the specified array.
    public SnareCymbal(double[] init) {
        n = init.length;
        string = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            string.enqueue(init[i]);
        }
    }

    // Returns the number of samples in the ring buffer.
    public int length() {
        return string.capacity();
    }

    // Hits the snare or cymbal by replacing the ring buffer with white noise.
    public void pluck() {
        for (int i = 0; i < string.capacity(); i++) {
            double x = StdRandom.uniform() - 0.5;
            string.dequeue();
            string.enqueue(x);
        }
    }

    // Advances the Karplus-Strong simulation one time step.
    public void tic() {
        double x = string.dequeue();
        double y = string.peek();
        double enqueue = (x + y) / 2 * 0.98; // The decay factor
        if (StdRandom.uniform() < 0.5) {  // Percussion Algorithm
            enqueue = enqueue * -1;
        }
        string.enqueue(enqueue);
    }

    // Returns the current sample.
    public double sample() {
        return string.peek();
    }

    // Tests this class by directly calling both constructors
    // and all instance methods.
    public static void main(String[] args) {
        // Create 37 Drums / Cymbals
        SnareCymbal[] snareCymbal = new SnareCymbal[37];
        for (int i = 0; i < 37; i++) {
            snareCymbal[i] = new SnareCymbal(440.0 * Math.pow(2, (i - 24) / 12.0));
        }
        StdOut.println(snareCymbal.length);

        // the main input loop
        Keyboard keyboard = new Keyboard();
        String piano =
                "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        char[] pianoKeys = new char[piano.length()];
        for (int i = 0; i < piano.length(); i++) {
            pianoKeys[i] = piano.charAt(i);
        }
        while (true) {

            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {

                // the key the user played
                char key = keyboard.nextKeyPlayed();

                // pluck the corresponding string
                for (int j = 0; j < piano.length(); j++) {
                    if (key == pianoKeys[j]) {
                        snareCymbal[j].pluck();
                    }
                }
            }

            // compute the superposition of the samples
            double sample = 0;
            for (int k = 0; k < piano.length(); k++) {
                sample += snareCymbal[k].sample();
            }

            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int m = 0; m < piano.length(); m++) {
                snareCymbal[m].tic();
            }
        }
    }
}

