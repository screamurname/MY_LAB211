
public class Controller {
    private Model model; 
    private View view;

    public Controller(Model model, View view) { // khoi tao Controller voi model va view
        this.model = model;
        this.view = view;
    }
    public void run(){ //dieu khien luong chinh cua chuong trinh
        int size = view.InputSize(1, 100);
        try {
            model.GenerateArray(size);
            view.DisplayArray(model.getArray(), "Unsorted array: ");
            model.BubbleSort(model.getArray());
            view.DisplayArray(model.getArray(), "Sortted array: ");
        } catch (IllegalArgumentException e) {
        view.DisplayError("Invalid input: " + e.getMessage());
    } catch (Exception e) {
        view.DisplayError("An unexpected error occurred: " + e.getMessage());
    } finally {
        view.close();
        
        }
    }
}//end class
