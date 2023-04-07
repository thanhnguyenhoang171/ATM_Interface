
package atm;

import java.security.MessageDigest;  // Thư viện dùng để tính toán các giá trị băm (hash) của các dữ liệu
import java.util.ArrayList; // Thư viện dùng để tạo mảng

public class User {
    // The first name of the user
    private String firstName;
    // the last name of the user
    private String lastName;
    // the ID number of the user
    private String uuid;
    // The MD5 hash of the user's pin number
    private byte pinHash[];
    // The list of account for this user
    private ArrayList<Account> accounts ;
   
    // Phương thức khởi tạo cho đối tượng (User)
    public User(String firstName, String lastName, String pin, Bank theBank) {
        // Gán firstName và lastName cho đối tượng User
        this.firstName = firstName ;
        this.lastName = lastName ;
        // Mã hóa mật khẩu (pin) bằng thuật toán MD5 để lưu trữ mật khẩu dưới dạng hash
            try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // MessageDigest lấy đối tượng mã hóa cho thuật toán MD5
                this.pinHash = md.digest(pin.getBytes()); // phương thức md.digest(pin.getBytes()) để mã hóa mật khẩu (biến đổi các byte của mật khẩu thành chuỗi hash)

            } catch (Exception e) { // Nếu việc mã hóa không thành công 
                System.out.println("error , caught exception:"+ e.getMessage()); // Hiển thị báo lỗi
                System.exit(1);
            }
            this.uuid = theBank.getNewUserUUID(); //  Phương thức này sẽ trả về một chuỗi UUID mới cho đối tượng User, và giá trị này sẽ được gán cho thuộc tính uuid để xác định đối tượng User.
            this.accounts = new ArrayList <Account> (); // Lưu trữ danh sách các account
            System.out.printf("New user %s, %s with ID %s created.\n", 
                    lastName, firstName, this.uuid); // Ouput hiển thị New user (lastName + firstName) và ID đã tạo (uuid)
    }
    // Hàm kiểm tra mã Pin 
    public boolean validatePin(String apin){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // Khai báo và lấy đối tượng mã hóa
            return MessageDigest.isEqual(md.digest(apin.getBytes()), this.pinHash); // Kiểm tra mã Pin nhập vào, trả vể true nếu đúng mã Pin
        } catch (Exception e) { 
            System.out.println("error , no such algorithm found"); // Output khi sai mã PIN
            System.exit(1);
        }
        
        return false ;

    }
    // Hàm lấy UUUD
    public String getUUID() {
            return this.uuid ; // Lấy giá trị UUID

    }
    // Hàm thêm Account 
    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);   // Thêm một account mới
    }
    // Hàm lấy FirstName
    public String getFirstName() {
        return this.firstName; // Lấy FirstNamme
    }
    // Hàm xuất ra Tổng tài khoản của khác hàng
    public void printAccountsSummary() {
        System.out.printf("\n\n%s's accounts summary\n", this.firstName); // Output: firstName 's accounts sumary

        for (int a = 0; a < this.accounts.size(); a++) {
            String line = this.accounts.get(a).getSummaryLine();// Lấy thông tin từ Account
            System.out.println(line); // In ra. Output: uuid : số tiền VND : Balance\Savings
        }
    }
    // Hàm đến số lượng Account hiện có
    public int numAccounts() {
        return this.accounts.size(); // trả về kích thước của mảng Accounts
    }
    // Hàm lấy Account balance
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance(); // nhận vào một tham số acctIdx kiểu int là chỉ số của tài khoản trong danh sách this.accounts và trả về số dư của tài khoản đó.
    }
    // Hàm lấy Account UUID
    public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID(); // nhận vào một tham số acctIdx kiểu int là chỉ số của tài khoản trong danh sách this.accounts và trả về mã định danh duy nhất của tài khoản đó.
	}
    // Hàm thêm Account Transaction
    public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo); // này nhận vào ba tham số: acctIdx kiểu int là chỉ số của tài khoản trong danh sách this.accounts, amount kiểu double là số tiền của giao dịch và memo kiểu String là mô tả cho giao dịch đó.
	}
    // Hàm in ra Account Transaction History
    public void printAcctTransHistory(int acc){
        this.accounts.get(acc).showTransactions(); // Lịch sử giao dịch. Output: uuid + amout + memo
        
    }
}