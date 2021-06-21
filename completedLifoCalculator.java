package practingJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* This code is used by Elenbaas Steel Supply to calculate their LIFO insurance on steel sales.
 * Each year, this must be done, and it is tedious process. While creating this code, I asked for
 * examples of the calculations done properly. After completing this code I discovered that there
 * was not a single page done by hand that was free of errors (creating a possible legal situation).
 * The prevelance of errors is not unique to Elenbaas Steel Supply.
 * In the past, ESS had accountants and book-keepers do these calculations, which was time consuming
 * and mind numbing (their words). The book-keeper is given a series of numbers. The number of columns 
 * is determined by the number of years this calculation has been required and the rows are the amount of
 * steel(in total pounds on top) and the average cost-per-pound for that year (on the bottom).
 * The first step is to look at the pound-row and skip the first value, and to search for the next value that
 * is greater than the first value (at index 0) without having values in betwixt the two values decrement.
 * In other words, you need to find the next value that is greater than the value in position 0, but by the 
 * smallest amount (this process will be repeated until we iterate through the entire row. Once we have found
 * our next value, we will subtract the value at position 0 from our newly found number and then multiply that
 * value by the corresponding cost value of the value at position 0 in our pounds-row.
 * 
 * 
 * 
 * 
 * rewrite
 Use +1 instead of a nested loop to improve performance on execution.
 for (int i = 0; i < lbCA.length; i++) {
 if (lbCA[i+1] < lbCA[i] && lbCA[i+1] < 17) {
tempNextPositiveIncrement = lbCA[i+1];
 }
 }
 */

public class completedLifoCalculator {

	public static void main(String[] args) {
		ArrayList fruitList = new ArrayList();

		double[] newLbArray = makeLbArray();

		double[] newCostArray = makeCostArray();

		/*
		 * Below, we feed our newly created array of pound values into our function that
		 * finds the index of the smallest value in the array, we then proceed to do the
		 * first calculation which is multiplying the value in the lb-array by the value
		 * in the corresponding value of the cost-array at index 0.
		 */
		int indexOfSmallestValue = findIndexOfSmallestValue(newLbArray);
		double firstCalculation = newLbArray[indexOfSmallestValue] * newCostArray[0];

		/*
		 * Below, because we have already done the first calculation, we now shift the
		 * smallest value to be called indexOfLastBiggestValue because for all intents and purposes
		 * the smallest value acts like your first nextPostiveIncrement value -- remember that we
		 * aren't strictly looking for the next biggest value without reservation,
		 * rather we are looking for the next value as we iterate through our arrays
		 * that is the bigger than our previously held value by the smallest possible
		 * increment. We cannot accept a larger value (lets call it P) as we iterate
		 * through the array if there is a value to the right of P that is larger than
		 * our last biggest value and also smaller than P. These rules are seemingly
		 * arbitrary but this is the law of the land in Michigan.
		 * 
		 */
		int indexOfSmallestValuePlaceholder = indexOfSmallestValue;

		int indexOfLastBiggestValue = indexOfSmallestValuePlaceholder;
		/*
		 * Below, we create a series of truncated versions of our pound-array, cutting away the values that 
		 * precede our current indexOfLastBiggestValue.
		 */

		for (int r = 0; r < 16; r++) {
			int lengthOfNewArray = newLbArray.length - indexOfLastBiggestValue;
			

			double[] calculationArray = makeCalculationArray(newLbArray, indexOfLastBiggestValue, lengthOfNewArray);

			ArrayList calculationArrayAsArrayList = new ArrayList();
			for (int w = 0; w < calculationArray.length; w++) {
				calculationArrayAsArrayList.add(calculationArray[w]);

			}

			double[] lbCA = makeLbCA(calculationArray);

			double tempNextPositiveIncrement = makeTempNextPositiveIncrement(lbCA);

			int findNextBiggest = makeFindNextPositiveIncrement(lbCA, indexOfLastBiggestValue, tempNextPositiveIncrement);

			calculationArrayAsArrayList.get(0);

			Object[] lasty = calculationArrayAsArrayList.toArray();

			double lastg = (double) lasty[1];
			double lastd = (double) lasty[0];
			
			double lastT = lastg - lastd;
			
			double calculationOfLastValue = lastT * newCostArray[16];

			double sumOfLbAndCostValues = 0;
			for (int z = 0; z < fruitList.size(); z++) {
				double valuesIteratingThroughFinalList = (double) fruitList.get(z);
				sumOfLbAndCostValues += valuesIteratingThroughFinalList;
			}

			double finalCalculation = firstCalculation + sumOfLbAndCostValues + calculationOfLastValue;

			System.out.println(" The answer is... " + finalCalculation);
			/*
			 * The below code will stop the code from iterating through our arrays if there is only
			 * one value left, which indicates that we have reached the last column of the pound array.
			 */
			if (lbCA.length < 2) {
				break;
			}

			double stepFive = newLbArray[findNextBiggest] - newLbArray[indexOfLastBiggestValue];

			int retty = Double.compare(newLbArray[findNextBiggest], newLbArray[indexOfLastBiggestValue] + 1);

			if (retty > 0) {
				fruitList.add(stepFive * newCostArray[indexOfLastBiggestValue + 1]);

			} else if (retty < 0) {
				fruitList.add(stepFive * newCostArray[indexOfLastBiggestValue + 1]);

			} else {
				fruitList.add(stepFive * newCostArray[findNextBiggest]);

			}

			indexOfLastBiggestValue = findNextBiggest;

		}

	}

	private static double makeTempNextPositiveIncrement(double[] lbCA) {
		double tempNextPositiveIncrement = 0;

		for (int i = 0; i < lbCA.length; i++) {
			for (int j = 1; j < lbCA.length; j++) {
				if (lbCA[j] < lbCA[i]) {
					tempNextPositiveIncrement = lbCA[j];

				}
			}
		}
		return tempNextPositiveIncrement;
	}

	private static int makeFindNextPositiveIncrement(double[] lbCA, int indexOfLastBiggestValue, double tempNextPositiveIncrement) {

		int nextPositiveIncrement = 0;
		double tempNPI = tempNextPositiveIncrement;

		for (int i = 0; i < lbCA.length; i++) {

			if (lbCA[i] > tempNPI && lbCA[i] < 0) {
				nextPositiveIncrement = i;
				tempNPI = lbCA[i];

			}
		}

		return indexOfLastBiggestValue + nextPositiveIncrement + 1;
	}
/*
 * 
 */
	private static double[] makeLbCA(double[] calculationArray) {
		double[] lbDifferences = new double[calculationArray.length - 1];
		for (int g = 0; g < lbDifferences.length; g++) {
			lbDifferences[g] = calculationArray[0] - calculationArray[g + 1];
		}

		return lbDifferences;
	}

	private static double[] makeCalculationArray(double[] newLbArray, int indexOfLastBiggestValue, int lengthOfNewArray) {
		/*
		 * In this function we create an array (this will be done several times, until one of the truncated arrays 
		 * has a length of one, indicating that we can cease doing calculations) that has as its' first member
		 * our previously attained largest value of the pound array. The function then proceeds to fill the rest of the array with 
		 * values starting with the next value after our current next biggest value.
		 */

		double[] calcArray = new double[lengthOfNewArray];
		calcArray[0] = newLbArray[indexOfLastBiggestValue];
		for (int i = 1; i < calcArray.length; i++) {
			calcArray[i] = newLbArray[indexOfLastBiggestValue + i];
		}

		return calcArray;

	}

	public static int findIndexOfSmallestValue(double[] newLbArray) {
		/*
		 * In this function we take in our previously created double array of
		 * pound-values and iterate through them in order to find the index of the
		 * smallest value present in the array. The lowest value cannot be at index 0.
		 *
		 */
		int index = 0;
		Double minimum = newLbArray[index];
		for (int g = 0; g < newLbArray.length; g++) {
			if (newLbArray[g] < minimum) {
				minimum = newLbArray[g];
				index = g;
			}
		}
		return index;
	}

	public static double[] makeCostArray() {

		/*
		 * This code is nearly identical to the makeLbArray function below, the only
		 * difference is that it takes in user input for the price of steel over the
		 * years and not the amount of steel sold. I am going to make a constructor to
		 * do both in the future.
		 */
		System.out.println("Please type in the cost row numbers.");
		Scanner input2 = new Scanner(System.in);
		String inputString2 = input2.nextLine();
		String[] inputStringArray2 = null;
		inputStringArray2 = inputString2.split(" ");
		double[] costDoubleArray = Arrays.stream(inputStringArray2).mapToDouble(Double::parseDouble).toArray();

		return costDoubleArray;
	}

	public static double[] makeLbArray() {

		/*
		 * This code takes in a series of numbers which are the amount of steel sold
		 * over a period of years, and stores the inputed information as a string.
		 */
		System.out.println("Please copy and paste in the lb row numbers.");
		Scanner input = new Scanner(System.in);
		String inputString = input.nextLine();
		/*
		 * This code takes the previously created string of values and makes it into a
		 * string array. The split is looking for spaces between the numbers so that it
		 * takes the numbers in and knows that if there is space between them that that
		 * is where to parse the string into separate pieces.
		 */
		String[] inputStringArray = null;
		inputStringArray = inputString.split(" ");
		/*
		 * This code takes the array of strings and turns it into an array of doubles so
		 * that we may do calculations.
		 */
		double[] lbDoubleArray = Arrays.stream(inputStringArray).mapToDouble(Double::parseDouble).toArray();

		return lbDoubleArray;

	}

}
