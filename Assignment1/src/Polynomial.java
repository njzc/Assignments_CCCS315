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
		
		for(Term term:termList)
		{
			if ( term.getExponent() == t.getExponent())
			{
				term.addCoefficient(t.getCoefficient());
				return;
			}
		}
		
		termList.add(t);
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
		Collections.sort(termList);
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

