import java.util.LinkedList;

public class Polynomial {
	
	private LinkedList<Term> termList;

	// Default constructor
	public Polynomial() {
		termList = new LinkedList<Term>();
	}

	// copy constructor
	public Polynomial(Polynomial q) {
		termList = q.getTermList();
	}
	
	public LinkedList<Term> getTermList()
	{
		return termList;
	}
	
	// add a term to this polynomial. If this polynomial already has a term
	// with the same exponent as the argument, then the argument term will be
	// combined with the existing one.
	public void addTerm(Term t) 
	{				
		if ( termList.isEmpty() )
		{
			termList.add(t);
			return;
		}
		else 
		{
			// if t's exponent is less than the first item exponent, put it at the first 
			if ( t.getExponent() < termList.getFirst().getExponent() )
			{
				termList.addFirst(t);
				return;
			}
			
			// if t's exponent is greater than the last item exponent, put it at the last 
			if ( t.getExponent() > termList.getLast().getExponent() )
			{
				termList.addLast(t);
				return;
			}
			
			// if there is only 1 term
			if ( termList.size() == 1 && t.getExponent() == termList.getFirst().getExponent() )
			{
				termList.getFirst().addCoefficient(t.getCoefficient());
				return;
			}
			
			for(int i = 0; i < termList.size() - 1; i++)
			{
				Term currentTerm = termList.get(i);
				Term nextTerm = termList.get(i + 1);
				
				if ( currentTerm.getExponent() == t.getExponent())
				{
					currentTerm.addCoefficient(t.getCoefficient());
					return;
				}
				
				if ( nextTerm.getExponent() == t.getExponent())
				{
					nextTerm.addCoefficient(t.getCoefficient());
					return;
				}
				
				if ( currentTerm.getExponent() < t.getExponent() && t.getExponent() < nextTerm.getExponent())
				{
					termList.add(i + 1,t);
					return;
				}	

			}
		}
	}
	
	// Method to multiply two polynomials
	// Postcondition: This polynomial is multiplied with the
	// polynomial specified by the parameter q. A
	// reference of the result is returned.
	public Polynomial multiply(Polynomial q) { 
		
		Polynomial result = new Polynomial();

		for(Term pTerm:termList)
		{
			for(Term qTerm:q.getTermList())
			{
				result.addTerm(pTerm.multiply(qTerm));
			}
		}
		
		return result;
		
	}
	
	// Method to return the derivative
	public Polynomial getDerivative() { 
		
		Polynomial derivative = new Polynomial();
		
		for(Term term:termList)
		{
			derivative.addTerm(term.getDerivative());
		}
		
		return derivative;
	}
	
	
	// Method to return the string containing the polynomial
	public String toString() { 
		
		String result = "";
		
		if ( termList.size() == 1 )
		{
			if ( termList.getFirst().getCoefficient() == 0)
			{
				// return "0" if the only term is "0"
				return "0";  
			}
		}
		
		
		for(Term term:termList)
		{
			String termString = term.toString();
			if ( result.equals("") )
			{
				// remove "+" and space at the very beginning of the string
				result += termString.replace("+", "").replace(" ", "");
			}
			else
			{
				result += termString;
			}
		}
		
		return result;
	}
	
	public static Polynomial parsePolynomial(String inputString) throws Exception
	{
		Polynomial polynomial = new Polynomial();
		
		// remove space in the string
		inputString = inputString.replace(" ","");

		if ( inputString.isEmpty())
		{
			throw new Exception("Input string cannot be empty");
		}
		
		// replace X with x
		inputString = inputString.replace("X","x");
		
		// remove unnecessary *
		inputString = inputString.replace("*x", "x");
		if ( inputString.contains("*"))
		{
			throw new Exception("Input string contains unnecessary *");
		}
		
		// check continuous signs 
		inputString = inputString.replace("++", "+").replace("+-", "-").replace("--", "+").replace("-+", "-");
		if ( inputString.contains("++") || inputString.contains("+-") || inputString.contains("--") || inputString.contains("-+"))
		{
			throw new Exception("Input string contains continuous signs");
		}
		
		// ensure input string starts with a sign
		if (!inputString.startsWith("-"))
		{
			inputString = "+" + inputString;
		}
		
		String termStringArray[] = inputString.split("[+-]",-1);
		char signArray[] = new char[termStringArray.length];
		int signIndex = 0;
		
		for(char c : inputString.toCharArray())
		{
			if ( c == '+' || c == '-' )
			{
				signArray[signIndex] = c;
				signIndex++;
			}
		}
		
		for(int i = 0; i < termStringArray.length; i++)
		{
			if (termStringArray[i].isEmpty() && i > 0)
			{
				throw new Exception("Number must follow a sign");
			}
			else
			{
				try
				{
					if ( i > 0 ) // ignore first empty string
					{
						Term term = Term.parseTerm(termStringArray[i], signArray[i - 1]);
						polynomial.addTerm(term);
					}
				}
				catch (Exception ex)
				{
					throw new Exception(ex.getMessage());
				}
			}
		}
		
		return polynomial;
	}
	
}

