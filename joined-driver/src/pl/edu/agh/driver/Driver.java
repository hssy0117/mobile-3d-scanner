package pl.edu.agh.driver;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.driver.servo.PlatformPositioner;

import com.kristou.urgLibJ.RangeSensor.RangeSensor;
import com.kristou.urgLibJ.RangeSensor.Capture.CaptureData;

public class Driver {
	
	public static void main(String[] args) {
		ComponentFactory componentFactory = new ComponentFactoryImpl();
		PlatformPositioner platformPositioner = componentFactory.getPlatformPositioner();
		RangeSensor rangeSensor = componentFactory.getRangeSensor();

		List<CaptureData> allAnglesData = new ArrayList<>(45);
		for(int i = 0; i < 45; i++) {
			platformPositioner.position(i);
			allAnglesData.add(rangeSensor.capture());
		}
	}

}
