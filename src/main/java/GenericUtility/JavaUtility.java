package GenericUtility;

import java.time.LocalDateTime;
import java.util.Random;

import org.apache.commons.io.input.TeeInputStream;

public class JavaUtility {

	public int getRandomNumber()
	{
		Random ran= new Random();
		return ran.nextInt();
	}
	
	public String dataStampFormat()
	{
		LocalDateTime ldt = LocalDateTime.now();
		String timestampe = ldt.toString().replace(':', '-');
		return timestampe;
	}
}
