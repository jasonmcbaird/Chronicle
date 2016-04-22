package utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer implements Runnable {
	
    private boolean stopped = true;
    private InputStream is;

    public AudioPlayer(InputStream inputStream) {
    	is = inputStream;
    }
    
    public void terminate() {
    	stopped = true;
    	Logger.print("Terminating the music.");
    }

	public void run() {
		int total, totalToRead, numBytesRead, numBytesToRead;
        byte[] buffer;
        AudioFormat     wav;
        SourceDataLine  lineIn;
        DataLine.Info   info;

        //AudioFormat(float sampleRate, int sampleSizeInBits, 
        //int channels, boolean signed, boolean bigEndian) 
        wav = new AudioFormat(44100, 16, 2, true, false);
        info = new DataLine.Info(SourceDataLine.class, wav);


        buffer = new byte[1024*333];
        numBytesToRead = 1024*333;
        total=0;
        stopped = false;

        if (!AudioSystem.isLineSupported(info)) {
            System.out.print("no support for " + wav.toString() );
        }
        try {
            // Obtain and open the line.
            lineIn = (SourceDataLine) AudioSystem.getLine(info);
            lineIn.open(wav);
            lineIn.start();
            totalToRead = is.available();
            
            
        	while (total < totalToRead && !stopped){
                numBytesRead = is.read(buffer, 0, numBytesToRead);
                if (numBytesRead == -1) break;
                total += numBytesRead;
                lineIn.write(buffer, 0, numBytesRead);
            }
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException nofile) {
            nofile.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
	}
}