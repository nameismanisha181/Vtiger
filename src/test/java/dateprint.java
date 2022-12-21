import java.time.LocalDateTime;

public class dateprint {

	public static void main(String[] args) {
		
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		String timestampt = ldt.toString().replace(':', '-');
		System.out.println(timestampt);
		
	}
}
