package googlecodejam.year2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://codingcompetitions.withgoogle.com/codejam/round/0000000000000130/00000000000004c0
 * Can you construct an evacuation plan? The senate is counting on you!
 *
 *
 * @author Srinivas Maroju
 *
 */
public class SenateEvacuation {
	public static void main(String []args) throws FileNotFoundException {
		Scanner input = new Scanner(new File(System.getProperty("user.dir") + "/src/resources/SenateEvacuationTest.txt"));
		int T = input.nextInt();
		//System.out.println(String.format("Number of Test cases %s", T));
 
		//For All test cases
		for(int i=0;i<T;i++) {
			//Parse inputs
			int numberOfParties = input.nextInt();
			int[] parties = new int[numberOfParties];
			for(int j=0;j<numberOfParties;j++) {
				parties[j] = input.nextInt();
			}
			
			//Generate output
			findEvacuationPlan(numberOfParties, parties, i+1);
			//break;
		}
		//Cleanup
		input.close();
	}
	public static void findEvacuationPlan(int numberOfParties, int[] parties, int index) {
		//System.out.println("parties: " + numberOfParties + " , details:" + Arrays.toString(parties) );
		PriorityQueue<Party> pq = new PriorityQueue<Party>((p1,p2) -> new Integer(p2.value).compareTo(new Integer(p1.value)));
		for(int i=0;i<parties.length;i++) {
			pq.add(new Party(((char)(65+i)), parties[i]));
		}

		StringBuffer output = new StringBuffer("Case #" + index+": ");
		int count = 0;
		while(!pq.isEmpty()) {
			//System.out.println(pq);
			//System.out.println("In loop: " + count);
			//Greedy Approach
			int[] newParties = parties.clone();
			
			//Try to remove 2 from head
			Party head = pq.poll();
			if(newParties[head.index]>1) {
				newParties[head.index] = newParties[head.index]-2;
				if(!isAnyMajority(newParties)) {
					//System.out.println("Majority didn't fail...removing  2 from " + head.index);
					parties = newParties;
					output.append(head.name);
					output.append(head.name);
					head.value = head.value - 2;
					if(head.value > 0) {
						pq.add(head);
					}
				} else { 
					// Can't remove 2 due to failing majority
					// Remove 1 from both
					//System.out.println("Majority with 2 failed...trying 1");
					Party second = pq.poll();
					//System.out.println("Removing " + head.index + " , " + second.index);
					newParties[head.index] = parties[head.index]-1;
					newParties[second.index] = parties[second.index]-1;
					//System.out.println(Arrays.toString(newParties));
					output.append(head.name);
					output.append(second.name);
					head.value = head.value -1;
					second.value = second.value-1;
					if(head.value > 0) {
						pq.add(head);
					}
					if(second.value > 0) {
						pq.add(second);
					}
					parties = newParties;
				}
				
			} 
			// Max is 1
			else if(newParties[head.index]==1){
				newParties[head.index] = newParties[head.index]-1;
				if(!isAnyMajority(newParties)) {
					parties = newParties;
					output.append(head.name);
				} else {
					Party second = pq.poll();
					//System.out.println("Removing " + head.index + " , " + second.index);
					newParties[head.index] = parties[head.index]-1;
					newParties[second.index] = parties[second.index]-1;
					output.append(head.name);
					output.append(second.name);
					head.value = head.value -1;
					second.value = second.value-1;
					if(head.value > 0) {
						pq.add(head);
					}
					if(second.value > 0) {
						pq.add(second);
					}
					parties = newParties;
				}
			} else {
				//All Done
				//System.out.println("All done");
				return;
			}
			output.append(" ");
			//if(count++ > 3) break;
			count++;
			//System.out.println(Arrays.toString(parties));

		}
		System.out.println(output);
		
	}
	
	//Check if any party has majority
	public static boolean isAnyMajority(int[] parties) {
		float totalSum = 0;
		for(int i=0;i<parties.length;i++) {
			totalSum+= parties[i];
		}
		//System.out.println("Calculating result for  " + Arrays.toString(parties) + ": totalSum: " + totalSum);
		for(int i=0;i<parties.length;i++) {
			if((float)parties[i]>(totalSum/2)) {
				return true;
			}
		}
		return false;
	}
}

class Party {
	char name;
	int value;
	int index;
	public Party(char name, int value) {
		this.name = name;
		this.value = value;
		this.index = ((int)name) - 65;
	}
	@Override
	public String toString() {
		return name+":"+value + ":" + index;
	}
}