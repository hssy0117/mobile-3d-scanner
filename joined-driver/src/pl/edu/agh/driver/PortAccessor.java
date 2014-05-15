package pl.edu.agh.driver;

import gnu.io.SerialPort;

public interface PortAccessor {

	SerialPort getPort(String portName, String applicationName,
			int waitMilis);
	
}
