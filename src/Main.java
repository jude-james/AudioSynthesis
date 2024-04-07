import javax.sound.sampled.LineUnavailableException;

public class Main {
    public static void main(String[] args) throws LineUnavailableException {
        Synth synth = new Synth();
        synth.play(Note.A, 3, 1000, 0.5f, Synth.Waveform.Sine);
        synth.play(Note.A, 3, 1000, 0.5f, Synth.Waveform.Square);
        synth.play(Note.A, 3, 1000, 0.5f, Synth.Waveform.Triangle);
        synth.play(Note.A, 3, 1000, 0.5f, Synth.Waveform.Saw);
        synth.play(Note.A, 3, 1000, 0.5f, Synth.Waveform.Noise);
        synth.closeLine();
    }
}

