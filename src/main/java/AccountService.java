import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private ArrayList<Account> accounts = new ArrayList<>();

    public boolean SignUpCheck(Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getEmail().equals(account.getEmail())) {
                System.out.println("Account with email " + account.getEmail() + " already exists.");
                return false; // means account already exists

            }
        }

            Account newAccount = new Account(account.getEmail(), account.getPassword());
            accounts.add(newAccount);
        System.out.println("Account created successfully: " + newAccount);
            return true;      // account didn't exist and it is created now

        }


    public boolean SignInCheck(Account account) {

             for(int i = 0 ; i<accounts.size(); i++) {
                 if(accounts.get(i).getEmail().equals(account.getEmail()) && accounts.get(i).getPassword().equals(account.getPassword())){
                     return true; // account existed
                 }
             }
                         return false; // account didn't exist

                 }
             }








