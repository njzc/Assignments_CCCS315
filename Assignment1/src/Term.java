import java.text.DecimalFormat;

public class Term implements Comparable<Term> {

	private double coefficient;
	private int exponent;

	public Term(double coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public int getExponent() {
		return exponent;
	}

	public void addCoefficient(double coefficient) {
		this.coefficient += coefficient;
	}
	
	public Term multiply(Term term)
	{
		return new Term(coefficient * term.getCoefficient(), exponent + term.getExponent());
	}

	public Term getDerivative()
	{
		return new Term(coefficient * exponent, exponent - 1);
	}
	
	public String toString()
	{
		String result = "";
		
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#.####");
		
		if ( coefficient > 0 )
		{
			if ( coefficient == 1.0 && exponent != 0)
			{
				//display "1x" as "x" 
				result += " + ";
			}
			else
			{
				result += " + "+ df.format(coefficient);
			}
		}
		else if ( coefficient < 0 )
		{
			if ( coefficient == -1.0 && exponent != 0)
			{
				//display "-1x" as "-x" 
				result += " - ";
			}
			else
			{
				result += " - " + df.format((-coefficient)); 
			}
		}
		else 
		{
			return "";
		}
		
		//only print exponent when exponent is greater than 1
		if ( exponent > 1 ) //
		{
			result += "x^" + exponent;
		}
		else if ( exponent == 1 )
		{
			result += "x";
		}
		else if ( exponent == 0 )
		{
			//don't print anything when exponent is equal to 0
		}
		
		return result;
	}
	
	@Override
	public int compareTo(Term target) {

		int comparedExponent = target.getExponent();
		
		if (this.exponent > comparedExponent) {
			return 1;
		} else if (this.exponent == comparedExponent) {
			return 0;
		} else {
			return -1;
		}
	}
}
