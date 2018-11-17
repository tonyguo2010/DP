package com.seakie;

import java.util.ArrayList;

public class DPDemo {
	public void maxSubSum() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		// -2 1 -3 4 -1 2 1 -5 4
		list.add(-2);
		list.add(1);
		list.add(-3);
		list.add(4);
		list.add(-1);
		list.add(2);
		list.add(1);
		list.add(-5);
		list.add(4);
		System.out.println(list);
		
		int sum = 0;
		int max = 0;
		for (int index = 0; index < list.size(); index++) {
			// f(N) = f(N-1) + N;
			sum += list.get(index);
			// if sum is already a negtive property, f it! RENEW!
			if (sum < 0)
				sum = 0;
			else  // sum is still >= 0, it is fine to go on
			{
				// it is necessary to 
				if (sum > max) 
					max = sum;
			}
		}
		
		System.out.println(max);
	}

	public void stepChoice() {
		int steps = 10;
		ArrayList<Integer> choice = new ArrayList<Integer>();
		while(choice.size() < steps) choice.add(0);
		
		choice.set(0, 1);
		choice.set(1, 1);
		for (int index = 2; index < steps; index++) {
			int newChoice = 0;
			// since I can only use 1 or 2 steps only, so collect steps now
			newChoice += choice.get(index - 1);
			newChoice += choice.get(index - 2);
			
			// record the result
			choice.set(index, newChoice);
		}
		System.out.println(choice);
	}

	private char transRule(char code) {
		int diff = 'A' - 1;
		return (char) (code + diff);
	}
	public void transCode() {
		String orig = "1201345";
		ArrayList<Integer> record = new ArrayList<>();
		while (record.size() < orig.length()) record.add(0);
		
		// set the start point value
		record.set(0, 1);
		
		for (int index = 0; index < orig.length(); index++) {
			for (int end = index + 1; end <= Integer.min(index + 2, orig.length() - 1); end++) {
				int value = Integer.valueOf(orig.substring(index, end));
//				System.out.println(value + " " + orig.charAt(end));
				if ( (value <= 26) 
					&& (end < orig.length() && orig.charAt(end) != '0')
					&& (orig.charAt(index) != '0')
					) {
//					System.out.println(orig.substring(index, end));
					record.set(end, record.get(end) + record.get(index));
				}
			}
		}
//		System.out.println(record);
		System.out.println(record.get(orig.length() - 1));
	}
}
