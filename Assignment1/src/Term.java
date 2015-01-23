import java.text.DecimalFormat;

public class Term
{

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
		df.applyPattern("#.#####");
		
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
	
	
	// return a new term initialized to the value represented by the specified String
	public static Term parseTerm(String inputString, char sign) throws Exception
	{
		double coefficient = 0;
		int exponent = 0;
		String exceptionMessage = "Term string: " + inputString + " is invalid.";
		
		if (inputString.contains("x"))
		{
			String array[] = inputString.split("x",-1);
			
			if ( array.length == 2)
			{
				String coefficientString = array[0];
				String exponentString = array[1];
				
				// get coefficient
				if ( coefficientString.isEmpty())
				{
					coefficient = 1;
				}
				else
				{
					try
					{
						coefficient = Double.parseDouble(coefficientString);
					}
					catch (Exception ex)
					{
						// coefficient  string is not a valid double
						throw new Exception(exceptionMessage);
					}
				}
				
				// get exponent
				if ( exponentString.isEmpty())
				{
					exponent = 1;
				}
				else
				{
					if ( exponentString.startsWith("^"))
					{
						// remove "^"
						exponentString = exponentString.substring(1);

						try
						{
							exponent = Integer.parseInt(exponentString);
						}
						catch (Exception ex)
						{
							// exponent string is not a valid integer
							throw new Exception(exceptionMessage);
						}
					}
					else
					{
						// "^" doesn't follow "x"
						throw new Exception(exceptionMessage);
					}
				}
			}
			else
			{
				// more than one x
				throw new Exception(exceptionMessage);
			}
			if (inputString.startsWith("x"))
			{
				coefficient = 1;
			}
		}
		else
		{
			// only number
			try
			{
				coefficient = Double.parseDouble(inputString);
			}
			catch (Exception ex)
			{
				// coefficient  string is not a valid double
				throw new Exception(exceptionMessage);
			}			
		}
		
		if ( sign == '-')
		{
			coefficient = 0 - coefficient;
		}
		
		
		return new Term(coefficient, exponent);
	}
}
