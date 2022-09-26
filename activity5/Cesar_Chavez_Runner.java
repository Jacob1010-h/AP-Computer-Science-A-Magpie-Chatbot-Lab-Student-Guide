import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * @author Laurie White
 * @version April 2012
 */
public class Cesar_Chavez_Runner
{
	static String userName;
	/**
	 * Create a Magpie, give it user input, and print its replies.
	 */
	public static void main(String[] args)
	{
		boolean firstRun = true;
		Cesar_Chavez maggie = new Cesar_Chavez();

		System.out.println (maggie.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		if (firstRun == true) {
			userName = statement;
			System.out.println("Hello %s, my name is Cesar Chavez, a civil rights activist.".formatted(userName));
			firstRun = false;
			statement = in.nextLine();
		}

		while (!statement.equals("Bye"))
		{
			System.out.println (maggie.getResponse(statement));
			statement = in.nextLine();
		}
	}

	public static String getName(){
		return userName;
	}

	public static void setName(String name){
		userName = name;
	}

}
