import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private List<Account> accounts;


    public Account AccountRegisterCheck(Account account ){
       for( int i = 0 ; i< accounts.size() ; i++){
           if(accounts.get(i).equals(account))
               throw new IllegalArgumentException("Email already registered");

       }
        Account newAccount = new Account(email, name, password, new ArrayList<>());
        accounts.add(newAccount);
        return newAccount;
    }
}
