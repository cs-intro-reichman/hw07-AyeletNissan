
public class SpellChecker {


	public static void main(String[] args) {
		/*String word = args[0];
		String word2 = args[1];

		System.out.println(levenshtein(word, word2));*/

		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}
	public static char head(String str) {
		return str.charAt(0);
	}

	public static int levenshtein(String word1, String word2) {
		word1=word1.toLowerCase();
		word2=word2.toLowerCase();
		if(word1.length()==0) return word2.length();
		if(word2.length()==0) return word1.length();

		if(head(word1)== head(word2)){
			return levenshtein(tail(word1), tail(word2));
		} 

		return 1 + Math.min(levenshtein(tail(word1), word2),Math.min(levenshtein(word1, tail(word2)), levenshtein(tail(word1), tail(word2))));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for(int i=0; i<dictionary.length; i++){
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minEdit = threshold;
		String minWord = word;

		for(int i=dictionary.length-1; i>=0; i--){
			int lev = levenshtein(word, dictionary[i]);
			if(lev<=minEdit){
				minEdit=lev;
				minWord=dictionary[i];
			}
		}
		return minWord;
	}

}
