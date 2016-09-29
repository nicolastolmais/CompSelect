import java.util.ArrayList;

public class FooLoop {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		String loop = "Hello";
		for(int i = 0; i <100; i++){
			list.add(loop += "Hello");
			if(i == 99){
				System.out.println(loop);
			}
		}
		

	}

}
