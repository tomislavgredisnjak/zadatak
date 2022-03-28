package zadatak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

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
	
	private static void flipLetterOrder(String text) {
		
	}
	
	private static void flipWordOrder(String text) {
		String sentences[] = text.split("[?.!]+\s");

		
	}
	
	private static void flipSentenceOrder(String text) {
		//ovdje popraviti da uzima i tocke
		String sentences[] = text.split("[?.!]+\s");

		for(int i = sentences.length - 1; i != -1; i--) {
			//System.out.println(sentences[i]);
		}
	}
	
	private static void statistic(String text) {
		
	}

}
