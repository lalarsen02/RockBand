public class Effects {
    // Returns a new array that rescales a[] by a factor of alpha.
    public static double[] amplify(double[] a, double alpha) {
        double[] b = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i] * alpha;
        }
        return b;
    }

    // Returns a new array that is the reverse of a[].
    public static double[] reverse(double[] a) {
        double[] b = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[a.length - i - 1];
        }
        return b;
    }

    // Returns a new array that adds an echo of the old array to itself
    public static double[] echo(double[] a, int tempo, int echoDelay) {
        int firstDelay = (int) Math.floor((60.0 / tempo) * 4 * (echoDelay / 8.0) * 44100);
        int secondDelay = (int) Math.floor((60.0 / tempo) * 4 * (2.0 * echoDelay / 8.0) * 44100);
        double[] b = new double[a.length - firstDelay];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[i] * 0.8;
        }
        double[] c = new double[a.length - secondDelay];
        for (int j = 0; j < c.length; j++) {
            c[j] = a[j] * 0.4;
        }
        for (int k = 0; k < b.length; k++) {
            a[k + firstDelay] = a[k + firstDelay] + b[k];
        }
        for (int m = 0; m < c.length; m++) {
            a[m + secondDelay] = a[m + secondDelay] + c[m];
        }
        return a;
    }

    // Adds a Reverberation of the Sound to itself
    public static double[] reverb(double[] a) {
        double sampleRate = 44100;
        double[] b = new double[a.length - (int) (sampleRate * 0.1)];
        double[] c = new double[a.length - (int) (sampleRate * 0.12)];
        double[] d = new double[a.length - (int) (sampleRate * 0.14)];
        double[] e = new double[a.length - (int) (sampleRate * 0.17)];
        double[] f = new double[a.length - (int) (sampleRate * 0.21)];

        for (int g = 0; g < b.length; g++) {
            b[g] = a[g] * 0.3;
            a[g + (int) (sampleRate * 0.1)] = a[g + (int) (sampleRate * 0.1)] + b[g];
        }
        for (int h = 0; h < c.length; h++) {
            c[h] = a[h] * 0.26;
            a[h + (int) (sampleRate * 0.12)] = a[h + (int) (sampleRate * 0.12)] + c[h];
        }
        for (int i = 0; i < d.length; i++) {
            d[i] = a[i] * 0.23;
            a[i + (int) (sampleRate * 0.14)] = a[i + (int) (sampleRate * 0.14)] + d[i];
        }
        for (int j = 0; j < e.length; j++) {
            e[j] = a[j] * 0.18;
            a[j + (int) (sampleRate * 0.17)] = a[j + (int) (sampleRate * 0.17)] + e[j];
        }
        for (int k = 0; k < f.length; k++) {
            f[k] = a[k] * 0.14;
            a[k + (int) (sampleRate * 0.21)] = a[k + (int) (sampleRate * 0.21)] + f[k];
        }
        return a;
    }

    // Returns a new array that is the sum of a[] and b[],
    // padding the shorter array with trailing 0s if necessary.
    public static double[] mix(double[] a, double[] b) {
        double[] mixed = new double[Math.max(a.length, b.length)];
        for (int i = 0; i < a.length; i++)
            mixed[i] += a[i];
        for (int i = 0; i < b.length; i++)
            mixed[i] += b[i];
        return mixed;
    }

    // Normalizes a sound so that all values are between -1 and 1
    public static double[] normalize(double[] a) {
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;
        double rescale;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) max = a[i];
            if (a[i] < min) min = a[i];
        }
        rescale = Math.max(Math.abs(max), Math.abs(min));
        for (int j = 0; j < a.length; j++) {
            a[j] = a[j] / rescale * 0.95;
        }
        return a;
    }

    // Creates Short Clipping Overdrive
    // https://users.cs.cf.ac.uk/Dave.Marshall/CM0268/PDF/10_CM0268_Audio_FX.pdf
    public static double[] overdrive(double[] a) {
        for (int i = 0; i < a.length; i++) {
            if (Math.abs(a[i]) < 0.33) {
                a[i] = a[i] * 2;
            }
            else if (Math.abs(a[i]) < 0.67) {
                if (a[i] > 0) {
                    a[i] = (3 - Math.pow((2 - 3 * a[i]), 2)) / 3;
                }
                else a[i] = (-3 + Math.pow((2 + 3 * a[i]), 2)) / 3;
            }
            else {
                if (a[i] > 0) a[i] = 1;
                else a[i] = -1;
            }
        }
        return a;
    }

    // Creates Distortion or Fuzz
    // https://users.cs.cf.ac.uk/Dave.Marshall/CM0268/PDF/10_CM0268_Audio_FX.pdf
    public static double[] distortion(double[] a, double gain, double mix) {
        double[] b = new double[a.length];
        double[] c = new double[a.length];
        double[] d = new double[a.length];
        double maxA = Double.NEGATIVE_INFINITY;
        double maxC = Double.NEGATIVE_INFINITY;
        double maxD = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < a.length; i++) {
            if (Math.abs(a[i]) > maxA) maxA = Math.abs(a[i]);
        }
        for (int j = 0; j < a.length; j++) {
            b[j] = a[j] * gain / maxA;
        }
        for (int k = 0; k < a.length; k++) {
            int e;
            if (b[k] > 0) e = 1;
            else if (b[k] < 0) e = -1;
            else e = 0;

            c[k] = e * (1 - Math.E * (e * b[k]));
        }
        for (int n = 0; n < a.length; n++) {
            if (Math.abs(c[n]) > maxC) maxC = Math.abs(c[n]);
        }
        for (int m = 0; m < a.length; m++) {
            d[m] = mix * c[m] * maxA / (maxC + (1 - mix) * a[m]);
        }
        for (int p = 0; p < a.length; p++) {
            if (Math.abs(d[p]) > maxD) maxD = Math.abs(d[p]);
        }
        for (int q = 0; q < a.length; q++) {
            d[q] = d[q] * maxA / maxD;
        }
        return d;
    }

    // Test Client for these effects
    public static void main(String[] args) {

        // Audio being used
        double[] song = StdAudio.read("singer.wav");
        double[] song2 = StdAudio.read("beatbox.wav");


        StdAudio.play(song);
        StdAudio.play(normalize(amplify(song, 5)));
        StdAudio.play(reverse(song));
        StdAudio.play(echo(song, 100, 1));
        StdAudio.play(reverb(song));
        StdAudio.play(mix(song, song2));
        StdAudio.play(overdrive(song));
        StdAudio.play(distortion(song, 10, 0.5));
    }
}
