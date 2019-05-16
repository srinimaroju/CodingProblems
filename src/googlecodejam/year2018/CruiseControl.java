package googlecodejam.year2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class CruiseControl {
	public static void main(String []args) throws FileNotFoundException {
		//Scanner in = new Scanner(System.in);

		Scanner in = new Scanner(new File(System.getProperty("user.dir") + "/src/resources/CruiseControlTest.txt"));

		int T = Integer.parseInt(in.next());
		//System.out.println("Number of test cases " + T);

		for(int i=0;i<T;i++) {
			int totalDistance = Integer.parseInt(in.next());
			int numberOfHorses = Integer.parseInt(in.next());
			int[] distances = new int[numberOfHorses];
			int[] speeds = new int[numberOfHorses];
			for(int j=0;j<numberOfHorses;j++) {
				distances[j] = Integer.parseInt(in.next());
				speeds[j] = Integer.parseInt(in.next());
			}
			calculateSpeed(totalDistance, distances, speeds, i+1);
		}
		in.close();
	}
	public static void calculateSpeed(int totalDistance, int[] distances, int[] speeds, int index) {
		//System.out.println("Horses: " + Arrays.toString(distances));
		//System.out.println("Speeds: " + Arrays.toString(speeds));

		StringBuffer output = new StringBuffer("");
		output.append("Case #"+index+": ");
		double maxTime = 0;
		for(int i=0;i<distances.length;i++) {
			double horseDestTime = (totalDistance-distances[i]) / speeds[i];
			if(maxTime < horseDestTime) {
				maxTime = horseDestTime;
			}
		}
		//System.out.println("Max Time: " + maxTime);
		//float maxSpeed = ((float)totalDistance) / maxTime;
		
		DecimalFormat df = new DecimalFormat("#.######");
		df.setRoundingMode(RoundingMode.CEILING);
		
		output.append(String.format("%2f",((float)totalDistance) / maxTime));
		
		System.out.println(output.toString());
	}
}
