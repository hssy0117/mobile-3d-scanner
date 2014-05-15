package pl.edu.agh.driver;

import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

class PortAccessorImpl implements PortAccessor {

	public SerialPort getPort(String portName, String applicationName,
			int waitMilis) {
		Enumeration<?> portIdentifiers = CommPortIdentifier
				.getPortIdentifiers();

		CommPortIdentifier desiredPortId = null;
		while (portIdentifiers.hasMoreElements()) {
			CommPortIdentifier currentPortID = (CommPortIdentifier) portIdentifiers
					.nextElement();

			if (currentPortID != null
					&& currentPortID.getPortType() == CommPortIdentifier.PORT_SERIAL
					&& currentPortID.getName().equals(portName)) {
				desiredPortId = currentPortID;
				break;
			}
		}

		SerialPort port = null;
		if (desiredPortId != null) {
			try {
				port = (SerialPort) desiredPortId.open(applicationName,
						waitMilis);
			} catch (PortInUseException e) {
			}
		}

		return port;
	}
}
