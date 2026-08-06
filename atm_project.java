import java.util.*;
interface bankOperation
{
    void deposit(double amount);
    void withdraw(double amount);
    void checkBalance();
}
abstract class Account implements bankOperation {
    
    final int accountNumber;
   private String password;
   protected double balance;
    Account(int accountNumber,String password, double balance) 
    {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
        
    }
    int getAccountNumber()
    {
        return accountNumber;
    } 
    boolean passwordCheck(String password)
    {
        return password.equals(this.password);
    }
    void passwordChange(String password)
    {
        this.password = password;
    }
    public void checkBalance()
    {
        System.out.println(" Balance amount is: "+ balance);
    }
}
class savingsAccount extends Account 
{
    savingsAccount(int accountNumber,String password, double balance)
    {
    super(accountNumber, password, balance);
    }
    int limit = 10000;
   public  void deposit(double amount)
    {
        balance += amount;
        System.out.println("Succesfully deposited ");
    }
    public void withdraw(double amount)
    {
        if( amount > limit)
        { 
            System.out.println("Limit exceeded ");
            return ;
            
        }
        else if( amount <= balance)
        {
            balance -= amount;
        }
        else 
        {
            System.out.println("Insufficient balance ");
        }
    }
}
class currentAccount extends Account 
{
    currentAccount(int accountNumber,String password, double balance)
    {
    super(accountNumber, password, balance);
    }
    public void deposit(double amount)
    {
        balance += amount;
        System.out.println("Succesfully deposited ");
    }
   public void withdraw(double amount)
    {
        if( amount <= balance) 
        {
            balance -= amount;
        }
        else 
        {
            System.out.println("Insufficient balance ");
        }
    }
}
class Bank 
{
    static List<Account> accounts = new ArrayList<>();
    static Account findAccount(int accountNumber) {
        for(Account i : accounts){
            if(i.getAccountNumber()==accountNumber) return i; 
        }
        return null;
    }
    static int captcha(){
        return 1000 + new Random().nextInt(9000);
    }
}
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) 
        {
            System.out.println("Welcome to Banking System :)");
            System.out.println("1. Exsisting User");
            System.out.println("2. New User");
            System.out.println("3. Exit");
            int op = sc.nextInt();
            if(op == 3) break;
            else if( op == 2) 
            {
            System.out.println("Enter Account number");
            int accountNumber = sc.nextInt();
            if(Bank.findAccount(accountNumber)!= null)
            {
            System.out.println("Account number Already Exsist");
            }
            sc.nextLine();
            System.out.println("Create Password");
            String password = sc.nextLine();
            // System.out.println("Re Password");
            // String repassword = sc.nextLine();
            System.out.println("Enter the Intial Amount");
            double amount = sc.nextDouble(); 
            // Account acc = new Account(accountNumber, password,amount);
            System.out.println("1. current Account");
            System.out.println("2. Scaving Account");
            int t = sc.nextInt();
            if(t==2){
                Bank.accounts.add(new savingsAccount(accountNumber, password,amount));
            }
            else {
                Bank.accounts.add(new currentAccount(accountNumber, password,amount));
            }
           
    continue;
            
           }
           else if (op == 1)
           {
               System.out.println("Enter Account number");
                int accountNumber = sc.nextInt();
                if(Bank.findAccount(accountNumber)== null)
                {
                    System.out.println("No account existed"); continue;
                }
               Account acc = Bank.findAccount(accountNumber);
               sc.nextLine();
                    System.out.println("Enter password");
                    String password = sc.nextLine();
                    if(!acc.passwordCheck(password))
                    {
                       System.out.println("Incorrect Password");
                       continue;
                    }
                    int captcha = Bank.captcha();
                    System.out.println( " Enter the below captcha: " + captcha);
                    int recv = sc.nextInt();
                    if(recv!=captcha) {
                        System.out.println("Invalid captcha");
                        continue;
                    }
                    boolean f= true;
                    outer:while( f ==true)
                    {
                       System.out.println("Choose one option "); 
                       System.out.println("1. Deposit "); 
                       System.out.println("2. Withdraw "); 
                       System.out.println("3. Check Blance "); 
                       System.out.println("4. Account transfer ");
                       System.out.println("5. Exit");
                       int opr = sc.nextInt();
                       switch ( opr )
                       {
                           case 1 :
                            {
                               System.out.println("Enter amount");
                               double amount = sc.nextInt();
                               acc.deposit(amount);break;
                            }
                           case 2 :
                            {
                               System.out.println("Enter amount");
                               double amount = sc.nextInt();
                               acc.withdraw(amount);break;
                            }
                           case 3 : 
                            {
                               acc.checkBalance();break;
                            }
                           case 4 : 
                           {  System.out.println("Enter Transfer Account number");
                               int accno = sc.nextInt();
                          if(Bank.findAccount(accno)== null)
                           {
                         System.out.println("No account existed"); 
                         break;
                           }
                         Account tac = Bank.findAccount(accno);
                         System.out.println("Enter Transfer Amount");
                               int amount = sc.nextInt();
                         acc.withdraw(amount);
                         tac.deposit(amount);
                               
                           }
                           case 5 : { f= false;break;}
                        }
                    }
                    
                }
           }
        }
    }