import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestPolynomial {
	
	public static void main(String[] args)
	{
		
		String inputString = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean canContinue = true;
		
		while(canContinue)  
		{		
			Polynomial p = null;
			Polynomial q = null; 
			
			do
			{
				// ask user to input p(x)
				System.out.println("Please enter the polynomials p(x): press \"q\" to quit");				
				try 
				{
					inputString = br.readLine();
				} 
				catch (IOException e) {
					canContinue = false;
					break;
				}
				
				if ( !inputString.equals("q"))
				{
					try
					{
						p = Polynomial.parsePolynomial(inputString);
		
					}
					catch (Exception ex)
					{
						p = null;
						System.out.println(ex.getMessage());
					}
				}
				else
				{
					canContinue = false;
					break;
				}
			}while (p == null);
			
			// ask user to input q(x)
			if ( canContinue )
			{
				do
				{
					System.out.println("Please input the polymonial q(x):");
					try 
					{
						inputString = br.readLine();
					} 
					catch (IOException e) {
						canContinue = false;
						break;
					}
					
					try
					{
						q = Polynomial.parsePolynomial(inputString);
					}
					catch (Exception ex)
					{				
						q = null;
						System.out.println(ex.getMessage());
					}
				}while (q == null);
				
				// print out the result
				System.out.println("p(x) = " + p);
				System.out.println("q(x) = " + q);
				System.out.println("p(x) * q(x) = " + p.multiply(q));
				System.out.println("Derivative of p(x) = " + p.getDerivative());
				System.out.println();
				
			}			
		}
		try 
		{
			br.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}		
	}

}
