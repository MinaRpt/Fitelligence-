import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Account {

    private String accountID;
    private String email;
    private String password;
    private String name;
    private ArrayList<UserProfiles> profiles = new ArrayList<>();


    public Account(String email,String password) {
        this.email = email;
        this.password = password;
        this.accountID = UUID.randomUUID().toString();         // Generate unique account ID using UUID then makes it as a string else it will give an error
    }

    public String getAccountID() {
        return accountID;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<UserProfiles> getProfiles() {
        return profiles;
    }

    public void addProfile(UserProfiles profile) {
        profiles.add(profile);

    }

    public void removeProfile(UserProfiles profile) {
                profiles.remove(profile);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(email, account.email);
    }


    @Override
    public String toString() {
        return accountID + "," + email + "," + password + "," + name + "," + profiles;
    }

    public void displayProfiles() {
        System.out.println(profiles);
    }
}
