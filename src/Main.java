import javax.sound.midi.*;

public class Main {
    public static void main(String[] args) throws InvalidMidiDataException, MidiUnavailableException, InterruptedException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel channel = synth.getChannels()[0];
        channel.noteOn(60, 80);
        Thread.sleep(500);
    }
}

