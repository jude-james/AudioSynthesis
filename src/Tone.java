public class Tone {
    public boolean isPlaying;
    private static int currentlyPlaying = 0;

    public byte[] tone;

    private boolean toggle = true;

    public Tone(double frequency, float volume) {
        this.tone = Wavetable.generateSaw(frequency, volume);
    }

    public void startPlaying() {
        isPlaying = true;

        if (toggle) {
            currentlyPlaying++;
            toggle = false;
        }
    }

    public void stopPlaying() {
        isPlaying = false;

        toggle = true;
        currentlyPlaying--;
    }

    public static int getCurrentlyPlaying() {
        return currentlyPlaying;
    }
}
