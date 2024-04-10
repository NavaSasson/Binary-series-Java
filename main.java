import java.util.Scanner;
import java.lang.*;
import java.util.stream.IntStream;

public class main {
	
	public static void main(String[] args)
    {
        System.out.println("Please enter 3 positive binary numbers, each with 9 digits");
        String firstBinaryNumberFromUser = getValidBinaryNumberFromUser();
        String secondBinaryNumberFromUser = getValidBinaryNumberFromUser();
        String thirdBinaryNumberFromUser = getValidBinaryNumberFromUser();
        int firstDecimal = convertBinaryToDecimal(firstBinaryNumberFromUser);
        int secondtDecimal = convertBinaryToDecimal(secondBinaryNumberFromUser);
        int thirdtDecimal = convertBinaryToDecimal(thirdBinaryNumberFromUser);
        MinMaxAndAscendingOrderOfDecimals(firstDecimal, secondtDecimal, thirdtDecimal);
        averageOccurrencesOfZeroAndOneDigits(firstBinaryNumberFromUser, secondBinaryNumberFromUser, thirdBinaryNumberFromUser);
        howManyNumbersArePowerOfTwo(firstDecimal, secondtDecimal, thirdtDecimal);
        countInHowManyNumbersDigitsInAscendingOrder(firstDecimal, secondtDecimal, thirdtDecimal);
    }

    private static String getValidBinaryNumberFromUser()
    {
    	Scanner scanner = new Scanner(System.in);
        String inputFromUser = scanner.nextLine();
        boolean isValidInput = checkIfValidInput(inputFromUser);

        if (!isValidInput)
        {
        	System.out.println("Invalid number, try again!");
            inputFromUser = getValidBinaryNumberFromUser();
        }

        return inputFromUser;
    }

    private static boolean checkIfValidInput(String i_InputStr)
    {
    	boolean isContainsOnlyZeroesAndOnes = i_InputStr.matches("[01]+");
    	boolean isValidInput = isContainsOnlyZeroesAndOnes;

	    if (isValidInput) {
	        boolean isPositiveNumber = !i_InputStr.equals("0");
	        isValidInput = isPositiveNumber;
	        if (isPositiveNumber) {
	            boolean isContainsNineDigits = i_InputStr.length() == 9;
	            isValidInput = isContainsNineDigits;
	        }
	    }

	    return isValidInput;
    }

    private static int convertBinaryToDecimal(String i_BinaryStr)
    {
    	int decimalFromBinary = 0;
        int binaryStrLength = i_BinaryStr.length();

        for (int i = 0; i < binaryStrLength; i++) {
            int digit = Character.getNumericValue(i_BinaryStr.charAt(i));
            decimalFromBinary += digit * Math.pow(2, binaryStrLength - i - 1);
        }

        return decimalFromBinary;
    }

    private static void MinMaxAndAscendingOrderOfDecimals(int i_FirstDecimal, int i_SecondDecimal, int i_ThirdDecimal)
    {
        int min = Math.min(Math.min(i_FirstDecimal, i_SecondDecimal), i_ThirdDecimal);
        int max = Math.max(Math.max(i_FirstDecimal, i_SecondDecimal), i_ThirdDecimal);
        int middle = i_FirstDecimal + i_SecondDecimal + i_ThirdDecimal - min - max;

        System.out.println(String.format(
        	    "This is their ascending order:\n" + min + "\n" + middle + "\n" + max + "\n" +
        	    "The min decimal is " + min + "\n The max decimal is " +  max));

    }

    private static void averageOccurrencesOfZeroAndOneDigits(String i_FirstBinaryNumber, String i_SecondBinaryNumber, String i_ThirdBinaryNumber)
    {
        int zeroDigitCounter = 0;
        int oneDigitCounter = 0;
        int numOfInputs = 3;

        zeroDigitCounter += howManyOccurrencesDigitHasInStr(i_FirstBinaryNumber, '0');
        zeroDigitCounter += howManyOccurrencesDigitHasInStr(i_SecondBinaryNumber, '0');
        zeroDigitCounter += howManyOccurrencesDigitHasInStr(i_ThirdBinaryNumber, '0');
        oneDigitCounter += howManyOccurrencesDigitHasInStr(i_FirstBinaryNumber, '1');
        oneDigitCounter += howManyOccurrencesDigitHasInStr(i_SecondBinaryNumber, '1');
        oneDigitCounter += howManyOccurrencesDigitHasInStr(i_ThirdBinaryNumber, '1');
        float zeroAvg = (float)zeroDigitCounter / numOfInputs;
        float oneAvg = (float)oneDigitCounter / numOfInputs;
        System.out.println(String.format(
        	    "The average number of occurrences of the digit 0 is " + zeroAvg + "\n" +
        	    "The average number of occurrences of the digit 1 is " +  oneAvg ));
    }

    private static int howManyOccurrencesDigitHasInStr(String i_BinaryStr, char i_CharDigit)
    {
    	 // Convert the string to an IntStream of characters
        return (int) i_BinaryStr.chars()
                // Filter the stream to include only the specified character
                .filter(ch -> ch == i_CharDigit)
                // Count the occurrences of the specified character
                .count();
    }

    private static int howManyNumbersArePowerOfTwo(int i_FirsDecimal, int i_SecondDecimal, int i_ThirdDecimal)
    {

        int powersOfTwoCounter = (checkIfNumberIsPowerOfTwo(i_FirsDecimal) ? 1 : 0) + (checkIfNumberIsPowerOfTwo(i_SecondDecimal) ? 1 : 0) + (checkIfNumberIsPowerOfTwo(i_ThirdDecimal) ? 1 : 0);
        
        System.out.println(powersOfTwoCounter + "of the numbers you entered are powers of 2");

        return powersOfTwoCounter;
    }

    private static boolean checkIfNumberIsPowerOfTwo(int i_DecimalNumber)
    {
        double powerOfNumber = Math.log(i_DecimalNumber) / Math.log(2);
        boolean isPowerOfTwo = powerOfNumber == (int)powerOfNumber;

        return isPowerOfTwo;
    }

    private static void countInHowManyNumbersDigitsInAscendingOrder(int i_FirsDecimal, int i_SecondDecimal, int i_ThirdDecimal)
    {
        int numbersInAscendingOrderCounter = (checkIfDigitsInAscendingOrder(i_FirsDecimal) ? 1 : 0) + (checkIfDigitsInAscendingOrder(i_SecondDecimal) ? 1 : 0) + (checkIfDigitsInAscendingOrder(i_ThirdDecimal) ? 1 : 0);

        System.out.println("In" + numbersInAscendingOrderCounter + "of the numbers you entered, the digits makes a strictly increasing series");
    }

    private static boolean checkIfDigitsInAscendingOrder(int i_DecimalNumber)
    {
    	boolean isDigitsInAscendingOrder = true;
        String decimalNumberStr = String.valueOf(i_DecimalNumber);

        for (int i = 0; i < decimalNumberStr.length() - 1 && isDigitsInAscendingOrder; i++)
        {
            isDigitsInAscendingOrder = decimalNumberStr.charAt(i) < decimalNumberStr.charAt(i + 1);
        }

        return isDigitsInAscendingOrder;
    }
}


