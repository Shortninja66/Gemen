package us.theaura.gemen.util.lib;

/**
 * Object class that converts numbers to roman numerals. I really only am using
 * this because reinventing the wheel is not necessary and this one supports
 * every number we will ever need.
 * 
 * @since 2 April 2016 6:50 AM
 * @author David J. Eck
 */

public class RomanNumeral {
	private final int NUMBER;
	private static int[] numbers = {
	1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
	};
	private static String[] letters = {
	"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
	};

	public RomanNumeral(int arabic) {
		if(arabic < 1)
			throw new NumberFormatException("Value of RomanNumeral must be positive.");
		if(arabic > 3999)
			throw new NumberFormatException("Value of RomanNumeral must be 3999 or less.");
		NUMBER = arabic;
	}

	public RomanNumeral(String roman) {
		if(roman.length() == 0) {
			throw new NumberFormatException("An empty string does not define a Roman numeral.");
		}

		roman = roman.toUpperCase();
		int i = 0;
		int arabic = 0;

		while(i < roman.length()) {
			char letter = roman.charAt(i);
			int number = letterToNumber(letter);

			i++;

			if(i == roman.length()) {
				arabic += number;
			}else {
				int nextNumber = letterToNumber(roman.charAt(i));
				if(nextNumber > number) {
					arabic += (nextNumber - number);
					i++;
				}else arabic += number;
			}

		}

		if(arabic > 3999)
			throw new NumberFormatException("Roman numeral must have value 3999 or less.");

		NUMBER = arabic;
	}

	private int letterToNumber(char letter) {
		switch(letter) {
			case 'I':
			return 1;
			case 'V':
			return 5;
			case 'X':
			return 10;
			case 'L':
			return 50;
			case 'C':
			return 100;
			case 'D':
			return 500;
			case 'M':
			return 1000;
			default:
			throw new NumberFormatException("Illegal character \"" + letter + "\" in Roman numeral");
		}
	}

	@Override
	public String toString() {
		String roman = "";
		int N = NUMBER;

		for(int i = 0; i < numbers.length; i++) {
			while(N >= numbers[i]) {
				roman += letters[i];
				N -= numbers[i];
			}
		}
		return roman;
	}

	public int toInt() {
		return NUMBER;
	}
}