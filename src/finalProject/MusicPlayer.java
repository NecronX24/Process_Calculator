package finalProject;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum MusicPlayer {
	player;
	public void reproducirMusica() {
        try {
            // Cargar el archivo de audio
        	String directorioActual = System.getProperty("user.dir");
            
            // Imprimir el directorio actual
        	
            File archivoMusica = new File( directorioActual + "/Latin Industries.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoMusica);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            
            // Reproducir en bucle
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
