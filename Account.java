
package atm;

import java.util.ArrayList;


public class Account {
    // The name of the account
    private String name;
    // The account ID number
    private String uuid;
    // The User object that owns this account
    private User holder;
    // The list of transactions for this account
    private ArrayList<Transaction> transactions ;
    
    public Account (String name , User holder, Bank theBank){
        this.name = name ;
        this.holder = holder ;
        this.uuid = theBank.getNewAccountUUID ();
        this.transactions = new ArrayList<Transaction> ();
        
        
    }
  

    public String getUUID() {
        return uuid;
    }
    
    public String  getSummaryLine(){
       double balance = this.getBalance();
       
       if (balance >= 0) {
			return String.format("%s : %.02f VND: %s", this.uuid, balance, 
					this.name);
		} else {
			return String.format("%s : (%.02f) VND : %s", this.uuid, balance, 
					this.name);
		}


    }
    
    public double getBalance(){
        double balance = 0;
        for (Transaction t :this.transactions){
            balance += t.getMoney();
        }
        return balance ;
    }
    public void addTransaction(double amount, String memo) {
		
		// create new transaction and add it to our list
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
		
	}
    
    public void showTransactions(){ 
        for (Transaction t : transactions){
            t.transactionInfo();
        }
    }
}
