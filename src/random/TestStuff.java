package random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestStuff {
	public static void main(String[] args) {
		try {
			work();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void work() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Map dimension: ");
		int dim = Integer.parseInt(br.readLine());
		for(int count = 0;count<dim;count++){
			for(int count2 = 0;count2<dim;count2++){
				System.out.print("1");
				if(count2!=dim-1){
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}
}
