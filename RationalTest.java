import junit.framework.TestCase;

public class RationalTest extends TestCase {

    protected Rational HALF;
	static final int INT_MAX = 2147483647;	
	static final int INT_MIN = -2147483648;
	
    protected void setUp() {
    	HALF = new Rational( 1, 2 );

    }
    
    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
    }
    
    public void testEqualityForNull() {
        Rational a = new Rational(1,3);
        Rational b = null;
        
        assertFalse("Confirm that null objects are handled by equals() function", a.equals(b));
    }
    
    public void testEqualityForIncorrectObjectType() {
        Rational a = new Rational(1,3);
        Rational b = new RationalSpoof(1,3);
        
        assertFalse("Confirm that improper object types are screened"+b.getClass().toString(), a.equals(b));
    }    
    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
            new Rational(1,3)));
    }

    public void testAbs(){
        Rational a = new Rational(-1,3);
        Rational b = a.abs();
 
        assertEquals("Test that Absolute value function works if only numerator is negative", 1, b.numerator());
    }
    
    public void testAccessors() {
    	assertEquals(new Rational(2,3).numerator(), 2);
    	assertEquals(new Rational(2,3).denominator(), 3);
    }

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }
    
    public void testRootWithNegativeNumber() {
        Rational s = new Rational( -4, 1 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
            
            //This test should fail and throw IllegalArgumentToSquareRootException
            //Fail if a different type of exception is thrown. 
            assertTrue("Confirm Exception Class Type:"+e.getClass().toString(), e.getClass().toString().equals(new String("class IllegalArgumentToSquareRootException")));
        
            fail("Illegal operation: Square Root of a negative number");

        }
        //This test should always fail.  Fail if it has not already thrown an exception by this point. 
        fail("This Test should never reach this point.");
    }
    
    
    /*
    //  Test Constructor, primarily by attacking the GCD private function. 
    */
    public void testConstructorGcdBasic()
    {	
    	//Test Baseline Functionality
    	Rational s = new Rational(12,4);
    	assertEquals("Test Basic GCD Functionality, check numerator: ", 3, s.numerator() );
    	assertEquals("Test Basic GCD Functionality, check denominator: ", 1, s.denominator());
    }
    
    public void testConstructorGcdRelativelyPrime()
    {	
    	//Test GCD functionality with 2 relatively prime numbers
    	Rational s = new Rational(12,5);
    	assertEquals("Test GCD Functionality using 2 relatively prime numbers, check numerator: ", 12, s.numerator() );
    	assertEquals("Test GCD Functionality using 2 relatively prime numbers, check denominator: ", 5, s.denominator());
    }

    public void testConstructorGcdPrimeVsPrime()
    {	
    	//Test GCD functionality with 2 prime numbers
    	Rational s = new Rational(11,5);
    	assertEquals("Test Basic GCD Functionality using 2 prime numbers, check numerator: ", 11, s.numerator() );
    	assertEquals("Test Basic GCD Functionality using 2 prime numbers, check denominator: ", 5, s.denominator());
    }
    
    public void testConstructorGcdZeroInNumerator()
    {	
    	//Test GCD functionality with zero provided to numerator
    	Rational s = new Rational(0,5);
    	assertEquals("Test Basic GCD Functionality with zero provided as numerator, check numerator: ", 0, s.numerator() );
    	assertEquals("Test Basic GCD Functionality with zero provided as numerator, check denominator: ", 5, s.denominator());
    }
    
    public void testConstructorGcdZeroInDenominator()
    {	
    	//Test GCD functionality with zero provided to denominator
    	Rational s = new Rational(5,0);
    	assertEquals("Test Basic GCD Functionality with zero provided as denominator, check numerator: ", 5, s.numerator() );
    	assertEquals("Test Basic GCD Functionality with zero provided as denominator, check denominator: ", 0, s.denominator());
    }
    
    public void testConstructorGcdNegInDenominator()
    {	
    	//Test GCD functionality with negative number provided to denominator
    	Rational s = new Rational(-10,5);
    	assertEquals("Test Basic GCD Functionality with negative number provided to denominator, check numerator: ", -2, s.numerator() );
    	assertEquals("Test Basic GCD Functionality with negative number provided to denominator, check denominator: ", 1, s.denominator());
    }
    
    public void testConstructorGcdNegInNumerator()
    {	
    	//Test GCD functionality with negative number provided to numerator
    	Rational s = new Rational(10,-5);
    	assertEquals("Test Basic GCD Functionality with negative number provided to numerator, check numerator: ", 2, s.numerator() );
    	assertEquals("Test Basic GCD Functionality with negative number provided to numerator, check denominator: ", -1, s.denominator());
    }
    
    public void testSetGetTolerance()
    {	
    	Rational n = new Rational(1,100);
    	
    	Rational t = Rational.getTolerance();  //get default tolerance
    	
    	assertEquals("Testing Tolerance Get/Set: get original tolerance numerator", 1, t.numerator() );
    	assertEquals("Testing Tolerance Get/Set: get original tolerance denominator", 1000, t.denominator() );

    	Rational.setTolerance(n);
    	t = Rational.getTolerance();
    	
    	assertEquals("Testing Tolerance Get/Set: set and recheck new tolerance numerator", 1, t.numerator() );
    	assertEquals("Testing Tolerance Get/Set: set and recheck new tolerance denominator", 100, t.denominator() );
    	
    }
    
    public void testMain()
    {	

    	//invoke main method
    	Rational.main(null);
    	
    }    
    
    /*
    //  Test Plus
    */
    public void testPlusBasicExercise()
    {	
    	//Test LCM basic functionality
    	Rational s = new Rational(-30,-1);
    	Rational r = s.plus(new Rational (-45,-1));
    	
    	assertEquals("Test Basic Plus Functionality, check result's numerator: ", 75, r.numerator() );
    	assertEquals("Test Basic Plus Functionality, check result's denominator: ", 1, r.denominator() );
    }
    
    public void testPlusLargeNegativeA()
    {	
    	Rational s = new Rational(INT_MIN,1);
    	Rational r = s.plus(new Rational (45,1));
    	
    	assertEquals("Test Basic Plus Functionality, check result's numerator: ", INT_MIN + 45, r.numerator() );
    	assertEquals("Test Basic Plus Functionality, check result's denominator: ", 1, r.denominator() );
    }
    
    public void testPlusLargeNegativeB()
    {	
    	Rational s = new Rational(45,1);
    	Rational r = s.plus(new Rational (INT_MIN,1));
    	
    	assertEquals("\nTest Basic Plus Functionality, check result's numerator: ", INT_MIN + 45, r.numerator() );
    	assertEquals("\nTest Basic Plus Functionality, check result's denominator: ", 1, r.denominator() );
    }   
    
    public void testPlusLargePositiveA1()
    {	
    	Rational s = new Rational(INT_MAX,1);
    	Rational r = s.plus(new Rational (45,1));
   	
    	assertEquals("\nTest1 Basic Plus Functionality, check result's numerator: ", INT_MAX + 45L, r.numerator() );
    }
    
    public void testPlusLargePositiveA2()
    {	
    	Rational s = new Rational(INT_MAX,1);
    	Rational r = s.plus(new Rational (45,1));

    	assertEquals("\nTest2 Basic Plus Functionality, check result's denominator: ", 1, r.denominator() );
    } 
    
    public void testPlusLargePositiveB()
    {	
    	Rational s = new Rational(45,1);
    	Rational r = s.plus(new Rational (INT_MAX,1));
    	
    	assertEquals("Test Basic Plus Functionality, check result's numerator: ", INT_MAX + 45, r.numerator() );
    	assertEquals("Test Basic Plus Functionality, check result's denominator: ", 1, r.denominator() );
    }    
    
    public void testPlusLargePositiveLongVariableCheck1()
    {	
    	Rational s = new Rational(INT_MAX,1);
    	Rational r = s.plus(new Rational (45,1));

    	long resultNumerator = r.numerator();
    	long increment = resultNumerator - INT_MAX;
    	
    	assertEquals("\nTest3 for int overflow, calculate increment value using long vars and compare"
    				 + "\nr.numerator(): " + String.valueOf(r.numerator())
    				 + "\nresultNumerator: " + String.valueOf(resultNumerator)
    				 + "\nINT_MAX: " + String.valueOf(INT_MAX)
    				 + "\nincrement: " + String.valueOf(increment) +"\n", 45, increment);
    	
    }
    
    public void testPlusLargePositiveLongVariableCheck2()
    {	
    	Rational s = new Rational(INT_MAX,1);
    	Rational r = s.plus(new Rational (45,1));
    	
    	long resultNumerator = r.numerator();
    	long testValue = INT_MAX + 45L;
    	
    	assertEquals("\nTest4 for int overflow, calculate increment value using long vars and compare", testValue, resultNumerator );
    	
    }    
    
    public void testDividesBasic()
    {
    	Rational n = new Rational(6,1);
    	Rational d = new Rational(2,1);
    	
    	assertEquals("\nTest4 for int overflow, calculate increment value using long vars and compare", 3, n.divides(d) );    	
    	
    }
    

    public static void main(String args[]) {
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
}

class RationalSpoof extends Rational{
	//This is a bogus sub-class of Rational Class.
	
	public RationalSpoof(int n, int d)
	{
		super(n, d);
	}
}
