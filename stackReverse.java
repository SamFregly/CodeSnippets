//Author: Samuel Fregly for Clever Devices
import java.util.*;

public class stackReverse
{
	private ArrayList<String> input;

	public stackReverse() // constructor 
	{
		input = new ArrayList();
	}

	public boolean isEmpty() // check if empty
	{return input.isEmpty();}
	
	public String pop() //pop from stack
	{
		String last;
		last = input.remove((input.size()-1));
		return(last);
	}
	private void push(String x) // push to stack
	{input.add(x);}

	public void addSentence(String S)// push a sentence to a stack
	{	
		String[] str = S.split(" ");
		for(String word : str)
		{push(word);}
	}

	public void reverse() // automate popping from stack to revers
	{ 
		while(! isEmpty())
		{System.out.print(pop() + " ");}
		System.out.println(" ");
	}
	public static void main(String arg[])
	{
		stackReverse test1 = new stackReverse();
		test1.addSentence("Write a function that reverses the order of words in a sentence.");
		test1.reverse();	
			
	}

}
