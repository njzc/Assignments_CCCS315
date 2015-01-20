import java.util.Collections;
import java.util.LinkedList;


public class Polynomial {
	private LinkedList<Term> termList;

	// Default constructor
	public Polynomial() {
		termList = new LinkedList<Term>();
	}

	// copy constructor
	public Polynomial(Polynomial q) {
		
	}
	
	public LinkedList<Term> getTermList()
	{
		return termList;
	}
	
	// add a term to this polynomial. If this polynomial already has a term
	// with the same exponent as the argument, then the argument term will be
	// combined with the existing one.
	public void addTerm(Term t) {
		
		int minExponent = 0;
		int maxExponent = 0;
		
		if ( termList.size() > 0 )
		{
			minExponent = termList.get(0).getExponent();
			maxExponent = termList.get(0).getExponent();
		}
		
		for(Term term:termList)
		{
			if ( term.getExponent() == t.getExponent())
			{
				term.addCoefficient(t.getCoefficient());
				return;
			}
			
			if ( term.getExponent() >= maxExponent )
			{
				maxExponent = term.getExponent();
			}
			
			if ( term.getExponent() <= minExponent )
			{
				minExponent = term.getExponent();
			}
		}
		
		if ( t.getExponent() <= minExponent )
		{
			termList.addFirst(t);
		}
		else if ( t.getExponent() >= maxExponent)
		{
			termList.addLast(t);
		}
		else
		{
			for(int i = 0; i < termList.size() - 1;i ++)
			{
				if ( termList.get(i).getExponent() < t.getExponent() && t.getExponent() < termList.get(i + 1).getExponent())
				{
					termList.add(i + 1,t);
					break;
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
		//Collections.sort(termList);
		for(Term term:termList)
		{
			String termString = term.toString();
			if ( result.equalsIgnoreCase("") )
			{
				//remove + and space at the very beginning of the string
				result += termString.replace("+", "").replace(" ", "");
			}
			else
			{
				result += termString;
			}
		}
		
		return result;
	}
	
}

