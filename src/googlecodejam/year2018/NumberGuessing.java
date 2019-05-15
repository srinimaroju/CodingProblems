package googlecodejam.year2018;

import java.util.Scanner;

/**
 * https://codingcompetitions.withgoogle.com/codejam/round/0000000000000130/0000000000000523
 * @author Srinivas Maroju
 *
 */
public class NumberGuessing {
	public static String TOO_SMALL = "TOO_SMALL";
	public static String TOO_BIG = "TOO_BIG";
	public static String CORRECT = "CORRECT";
			
	public static void main(String []args) {
		Scanner in = new Scanner(System.in);
		int T = Integer.parseInt(in.next());
		//System.out.println("Number of test cases " + T);

		for(int i=0;i<T;i++) {
			int lower = Integer.parseInt(in.next());
			int upper = Integer.parseInt(in.next());
			int N = Integer.parseInt(in.next());
			guessIt(upper,lower,N, in);
		}
	}
	public static void guessIt(int upper, int lower, int N, Scanner in) {
		String input;
		for(int j=0;j<N;j++) {
			int avg = (upper+lower)/2;
			System.out.println(avg);
			input = in.next().trim();
			//System.out.println("Input is:" + input);
			if(input.equals(TOO_BIG)) {
				//System.out.println("Reducing upper");
				upper = avg-1;
			} else if(input.equals(TOO_SMALL))  {
				//System.out.println("Increasing lower");
				lower = avg+1;
			} else {
				return;
			}
			//System.out.println(String.format("Number of test cases upper: %s, lower:%s ", upper, lower));
		}
	}
}
