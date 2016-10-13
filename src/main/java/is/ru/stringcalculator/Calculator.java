package is.ru.stringcalculator;

public class Calculator {
	
	private static String delimiter = "\n|,";

	public static int add(String text){
		if(text.equals(""))
			return 0;
		else if(text.contains("-")) {
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
			throw new IllegalArgumentException(exception);
		}
		else if(text.contains(",") || text.contains("\n")) {
			return total(splitNumbers(text));
		}		
		else return toInt(text);
    }
	private static int toInt(String text) {
		return Integer.parseInt(text);
	}
	private static String[] splitNumbers(String numbers) {
		return numbers.split(delimiter);
	}
	private static int total(String[] numbers) {
		int total = 0;
		for(String num : numbers) {
			total += toInt(num);
		}
		return total;
	}
}
