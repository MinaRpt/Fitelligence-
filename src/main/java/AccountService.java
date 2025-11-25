import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private List<Account> accounts = new ArrayList<>();


    public Account AccountRegisterCheck(Account account ){
       for( int i = 0 ; i< accounts.size() ; i++){
           if(accounts.get(i).equals(account))
               System.out.println("Account is already created");

       }
        Account newAccount = new Account(account.getEmail(), account.getName(), account.getPassword() , new ArrayList<>() );
        accounts.add(newAccount);
        return newAccount;
    }
}
