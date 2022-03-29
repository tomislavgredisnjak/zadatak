package zadatak;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	  private static final DecimalFormat df = new DecimalFormat("0");

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

	//ovaj random ne radi bas i treba dodati dijakriticke znakove
	private static void changeLetterOrder(String text) {
		String words[] = text.split("[ ,.?!]+");
		for(String word : words) {
			List<String> strList = new ArrayList<>(Arrays.asList(word.substring(1, word.length()-1).split("\s")));
			//strList.forEach(str -> System.out.println(str));
			Collections.shuffle(strList);
			//strList.forEach(str -> System.out.println(str + "\n"));
			String[] middleLettersArray = strList.toArray(new String[strList.size()]);
			String middleLetters = "";
			for (String middleLetter : middleLettersArray) {
				middleLetters += middleLetter;
			}
			word = String.valueOf(word.charAt(word.length()-1)) + middleLetters + String.valueOf(word.charAt(0));
			//System.out.print(word + " ");
		}
	}
	
	//ovdje isto treba dodati dijakriticke znakove
	private static void flipLetterOrder(String text) {
		String sentences[] = text.split("[?.!]+\s");
		for(String sentence : sentences) {
			String words[] = sentence.split("[ ,.?!]+");
			for(int j = 0; j < words.length; j++) {
				for (int i = words[j].length() - 1; i != -1; i--) {
					if(j == 0 && i == words[j].length() - 1) {
						//System.out.print(words[j].toUpperCase().charAt(i));
					} else {
						//System.out.print(words[j].toLowerCase().charAt(i));
					}	
				}
				//System.out.print(" ");
			}
		}
	}
	
	//ovdje isto dodati te tocke
	private static void flipWordOrder(String text) {
		String sentences[] = text.split("[?.!]+\s");
		for(String sentence : sentences) {
			String words[] = sentence.split("\s");
			for(int i = words.length - 1; i != -1; i--) {
				if (i == words.length - 1) {
					//System.out.print(words[i].substring(0, 1).toUpperCase() + words[i].substring(1) + " ");
				} else {
					//System.out.print(words[i].toLowerCase() + " ");
				}
			}
		}
		
	}
	
	private static void flipSentenceOrder(String text) {
		//ovdje popraviti da uzima i tocke
		String sentences[] = text.split("[?.!]+\s");

		for(int i = sentences.length - 1; i != -1; i--) {
			//System.out.println(sentences[i]);
		}
	}
	
	private static void statistic(String text) {
		String sentences[] = text.split("[?.!]+\s");
		Integer vowelTotalCount = 0;
		Integer consonantsTotalCount = 0;
		List<Character> vowels = new ArrayList<>() {
			{
				add('a');
				add('e');
				add('i');
				add('o');
				add('u');
			}
		};
		
		List<Character> signs = new ArrayList<>() {
			{
				add(' ');
				add(',');
				add('.');
				add('?');
				add('!');
			}
		};
		
		for(int j = 0; j < sentences.length; j++) {
			Integer sentenceVowelCount = 0;
			Integer sentenceConsonantsCount = 0;

			for (int i = 0; i < sentences[j].length(); i++) {
				if(vowels.contains(sentences[j].toLowerCase().charAt(i))){
					vowelTotalCount++;
					sentenceVowelCount++;
		        } else if (!signs.contains(sentences[j].toLowerCase().charAt(i))) {
		        	consonantsTotalCount++;
		        	sentenceConsonantsCount++;
		        }
			}
			
			System.out.println(j+1 + ". sentence vowel count: " + sentenceVowelCount);
			System.out.println(j+1 + ". sentence consonants count: " + sentenceConsonantsCount);
			double percentage = 1;
			if(sentenceVowelCount > sentenceConsonantsCount) {
				percentage = (double)sentenceVowelCount/(double)sentenceConsonantsCount;
				System.out.println(j+1 + ". sentence vowel count is " + df.format(percentage*100 - 100) + "% greater than consonant's");
			} else {
				percentage = (double)sentenceConsonantsCount/(double)sentenceVowelCount;
				System.out.println(j+1 + ". sentence consonant count is " + df.format(percentage*100 - 100) + "% greater than vowel's");
			}
		}

		System.out.println("Total vowel count: " + vowelTotalCount);
		System.out.println("Total consonants count: " + consonantsTotalCount);
		System.out.println(vowelTotalCount < consonantsTotalCount ? 
			"Consonant count is " + df.format((double)consonantsTotalCount/(double)vowelTotalCount*100 - 100) + "% greater than vowel's" :
			"Vowel count is " + df.format((double)vowelTotalCount/(double)consonantsTotalCount*100 - 100) + "% greater than consonant's");
	}

}
