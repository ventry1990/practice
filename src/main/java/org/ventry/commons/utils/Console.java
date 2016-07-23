package org.ventry.commons.utils;

public class Console {
	public static void writeLine(Object... objects) {
		for (Object object : objects) {
			System.out.println(object);
		}
	}

	public static void write(int num, int... objects) {
		int i = 1;
		for (int object : objects) {
			System.out.print(object + "\t");
			if (i++ % num == 0)
				System.out.println();
		}
		System.out.println();
	}
}
