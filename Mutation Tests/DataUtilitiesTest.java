package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import java.util.Arrays;
import org.jmock.*;
import org.jmock.Mockery;
import org.junit.Test;
import org.jfree.chart.util.ParamChecks;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.jfree.data.Values2D;

public class DataUtilitiesTest extends DataUtilities {
	
	//additional tests for assignment 3
	
	@Test
	public void test_equal_AllNullInput() {
		double a[][] = null;
		double b[][] = null;
		
		assertEquals("equal with all null inputs", 
				true, DataUtilities.equal(a, b));
	}
	
	@Test
	public void test_equal_Nulla() {
		double a[][] = null;
		double b[][] = {{1,1},{1,1},{1,1}};
		
		assertEquals("equal with Null a Value", 
				false, DataUtilities.equal(a, b));
	}
	
	
	@Test
	public void test_equal_SameLength_DifferentInputs() {
		double b[][] = {{2,1},{2,1},{1.1}};
		double a[][] = {{1,1},{1,1},{1,1}};
		
		assertEquals("equal with the different inputs with same length", 
				false, DataUtilities.equal(a, b));
	}
	
	
	@Test
	//killed mutant original test suite 
	public void test_clone_NominalInput() {
		double a[][] = {{1,1},{1,1},{1,1}};
		double expected[][] = {{1,1},{1,1},{1,1}};

		Assert.assertArrayEquals("clone with nominal source",
				expected, DataUtilities.clone(a));	
	}
	
	@Test
	public void test_clone_NullInput() {
		double a[][] = {{1,1},{1,1}, null};
		double expected[][] = {{1,1},{1,1}, null};

		Assert.assertArrayEquals("clone with null source",
				expected, DataUtilities.clone(a));	
	}
	//RYAN -----------------------
	//testing for getCumulativePercentages()
	
	
//	test
//	 This tests the result of the first index using the table values
	@Test
	public void testAllPositiveFirstResult() {
		// Look at the top of the class for more information about this
		Mockery mockingContext = new Mockery();
		final KeyedValues kv = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
			atLeast(1).of(kv).getItemCount();
			will(returnValue(3));
			atLeast(1).of(kv).getValue(0); 
			will(returnValue(5));
			atLeast(1).of(kv).getValue(1); 
			will(returnValue(9));
			atLeast(1).of(kv).getValue(2); 
			will(returnValue(2));
			atLeast(1).of(kv).getKey(0);
			will(returnValue(0));
			atLeast(1).of(kv).getKey(1);
			will(returnValue(1));
			atLeast(1).of(kv).getKey(2);
			will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(kv);
		assertEquals("This tests that it returns 5/16 which is 0.3125 for keyedvalue 0.", 0.3125, (double) result.getValue(0), 0.0000000001d);
	}
	
	// This tests the result of the second index using the table values 
	@Test
	public void testAllPositiveSecondResult() {
		// Look at the top of the class for more information about this
		Mockery mockingContext = new Mockery();
		final KeyedValues kv = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
			atLeast(1).of(kv).getItemCount();
			will(returnValue(3));
			atLeast(1).of(kv).getValue(0); 
			will(returnValue(5));
			atLeast(1).of(kv).getValue(1); 
			will(returnValue(9));
			atLeast(1).of(kv).getValue(2); 
			will(returnValue(2));
			atLeast(1).of(kv).getKey(0);
			will(returnValue(0));
			atLeast(1).of(kv).getKey(1);
			will(returnValue(1));
			atLeast(1).of(kv).getKey(2);
			will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(kv);
		assertEquals("This tests that it returns 14/16 which is 0.875 for keyedvalue 1.", 0.875, (double) result.getValue(1), 0.0000000001d);
		
	}
	
	// This tests the result of the third index using the table values. (In a table of 3, should be 1)
	@Test
	public void testAllPositiveThirdResult() {
		// Look at the top of the class for more information about this
		Mockery mockingContext = new Mockery();
		final KeyedValues kv = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
			atLeast(1).of(kv).getItemCount();
			will(returnValue(3));
			atLeast(1).of(kv).getValue(0); 
			will(returnValue(5));
			atLeast(1).of(kv).getValue(1); 
			will(returnValue(9));
			atLeast(1).of(kv).getValue(2); 
			will(returnValue(2));
			atLeast(1).of(kv).getKey(0);
			will(returnValue(0));
			atLeast(1).of(kv).getKey(1);
			will(returnValue(1));
			atLeast(1).of(kv).getKey(2);
			will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(kv);
		assertEquals("This tests that it returns 16/16 which is 1.0 for keyedvalue 2.", 1.0, (double) result.getValue(2), 0.0000000001d);
	}
	
	/*
	 * We assume that the values being returned get converted to absolute values for the purposes of testing.
	 * We have no idea if this is actually how it works, but we have to have a "Correct" value
	 * This test checks the first value of an all negative table
	 * */
	@Test
	public void testAllNegativeFirstResult() {
		// Look at the top of the class for more information about this
		Mockery mockingContext = new Mockery();
		final KeyedValues kv = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
			atLeast(1).of(kv).getItemCount();
			will(returnValue(3));
			atLeast(1).of(kv).getValue(0); 
			will(returnValue(-5));
			atLeast(1).of(kv).getValue(1); 
			will(returnValue(-9));
			atLeast(1).of(kv).getValue(2); 
			will(returnValue(-2));
			atLeast(1).of(kv).getKey(0);
			will(returnValue(0));
			atLeast(1).of(kv).getKey(1);
			will(returnValue(1));
			atLeast(1).of(kv).getKey(2);
			will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(kv);
		assertEquals("This tests that it returns 5/16 which is 0.3125 for keyedvalue 0.", 0.3125, (double) result.getValue(0), 0.0000000001d);
	}
	
	// This tests the second index result from an all negative table (assume negatives are absolute values)
	@Test
	public void testAllNegativeSecondResult() {
		// Look at the top of the class for more information about this
		Mockery mockingContext = new Mockery();
		final KeyedValues kv = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
			atLeast(1).of(kv).getItemCount();
			will(returnValue(3));
			atLeast(1).of(kv).getValue(0); 
			will(returnValue(-5));
			atLeast(1).of(kv).getValue(1); 
			will(returnValue(-9));
			atLeast(1).of(kv).getValue(2); 
			will(returnValue(-2));
			atLeast(1).of(kv).getKey(0);
			will(returnValue(0));
			atLeast(1).of(kv).getKey(1);
			will(returnValue(1));
			atLeast(1).of(kv).getKey(2);
			will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(kv);
		assertEquals("This tests that it returns 14/16 which is 0.875 for keyedvalue 1.", 0.875, (double) result.getValue(1), 0.0000000001d);
		
	}
	
	// This tests the third table result from the all negative table (assuming calculation values are absolute values)
	@Test
	public void testAllNegativeThirdResult() {
		// Look at the top of the class for more information about this
		Mockery mockingContext = new Mockery();
		final KeyedValues kv = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
			atLeast(1).of(kv).getItemCount();
			will(returnValue(3));
			atLeast(1).of(kv).getValue(0); 
			will(returnValue(-5));
			atLeast(1).of(kv).getValue(1); 
			will(returnValue(-9));
			atLeast(1).of(kv).getValue(2); 
			will(returnValue(-2));
			atLeast(1).of(kv).getKey(0);
			will(returnValue(0));
			atLeast(1).of(kv).getKey(1);
			will(returnValue(1));
			atLeast(1).of(kv).getKey(2);
			will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(kv);
		assertEquals("This tests that it returns 16/16 which is 1.0 for keyedvalue 2.", 1.0, (double) result.getValue(2), 0.0000000001d);
	}
	
	// This tests when 0 is in the first index (index 0) of the table (tests all values at the same time)
	@Test
	public void testZeroInFirstSlot() {
		// Look at the top of the class for more information about this
		Mockery mockingContext = new Mockery();
		final KeyedValues kv = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
			atLeast(1).of(kv).getItemCount();
			will(returnValue(3));
			atLeast(1).of(kv).getValue(0); 
			will(returnValue(0));
			atLeast(1).of(kv).getValue(1); 
			will(returnValue(9));
			atLeast(1).of(kv).getValue(2); 
			will(returnValue(2));
			atLeast(1).of(kv).getKey(0);
			will(returnValue(0));
			atLeast(1).of(kv).getKey(1);
			will(returnValue(1));
			atLeast(1).of(kv).getKey(2);
			will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(kv);
		assertEquals("This tests that it returns 0/11 which is 0 for keyedvalue 0.", 0.0, (double) result.getValue(0), 0.0000000001d);
		assertEquals("This tests that it returns 9/11 which is 0.8181 for keyedvalue 1.", 0.8181, (double) result.getValue(1), 0.001d);
		assertEquals("This tests that it returns 11/11 which is 1.0 for keyedvalue 2.", 1.0, (double) result.getValue(2), 0.0000000001d);
	}
	// Tests when there is a zero in the second index (index 1) of the table. 
	@Test
	public void testZeroInSecondSlot() {
		// Look at the top of the class for more information about this
		Mockery mockingContext = new Mockery();
		final KeyedValues kv = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
			atLeast(1).of(kv).getItemCount();
			will(returnValue(3));
			atLeast(1).of(kv).getValue(0); 
			will(returnValue(5));
			atLeast(1).of(kv).getValue(1); 
			will(returnValue(0));
			atLeast(1).of(kv).getValue(2); 
			will(returnValue(2));
			atLeast(1).of(kv).getKey(0);
			will(returnValue(0));
			atLeast(1).of(kv).getKey(1);
			will(returnValue(1));
			atLeast(1).of(kv).getKey(2);
			will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(kv);
		assertEquals("This tests that it returns 5/7 which is 0.7142 for keyedvalue 0.", 0.7142, (double) result.getValue(0), 0.001d);
		assertEquals("This tests that it returns 5/7 which is 0.7142 for keyedvalue 1.", 0.7142, (double) result.getValue(1), 0.001d);
		assertEquals("This tests that it returns 7/7 which is 1 for keyedvalue 2.", 1.0, (double) result.getValue(2), 0.0000000001d);
	}
	
	// This tests when there is a zero in the third index (index 2) 
	@Test
	public void testZeroInThirdSlot() {
		// Look at the top of the class for more information about this
		Mockery mockingContext = new Mockery();
		final KeyedValues kv = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
			atLeast(1).of(kv).getItemCount();
			will(returnValue(3));
			atLeast(1).of(kv).getValue(0); 
			will(returnValue(5));
			atLeast(1).of(kv).getValue(1); 
			will(returnValue(9));
			atLeast(1).of(kv).getValue(2); 
			will(returnValue(0));
			atLeast(1).of(kv).getKey(0);
			will(returnValue(0));
			atLeast(1).of(kv).getKey(1);
			will(returnValue(1));
			atLeast(1).of(kv).getKey(2);
			will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(kv);
		assertEquals("This tests that it returns 5/14 which is 0.3571 for keyedvalue 0.", 0.3571, (double) result.getValue(0), 0.001d);
		assertEquals("This tests that it returns 14/14 which is 1 for keyedvalue 1.", 1.0, (double) result.getValue(1), 0.001d);
		assertEquals("This tests that it returns 14/14 which is 1 for keyedvalue 2.", 1.0, (double) result.getValue(2), 0.0000000001d);
	}
	
	// This tests when all table values are 0
	@Test
	public void testZeroInAllSlots() {
		// Look at the top of the class for more information about this
		Mockery mockingContext = new Mockery();
		final KeyedValues kv = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
			atLeast(1).of(kv).getItemCount();
			will(returnValue(3));
			atLeast(1).of(kv).getValue(0); 
			will(returnValue(0));
			atLeast(1).of(kv).getValue(1); 
			will(returnValue(0));
			atLeast(1).of(kv).getValue(2); 
			will(returnValue(0));
			atLeast(1).of(kv).getKey(0);
			will(returnValue(0));
			atLeast(1).of(kv).getKey(1);
			will(returnValue(1));
			atLeast(1).of(kv).getKey(2);
			will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(kv);
		assertEquals("This tests that it returns NaN which is NaN for keyedvalue 0.", Double.longBitsToDouble(0x7ff8000000000000L), (double) result.getValue(0), 0.001d);
		assertEquals("This tests that it returns NaN which is NaN for keyedvalue 1.", Double.longBitsToDouble(0x7ff8000000000000L), (double) result.getValue(1), 0.001d);
		assertEquals("This tests that it returns NaN which is NaN for keyedvalue 2.", Double.longBitsToDouble(0x7ff8000000000000L), (double) result.getValue(2), 0.0000000001d);
	}
	
//	// LUKE -----------------------------
//	//testing for CreateNumberArray
//
	@Test
	//test CreateNumberArray() with nominal value of elements and number of elements
	public void test_CreateNumberArray_NominalNumElements_NominalValues() {
		
		double data[] = {1.0,2.0,3.0,4.0};
		Number expected[] = {1.0,2.0,3.0,4.0};
		
		Number actual[] = DataUtilities.createNumberArray(data);
		assertArrayEquals("creating a Number array with nominal values and number of elements", expected, actual);
	}
	
	@Test
	//test CreateNumberArray() with LB of number of elements and nominal elements
	public void test_CreateNumberArray_LBNumElements_NominalValues() {
		
		double data[] = {};
		Number expected[] = {};
		Number actual[] = DataUtilities.createNumberArray(data);

		assertArrayEquals("creating a Number array with no elements", expected, actual);
	}
	
	@Test
	//test CreateNumberArray() with BLB of number of elements and nominal elements
	public void test_CreateNumberArray_BLBNumElements_NominalValues() {
		
		double data[] = null;
		boolean caught = false;

		try {
			Number actual[] = DataUtilities.createNumberArray(data);	
		}
		catch(Exception InvalidParameterException) {
			caught = true;
		}
		
		assertEquals("inputting a null pointer as data array", true, caught);
	}
	
	@Test
	//test CreateNumberArray() with BUB of number of elements and nominal elements
	public void test_CreateNumberArray_UBNumElements_NominalValues() {
		
		double data[] = new double[1000000];
		Arrays.fill(data, 1.0);
		Number expected[] = new Number[1000000];
		Arrays.fill(expected, 1.0);
		Number actual[] = DataUtilities.createNumberArray(data);

		assertArrayEquals("creating a Number array with 1000000 elements and nominal elements", expected, actual);
	}
	
	@Test
	//test CreateNumberArray() with ALB of number of elements and nominal elements
	public void test_CreateNumberArray_ALBNumElements_NominalValues() {
		
		double data[] = {1.0};
		Number expected[] = {1.0};
		Number actual[] = DataUtilities.createNumberArray(data);

		assertArrayEquals("creating a Number array with 1 elements and nominal elements", expected, actual);
	}
	
	
	@Test
	//test CreateNumberArray() with LB of elements and nominal number of elements
	public void test_CreateNumberArray_NominalNumElements_LBValues() {
		
		double data[] = {Double.MIN_VALUE, Double.MIN_VALUE};
		Number expected[] = {Double.MIN_VALUE, Double.MIN_VALUE};

		Number actual[] = DataUtilities.createNumberArray(data);

		assertArrayEquals("creating a Number array with the min value of double and nominal elements", expected, actual);
	}
	
	@Test
	//test CreateNumberArray() with UB of elements and nominal number of elements
	public void test_CreateNumberArray_NominalNumElements_UBValues() {
		
		double data[] = {Double.MAX_VALUE, Double.MAX_VALUE};
		Number expected[] = {Double.MAX_VALUE, Double.MAX_VALUE};

		Number actual[] = DataUtilities.createNumberArray(data);

		assertArrayEquals("creating a Number array with the max value of double and nominal elements", expected, actual);
	}
	
	//testing for createNumberArray2D	
	
	@Test
	//testing for createNumberArray2D
	//test createNumberArray2D() with 1 1D elements, nominal 2D elements, and nominal values
	public void test_CreateNUmberArray2D_ALB1D_Nominal2D_NominalValues() {
		double data[][] = {{1.0,2.0,3.0,4.0}};
		Number expected[][] = {{1.0,2.0,3.0,4.0}};
		assertArrayEquals("creating a Number array with 1 1D elements, nominal 2D elements, and nominal values", 
				expected, DataUtilities.createNumberArray2D(data));
	}
	
	@Test
	//testing for createNumberArray2D
	//test createNumberArray2D() with large amount of 1D elements, nominal 2D elements, and nominal values
	public void test_CreateNUmberArray2D_BUB1D_Nominal2D_NominalValues() {
		double data[][] = new double[100000][1];
		double expected[][] = new double[100000][1];
		assertArrayEquals("creating a Number array with large amount of 1D elements, nominal 2D elements, and nominal values", 
				expected, DataUtilities.createNumberArray2D(data));
	}
	
	@Test
	//testing for createNumberArray2D
	//test createNumberArray2D() with nominal 1D elements, null 2D elements, and nominal values
	public void test_CreateNUmberArray2D_Nominal1D_BLB2D_NominalValues() {
		double data[][] = {null, null, null, null};
		boolean caught = false;
		try {
			Number actual[][] = DataUtilities.createNumberArray2D(data);	
		}
		catch(Exception InvalidParamterException) {
			caught = true;
		}
		assertEquals("creating a Number array with nominal 1D elements, null 2D elements, and nominal values", 
				true, caught);
	}
	
	@Test
	//testing for createNumberArray2D
	//test createNumberArray2D() with nominal 1D elements, no 2D elements, and nominal values
	public void test_CreateNUmberArray2D_Nominal1D_LB2D_NominalValues() {
		double data[][] = {{},{},{}};
		Number expected[][] = {{},{},{}};
		assertArrayEquals("creating a Number array with large amount of 1D elements, no 2D elements, and nominal values", 
				expected, DataUtilities.createNumberArray2D(data));
	}
	
	@Test
	//testing for createNumberArray2D
	//test createNumberArray2D() with nominal 1D elements, many 2D elements, and nominal values
	public void test_CreateNUmberArray2D_Nominal1D_BUB2D_NominalValues() {
		double data[][] = new double[5][];
		for(int i=0;i<data.length; i++) {
			data[i] = new double[100000];
			Arrays.fill(data[i], 8.0);
		}
		Number expected[][] = new Number[5][];
		for(int i=0;i<data.length; i++) {
			expected[i] = new Number[100000];
			Arrays.fill(expected[i], 8.0);
		}
		assertArrayEquals("creating a Number array with large amount of 1D elements, many 2D elements, and nominal values", 
				expected, DataUtilities.createNumberArray2D(data));
	}
	
	@Test
	//testing for createNumberArray2D
	//test createNumberArray2D() with nominal 1D elements, nominal 2D elements, and max values
	public void test_CreateNUmberArray2D_Nominal1D_Nominal2D_UBValues() {
		double data[][] = {{Double.MAX_VALUE,Double.MAX_VALUE},{Double.MAX_VALUE,Double.MAX_VALUE},
				{Double.MAX_VALUE,Double.MAX_VALUE}};
		Number expected[][] = {{Double.MAX_VALUE,Double.MAX_VALUE},{Double.MAX_VALUE,Double.MAX_VALUE},
				{Double.MAX_VALUE,Double.MAX_VALUE}};
		assertArrayEquals("creating a Number array with nominal 1D elements, nominal 2D elements, and max values", 
				expected, DataUtilities.createNumberArray2D(data));
	}
	
	@Test
	//testing for createNumberArray2D
	//test createNumberArray2D() with nominal 1D elements, nominal 2D elements, and min values
	public void test_CreateNUmberArray2D_Nominal1D_Nominal2D_LBValues() {
		double data[][] = {{Double.MIN_VALUE,Double.MIN_VALUE},{Double.MIN_VALUE,Double.MIN_VALUE},
				{Double.MIN_VALUE,Double.MIN_VALUE}};
		Number expected[][] = {{Double.MIN_VALUE,Double.MIN_VALUE},{Double.MIN_VALUE,Double.MIN_VALUE},
				{Double.MIN_VALUE,Double.MIN_VALUE}};
		assertArrayEquals("creating a Number array with nominal 1D elements, nominal 2D elements, and min values", 
				expected, DataUtilities.createNumberArray2D(data));
	}
	
	// GABE --------------------------
	
	Mockery mockingContext = new Mockery();
    final Values2D threeByThree = mockingContext.mock(Values2D.class);
    Mockery hugeContext = new Mockery();
    Mockery smallContext = new Mockery();
    Mockery mixedContext = new Mockery();
    final Values2D mix = mixedContext.mock(Values2D.class);
    Mockery negativeContext = new Mockery();
    final Values2D neg = negativeContext.mock(Values2D.class);
    Mockery zeroContext = new Mockery();
    final Values2D zero = zeroContext.mock(Values2D.class);
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {	
    }
    
	@Test 
	public void test_3x3_ColumnTotal() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // 7.5	 0.24  8.0
                //
                // 2.52  23.4  9.4
                //
                // 10.5	 5.0   18.11
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(7.5));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(23.4));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(5.0));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(18.11));
               
            }
        });
		assertEquals("Summing a valid column.",
		        20.52, DataUtilities.calculateColumnTotal(threeByThree, 0), .000000001d);
	}
	
//	@Test
//	public void testTooBigColumnTotal() {
//		final Values2D hugeColumn = hugeContext.mock(Values2D.class);
//		hugeContext.checking(new Expectations() {
//			{
//				oneOf(hugeColumn).getRowCount();
//		        will(returnValue(2));
//		        oneOf(hugeColumn).getValue(0, 0);
//		        will(returnValue(Double.MAX_VALUE));
//		        oneOf(hugeColumn).getValue(1, 0);
//		        will(returnValue(Double.MAX_VALUE));
//			}
//			
//		});
//		
//		boolean caught = false;
//		try {
//			DataUtilities.calculateColumnTotal(hugeColumn, 0);
//		}
//		catch (Exception InvalidParameterException){
//			caught = true;
//		}
//		assertTrue("Sum of max values in a column.", caught);
//	}
	
//	@Test
//	public void testTooSmallColumnTotal() {
//		final Values2D smallColumn = hugeContext.mock(Values2D.class);
//		hugeContext.checking(new Expectations() {
//			{
//				oneOf(smallColumn).getRowCount();
//		        will(returnValue(2));
//		        oneOf(smallColumn).getValue(0, 0);
//		        will(returnValue(Double.MIN_VALUE));
//		        oneOf(smallColumn).getValue(1, 0);
//		        will(returnValue(Double.MIN_VALUE));
//			}
//			
//		});
//		
//		boolean caught = false;
//		try {
//			DataUtilities.calculateColumnTotal(smallColumn, 0);
//		}
//		catch (Exception InvalidParameterException){
//			caught = true;
//		}
//		assertTrue("Sum of max negative values in a column.", caught);
//	}
	
	@Test
	public void testNullColumnTotal() {
		boolean caught = false;
		try {
			DataUtilities.calculateColumnTotal(null, 0);
		}
		catch (Exception InvalidParameterException){
			caught = true;
		}
		assertTrue("Sum of column with invalid input.", caught);
	}
	
	@Test
	public void testNullValueColumnTotal() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // null	 0.24  8.0
                //
                // 2.52  null  9.4
                //
                // 10.5	 5.0   null
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(null));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(null));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(5.0));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(null));
                
            }
        });
		assertEquals("Summing a column with null values.",
		        13.02, DataUtilities.calculateColumnTotal(threeByThree, 0), .000000001d);
	}
	
//	@Test
//	public void testNegRowCountColumnTotal() {
//		mockingContext.checking(new Expectations() {
//            {
//                oneOf(threeByThree).getRowCount();
//                will(returnValue(-3));
//                oneOf(threeByThree).getColumnCount();
//                will(returnValue(3));
//                
//                // set values
//                // threeByThree:
//                // 7.5	 0.24  8.0
//                //
//                // 2.52  23.4  9.4
//                //
//                // 10.5	 5.0   18.11
//              
//                allowing(threeByThree).getValue(0, 0);
//                will(returnValue(7.5));
//                allowing(threeByThree).getValue(1, 0);
//                will(returnValue(2.52));
//                allowing(threeByThree).getValue(2, 0);
//                will(returnValue(10.5));
//                allowing(threeByThree).getValue(0, 1);
//                will(returnValue(0.24));
//                allowing(threeByThree).getValue(1, 1);
//                will(returnValue(23.4));
//                allowing(threeByThree).getValue(2, 1);
//                will(returnValue(5.0));
//                allowing(threeByThree).getValue(0, 2);
//                will(returnValue(8.0));
//                allowing(threeByThree).getValue(1, 2);
//                will(returnValue(9.4));
//                allowing(threeByThree).getValue(2, 2);
//                will(returnValue(18.11));
//                never(threeByThree).getValue(3, 0);
//                
//            }
//        });
//		assertEquals("Summing a column with negative rowcount.",
//		        0.0, DataUtilities.calculateColumnTotal(threeByThree, 0), .000000001d);
//	}
	
	@Test
	public void testSubsetColumnTotalOverload() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // 7.5	 0.24  8.0
                //
                // 2.52  23.4  9.4
                //
                // 10.5	 5.0   18.11
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(7.5));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(23.4));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(5.0));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(18.11));
                
            }
        });
		int[] vrows = {0, 2};
		assertEquals("Summing a subset of rows", 5.24, 
				DataUtilities.calculateColumnTotal(threeByThree, 1, vrows), .000000001d);
	}
	
	@Test
	public void testUOB_RowsColumnTotalOverload() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // 7.5	 0.24  8.0
                //
                // 2.52  23.4  9.4
                //
                // 10.5	 5.0   18.11
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(7.5));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(23.4));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(5.0));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(18.11));
                
            }
        });
		int[] vrows = {0, 1, 4, 5, 6};
		assertEquals("Summing with out-of-bounds rows", 10.02, 
				DataUtilities.calculateColumnTotal(threeByThree, 0, vrows), .000000001d);
	}
	
	@Test
	public void testNullValueColumnTotalOverload() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // 7.5	 0.24  8.0
                //
                // 2.52  23.4  9.4
                //
                // 10.5	 5.0   NULL
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(7.5));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(23.4));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(5.0));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(null));
                
            }
        });
		int[] vrows = {1, 2};
		assertEquals("Summing a column with select rows and null values", 9.4, 
				DataUtilities.calculateColumnTotal(threeByThree, 2, vrows), .000000001d);
	}
	
	@Test
	public void test_3x3_RowTotal() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // 7.5	 0.24  8.0
                //
                // 2.52  23.4  9.4
                //
                // 10.5	 5.0   18.11
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(7.5));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(23.4));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(5.0));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(18.11));
                
            }
        });
		assertEquals("Summing a valid row.",
		        35.32, DataUtilities.calculateRowTotal(threeByThree, 1), .000000001d);
	}

	@Test
	public void testNullValueRowTotal() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // null	 0.24  8.0
                //
                // 2.52  null  9.4
                //
                // 10.5	 5.0   null
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(null));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(null));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(5.0));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(null));
                
            }
        });
		assertEquals("Summing a row with null values.",
		        11.92, DataUtilities.calculateRowTotal(threeByThree, 1), .000000001d);
	}
	
//	@Test
//	public void testNegColumnCountRowTotal() {
//		mockingContext.checking(new Expectations() {
//            {
//                oneOf(threeByThree).getRowCount();
//                will(returnValue(3));
//                oneOf(threeByThree).getColumnCount();
//                will(returnValue(-3));
//                
//                // set values
//                // threeByThree:
//                // 7.5	 0.24  8.0
//                //
//                // 2.52  23.4  9.4
//                //
//                // 10.5	 5.0   18.11
//              
//                oneOf(threeByThree).getValue(0, 0);
//                will(returnValue(7.5));
//                oneOf(threeByThree).getValue(1, 0);
//                will(returnValue(2.52));
//                oneOf(threeByThree).getValue(2, 0);
//                will(returnValue(10.5));
//                oneOf(threeByThree).getValue(0, 1);
//                will(returnValue(0.24));
//                oneOf(threeByThree).getValue(1, 1);
//                will(returnValue(23.4));
//                oneOf(threeByThree).getValue(2, 1);
//                will(returnValue(5.0));
//                oneOf(threeByThree).getValue(0, 2);
//                will(returnValue(8.0));
//                oneOf(threeByThree).getValue(1, 2);
//                will(returnValue(9.4));
//                oneOf(threeByThree).getValue(2, 2);
//                will(returnValue(18.11));
//                never(threeByThree).getValue(1, 3);
//                
//            }
//        });
//		assertEquals("Summing a row with negative column count.",
//		        35.32, DataUtilities.calculateRowTotal(threeByThree, 1), .000000001d);
//	}
	
//	@Test
//	public void testNegColumnCountNegvallRowTotal() {
//		mockingContext.checking(new Expectations() {
//            {
//                oneOf(threeByThree).getRowCount();
//                will(returnValue(3));
//                oneOf(threeByThree).getColumnCount();
//                will(returnValue(-3));
//                
//                // set values
//                // threeByThree:
//                // 7.5	 0.24  8.0
//                //
//                // 2.52  null  9.4
//                //
//                // 10.5	 5.0   18.11
//              
//                oneOf(threeByThree).getValue(0, 0);
//                will(returnValue(7.5));
//                oneOf(threeByThree).getValue(1, 0);
//                will(returnValue(2.52));
//                oneOf(threeByThree).getValue(2, 0);
//                will(returnValue(10.5));
//                oneOf(threeByThree).getValue(0, 1);
//                will(returnValue(0.24));
//                oneOf(threeByThree).getValue(1, 1);
//                will(returnValue(null));
//                oneOf(threeByThree).getValue(2, 1);
//                will(returnValue(5.0));
//                oneOf(threeByThree).getValue(0, 2);
//                will(returnValue(8.0));
//                oneOf(threeByThree).getValue(1, 2);
//                will(returnValue(9.4));
//                oneOf(threeByThree).getValue(2, 2);
//                will(returnValue(18.11));
//                never(threeByThree).getValue(1, 3);
//                
//            }
//        });
//		assertEquals("Summing a row with negative column count and null values.",
//		        11.92, DataUtilities.calculateRowTotal(threeByThree, 1), .000000001d);
//	}
	
	@Test
	public void testSubsetRowTotalOverload() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // 7.5	 0.24  8.0
                //
                // 2.52  23.4  9.4
                //
                // 10.5	 5.0   18.11
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(7.5));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(23.4));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(5.0));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(18.11));
                
            }
        });
		int[] vcols = {0, 2};
		assertEquals("Summing a subset of columns.",
		        11.92, DataUtilities.calculateRowTotal(threeByThree, 1, vcols), .000000001d);
	}
	
	@Test
	public void testUOB_ColsRowTotalOverload() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // 7.5	 0.24  8.0
                //
                // 2.52  23.4  9.4
                //
                // 10.5	 5.0   18.11
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(7.5));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(23.4));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(5.0));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(18.11));
                
            }
        });
		int[] vcols = {0, 1, 4, 5, 6};
		assertEquals("Summing with out-of-bounds columns.",
		        25.92, DataUtilities.calculateRowTotal(threeByThree, 1, vcols), .000000001d);
	}
	
	@Test
	public void testNullValueRowTotalOverload() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // 7.5	 0.24  8.0
                //
                // 2.52  23.4  9.4
                //
                // 10.5	 null   18.11
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(7.5));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(23.4));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(null));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(18.11));
                
            }
        });
		int[] vcols = {1, 2};
		assertEquals("Summing a valid row.",
		        18.11, DataUtilities.calculateRowTotal(threeByThree, 2, vcols), .000000001d);
	}
	
	@Test
	//mutation line 201 
	public void testNullValueRowTotalOverload_mutationline201() {
		mockingContext.checking(new Expectations() {
            {
                oneOf(threeByThree).getRowCount();
                will(returnValue(3));
                oneOf(threeByThree).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // 7.5	 0.24  8.0
                //
                // 2.52  23.4  9.4
                //
                // 10.5	 null   18.11
              
                oneOf(threeByThree).getValue(0, 0);
                will(returnValue(7.5));
                oneOf(threeByThree).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(threeByThree).getValue(2, 0);
                will(returnValue(10.5));
                oneOf(threeByThree).getValue(0, 1);
                will(returnValue(0.24));
                oneOf(threeByThree).getValue(1, 1);
                will(returnValue(23.4));
                oneOf(threeByThree).getValue(2, 1);
                will(returnValue(null));
                oneOf(threeByThree).getValue(0, 2);
                will(returnValue(8.0));
                oneOf(threeByThree).getValue(1, 2);
                will(returnValue(9.4));
                oneOf(threeByThree).getValue(2, 2);
                will(returnValue(18.11));
                ParamChecks.nullNotPermitted(hugeContext, null);
            }
        });
		int[] vcols = {1,2};
		assertEquals("Summing a valid row.",
		        18.11, DataUtilities.calculateRowTotal(threeByThree, 2, vcols), .000000001d);
	}
	
	@Test
	public void testMixedColumnTotal() {

		mixedContext.checking(new Expectations() {
            {
                oneOf(mix).getRowCount();
                will(returnValue(3));
                oneOf(mix).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // -2.5	 19.1  -21.833
                //
                // 2.52  -11.2  8.88
                //
                // 26.62  5.4   -0.312
              
                oneOf(mix).getValue(0, 0);
                will(returnValue(-2.5));
                oneOf(mix).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(mix).getValue(2, 0);
                will(returnValue(26.62));
                oneOf(mix).getValue(0, 1);
                will(returnValue(19.1));
                oneOf(mix).getValue(1, 1);
                will(returnValue(-11.2));
                oneOf(mix).getValue(2, 1);
                will(returnValue(5.4));
                oneOf(mix).getValue(0, 2);
                will(returnValue(-21.833));
                oneOf(mix).getValue(1, 2);
                will(returnValue(8.88));
                oneOf(mix).getValue(2, 2);
                will(returnValue(-0.312));
                
            }
        });
		
		assertEquals("Summing positive and negative values in a column.",
		        13.3, DataUtilities.calculateColumnTotal(mix, 1), .000000001d);
	}
	
	@Test
	public void testMixedRowTotal() {
		mixedContext.checking(new Expectations() {
            {
                oneOf(mix).getRowCount();
                will(returnValue(3));
                oneOf(mix).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // -2.5	 19.1  -21.833
                //
                // 2.52  -11.2  8.88
                //
                // 26.62  5.4   -0.312
              
                oneOf(mix).getValue(0, 0);
                will(returnValue(-2.5));
                oneOf(mix).getValue(1, 0);
                will(returnValue(2.52));
                oneOf(mix).getValue(2, 0);
                will(returnValue(26.62));
                oneOf(mix).getValue(0, 1);
                will(returnValue(19.1));
                oneOf(mix).getValue(1, 1);
                will(returnValue(-11.2));
                oneOf(mix).getValue(2, 1);
                will(returnValue(5.4));
                oneOf(mix).getValue(0, 2);
                will(returnValue(-21.833));
                oneOf(mix).getValue(1, 2);
                will(returnValue(8.88));
                oneOf(mix).getValue(2, 2);
                will(returnValue(-0.312));
                
            }
        });
		
		assertEquals("Summing positive and negative values in a row.",
		        -5.233, DataUtilities.calculateRowTotal(mix, 0), .000000001d);
	}
	
	@Test
	public void testNegativeColumnTotal() {
		negativeContext.checking(new Expectations() {
            {
                oneOf(neg).getRowCount();
                will(returnValue(3));
                oneOf(neg).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // -2.5	 -19.1  -21.833
                //
                // -2.52  -11.2  -8.88
                //
                // -26.62  -5.4   -0.312
              
                oneOf(neg).getValue(0, 0);
                will(returnValue(-2.5));
                oneOf(neg).getValue(1, 0);
                will(returnValue(-2.52));
                oneOf(neg).getValue(2, 0);
                will(returnValue(-26.62));
                oneOf(neg).getValue(0, 1);
                will(returnValue(-19.1));
                oneOf(neg).getValue(1, 1);
                will(returnValue(-11.2));
                oneOf(neg).getValue(2, 1);
                will(returnValue(-5.4));
                oneOf(neg).getValue(0, 2);
                will(returnValue(-21.833));
                oneOf(neg).getValue(1, 2);
                will(returnValue(-8.88));
                oneOf(neg).getValue(2, 2);
                will(returnValue(-0.312));
                
            }
        });
		
		assertEquals("Summing negative values in a column.",
		        -31.025, DataUtilities.calculateColumnTotal(neg, 2), .000000001d);
	}
	
	@Test
	public void testNegativeRowTotal() {
		negativeContext.checking(new Expectations() {
            {
                oneOf(neg).getRowCount();
                will(returnValue(3));
                oneOf(neg).getColumnCount();
                will(returnValue(3));
                
                // set values
                // threeByThree:
                // -2.5	 -19.1  -21.833
                //
                // -2.52  -11.2  -8.88
                //
                // -26.62  -5.4   -0.312
              
                oneOf(neg).getValue(0, 0);
                will(returnValue(-2.5));
                oneOf(neg).getValue(1, 0);
                will(returnValue(-2.52));
                oneOf(neg).getValue(2, 0);
                will(returnValue(-26.62));
                oneOf(neg).getValue(0, 1);
                will(returnValue(-19.1));
                oneOf(neg).getValue(1, 1);
                will(returnValue(-11.2));
                oneOf(neg).getValue(2, 1);
                will(returnValue(-5.4));
                oneOf(neg).getValue(0, 2);
                will(returnValue(-21.833));
                oneOf(neg).getValue(1, 2);
                will(returnValue(-8.88));
                oneOf(neg).getValue(2, 2);
                will(returnValue(-0.312));
                
            }
        });
		
		assertEquals("Summing negative values in a row.",
		        -22.6, DataUtilities.calculateRowTotal(neg, 1), .000000001d);
	}

	
    //new tests for mutations 
	@Test(expected = IllegalArgumentException.class) 
	//mutation test increase line 102
	public void test_clone_mutationline102_bothNull() {
		double a[][] = null;
		double expected[][] = null;
		Assert.assertArrayEquals("clone with null", expected, DataUtilities.clone(a));
	}
	
	@Test 
	//mutation test increase line 104
	public void test_clone_mutationline104_differentlength() {
		double a[][] = {};
		double expected[][] = {};
		Assert.assertArrayEquals("clone with empty array", expected, DataUtilities.clone(a));
	}
	
	@Test 
	//getting rid of mutation line 245 
	public void test_createNumberArray2D_line245mutation() {
		double data[][] = new double [0][];
		for(int i = 0; i < data.length; i++) {
			data[i] = new double [100000];
			Arrays.fill(data[i], 8.0);
		}
		Number expected[][] = new Number[0][];
		for(int i = 0; i < data.length; i++) {
			expected[i] = new Number[100000];
			Arrays.fill(data[i], 8.0);
		}
		assertArrayEquals("creating a number array with 0", expected, DataUtilities.createNumberArray2D(data));
	}
	
	@Test
	public void test_equal_Nullb() {
		double b[][] = null;
		double a[][] = {{1,1},{1,1},{1,1}};
		
		assertEquals("equal with Null b Value", 
				false, DataUtilities.equal(a, b));
	}
	
	@Test
	public void test_equal_differentLengths() {
		double b[][] = {{1,1},{1,1}};
		double a[][] = {{1,1},{1,1},{1,1}};
		
		assertEquals("equal with different lengths", 
				false, DataUtilities.equal(a, b));
	}
	
	@Test
	public void test_equal_SameLength_EqualInputs() {
		double b[][] = {{1,1},{1,1},{1,1}};
		double a[][] = {{1,1},{1,1},{1,1}};
		
		assertEquals("equal with the same inputs", 
				true, DataUtilities.equal(a, b));
	}
	
	@Test (expected = IllegalArgumentException.class)
	//Test to get rid of line 225 mutation 
	public void test_mutation225_CreatNumberArray_ParamChecks() {
		double data[] = null;
		Number expected[] = null;
		Number actual[] = DataUtilities.createNumberArray(data);
		assertArrayEquals("creating a array with null value", expected, actual);
		
	}
	
	@Test
	public void testZeroColumnTotal() {
		zeroContext.checking(new Expectations() {
            {
                oneOf(zero).getRowCount();
                will(returnValue(3));
                oneOf(zero).getColumnCount();
                will(returnValue(3));
                
                // creates a 3x3 matrix of zeroes
              
                oneOf(zero).getValue(0, 0);
                will(returnValue(0));
                oneOf(zero).getValue(1, 0);
                will(returnValue(0));
                oneOf(zero).getValue(2, 0);
                will(returnValue(0));
                oneOf(zero).getValue(0, 1);
                will(returnValue(0));
                oneOf(zero).getValue(1, 1);
                will(returnValue(0));
                oneOf(zero).getValue(2, 1);
                will(returnValue(0));
                oneOf(zero).getValue(0, 2);
                will(returnValue(0));
                oneOf(zero).getValue(1, 2);
                will(returnValue(0));
                oneOf(zero).getValue(2, 2);
                will(returnValue(0));
            }
        });
		
		assertEquals("Summing zeroes in a column.",
		        0, DataUtilities.calculateColumnTotal(zero, 1), .000000001d);
	}
	
	@Test
	public void testZeroRowTotal() {
		zeroContext.checking(new Expectations() {
            {
                oneOf(zero).getRowCount();
                will(returnValue(3));
                oneOf(zero).getColumnCount();
                will(returnValue(3));
                
                // creates a 3x3 matrix of zeroes
              
                oneOf(zero).getValue(0, 0);
                will(returnValue(0));
                oneOf(zero).getValue(1, 0);
                will(returnValue(0));
                oneOf(zero).getValue(2, 0);
                will(returnValue(0));
                oneOf(zero).getValue(0, 1);
                will(returnValue(0));
                oneOf(zero).getValue(1, 1);
                will(returnValue(0));
                oneOf(zero).getValue(2, 1);
                will(returnValue(0));
                oneOf(zero).getValue(0, 2);
                will(returnValue(0));
                oneOf(zero).getValue(1, 2);
                will(returnValue(0));
                oneOf(zero).getValue(2, 2);
                will(returnValue(0));
            }
        });
		
		assertEquals("Summing zeroes in a row.",
		        0, DataUtilities.calculateRowTotal(zero, 1), .000000001d);
	}
	@Test
	//testing for createNumberArray2D
	//test createNumberArray2D() with nominal 1D elements, 1 2D elements, and nominal values
	public void test_CreateNUmberArray2D_Nominal1D_ALB2D_NominalValues() {
		double data[][] = {{1.0},{2.0},{3.0}};
		Number expected[][] = {{1.0},{2.0},{3.0}};
		assertArrayEquals("creating a Number array with large amount of 1D elements, 1 2D elements, and nominal values", 
				expected, DataUtilities.createNumberArray2D(data));
	}
	
	@Test (expected = IllegalArgumentException.class)
	//getting rid of mutation line 242
	public void test_mutation242_CreateNummberArray2D(){
		double data [][] = null;
		Number expected [][] = null;
		assertArrayEquals("creating a number array with null values", expected, DataUtilities.createNumberArray2D(data));
	}

	@Test
	//testing for createNumberArray2D
	//test createNumberArray2D() with LB 1D elements, nominal 2D elements, and nominal values
	public void test_CreateNUmberArray2D_LB1D_Nominal2D_NominalValues() {
		double data[][] = {};
		Number expected[][] = {};
		assertArrayEquals("creating a Number array with no 1D elements, nominal 2D elements, and nominal values", 
				expected, DataUtilities.createNumberArray2D(data));
	}
	@Test
	//test createNumberArray2D() with nominal 1D elements, nominal 2D elements, and nominal values
	public void test_CreateNUmberArray2D_Nominal1D_Nominal2D_NominalValues() {
		double data[][] = {{1.0,2.0,3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}};
		Number expected[][] = {{1.0,2.0,3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}};
		assertArrayEquals("creating a Number array with nominal 1D elements, nominal 2D elements, and nominal values", 
				expected, DataUtilities.createNumberArray2D(data));
	}
	
	@Test
	//testing for createNumberArray2D
	//test createNumberArray2D() with BLB 1D elements, nominal 2D elements, and nominal values
	public void test_CreateNUmberArray2D_BLB1D_Nominal2D_NominalValues() {
		double data[][] = null;
		boolean caught = false;
		try {
			Number actual[][] = DataUtilities.createNumberArray2D(data);	
		}
		catch(Exception InvalidParamterException) {
			caught = true;
		}
		assertEquals("creating a Number array with null 1D elements, nominal 2D elements, and nominal values", 
				true, caught);
	}
	@Test
	public void testNullRowTotal() {
		boolean caught = false;
		try {
			DataUtilities.calculateRowTotal(null, 0);
		}
		catch (Exception InvalidParameterException){
			caught = true;
		}
		assertTrue("Sum of row with invalid input.", caught);
	}
	@Test 
	//New Test for mutation score increase line 84
	public void test_line84mutation_equals() {
		double a[][] = {{1,2}, {2,3}, {1,2}};
		double b[][] = {{2,3}, {1,2}};
		
		assertEquals("different inputs with different lenghts", false, DataUtilities.equal(a,b));
	}
	
}

	
