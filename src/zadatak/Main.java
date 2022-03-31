package zadatak;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Main {
	private static List<Character> signs = new ArrayList<Character>() {
	private static final long serialVersionUID = 1L;
		{
			add(' ');
			add(',');
			add('.');
			add('?');
			add('!');
		}
	};
	private static final String text = new String("Sed ut perspiciatis unde omnis iste natus error sit voluptatem "
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
	
	public static void main(String[] args) {
		new Thread(() -> changeLetterOrder(text)).start();
		new Thread(() -> flipLetterOrder(text)).start();
		new Thread(() -> flipWordOrder(text)).start();
		new Thread(() -> flipSentenceOrder(text)).start();
		new Thread(() -> statisticVowels(text)).start();
	}

	private static void changeLetterOrder(String text) {
		String result = "";
		String words[] = text.split("\\s+");

		for(String word : words) {
			Character sign = null;
			if(signs.contains(word.charAt(word.length()-1))) {
				sign = word.charAt(word.length()-1);
				word = word.substring(0, word.length() - 1);
			}
			List<Character> middleLettersList = new ArrayList<>();
			String middleLetterOrg = word.substring(1, word.length()-1);
			for (int i = 0; i < middleLetterOrg.length(); i++) {
				middleLettersList.add((Character) middleLetterOrg.charAt(i));
			}
			Collections.shuffle(middleLettersList);
			String middleLettersShuffled = "";
			for (Character middleLetter : middleLettersList) {
				middleLettersShuffled += middleLetter;
			}
			word = String.valueOf(word.charAt(0)) + middleLettersShuffled + String.valueOf(word.charAt(word.length()-1)) + (sign != null ? String.valueOf(sign) : "");
			result += word + " ";
		}

		System.out.println(result);
		spacer();
	}
	
	private static void flipLetterOrder(String text) {
		String result = "";
		List<String> sentences = getSentences(text);

		for(String sentence : sentences) {
			String words[] = sentence.split("\\s+");
			for(int i = 0; i < words.length; i++) {
				Character sign = null;
				for (int j = words[i].length() - 1; j != -1; j--) {
					if (i == 0 && j == words[i].length() - 1) {
						result += words[i].toUpperCase().charAt(j);
					} else {
						if (signs.contains(words[i].charAt(j))) {
							sign = words[i].charAt(j);
						} else {
							result += words[i].toLowerCase().charAt(j);
						}
					}
				}
				if(sign != null) {
					result += sign;
				}
				result += " ";
			}
		}
		
		System.out.println(result);
		spacer();
	}
	
	private static void flipWordOrder(String text) {
		String result = "";
		List<String> sentences = getSentences(text);

		for(String sentence : sentences) {
			String sign = null;
			String words[] = sentence.split("\\s+");
			for(int i = words.length - 1; i != -1; i--) {
				if (i == words.length - 1) {
					result += (words[i].substring(0, 1).toUpperCase() + words[i].substring(1, words[i].length() - 1) + " ");
					sign = words[i].substring(words[i].length() - 1);
				} else {
					result += (i == 0 ? words[i].toLowerCase() + "" : words[i].toLowerCase() + " ");
				}
			}
			result += (sign + " ");
		}

		System.out.println(result);
		spacer();
	}
	
	private static void flipSentenceOrder(String text) {
		String result = "";
		List<String> sentences = getSentences(text);

		for(int i = sentences.size() - 1; i != -1; i--) {
			result += sentences.get(i);
			if(sentences.get(i).charAt(sentences.get(i).length()-1) != ' ') {
				result += " ";
			}
		}

		System.out.println(result);
		spacer();
	}
	
	private static void statisticVowels(String text) {
		String result = "";
		List<String> sentences = getSentences(text);
		Map<Character, Integer> totalVowels = new HashMap<Character, Integer>() {
		private static final long serialVersionUID = 1L;
			{
				put('a', 0);
				put('e', 0);
				put('i', 0);
				put('o', 0);
				put('u', 0);
			}
		};

		for(int i = 0; i < sentences.size(); i++) {
			Map<Character, Integer> sentenceVowels = new HashMap<>();
			for (int j = 0; j < sentences.get(i).length(); j++) {
				if(totalVowels.containsKey(sentences.get(i).toLowerCase().charAt(j))){
					if(sentenceVowels.containsKey(sentences.get(i).toLowerCase().charAt(j))) {
						sentenceVowels.put(sentences.get(i).toLowerCase().charAt(j), sentenceVowels.get(sentences.get(i).toLowerCase().charAt(j)) + 1);
					} else {
						sentenceVowels.put(sentences.get(i).toLowerCase().charAt(j), 1);
					}
					totalVowels.put(sentences.get(i).toLowerCase().charAt(j), totalVowels.get(sentences.get(i).toLowerCase().charAt(j)) + 1);
		        }
			}
			result += (i+1 + ". rečenica:\n");
			int count = 0;
			for (Map.Entry<Character, Integer> vowel : sentenceVowels.entrySet()) {
			    count++;
			    result += (vowel.getKey() + ": " + vowel.getValue() + (count == sentenceVowels.size() ? "\n" : ", ")); 
			}
		}

		int count = 0;
		result += "Sveukuno:\n";

		for (Map.Entry<Character, Integer> vowel : totalVowels.entrySet()) {
		    count++;
		    result += (vowel.getKey() + ": " + vowel.getValue() + (count == totalVowels.size() ? "\n" : ", ")); 
		}

		System.out.println(result);
	}

	private static List<String> getSentences(String text) {
		List<String> sentences = new ArrayList<>();
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		iterator.setText(text);
		int start = iterator.first();

		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			sentences.add(text.substring(start,end));
		}

		return sentences;
	}
	
	private static void spacer() {
		System.out.println();
		System.out.println();
	}
}
