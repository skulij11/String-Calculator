package is.ru.stringcalculator;

public class Calculator {
	
	private static String delimiter = "\n|,";

	public static int add(String text) {
		if(text.equals(""))
			return 0;
		else if(text.contains("-")) {
			String exceptionMessage = makeExceptionMessage(text);
			throw new IllegalArgumentException(exceptionMessage);
		}
		else if(text.startsWith("//")) {
			return total(splitWithAnotherDelimiter(text));
		}
		else {
			return total(splitNumbers(text));
		}		
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
	
	private static String[] splitWithAnotherDelimiter(String text) {
		String[] splitString = text.split("//|\n");
		String newDelimiter = splitString[1];

		String[] numbers = splitString[2].split(newDelimiter);
		return numbers;
	}

	private static String[] splitNumbers(String text) {
		return text.split(delimiter);
	}
	private static int total(String[] numbers) {
		int total = 0;
		for(String num : numbers) {
			if(toInt(num) > 1000)
				continue;
			total += toInt(num);
		}
		return total;
	}
}
