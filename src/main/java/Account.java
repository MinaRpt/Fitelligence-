import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Account {

    private String accountID;
    private String email;
    private String password;
    private String name;
    private List<UserProfiles> profiles;

    public Account(String email, String name, String password, List<UserProfiles> profiles) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.profiles = profiles;
        this.accountID = "name_" + UUID.randomUUID();         // Generate unique account ID using UUID
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
        return accountID + "," + email + "," + password + "," + name + "," +  profiles;
    }

    public void displayProfiles() {
        System.out.println(profiles);
    }
}
