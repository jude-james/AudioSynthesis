public class Synth {
    public static final int SAMPLE_RATE = 16 * 1024; // 16KHz

    public static byte[] createSinWaveBuffer(double frequency, int ms) {
        double waveLength = 1.0 / frequency;
        double period = (double) SAMPLE_RATE / frequency;

        // int sampleCount = (int) Math.round(waveLength * SAMPLE_RATE * 100);
        int sampleCount = (ms * SAMPLE_RATE) / 1000;
        byte[] output = new byte[sampleCount];

        for (int i = 0; i < output.length; i++) {
            double angle = 2.0 * Math.PI * i / period;
            output[i] = (byte) (Math.sin(angle) * 127f);
        }

        return output;
    }

    public static byte[] createSawWaveBuffer(double frequency, int ms) {
        return null;
    }

    public static byte[] createSquareWaveBuffer(double frequency, int ms) {
        double waveLength = 1.0 / frequency;
        double period = (double) SAMPLE_RATE / frequency;

        //int sampleCount = (int) Math.round(waveLength * 5 * SAMPLE_RATE);
        int sampleCount = (ms * SAMPLE_RATE) / 1000;
        byte[] output = new byte[sampleCount];

        for (int i = 0; i < output.length; i++) {
            double angle = 2.0 * Math.PI * i / period;
            output[i] = (byte) (Math.signum(Math.sin(angle)) * 127f);
        }

        return output;
    }
}

