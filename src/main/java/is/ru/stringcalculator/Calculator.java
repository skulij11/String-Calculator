package is.ru.stringcalculator;

public class Calculator {
	
	public static int add(String text) {
		if(text.contains("-")) {
			String exceptionMessage = makeExceptionMessage(text);
			throw new IllegalArgumentException(exceptionMessage);
		}	
		else 
			return total(splitNumbers(text));
    }

	private static String makeExceptionMessage(String text) {
		String[] numbers = splitNumbers(text);
		String exception = "Negatives not allowed: ";
		boolean alreadyNegative = false;

		for(String num : numbers) {
			if(toInt(num) < 0) {
				if(alreadyNegative) 
					exception += ",";
					
				exception += num;
				alreadyNegative = true;
			}				
		}
		return exception;
	}
	
	private static int toInt(String text) {
		return Integer.parseInt(text);
	}
	
	private static String[] splitNumbers(String text) {
		String delimiter = "\n|,";
		String[] numbers;

		if(text.startsWith("//")) {
			String[] splitString = text.substring(2).split("\n", 2);
			String newDelimiter = splitString[0];
			numbers = splitString[1].split(newDelimiter + "|" + delimiter);
		}
		else 
			numbers = text.split(delimiter);
		
		return numbers;
	}

	private static int total(String[] numbers) {
		int total = 0;
		for(String num : numbers) {
			if(num.equals("") || toInt(num) > 1000)
				continue;
			total += toInt(num);
		}
		return total;
	}
}
