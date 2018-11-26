package com.seakie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
		String orig = "123421";
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

	public void binarySearchTree() {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		HashMap<Integer, Integer> dict = new HashMap<>();
		dict.put(0, 0);
		dict.put(1, 1);
		
		for (int index = 2; index <= N; index ++) {
			int key = index;
			int value = getValueByDict(dict, key);
			dict.put(key, value);
//			System.out.println("Get " + value);
		}
		
//		for (Integer nodes : dict.keySet()) {
//			System.out.println(dict.get(nodes));
//		}
		System.out.println(dict.get(N));
	}

	private int getValueByDict(HashMap<Integer, Integer> dict, int key) {
		int position = 0;
		int result = 0;
		int left = 0;
		int right = 0;
		
		for (position = 1; position <= key; position++) {
			result ++;
			left = dict.get(position - 1) - 1; // left side
			if (left < 0) 
				left = 0;
			right = dict.get(key - position) - 1; // the right side
			if (right < 0)
				right = 0;
			result += (left + right);
		}
		
		return result;
	}

	public void minPathInTriangle() {
		/* 
		[2],
		[3,4],
		[6,5,7],
		[4,1,8,3]
		 */
		ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> line = new ArrayList<Integer>();
		line.add(2);
		tree.add((ArrayList<Integer>) line.clone());
		line.clear();
		line.add(3); line.add(4);
		tree.add((ArrayList<Integer>) line.clone());
		line.clear();
		line.add(6); line.add(5); line.add(7);
		tree.add((ArrayList<Integer>) line.clone());
		line.clear();
		line.add(4); line.add(1);line.add(8); line.add(3);
		tree.add((ArrayList<Integer>) line.clone());

//		System.out.println(tree);

		for (int row = tree.size() - 2; row >= 0; row--) {
//			System.out.println(tree.get(row));
			ArrayList<Integer> current = tree.get(row);
			ArrayList<Integer> next = tree.get(row + 1);
			// reverse from the 2nd last line and upward
			for (int pos = 0; pos < current.size(); pos++) {
				int toAdd = 0;  // to pick the bigger one in the closed node
				if (next.get(pos) > next.get(pos + 1)){
					toAdd = next.get(pos);
				} else {
					toAdd = next.get(pos + 1);
				}
				current.set(pos, current.get(pos) + toAdd);
			}
		}
		
//		System.out.println(tree);
		System.out.println(tree.get(0).get(0));
	}

	public void findPali() {
		String orig = "abccbc";
		for (int end = 1; end <= orig.length(); end++) {
			for (int start = 0; start < end; start++) {
//				System.out.println(orig.substring(start, end));
				String work = orig.substring(start, end);
				if (isPali(work) == true) {
					System.out.println(work);
				}
			}
		}
	}

	private boolean isPali(String work) {
		for (int index = 0; index <= work.length() / 2; index++) {
			if (work.charAt(index) != work.charAt(work.length() - 1 - index)) {
				return false;
			}
		}
		return true;
	}

	private boolean stringExist(String work, ArrayList<String> dict) {
		for (String line : dict) {
			if (line.contains(work)){
				return true;
			}
		}
		return false;
	}
	public void stringInStrings() {
		ArrayList<Boolean> record = new ArrayList<Boolean>();
		String word = "able";
		while (record.size() <= word.length()) record.add(false);
		record.set(0, true);
		ArrayList<String> dict = new ArrayList<String>();
		dict.add("apple");
		dict.add("berry");
		dict.add("peach");
		
		for (int index = 0; index < word.length(); index++) {
			if (record.get(index) == false) {
				// if it is not a correct cut here, skip
				continue;
			}
			for (int end = index + 1; end <= word.length(); end++) {
				String cut = word.substring(index, end);
//				System.out.println(cut);
				if (stringExist(cut, dict) == true) {
					record.set(end, true);
				}
			}
		}
		
		// just check the final record
		if (record.get(word.length()) == true) {
			System.out.println("Good");
		} else {
			System.out.println("bad");
		}
	}

	public void stocking() {
		ArrayList<Integer> prices = new ArrayList<Integer>();
		prices.add(3);
		prices.add(1);
		prices.add(2);
		prices.add(3);
		prices.add(9);
		prices.add(8);
		int buy = 0;
		int sell = 0;
		int max = 0;
		for (int start = 0; start < prices.size(); start++) {
			for (int end = start + 1; end < prices.size(); end++) {
//				System.out.println(prices.get(start) + " " + prices.get(end));
				buy = prices.get(start);
				sell = prices.get(end);
				if (max < sell - buy) {
					max = sell - buy;
				}
			}
		}
		
		System.out.println(max);
	}

	public void stepChoiceGeneral() {
		int steps = 10;
		int width = 2;
		int protect = 5;
		
		ArrayList<Integer> choice = new ArrayList<Integer>();
		while(choice.size() < steps + protect) choice.add(0);
		choice.set(0, 1);
		for (int i = 0; i < steps; i++) {
//			for (int j = i + 1; j <= i + width; j++) {
//				choice.set( j, choice.get(i) + choice.get( j));
//			}
			choice.set(i + 1, choice.get(i) + choice.get(i + 1));
			choice.set(i + 2, choice.get(i) + choice.get(i + 2));
		}
		
		System.out.println(choice);
	}

	public void longestPali() {
		String str = "abcdcbaf";
		int start = 0, end = 0;
		String sub;
		String max = "";
		
		for (start = 0; start <= str.length(); start++) {
			for (end = start + 1; end <= str.length(); end++){
//				System.out.println(str.substring(start, end));
				sub = str.substring(start, end);
				if (isPali(sub)) {
					if (sub.length() > max.length()){
						max = sub;
					}
				}
			}
		}
		
		System.out.println(max);
	}

	public void splitedWord() {
		String base = "helloworldhello!";
		String word1 = "hello";
		String word2 = "world";
		
		ArrayList<Boolean> record = new ArrayList<Boolean>();
		while (record.size() < base.length() + 1) record.add(false);
		
		record.set(0, true);
		for (int start = 0; start <= base.length(); start++){
			for (int end = start + 1; end <= base.length(); end++) {
				if (record.get(start) == false)
					continue;
				String sub = base.substring(start, end);
				if ((sub.equals(word1) == true)
					|| (sub.equals(word2) == true) ){
					record.set(end, true);
				}
			}
		}
		
		System.out.println(record.get(base.length()));
	}
}

