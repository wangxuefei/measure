package measure;

import java.util.ArrayList;
import java.util.LinkedList;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public class LinkedListVSArrayList {

	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		Builder<Integer> immutableList = ImmutableList.builder();

		// ArrayList add
		long startTime = System.nanoTime();

		for (int i = 0; i < 1000000; i++) {
			arrayList.add(i);
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println("ArrayList     add:  " + duration);

		// LinkedList add
		startTime = System.nanoTime();

		for (int i = 0; i < 1000000; i++) {
			linkedList.add(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("LinkedList    add: " + duration);

		// ImmutableList add
		startTime = System.nanoTime();

		for (int i = 0; i < 1000000; i++) {
			immutableList.add(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("ImmutableList add: " + duration);

		// ArrayList get
		startTime = System.nanoTime();

		for (int i : arrayList) {

		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("ArrayList     get:  " + duration);

		// LinkedList get
		startTime = System.nanoTime();

		for (int i : linkedList) {

		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("LinkedList    get: " + duration);

		// ImmutableList get
		startTime = System.nanoTime();

		for (int i : immutableList.build()) {

		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("ImmutableList get: " + duration);

	}

}
