package pl.edu.agh.driver;

import pl.edu.agh.driver.servo.PlatformPositioner;

import com.kristou.urgLibJ.RangeSensor.RangeSensor;

public interface ComponentFactory {
	
	PlatformPositioner getPlatformPositioner();
	
	RangeSensor getRangeSensor();

}
