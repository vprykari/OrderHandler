
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model,view);
		view.setQueryListener(controller);
		
		
	}

}
