import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.DataLine.Info;

public class PlaySound {

  private InputStream waveStream;
  private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
  private boolean paused = false;
  private int offset = 0;

  public PlaySound(InputStream waveStream) {
		this.waveStream = waveStream;
  }

  SourceDataLine dataLine = null;
  public void play() throws PlayWaveException {

		AudioInputStream audioInputStream = null;
		try {
			InputStream bufferedIn = new BufferedInputStream(this.waveStream); // new
		    audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
		} catch (UnsupportedAudioFileException e1) {
		    throw new PlayWaveException(e1);
		} catch (IOException e1) {
		    throw new PlayWaveException(e1);
		}

		// Obtain the information about the AudioInputStream
		AudioFormat audioFormat = audioInputStream.getFormat();
		Info info = new Info(SourceDataLine.class, audioFormat);

		// opens the audio channel
		try {
		    dataLine = (SourceDataLine) AudioSystem.getLine(info);
		    dataLine.open(audioFormat, this.EXTERNAL_BUFFER_SIZE);
		} catch (LineUnavailableException e1) {
		    throw new PlayWaveException(e1);
		}

		// Starts the music :P
		dataLine.start();

		int readBytes = 0;
		byte[] audioBuffer = new byte[this.EXTERNAL_BUFFER_SIZE];
		try {
			while (readBytes != -1) {
				readBytes = audioInputStream.read(audioBuffer, 0, audioBuffer.length);
				if (readBytes >= 0){
					dataLine.write(audioBuffer, 0, readBytes);
					offset++;
				}
				if(paused){
					break;
				}
			}
		}catch (IOException e1) {
			throw new PlayWaveException(e1);
		}finally {
			// plays what's left and and closes the audioChannel
			dataLine.drain();
			dataLine.close();
		}
  }
  
  public void stop() {
	  dataLine.close();	  
  }
  
  public void pause() {
	  dataLine.close();	  
	  paused = true;
	  System.out.println(offset);
  }
}
