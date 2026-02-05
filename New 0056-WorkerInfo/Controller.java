import java.util.ArrayList;

class Controller {
    private Model model;
    private View view;
    private ArrayList<Worker> lw;  
    private ArrayList<History> lh; 
    
    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.lw = new ArrayList<>();
        this.lh = new ArrayList<>();
    }
    
    void run() {
        //loop until user want to exit
        while (true) {
            int choice = view.displayMenu();
            switch (choice) {
                case 1:
                    model.addWorker(lw);
                    break;
                case 2:
                    model.changeSalary(lw, lh, 1);
                    break;
                case 3:
                    model.changeSalary(lw, lh, 2);
                    break;
                case 4:
                    model.printListHistory(lh); 
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
            }
            System.out.println(); 
        }
    }
}