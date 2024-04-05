public enum Notes {

    REST, C, C$, D, D$, E, F, F$, G, G$, A, A$, B;

    private static final double STANDARD_PITCH = 440d;
    private static final int DEFAULT_OCTAVE = 4;

    private double frequency = 0d;

    Notes() {
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
        if (octave > DEFAULT_OCTAVE) {
            return (octave - (DEFAULT_OCTAVE-1)) * frequency;
        }
        else if (octave < DEFAULT_OCTAVE) {
            return frequency / ((DEFAULT_OCTAVE+1) - octave);
        }
        else
            return frequency;
    }
}
