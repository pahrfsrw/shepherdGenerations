package test;

public class Test {
	
	interface A {
		
		int f(int n, int m);
		
	}

	public static void main(String[] args){
		
		A a = (int n, int m) -> {return n + m;};
		
		System.out.println(a.f(10, 4));
		
	}
}
