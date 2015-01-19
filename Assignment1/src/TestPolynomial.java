
public class TestPolynomial {
	
	public static void main(String[] args)
	{
		Polynomial p = new Polynomial();
		p.addTerm(new Term(1.2, 2));
		p.addTerm(new Term(-1.2, 1));
		p.addTerm(new Term(-5.2,1));
		p.addTerm(new Term(5.2,0));
		
		Polynomial q = new Polynomial();
		q.addTerm(new Term(2, 2));
		q.addTerm(new Term(-1, 1));
		q.addTerm(new Term(1,0));
		
		System.out.println(p);
		System.out.println(q);
		System.out.println(p.multiply(q));
		System.out.println(p.getDerivative());
	}
}
