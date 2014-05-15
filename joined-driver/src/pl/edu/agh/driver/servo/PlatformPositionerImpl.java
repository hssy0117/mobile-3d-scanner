package pl.edu.agh.driver.servo;

import java.io.IOException;
import java.io.OutputStream;

import gnu.io.SerialPort;

public class PlatformPositionerImpl implements PlatformPositioner {

	private final SerialPort port;
	
	private final int baseValue = 1950;
	
	private final int degreeValue = 10;

	public PlatformPositionerImpl(SerialPort port) {
		this.port = port;
	}

	public void position(int angle) {
		if(angle >= 0 && angle <= 45) {
			sendSignal(baseValue - degreeValue * angle);
		}
	}

	private void sendSignal(int microSeconds) {
		try {
			int signal = microSeconds * 4;

			OutputStream os = port.getOutputStream();
			os.write(0x84);
			os.write(5);
			os.write(signal & 0x7F);
			os.write(signal >> 7 & 0x7F);
			os.flush();
		} catch (IOException e) {
		}
	}

}
