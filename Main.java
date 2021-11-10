
import java.lang.Exception;
import java.math.BigInteger;
import java.util.Scanner;

public class Main
{
  public static void main(String[] args) throws java.lang.Exception
	{    
    boolean runAgain = false;
    Scanner in = new Scanner(System.in);

    do
    {
      // Easier than repeatedly referencing BigInteger.ZERO and BigInteger.ONE
      BigInteger zero = BigInteger.ZERO;
      BigInteger one = BigInteger.ONE;
    
      String input = "";
      boolean validInput = false;
      BigInteger prime = one; // Initializes value

      while (!validInput) // Continues to run till the variable "prime" is set to a positive integer
      {
        System.out.print("Enter a positive integer to test: ");
        input = in.next();
        try // Set final value and exit loop
        {
          prime = new BigInteger(input); // Will naturally throw an exception if not a number
          if (prime.compareTo(zero) < 0) // prime is negative
          {
            throw new Exception(); // Will also jump to catch statement
          }
          validInput = true;
        }
        catch (Exception e) // Input is not a positive integer
        {
          // validInput not affected, will restart loop
          System.out.println("Not a positive integer. Try again. \n");
        }
      }

      // Will not test for factors above 1/2 the given number
      BigInteger limit = prime.divide(BigInteger.valueOf(2));

      // Used to calculate elapsed time, starts "clock"
      long startTimeMillis = System.currentTimeMillis();

		  boolean isComposite = false;
      boolean isZeroOrOne = false;

		  if (prime.compareTo(one) < 1) // prime is 0 or 1
      {
        if (prime.compareTo(zero) == 0) // prime is 0
        {
          System.out.println("Zero is neither prime nor composite.");
        }
        else // prime is 1
        {
          System.out.println("The number one is a unit, neither prime nor composite.");
        }
        isZeroOrOne = true;
      }

      for (BigInteger i = one; limit.compareTo(i) >= 0; i = i.add(one))
      {
        if (zero.compareTo(prime.remainder(i)) == 0 && i != one && prime.divide(i) != one)
	{
	  System.out.println("Success! The factors are " + i + " and " + prime.divide(i) + ".");
	  isComposite = true;
	  break;
        }
      }
	
      // Reached when the above for loop ends, whether it has found a prime factor or not
      if (!isComposite && !isZeroOrOne)
      {
	System.out.println("It appears the number " + prime + " is prime!");
      }
	    
      // Calculates the time in milliseconds taken to complete the calculation
      long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;

      // Converts the milliseconds into seconds and minutes
      long elapsedTimeSec = 0, elapsedTimeMin = 0;
      if (elapsedTimeMillis > 1000)
      {
        elapsedTimeSec += elapsedTimeMillis / 1000;
        elapsedTimeMillis = elapsedTimeMillis % 1000;
        if (elapsedTimeSec > 60)
        {
          elapsedTimeMin += elapsedTimeSec / 60;
          elapsedTimeSec = elapsedTimeSec % 60;
        }
      }
      System.out.println("Elapsed time: " + elapsedTimeMin + " minutes, " + elapsedTimeSec
        + " seconds, and " + elapsedTimeMillis + " milliseconds.");

      System.out.print("Press Y to run again: ");
      if (in.next().equalsIgnoreCase("y"))
      {
        runAgain = true;
        System.out.println();
      }
      else
      {
        runAgain = false;
      }
    }
    while (runAgain);

    in.close(); // Closes Scanner
  }
}
