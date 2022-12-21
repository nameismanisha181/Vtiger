package vtiger.scripts;
/**
 * 
 * @author 
 *
 */
public class genericmethodToAddNumbers {
	
	//main method -->Caller method
	//add-->Called method--Generic method
	
public static void main(String[] args) {
	
	int sum=add(19,15);
	System.out.println(sum);
	
}

/*	public static void add()
	{
		int a=10;
		int b=20;
		int c=a+b;
		System.out.println(c);
}*/
	
	public static int add(int a,int b)
	{
		int c=a+b;
		return c;
	}

}
