//Author: Samuel Fregly for Clever Devices

import java.util.*;
import javax.swing.*;
public class findPrimes
{

	int size;	
	public boolean[] primes; 
	int numprimes;
	
	public void Sieve(int n)
	{
		this.size = n;
		this.primes = new boolean[n];
		Arrays.fill(this.primes,true);
		this.primes[0] = this.primes[1] = false;
		for (int i =2; i< primes.length/2+1; i++)
		{  // this method changes all multiples of the current index to false. any multiple of an index is not prime
		  // Any index that when reached is false is skipped by the internal if statement. 
			if(primes[i])
			{
				for(int j =2; i*j<this.primes.length; j++)
				{  primes[i*j] = false;} 
			}
		}	
		countPrimes();	
	}
	
	public void printPrimes()
	{	// just to print all of the primes. each index that is still true represents an index
		
		for( int i =0; i<primes.length; i++)
		{
		 	if (primes[i])
			{System.out.print(String.valueOf(i) + ",");}
		}
	}

	public void countPrimes()
	{
		int total;
		for (int i = 0; i< size; i++)
			{if (primes[i] == true){numprimes +=1;}}
		// counts the number of true indexes after the sieve
	}

	public static void main(String arg[])
	{

		Scanner scanner = new Scanner(System.in);
		System.out.print("Input a number: ");
		int maxnum;
		maxnum = scanner.nextInt();
		
		findPrimes x = new findPrimes();
		x.Sieve(maxnum);
			
		System.out.println("There are " + x.numprimes +" primes between 1 and "+ maxnum); 
	// end of original question
	//Below is extra code to simply print out all the primes. 
		System.out.println(x.numprimes);
		System.out.println("Do you want me to list the primes? [y/n]");
		if (scanner.next().equals("y")) 
			{x.printPrimes();}
		else{System.out.println("Done");}
		
	 	
		
	}
}

