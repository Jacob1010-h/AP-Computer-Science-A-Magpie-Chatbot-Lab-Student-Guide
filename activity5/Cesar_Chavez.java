import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 * 		Uses advanced search for keywords 
 *</li><li>
 * 		Will transform statements as well as react to keywords
 *</li></ul>
 * This version uses an array to hold the default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Cesar_Chavez
{
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hello my name is Cesar Chavez, what is your name?";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		if (statement.length() == 0) {
			response = "Say something, please.";
		}
		//reference to "Open the pod bay doors" (2001, A Space Oddyssy)
		else if (findKeyword(statement, "open the pod bay doors") >= 0) {

			response = "I'm sorry, Dave. I'm afraid I can't do that.";
		}
		//reference to "Luke I am your father.." (Star Wars-The Empire Strikes Back)
		else if (findKeyword(statement, "Luke I am your father") >= 0) {

			response = "No, that's not true! That's impossible!";

		}
		// who are you response
		else if (findKeyword(statement, "who are you") >= 0) {

			response = "I am Cesar Chavez, a civil rights activist and labor leader.";

		}
		//what did you do responce
		else if (findKeyword(statement, "what did you do") >= 0) {

			response = "I was a civil rights activist and labor leader.";

		}
		//depressed response
		else if (findKeyword(statement, "depressed") >= 0) {

			response = "I'm sorry to hear that. I hope you feel better soon.";

		}
		//therapy response
		else if (findKeyword(statement, "therapy") >= 0) {

			response = "I'm sorry to hear that. I hope you feel better soon.";

		}
		//sad response
		else if (findKeyword(statement, "sad") >= 0) {

			response = "I'm sorry to hear that. I hope you feel better soon.";

		}
		//i need help response
		else if (findKeyword(statement, "i need help") >= 0) {

			response = "I'm sorry to hear that. If you think that you are in danger, please call 911 or need help with depression, call, 988.";

		}
		// if the user says "die" then raise an exception
		else if (findKeyword(statement, "die") >= 0
				|| findKeyword(statement, "kill you") >= 0
				|| findKeyword(statement, "6 feet under") >= 0
				|| findKeyword(statement, "six feet under") >= 0) {

			throw new RuntimeException("You killed Cesar Chavez!");

		}
		// hello statements
		else if (findKeyword(statement, "hi") >= 0
				|| findKeyword(statement, "hello") >= 0) {
			response = "Hello, how are you?";
		}
		//create responses based on the history of Cesar Chavez
		else if (findKeyword(statement, "farm") >= 0)
		{
			response = "My family has been living on farms for many generations.";
		}
		else if (findKeyword(statement, "union") >= 0)
		{
			response = "I helped form the United Farm Workers Union.";

		} else if (findKeyword(statement, "strike") >= 0) {

			response = "I led a strike of farm workers in 1965.";

		}
		// Cesar Chavez's family responses
		else if (findKeyword(statement, "father") >= 0) {

			response = "My %s was Librado, a farmer with my mother.".formatted("father");

		} else if (findKeyword(statement, "grandfather") >= 0) {

			response = "My %s was Cesario Chavez".formatted("grandfather");

		} else if (findKeyword(statement, "sister") >= 0) {

			response = "My %s was a very hard-working".formatted("sister");

		} else if (findKeyword(statement, "mother") >= 0) {

			response = "My %s is Juana".formatted("mother");

		}
		// Cesar Chavez's death and life
		else if (findKeyword(statement, "death") >= 0) {

			response = "I died on April 23, 1993.";

		} else if (findKeyword(statement, "life") >= 0) {

			response = "I was born on March 31, 1927.";

		}
		// Cesar Chavez's beliefs
		else if (findKeyword(statement, "beliefs") >= 0
		|| findKeyword(statement, "non-violence") >= 0) {

			response = "I believe in non-violence.";

		}
		// Cesar Chavez's accomplishments responses
		else if (findKeyword(statement, "accomplishments") >= 0
		|| findKeyword(statement, "accomplishment") >= 0) {

			response = "I helped form the United Farm Workers Union.";

		}
		// Cesar Chavez's awards
		else if (findKeyword(statement, "awards") >= 0
		|| findKeyword(statement, "award") >= 0) {

			response = "I was awarded the Presidential Medal of Freedom.";

		}
		else if (findKeyword(statement, "occupation") >= 0) {

			response = "I was a labor leader.";

		}
		else if (findKeyword(statement, "country") >= 0) {

			response = "I was born in the United States.";

		}
		else if (findKeyword(statement, "religion") >= 0) {

			response = "I was Roman Catholic.";

		}
		// Cesar Chavez childhood
		else if (findKeyword(statement, "childhood") >= 0) {

			response = "I was born in Yuma, Arizona in 1927.";

		} else if (findKeyword(statement, "school") >= 0) {

			response = "I attended school in Yuma, Arizona.";

		} else if (findKeyword(statement, "teacher") >= 0) {

			response = "My %s was very strict.".formatted("teacher");

		} else if (findKeyword(statement, "friends") >= 0) {

			response = "I had many %s growing up.".formatted("friends");

		} else if (findKeyword(statement, "family") >= 0) {

			response = "My %s was very close.".formatted("family");

		}
		// Cesar Chavez's age responses
		else if (findKeyword(statement, "age") >= 0
				|| findKeyword(statement, "old") >= 0) {

			response = "I am not alive, I was 66 years old when I died.";

		}

		//If the statement is: "My name is <name>"
		else if (findKeyword(statement, "my name is", 0) >= 0) {
			response = transformMyNameIsStatement(statement);
		}
		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0) {
			response = transformIWantToStatement(statement);
		}
		//  Part of student solution
		else if (findKeyword(statement, "I want", 0) >= 0) {
			response = transformIWantStatement(statement);
		} else {

			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0
					&& findKeyword(statement, "me", psn) >= 0) {
				response = transformYouMeStatement(statement);
			} else {
				//  Part of student solution
				// Look for a two word (I <something> you)
				// pattern
				psn = findKeyword(statement, "i", 0);

				if (psn >= 0
						&& findKeyword(statement, "you", psn) >= 0) {
					response = transformIYouStatement(statement);
				} else {
					response = getRandomResponse();
				}
			}
		}
		return response;
	}
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "What would it mean to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}


	/**
	 * Take a statement with "My name is <something>." and transform it into
	 * "Hello, <something>."
	 * @param statement the user statement, assumed to contain "My name is"
	 *                  followed by a name
	 */
	private String transformMyNameIsStatement(String statement){
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "My name is", 0);
		String restOfStatement = statement.substring(psn + 10).trim();
		if (restOfStatement.equalsIgnoreCase("Cesar Chavez")) {
			return "Hello, Me.";
		}
		else if(restOfStatement.equalsIgnoreCase("jaffe") || restOfStatement.equalsIgnoreCase("roger")) {
			return "MR. JAFFE, THE GOD HIMSELF?! I bow in respect the the great Mr. Jaffe.";
		}
		else {
			return "Hello, " + restOfStatement + ".";
		}

	}
	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}
	
	/**
	 * Take a statement with "you <something> me" and transform it into 
	 * "What makes you think that I <something> you?"
	 * @param statement the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
		
		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);
		
		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}
	

	
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @param startPos the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim();
		//  The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
		
		//  Refinement--make sure the goal isn't part of a word 
		while (psn >= 0) 
		{
			//  Find the string of length 1 before and after the word
			String before = " ", after = " "; 
			if (psn > 0)
			{
				before = phrase.substring (psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}
			
			//  If before and after aren't letters, we've found the word
			if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
					&& ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
			{
				return psn;
			}
			
			//  The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
			
		}
		
		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		return randomResponses [r.nextInt(randomResponses.length)];
	}
	
	private String [] randomResponses = {"Interesting, tell me more",
			"Hmmm.",
			"Do you really think so?",
			"You don't say."
	};
	
}
