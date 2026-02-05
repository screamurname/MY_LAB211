import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

class Model {

    public void addWorker(ArrayList<Worker> lw) {
    // Loại bỏ while (true) ở đây để không bị kẹt trong việc thêm mới liên tục
    System.out.print("Enter code: ");
    String id = Validate.checkInputID();       
    
    if (Validate.checkIdExist(lw, id)) {
        System.err.println("Code(id) already exists.");
        return;
    }     
    
    System.out.print("Enter name: ");
    String name = Validate.checkInputName();
    System.out.print("Enter age: ");
    int age = Validate.checkInputIntLimit(18, 50);
    System.out.print("Enter salary: ");
    int salary = Validate.checkInputSalary();
    System.out.print("Enter work location: ");
    String workLocation = Validate.checkInputString("^[A-Za-z][A-Za-z ]*[A-Za-z]$", "Error Work Location!");
    
    if (!Validate.checkWorkerExist(lw, id, name, age, salary, workLocation)) {
        System.err.println("Duplicate worker information.");
    } else {
        lw.add(new Worker(id, name, age, workLocation, salary));
        System.err.println("Add success.");
    }
}


    public void changeSalary(ArrayList<Worker> lw, ArrayList<History> lh, int status) {
        if (lw.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.print("Enter code: ");
        String id = Validate.checkInputID();
        Worker worker = getWorkerByCode(lw, id);
        if (worker == null) {
            System.err.println("Not exist worker.");
        } else {
            int salaryCurrent = worker.getSalary();
            int salaryUpdate;
            if (status == 1) {
                System.out.print("Enter salary: ");
                while (true) {     
                    salaryUpdate = Validate.checkInputSalary();
                    if (salaryUpdate <= salaryCurrent) {
                        System.err.println("Must be greater than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                lh.add(new History("UP", getCurrentDate(), worker.getId(),
                        worker.getName(), worker.getAge(), salaryUpdate,
                        worker.getWorkLocation()));
            } else {
                System.out.print("Enter salary: ");
                while (true) {
                    salaryUpdate = Validate.checkInputSalary();
                    if (salaryUpdate >= salaryCurrent) {
                        System.err.println("Must be smaller than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                lh.add(new History("DOWN", getCurrentDate(), worker.getId(),
                        worker.getName(), worker.getAge(), salaryUpdate,
                        worker.getWorkLocation()));
            }
            worker.setSalary(salaryUpdate);
            System.err.println("Update success");
        }
    }

   
    public void printListHistory(ArrayList<History> lh) {
        if (lh.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.printf("%-5s%-15s%-5s%-10s%-10s%-20s\n", "Code", "Name", "Age",
                "Salary", "Status", "Date");
        Collections.sort(lh);
        for (History history : lh) {
            printHistory(history);
        }
    }

    // Thêm 'static' nếu muốn, hoặc xóa cũng được
    public Worker getWorkerByCode(ArrayList<Worker> lw, String id) {
        for (Worker worker : lw) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return worker;
            }
        }
        return null;
    }

    // CÓ THỂ GIỮ 'static' vì không phụ thuộc vào instance
    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    // CÓ THỂ GIỮ 'static'
    public static void printHistory(History history) {
        System.out.printf("%-5s%-15s%-5d%-10d%-10s%-20s\n", history.getId(),
                history.getName(), history.getAge(), history.getSalary(),
                history.getStatus(), history.getDate());
    }
}