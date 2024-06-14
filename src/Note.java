public enum Note {
    REST, C, C$, D, D$, E, F, F$, G, G$, A, A$, B;

    private static final double STANDARD_PITCH = 440d;
    private static final int DEFAULT_OCTAVE = 4;

    private double frequency = 0d;

    Note() {
        calculateFrequency();
    }

    private void calculateFrequency() {
        // Uses A4 (= 440Hz) as a reference pitch by convention
        // However the scale starts at C4

        int n = ordinal();
        int standardPitchIndex = 10;
        int difference = n - standardPitchIndex;

        if (n > 0) { // so that REST doesn't get a frequency
            double exponent = difference / 12d;
            frequency = STANDARD_PITCH * Math.pow(2d, exponent);
        }
    }

    public double getFrequency(int octave) {
        return frequency * Math.pow(2d, octave - DEFAULT_OCTAVE);
    }
}
