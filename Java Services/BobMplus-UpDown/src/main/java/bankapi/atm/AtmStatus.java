package bankapi.atm;


public class AtmStatus {

	static int max=2;
	public static String isAtmUp(int atmId)
	{
		Integer j= (int) (Math.random()*max);
		return j.toString();
	}
	public static String isCashAvailable(int atmId)
	{
		Integer i= (int) (Math.random()*max);
		return i.toString();
	}
	public static void main (String a[])
	{
		AtmStatus a1=new AtmStatus();
		for (int i=0;i<=10;i++)
		{
			System.out.println(a1.isAtmUp(1)+" == "+a1.isCashAvailable(1));
		}
	}
}
