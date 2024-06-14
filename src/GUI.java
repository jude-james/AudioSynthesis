import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements KeyListener {

    ToneGenerator toneGenerator;

    GUI() throws LineUnavailableException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setVisible(true);
        this.setTitle("Simplest Synth");

        toneGenerator = new ToneGenerator();
        toneGenerator.play();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a' -> {
                // play a C
            }
            case 'w' -> {
                // play a C#
            }
            case 's' -> {
                // play a D
            }
            case 'e' -> {
                // play a D#
            }
            case 'd' -> {
                // play an E
            }
            case 'f' -> {
                // play an F
            }
            case 't' -> {
                // play an F#
            }
            case 'g' -> {
                // play a G
            }
            case 'y' -> {
                // play a G#
            }
            case 'h' -> {
                // play an A
            }
            case 'u' -> {
                // play an A#
            }
            case 'j' -> {
                // play a B
            }
            case 'k' -> {
                // play a C (+1 oct)
            }
        }

        System.out.println("Pressed char: " + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Released char: " + e.getKeyChar());
    }
}

