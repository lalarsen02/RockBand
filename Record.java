public class Record {

    // Instance Variable
    private ST<String, double[]> tracks;

    // initializes the Symbol Table
    public Record() {
        tracks = new ST<>();
    }

    // inserts a track into the symbol table
    public void insert(String name, double[] audio) {
        tracks.put(name, audio);
    }

    // removes a track from the symbol table
    public void remove(String name) {
        tracks.remove(name);
    }

    // obtains a track from the symbol table
    public double[] get(String name) {
        return tracks.get(name);
    }

    // returns the size of the symbol table
    public int size() {
        return tracks.size();
    }

    // returns if a certain key is in the symbol table.
    public boolean contain(String name) {
        if (tracks.contains(name)) return true;
        return false;
    }

    // Mixes all the tracks together for playback
    public double[] playback() {
        double[] fullTrack = new double[1];
        for (String name : tracks.keys()) {
            fullTrack = Effects.mix(fullTrack, tracks.get(name));
        }
        return Effects.normalize(fullTrack);
    }

    // Prints out the tracks in list order.
    public String toString() {
        String result = "";
        int i = 1;

        for (String name : tracks.keys()) {
            result += i + ": " + name + " ";
            i++;
            result += "\n";
        }

        return result;
    }

    // Test client
    public static void main(String[] args) {
        Record record = new Record();

        String audio1 = "banana";
        double[] doubles1 = new double[3];
        doubles1[0] = 1;
        doubles1[1] = 2;
        doubles1[2] = 3;
        String audio2 = "pear";
        double[] doubles2 = new double[3];
        String audio3 = "apple";
        double[] doubles3 = new double[3];
        String audio4 = "orange";
        double[] doubles4 = new double[4];
        for (int i = 0; i < 4; i++) {
            doubles4[i] = i;
        }

        record.insert(audio1, doubles1);
        record.insert(audio2, doubles2);
        record.insert(audio3, doubles3);
        record.insert(audio4, doubles4);

        record.remove(audio2);

        StdOut.println(record.size());
        StdOut.println();
        for (int j = 0; j < record.playback().length; j++) {
            StdOut.println(record.playback()[j]);
        }
        StdOut.println();

        StdOut.println(record.contain("pear"));

        StdOut.println(record);
    }
}
