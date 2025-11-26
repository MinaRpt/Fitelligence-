import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHandling {


    public void saveAccounts(Account accounts) {
        try (FileWriter writer = new FileWriter("accounts.txt")) {
            writer.write(accounts.toString());
            System.out.println("Accounts saved to file successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveProfiles(UserProfiles profiles) {
        try (FileWriter writer = new FileWriter("profiles.txt")) {
            writer.write(profiles.toString());
            System.out.println("Profiles saved to file successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readAccount(Account account) {
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
