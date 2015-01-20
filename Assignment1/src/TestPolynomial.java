import java.util.Scanner;


public class TestPolynomial {
	
	public static void main(String[] args)
	{
//		System.out.print("Please enter the polynomials p(x): ");
//		Scanner scanner = new Scanner(System.in);
//		String inputString = scanner.nextLine();
//		
//		String checkValidResult = checkValid(inputString);
//		if ( checkValidResult.isEmpty() ) 
//		{
//			//input string is valid
//		}
//		else
//		{
//			//prompt user to re-enter
//		}
		
		Polynomial p = new Polynomial();
		p.addTerm(new Term(1.2, 0));
		p.addTerm(new Term(1.2, 2));
		p.addTerm(new Term(-1.2, 1));
		p.addTerm(new Term(-5.2,1));
		p.addTerm(new Term(5.2,0));
		p.addTerm(new Term(8.2,5));
		p.addTerm(new Term(-7.2,3));
		
		Polynomial q = new Polynomial();
		q.addTerm(new Term(2, 2));
		q.addTerm(new Term(-1, 4));
		q.addTerm(new Term(-1, 1));
		q.addTerm(new Term(1,0));
		
		System.out.println("p(x) = " + p);
		System.out.println("q(x) = " + q);
		System.out.println("p(x) * q(x) = " + p.multiply(q));
		System.out.println("Derivative of p(x) = " + p.getDerivative());
	}
	
	private static String checkValid(String inputString)
	{
		String result = "";
		
		//remove unnecessary space
		inputString = inputString.replace(" ","");
		
		String terms[] = inputString.split("[+,-]");
		for(int i = 0; i < terms.length; i++)
		{
			String term = terms[i];
			if ( term.isEmpty() && i > 0 )
			{
				result = "";
				break;
			}
			
			
		}
		
		return result;
	}
}
