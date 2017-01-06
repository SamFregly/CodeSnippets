// Author: Samuel Fregly for Clever Devices
public class stringReverse
{	

	public String arrayReverse(String x)
	{	
		boolean col = false;
		boolean das = false; // bools to test if i need to undo the spacing at the end
		if (x.contains(":")) {x = x.replaceAll(":"," : ");col=true;}
		if (x.contains("-")) {x = x.replaceAll("-"," - ");das=true;}
		// correct for rare cases where words don't have a space.
		String[] str = x.split(" ");
		StringBuilder result = new StringBuilder(str.length+1); // initialize it to proper size with spaces accounted for
		String last = str[str.length-1];
		int lenlast = last.length();
		String punctuation = "!.?"; 				// holder string to account for all punctuation at the end of as setence

		if (punctuation.indexOf(last.charAt(last.length() -1))>=0);
			{
				String newlast = last.charAt(lenlast-1) + last.substring(0,lenlast-1); // moves the punctuation to begining of the word  rather than end. The result looks better when the sentence starts with a period. 
				str[str.length-1] = newlast;
			}	
				 
		for(int i = str.length; i>0; i--)
			{
				result.append(str[i-1]);
				result.append(" "); // add everything to strinbuilder object
			}
		String newresult = result.toString();
		if(col==true){newresult = newresult.replaceAll(" - ", "-");}
		if(das==true){newresult = newresult.replaceAll(" : ", " :");}	
	// uneccessary in most instances 
		return newresult;
	}



	public static void main(String arg[])
	{
		
		stringReverse test1 = new stringReverse();
		
		String q1 = new String("Write a function that reverses the order of words in a sentence.");
		String answer1 = test1.arrayReverse(q1);

		System.out.println(answer1);
		
	}
}
