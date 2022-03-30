package zadatak;

import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Main {
	private static final DecimalFormat df = new DecimalFormat("0");
	private static List<Character> signs = new ArrayList<>() {
	private static final long serialVersionUID = 1L;

		{
			add(' ');
			add(',');
			add('.');
			add('?');
			add('!');
		}
	};
	public static void main(String[] args) {
		String text = new String("Sed ut perspiciatis unde omnis iste natus error sit voluptatem "
				+ "accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab "
				+ "illo inventore veritatis et quasi architecto beatae vitae dicta sunt "
				+ "explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut "
				+ "odit aut fugit, sed quia consequuntur magni dolores eos qui ratione "
				+ "voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum "
				+ "quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam "
				+ "eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat "
				+ "voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam "
				+ "corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? "
				+ "Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse "
				+ "quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo "
				+ "voluptas nulla pariatur?");
		changeLetterOrder(text);
		flipLetterOrder(text);
		flipWordOrder(text);
		flipSentenceOrder(text);
		statistic(text);
	}

	private static void changeLetterOrder(String text) {
		String words[] = text.split("\s");
		for(String word : words) {
			Character sign = null;
			if(signs.contains(word.charAt(word.length()-1))) {
				sign = word.charAt(word.length()-1);
				word = word.substring(1, word.length() - 1);
			}
			List<Character> letterList = new ArrayList<>();
			String middleLetterOrg = word.substring(1, word.length()-1);
			for (int i = 0; i < middleLetterOrg.length(); i++) {
				letterList.add((Character) middleLetterOrg.charAt(i));
			}
			Collections.shuffle(letterList);
			String middleLetters = "";
			for (Character middleLetter : letterList) {
				middleLetters += middleLetter;
			}
			word = String.valueOf(word.charAt(word.length()-1)) + middleLetters + String.valueOf(word.charAt(0)) + (sign != null ? String.valueOf(sign) : "");
			System.out.print(word + " ");
		}
		spacer();
	}
	
	private static void flipLetterOrder(String text) {
		List<String> sentences = new ArrayList<>();
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		iterator.setText(text);
		int start = iterator.first();
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			sentences.add(text.substring(start,end));
		}
		for(String sentence : sentences) {
			String words[] = sentence.split("\s");
			for(int i = 0; i < words.length; i++) {
				Character sign = null;
				for (int j = words[i].length() - 1; j != -1; j--) {
					if(i == 0 && j == words[i].length() - 1) {
						System.out.print(words[i].toUpperCase().charAt(j));
					} else {
						if(signs.contains(words[i].charAt(j))) {
							sign = words[i].charAt(j);
						} else {
							System.out.print(words[i].toLowerCase().charAt(j));
						}
					}	
				}
				if(sign != null) {
					System.out.print(sign);
				}
				System.out.print(" ");
			}
		}
		spacer();
	}
	
	private static void flipWordOrder(String text) {
		List<String> sentences = new ArrayList<>();
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		iterator.setText(text);
		int start = iterator.first();
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			sentences.add(text.substring(start,end));
		}
		for(String sentence : sentences) {
			String sign = null;
			String words[] = sentence.split("\s");
			for(int i = words.length - 1; i != -1; i--) {
				if (i == words.length - 1) {
					System.out.print(words[i].substring(0, 1).toUpperCase() + words[i].substring(1, words[i].length() - 1) + " ");
					sign = words[i].substring(words[i].length() - 1);
				} else {
					System.out.print(i == 0 ? words[i].toLowerCase() + "" : words[i].toLowerCase() + " ");
				}
			}
			System.out.print(sign + " ");
		}
		spacer();
	}
	
	private static void flipSentenceOrder(String text) {
		List<String> sentences = new ArrayList<>();
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		iterator.setText(text);
		int start = iterator.first();
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			sentences.add(text.substring(start,end));
		}
		for(int i = sentences.size() - 1; i != -1; i--) {
			System.out.print(sentences.get(i));
			if(sentences.get(i).charAt(sentences.get(i).length()-1) != ' ') {
				System.out.print(" ");
			}
		}
		spacer();
	}
	
	private static void statistic(String text) {
		List<String> sentences = new ArrayList<>();
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		iterator.setText(text);
		int start = iterator.first();
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			sentences.add(text.substring(start,end));
		}
		Integer vowelTotalCount = 0;
		Integer consonantsTotalCount = 0;
		List<Character> vowels = new ArrayList<>() {
		private static final long serialVersionUID = 1L;

			{
				add('a');
				add('e');
				add('i');
				add('o');
				add('u');
			}
		};
		
		for(int i = 0; i < sentences.size(); i++) {
			Integer sentenceVowelCount = 0;
			Integer sentenceConsonantsCount = 0;

			for (int j = 0; j < sentences.get(i).length(); j++) {
				if(vowels.contains(sentences.get(i).toLowerCase().charAt(j))){
					vowelTotalCount++;
					sentenceVowelCount++;
		        } else if (!signs.contains(sentences.get(i).toLowerCase().charAt(j))) {
		        	consonantsTotalCount++;
		        	sentenceConsonantsCount++;
		        }
			}
			
			System.out.println(i+1 + ". sentence vowel count: " + sentenceVowelCount);
			System.out.println(i+1 + ". sentence consonants count: " + sentenceConsonantsCount);
			double percentage = 1;
			if(sentenceVowelCount > sentenceConsonantsCount) {
				percentage = (double)sentenceVowelCount/(double)sentenceConsonantsCount;
				System.out.println(i+1 + ". sentence vowel count is " + df.format(percentage*100 - 100) + "% greater than consonant's");
			} else {
				percentage = (double)sentenceConsonantsCount/(double)sentenceVowelCount;
				System.out.println(i+1 + ". sentence consonant count is " + df.format(percentage*100 - 100) + "% greater than vowel's");
			}
		}

		System.out.println("Total vowel count: " + vowelTotalCount);
		System.out.println("Total consonants count: " + consonantsTotalCount);
		System.out.println(vowelTotalCount < consonantsTotalCount ? 
			"Consonant count is " + df.format((double)consonantsTotalCount/(double)vowelTotalCount*100 - 100) + "% greater than vowel's" :
			"Vowel count is " + df.format((double)vowelTotalCount/(double)consonantsTotalCount*100 - 100) + "% greater than consonant's");
		spacer();
	}

	private static void spacer() {
		System.out.println();
		System.out.println();
	}
}
