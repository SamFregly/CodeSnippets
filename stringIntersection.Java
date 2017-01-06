//AUthor: Samuel Fregly for Clever Devices
import java.util.*;


public class stringIntersection
{

	public static String match(String w1, String w2)
	{
		String output = new String();
		w1 = w1.toLowerCase(); w2 = w2.toLowerCase(); 
			// convert everything to lowercase
		HashSet<Character> word1hash = new HashSet<Character>();
		HashSet<Character> word2hash = new HashSet<Character>();
		// make them into sets in order to use retainALL method
		for(int i = 0; i < w1.length(); i++)
		{
			word1hash.add(w1.charAt(i));
		}// send first word to hashset
		for(int i = 0; i < w2.length(); i++)
		{
			word2hash.add(w2.charAt(i));
		} // add second word to hashset
		word1hash.retainAll(word2hash);
		for(Character temp : word1hash)
		{
			output += temp.toString(); // consturcts output string
		}
		return output;
	}

	public static String matchSort(String word3)
	{
		char[] c = word3.toCharArray();
		Arrays.sort(c);
		String sortedString = new String(c);
		return sortedString; // not necessary to sort but nice to see
	}
	public static void main(String args[])
	{
		String word1 = "alphabet";
		String word2 = "flabbergasted";
		
		stringIntersection test1 = new stringIntersection();
		String result = test1.match(word1, word2);
	 	String resultFinal = test1.matchSort(result);
		System.out.println("The intersection of " + word1+ " and " + word2 + " is: " + resultFinal);
	}	

}
