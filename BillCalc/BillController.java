/**
 * Controller điều phối giữa Model và View - Sử dụng các hàm gốc
 */
public class BillController {
    private final BillModel model;
    private final BillView view;
    
    /**
     * Constructor
     */
    public BillController(BillModel model, BillView view) {
        this.model = model;
        this.view = view;
    }
    
    /**
     * Xử lý toàn bộ quy trình - GIỮ NGUYÊN LOGIC TỪ MAIN() GỐC
     */
    public void process() {
        try {
            // allow user input bill - GIỮ NGUYÊN TỪ CODE GỐC
            int[] bills = view.inputBills();
            model.setBills(bills);
            
            // allow user input amount of wallet - GIỮ NGUYÊN TỪ CODE GỐC
            int wallet = view.inputWallet();
            model.setWallet(wallet);
            
            // print total of bill and result - GIỮ NGUYÊN TỪ CODE GỐC
            int total = model.calcTotal();
            boolean canPay = model.payMoney(total, wallet);
            view.printTotalAndResult(total, canPay);
            
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Chạy chương trình
     */
    public void run() {
        try {
            process();
        } finally {
            view.close();
        }
    }
}