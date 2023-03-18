package org.jfree.data;

import static org.junit.Assert.*; import org.jfree.data.Range;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;

public class RangeTest {
	private Range testRange1;
	private Range testRange2;
	private Range testRange3;
	private Range testRange4;
	private Range test5Range;
	private Range test6Range;
    private Range posRange;
    private Range negRange;
    private Range zeroRange;
    private Range spanRange;
    private Range maxRange;
    private Range minRange;
	@Before
	// Sets up the range variables for testing later
	public void setUp() throws Exception{
    	posRange = new Range(3.5, 10.56);
        negRange = new Range(-7.98, -2.1);
        zeroRange = new Range(0.0, 0.0);
        spanRange = new Range(-10.0, 10.0);
        maxRange = new Range(1.0, Double.MAX_VALUE);
        minRange = new Range(Double.MIN_VALUE, 1.0);
		test5Range = new Range(5,10);
		test6Range = new Range(5,5); // What happens when the range is the same?
		//Test Range with a positive and negative end point 	
		testRange1 = new Range(-3,3); 
		//Test Range with a 2 negative values 
		testRange2 = new Range(-6,-3);
		//Test Range with 2 positive values 
		testRange3 = new Range(3,6);
		//Test Range with the same value 
		testRange4 = new Range(0,0);
		
		//The following tests followed the boundary value test method in order to fit every possible 
		//scenario. 
	}	//Testing the Lower Bound function with a - and + end value 
	
	//Additional assignment4 tests to increase mutation coverage
	@Test
	public void test_Range_ExceptionMessage(){
		try {
			Range invalidRangeObj = new Range(10.0,5.0);
		}
		catch(IllegalArgumentException e) {
			double lower = 10.0;
			double upper = 5.0;
			assertEquals("checking invalid exception message is correct",
					"Range(double, double): require lower (" + lower
	                + ") <= upper (" + upper + ").",
	                e.getMessage());
			return;
		}
	}
	
	@Test
	public void test_Range_LowerConstructor() {
		Range testRange = new Range(1.0,2.0);
		assertEquals("Range Lower var construction", 1.0, testRange.getLowerBound(),.000000001d);
	}
	
	@Test
	public void test_Range_UpperConstructor() {
		Range testRange = new Range(1.0,2.0);
		assertEquals("Range Upper var construction", 2.0, testRange.getUpperBound(),.000000001d);
	}
	
	@Test
	public void test_getCentralValue_LargerNominalValues(){
		Range testRange1 = new Range(10.0,20.0);
		assertEquals("getCentral Value for larger nominal Range",
				15.0, testRange1.getCentralValue(), .001d);
	}
	
	@Test
	public void test_contains_NominalLowerEdge() {
		Range testRange = new Range(5.0,10.0);
		assertTrue("contains with nominal lower edge", testRange.contains(5.0));
	}
	
	@Test
	public void test_contains_NominalUpperEdge() {
		Range testRange = new Range(5.0,10.0);
		assertTrue("contains with nominal upper edge", testRange.contains(5.0));
	}
	
	@Test
	public void test_contains_NominalUpperLowerEdge() {
		Range testRange = new Range(5.0,5.0);
		assertTrue("contains with nominal upper and lower edge", testRange.contains(5.0));
	}
	
	@Test
	public void test_intersects_TightRange() {
		Range testRange = new Range(5.0,5.0);
		assertTrue("intersects with tight range", testRange.intersects(5.0,5.1));
	}
	
	
	@Test
	public void test_intersects_UpperRange() {
		Range testRange = new Range(5.0,7.0);
		assertTrue("intersects with upper range", testRange.intersects(6.9,6.9));
	}
	
	@Test
	public void test_intersects_IntersectBelowRange() {
		Range testRange = new Range(3.0,5.0);
		Range interRange = new Range(1.0,2.0);
		assertFalse("intersects with Range object below range", testRange.intersects(interRange));
	}
	
	@Test
	public void test_intersects_AboveUpperRange() {
		Range testRange = new Range(3.0,5.0);
		Range interRange = new Range(5.1,7.0);
		assertFalse("intersects with Range object above range", testRange.intersects(interRange));
	}
	
	@Test 
	public void test_hashCode_IsCorrectHashProcess() {
		Range testRange = new Range(3.0,5.0);
        int result;
        long temp;
        temp = Double.doubleToLongBits(3.0);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(5.0);
        result = 29 * result + (int) (temp ^ (temp >>> 32));
        assertEquals("hashCode follows specified process", result, testRange.hashCode());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_expand_NullInput() {
		Range testRange1 = null;
		Range.expand(testRange1, 1.0, 1.0);
	}
	
	@Test
	public void test_expand_NotOneInput() {
		Range testRange1 = new Range(18.0,20.0);
		Range expectedRange = new Range(12.0, 30.0);
		assertEquals("expand with nominal none 1 input", 
				expectedRange, Range.expand(testRange1, 3.0, 5.0));
	}
	
	@Test
	public void test_expand_LowerBiggerThanUpper() {
		Range testRange1 = new Range(18.0,20.0);
		Range expectedRange = new Range(17.0, 17.0);
		assertEquals("expand so that lower is bigger than upper", 
				expectedRange, Range.expand(testRange1, -3.0, -5.0));
	}
	//Additional assignment3 tests -------
	
	@Test
	public void test_Range_LowerBiggerThanUpper(){
		boolean errorThrown = false;
		try {
			Range invalidRangeObj = new Range(10.0,5.0);
		}
		catch(IllegalArgumentException e) {
			errorThrown = true;
		}
		assertEquals("creating invalid Range object with lower > upper", true, errorThrown);
		
	}
	
	
	@Test
	public void test_getCentralValue_NominalValues(){
		Range testRange1 = new Range(2.0,6.0);
		assertEquals("getCentral Value for nominal Range",
				4.0, testRange1.getCentralValue(), .001d);
	}
	
	
	@Test
	public void test_intersects_nominalValues() {
		Range testRange1 = new Range(1.0,2.0);
		Range testRange2 = new Range(1.0,2.0);
		assertEquals("intersects for overlapping Range objects with nominal values", true, 
				testRange1.intersects(testRange2));
		
	}
	
	@Test
	public void test_constrain_objNotContainsUpperValue() {
		Range testRange1 = new Range(1.0,5.0);
		assertEquals("constrain for an upper value that is not contained", 
				5.0, testRange1.constrain(6.0), .000000001d);
	}
	
	@Test
	public void test_constrain_objNotContainsLowerValue() {
		Range testRange1 = new Range(2.0,5.0);
		assertEquals("constrain for a lower value that is not contained", 
				2.0, testRange1.constrain(1.0), .000000001d);
	}
	
	@Test
	public void test_combine_NullRange1() {
		Range testRange1 = null;
		Range testRange2 = new Range(1.0,2.0);
		assertEquals("combine Ranges with null Range1", 
				testRange2, Range.combine(testRange1, testRange2));
	}
	
	@Test
	public void test_combine_NullRange2() {
		Range testRange2 = null;
		Range testRange1 = new Range(1.0,2.0);
		assertEquals("combine Ranges with null Range2", 
				testRange1, Range.combine(testRange1, testRange2));
	}
	
	@Test
	public void test_combine_NominalInputs() {
		Range testRange1 = new Range(2.0, 5.0);
		Range testRange2 = new Range(1.0,2.0);
		Range expectedRange = new Range(1.0,5.0);
		assertEquals("combine Ranges with nominal values", 
				expectedRange, Range.combine(testRange1, testRange2));
	}
	
	@Test
	public void test_combineIgnoringNan_NullRange1_NominalRange2() {
		Range testRange1 = null;
		Range testRange2 = new Range(1.0,2.0);
		assertEquals("combineIgnoringNan Ranges with null Range1", 
				testRange2, Range.combineIgnoringNaN(testRange1, testRange2));
	}
	
	@Test
	public void test_combineIgnoringNan_NullRange2_NominalRange1() {
		Range testRange2 = null;
		Range testRange1 = new Range(1.0,2.0);
		assertEquals("combineIgnoringNan Ranges with null Range2", 
				testRange1, Range.combineIgnoringNaN(testRange1, testRange2));
	}
	
	@Test
	public void test_combineIgnoringNan_NominalRange1_NominalRange2() {
		Range testRange1 = new Range(1.0,2.0);
		Range testRange2 = new Range(1.0,2.0);
		assertEquals("combineIgnoringNan Ranges with nominal inputs", 
				testRange2, Range.combineIgnoringNaN(testRange1, testRange2));
	}
	
	@Test
	public void test_combineIgnoringNan_Range1NaNLower_NominalRange2() {
		Range testRange1 = new Range(Double.NaN,2.0);
		Range testRange2 = new Range(1.0,2.0);
		assertEquals("combineIgnoringNan Ranges with Range1 NaN Lower bound and nominal range2", 
				testRange2, Range.combineIgnoringNaN(testRange1, testRange2));
	}
	
	@Test
	public void test_combineIgnoringNan_Range2NaNLower_NominalRange1() {
		Range testRange2 = new Range(Double.NaN,2.0);
		Range testRange1 = new Range(1.0,2.0);
		assertEquals("combineIgnoringNan Ranges with Range1 NaN Lower bound and nominal range1", 
				testRange1, Range.combineIgnoringNaN(testRange1, testRange2));
	}
	
	@Test
	public void test_combineIgnoringNan_Range1NaNUpper_NominalRange2() {
		Range testRange1 = new Range(1.0,Double.NaN);
		Range testRange2 = new Range(1.0,2.0);
		assertEquals("combineIgnoringNan Ranges with Range1 NaN Upper bound and nominal range2", 
				testRange2, Range.combineIgnoringNaN(testRange1, testRange2));
	}
	
	@Test
	public void test_combineIgnoringNan_Range2NaNUpper_NominalRange1() {
		Range testRange2 = new Range(1.0,Double.NaN);
		Range testRange1 = new Range(1.0,2.0);
		assertEquals("combineIgnoringNan Ranges with Range1 NaN Upper bound and nominal range1", 
				testRange1, Range.combineIgnoringNaN(testRange1, testRange2));
	}
	
	@Test
	public void test_combineIgnoringNan_Range1NaN_NullRange2() {
		Range testRange1 = new Range(Double.NaN,Double.NaN);
		Range testRange2 = null;
		assertEquals("combineIgnoringNan Ranges with Range1 NaN and null range2", 
				null, Range.combineIgnoringNaN(testRange1, testRange2));
	}
	
	@Test
	public void test_combineIgnoringNan_Range2NaN_NullRange1() {
		Range testRange2 = new Range(Double.NaN,Double.NaN);
		Range testRange1 = null;
		assertEquals("combineIgnoringNan Ranges with Range2 NaN and null range1", 
				null, Range.combineIgnoringNaN(testRange1, testRange2));
	}
	
	@Test
	public void test_combineIgnoringNan_Range2NaN_Range1NaN() {
		Range testRange2 = new Range(Double.NaN,Double.NaN);
		Range testRange1 = new Range(Double.NaN,Double.NaN);
		assertEquals("combineIgnoringNan Ranges with Range2 NaN and NaN range1", 
				null, Range.combineIgnoringNaN(testRange1, testRange2));
	}
	
	@Test
	public void test_expandtoInclude_NullRange() {
		Range testRange1 = null;
		Range expectedRange = new Range(1.0, 1.0);
		assertEquals("expandToInclude with null RangeObj", 
				expectedRange, Range.expandToInclude(testRange1, 1.0));
	}
	
	@Test
	public void test_expandtoInclude_ValueBelowLB() {
		Range testRange1 = new Range(5.0,10.0);
		Range expectedRange = new Range(1.0, 10.0);
		assertEquals("expandToInclude with expanding to value below LB", 
				expectedRange, Range.expandToInclude(testRange1, 1.0));
	}
	
	@Test
	public void test_expandtoInclude_ValueAboveUB() {
		Range testRange1 = new Range(5.0,10.0);
		Range expectedRange = new Range(5.0, 12.0);
		assertEquals("expandToInclude with expanding to value above UB", 
				expectedRange, Range.expandToInclude(testRange1, 12.0));
	}
	
	@Test
	public void test_expandtoInclude_ValueWithinRange() {
		Range testRange1 = new Range(5.0,10.0);
		Range expectedRange = new Range(5.0, 10.0);
		assertEquals("expandToInclude with expanding to value within range", 
				expectedRange, Range.expandToInclude(testRange1, 7.0));
	}
	
	@Test
	public void test_expand_NominalExpandedRange() {
		Range testRange1 = new Range(3.0,4.0);
		Range expectedRange = new Range(2.0, 5.0);
		assertEquals("expand with nominal expanded range", 
				expectedRange, Range.expand(testRange1, 1.0, 1.0));
	}
	
	@Test
	public void test_shift_NominalInput() {
		Range testRange1 = new Range(2.0,3.0);
		Range expectedRange = new Range(3.0,4.0);
		assertEquals("shift with nominal shift value",
				expectedRange, Range.shift(testRange1, 1.0));
	}

	
	@Test
	public void test_shift_NominalInputWithZeroCrossing() {
		Range testRange1 = new Range(2.0,3.0);
		Range expectedRange = new Range(3.0,4.0);
		assertEquals("shift with ZeroCrossing and nominal shift value",
				expectedRange, Range.shift(testRange1, 1.0, true));
	}
	
	@Test
	public void test_shift_LowerBoundBelowZeroWithNoZeroCrossing() {
		Range testRange1 = new Range(-1.0,3.0);
		Range expectedRange = new Range(0.0,4.0);
		assertEquals("shift with NoZeroCrossing and LowerBoundBelowZero",
				expectedRange, Range.shift(testRange1, 1.0, false));
	}
	
	@Test
	public void test_shift_LowerBoundEqualsZeroWithNoZeroCrossing() {
		Range testRange1 = new Range(0.0,3.0);
		Range expectedRange = new Range(1.0,4.0);
		assertEquals("shift with NoZeroCrossing and LowerBoundBelowZero equals zero",
				expectedRange, Range.shift(testRange1, 1.0, false));
	}
	
	@Test
	public void test_shift_NominalInputWithoutZeroCrossing() {
		Range testRange1 = new Range(2.0,3.0);
		Range expectedRange = new Range(3.0,4.0);
		assertEquals("shift without ZeroCrossing and nominal shift value",
				expectedRange, Range.shift(testRange1, 1.0, false));
	}
	
	@Test
	public void test_scale_NominalFactor() {
		Range testRange1 = new Range(2.0,3.0);
		Range expectedRange = new Range(4.0,6.0);
		assertEquals("scale with nominal factor",
				expectedRange, Range.scale(testRange1, 2.0));
	}
	
	@Test
	public void test_scale_BelowZeroFactor() {
		boolean errorThrown = false;
		Range testRange1 = new Range(2.0,3.0);
		try {
			Range.scale(testRange1, -1.0);
		}
		catch(IllegalArgumentException e) {
			errorThrown = true;
		}
		assertEquals("scale with belowZero factor",
				true, errorThrown);
	}
	
	@Test
	public void test_equals_NonRangeObject() {
		Range testRange1 = new Range(1.0,2.0);
		Object obj = new String();
		assertEquals("equals with a NonRangeObject",
				false, testRange1.equals(obj));
	}
	
	@Test
	public void test_equals_DifferentLowerBoundRangeObject() {
		Range testRange1 = new Range(1.0,2.0);
		Object obj = new Range(0.0,2.0);
		assertEquals("equals with a RangeObject with different LowerBound",
				false, testRange1.equals(obj));
	}
	
	@Test
	public void test_equals_DifferentUpperBoundRangeObject() {
		Range testRange1 = new Range(1.0,2.0);
		Object obj = new Range(1.0,3.0);
		assertEquals("equals with a RangeObject with different UpperBound",
				false, testRange1.equals(obj));
	}
	
	@Test
	public void test_hashCode_SameHashForSameRange() {
		Range testRange1 = new Range(1.0,2.0);
		Range testRange2 = new Range(1.0,2.0);
		int expected = testRange2.hashCode();
		
		assertEquals("test that different range objects with same range return same hash",
				expected, testRange1.hashCode());
	}
	
	//ALEX -----------------------------
	
	//The following tests followed the boundary value test method in order to fit every possible 
	//scenario. 	//Testing the Lower Bound function with a - and + end value 
	@Test 
	public void test_getLowerBound_PostiveNegativeTest() {
		assertEquals("The lower bound value of -3 and 3 should be -3", -3, 
				testRange1.getLowerBound(), .000000001d);
	}
	//Testing the Lower bound function with 2 negative end values 
	@Test
	public void test_getLowerBound_NegativeTest() {
		assertEquals("The lower bound of -6 and -3 should be -6", -6, testRange2.getLowerBound(), 
				.000000001d);
	}
	
	//Testing the Lower bound function with 2 positive end values 
	@Test
	public void test_getLowerBound_PostiveTest() {
		assertEquals("The lower bound of 3 and 6 should be 3", 3, testRange3.getLowerBound(),
				.000000001d);
	}
	
	//Testing the Lower bound function with the same end values 
	@Test 
	public void test_getLowerBound_SameValueTest() {
		assertEquals("The lower bound of 0 and 0 should be 0", 0, testRange4.getLowerBound(),
				.000000001d);
	}
	
	
	//Testing the Length function with - and + end values
	@Test 
	public void test_getLength_PostiveNegativeTest() {
		assertEquals("The length value between -3 and 3 should be 6", 6, 
				testRange1.getLength(), .000000001d);
	}
	
	//Testing the length function with 2 negative end values 
	@Test 
	public void test_getLength_NegativeTest() {
		assertEquals("The length value between -6 and -3 should be 3", 3, testRange2.getLength(),
				.000000001d);
	}
	
	//Testing the length function with 2 positive end values 
	@Test
	public void test_getLength_PositveTest() {
		assertEquals("The length value between 3 and 6 should be 3", 3, testRange3.getLength(),
				.000000001d);
	}
	
	//Testing the length function with the same values 
	@Test 
	public void test_getLength_SameValueTest() {
		assertEquals("The length value between 0 and 0 should be 0", 0, testRange4.getLength(),
				.000000001d);
	}
	
	//Testing the contains function with the upper end value (3)
	@Test 
	public void test_contains_TestUpper() {
		assertEquals("The function should return true when looking for 3", true, testRange1.contains(3));
		
	}
	//Testing the contains function with the lower end value (-3)
	@Test 
	public void test_contains_TestLower() {
		assertEquals("The function should return true when looking for -3", true, testRange1.contains(-3));
	}
	//Testing the contains function with a value in the middle (-1)
	@Test 
	public void test_contains_TestMiddle() {
		assertEquals("The function should return true when looking for 1", true, testRange1.contains(1));
	}
	//Testing the contains function with a value that is lower then the lower bound (-4)
	@Test 
	public void test_contains_DoesNotContainLowerTest() {
		assertEquals("The function should return false when looking for -4,", false, testRange1.contains(-4));
		
	}
	
	//Testing the contains function with a value that is higher then the upper bound
	@Test 
	public void test_contains_DoesNotContainUpperTest() {
		assertEquals("The function should return false when looking for 4", false, testRange1.contains(4));
	}
	
	
	//GABE ------------------------
    @Test
    public void test_toString_posRangeToString() {
    	assertEquals("Positive range to string",
    			"Range[3.5,10.56]", posRange.toString());
    }
    
    @Test
    public void test_toString_negRangeToString() {
    	assertEquals("Negative range to string",
    			"Range[-7.98,-2.1]", negRange.toString());
    }
    
    @Test
    public void test_toString_zeroRangeToString() {
    	assertEquals("Range of zero to string",
    			"Range[0.0,0.0]", zeroRange.toString());
    }
    
    @Test
    public void test_toString_spanRangeToString() {
    	assertEquals("range spanning from negatives to positives to string",
    			"Range[-10.0,10.0]", spanRange.toString());
    }

    
    @Test
    public void test_toString_maxRangeToString() {
    	assertEquals("Maximum upper to string",
    			"Range[1.0,1.7976931348623157E308]", maxRange.toString());
    }
    
    @Test
    public void test_toString_minRangeToString() {
    	assertEquals("Minimum lower to string",
    			"Range[4.9E-324,1.0]", minRange.toString());
    }

	//RYAN --------------------
	
	//tests pertaining to intersects() -----------------------
	
	
	
	// Tests when both values given in intersection are below the values of the range. Should return false
	@Test
	public void test_inersects_testOutOfBoundsLower() {
		assertFalse("Range values 0, 2 are both below 5,10. Should be false.", test5Range.intersects(0, 2));
	}
	
	// Tests when the lower boundary of the range and the upper bound of the given values are the same. Should be true
	@Test
	public void test_inersects_testTouchingBoundsLower() {
		assertTrue("Range values 3,6 touches the lower bound, should return true.", test5Range.intersects(3, 6));
	}
	
	// Tests when the upper bound of the given values is inside the boundaries of the range. Should be true
	@Test
	public void test_inersects_testHalfOutOfBoundsLower() {
		assertTrue("Range values 2,6 partly intersect, should return true.", test5Range.intersects(2, 6));
	}
	
	// Tests when the given values are fully within the range boundaries. Should be true
	@Test
	public void test_inersects_testFullInRange() {
		assertTrue("Range values 7,8 fully intersect and this should be true.", test5Range.intersects(7, 8));
	}
	
	// Tests when the lower bound of the given values is within the range boundaries. Should be true
	@Test
	public void test_inersects_testHalfOutOfBoundsUpper() {
		assertTrue("Range values 9, 12 partly intersect, should return true.", test5Range.intersects(9, 12));
	}
	
	// Tests when the range and given values are exactly the same. Should be true.
	@Test
	public void test_inersects_testSameRangeValues() {
		assertTrue("Range values of 5,10 match the test5Range. Should return true", test5Range.intersects(5, 10));
	}
	
	// Tests when the lower bound of the given values touches the upper bound of the range. Should be true
	@Test
	public void test_inersects_testTouchingBoundsUpper() {
		assertTrue("Range values 9,11 touches the upper bound, should return true.", test5Range.intersects(9, 11));
	}
	
	// Tests when the given values are fully above the upper bound. Should be false
	@Test
	public void test_inersects_testOutOfBoundsUpper() {
		assertFalse("Range values 13, 20 are both above 5,10. Should be false.", test5Range.intersects(13, 20));
	}
	
	// Tests when the given values are fully above the upper bound. Should be false
	@Test
	public void test_inersects_testSurroundingRange() {
		assertTrue("Range values 4, 20 surround 5,10. Should be True.",test5Range.intersects(4, 20));
	}
	
	// ------------------------------------------------------------------------------------------------
	// This is a simple break. From here we use test6Range to test when the values are both the same
	
	// This tests when the given values are both below the range boundaries. Should be false
	@Test
	public void test_inersects_test2OutOfBoundsLower() {
		assertFalse("Range values 0, 2 are both below 5,5. Should be false.", test6Range.intersects(0, 2));
	}
	
	// This tests when both values are above the given range. Should be false
	@Test
	public void test_inersects_test2OutOfBoundsUpper() {
		assertFalse("Range values 6, 10 are both above 5,5. Should be false.", test6Range.intersects(6, 10));
	}
	
	// This tests when the upper bound of the given values touches the boundaries. Should be true.
	@Test
	public void test_inersects_test2TouchingBoundsLower() {
		assertFalse("Range values 3,5 touches the lower bound, should return false.", test6Range.intersects(3, 5));
	}
	
	// Same thing as above just from the top side this time. Should be true
	@Test
	public void test_inersects_test2TouchingBoundsUpper() {
		assertTrue("Range values 5,8 touches the upper bound, should return true.", test6Range.intersects(5, 8));
	}
	
	// This tests when the range is completely surrounded. Should be true
	@Test
	public void test_inersects_test2SurroundingBounds() {
		assertTrue("Range values 4,6 completely surround the bounds of test6Range, should return true.", test6Range.intersects(4, 6));
	}
	
	// This tests when Range boundaries = Given values. Should be true
	@Test
	public void test_inersects_test2SameRangeValues() {
		assertFalse("Range values of 5,5 match the test6Range. Should return false", test6Range.intersects(5, 5));
	}
	
}
