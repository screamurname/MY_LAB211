import java.util.ArrayList;

class Controller {

    Controller(Model model, View view) {
    }
    void run() {
        ArrayList<Worker> lw = new ArrayList<>();
        ArrayList<History> lh = new ArrayList<>();
        View view = new View();
        
        //loop until user want to exit
        while (true) {
            int choice = view.displayMenu();
            switch (choice) {
                case 1:
                    Model.addWorker(lw);
                    break;
                case 2:
                    Model.changeSalary(lw, lh, 1);
                    break;
                case 3:
                    Model.changeSalary(lw, lh, 2);
                    break;
                case 4:
                    Model.printListHistory(lh);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
            }
            System.out.println(); // Empty line for readability
        }
    }
}