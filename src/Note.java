public enum Note {
    REST, A4, A4$, B4, C4, C4$, D4, D4$, E4, F4, F4$, G4, G4$;

    private byte[] toneBuffer;

    Note() {
        // set each note to a tone using Synth.createSinWaveBuffer();
        // and a bit of math for note frequency's
        // toneBuffer = Synth.createSinWaveBuffer(frequency calculated above <--);

        int n = ordinal();

        if (n > 0) {
            double exponent = ((double) n - 1) / 12d;
            double frequency = 440d * Math.pow(2d, exponent);
            toneBuffer = Synth.createSinWaveBuffer(frequency);
        }
    }

    public byte[] tone() {
        return toneBuffer;
        // fix rest note returning null
    }
}
