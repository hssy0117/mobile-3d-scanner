package pl.edu.agh.driver;

import gnu.io.SerialPort;
import pl.edu.agh.driver.servo.PlatformPositioner;
import pl.edu.agh.driver.servo.PlatformPositionerImpl;

import com.kristou.urgLibJ.Connection.SerialConnection;
import com.kristou.urgLibJ.RangeSensor.RangeSensor;
import com.kristou.urgLibJ.RangeSensor.UrgDevice;
import com.kristou.urgLibJ.RangeSensor.Capture.CaptureSettings;

public class ComponentFactoryImpl implements ComponentFactory {

	private static final String applicationName = "Driver";

	private static final String platformPortName = "/dev/ttyACM0";

	private static final String urgPortName = "/dev/ttyACM1";

	@Override
	public PlatformPositioner getPlatformPositioner() {
		PortAccessorImpl portAccessor = new PortAccessorImpl();
		SerialPort port = portAccessor.getPort(platformPortName,
				applicationName, 10000);

		if (port != null) {
			return new PlatformPositionerImpl(port);
		} else {
			throw new IllegalStateException(
					"Could not establish connection to platform.");
		}
	}

	@Override
	public RangeSensor getRangeSensor() {
		UrgDevice rangeSensor = new UrgDevice(new SerialConnection());

		if (rangeSensor.connect(urgPortName)) {
			rangeSensor.setCaptureMode(CaptureSettings.CaptureMode.GD_Capture_mode);
			return rangeSensor;
		} else {
			throw new IllegalStateException(
					"Could not establish connection to range sensor.");
		}
	}

}
