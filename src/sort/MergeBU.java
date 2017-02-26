package sort;

import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
	public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		for(int k = lo; k <= hi; k++)
			if	(i > mid)	a[k] = aux[j++];
			else if	(j > hi)	a[k] = aux[i++];
			else if	(less(aux[j], aux[i]))	a[k] = aux[j++];
			else	a[k] = aux[i++];
	}
	
	public static void sort(Comparable[] a){
		// 通过传参使每次排序有自己的aux[]数组
		Comparable[] aux = new Comparable[a.length];
		int N = a.length;
		aux = new Comparable[N];
		for(int sz = 1; sz < N; sz = sz+sz)
			for(int lo = 0; lo < N - sz; lo += sz + sz)
				merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
	}
	
	private static boolean less(Comparable a, Comparable b) 
	{ return a.compareTo(b) < 0; }
	
	private static void exch(Comparable[] a, int i, int j)
	{ Comparable t = a[i]; a[i] = a[j]; a[j] = t;}
	
	private static void show(Comparable[] a) { 
		for(int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}
	
	public static boolean isSorted(Comparable[] a) {
		for(int i = 1; i < a.length; i++) {
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String[] a = {"s","a","D","w","q","2","3","B","0","A","1","A","u","d","3","a","2"};
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
