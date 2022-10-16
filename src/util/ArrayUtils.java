package util;

public class ArrayUtils {
	public static boolean includes(int[] array, int n) {
		for(int i:array) {
			if(i == n) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean includes(String[] array, String s) {
		for(String i:array) {
			if(s.equals(i)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void printArray(Object[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.println((i+1)+"º"+array[i]);
		}
	}
}
