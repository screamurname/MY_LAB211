class View {
    public int displayMenu(){
        System.out.println("======== Worker Management ========");
        System.out.println("1. Add worker.");
        System.out.println("2. Increase salary for worker.");
        System.out.println("3. Decrease salary for worker");
        System.out.println("4. Show adjusted salary worker information");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = Validate.checkInputIntLimit(1, 5);
        return choice;
    }
}