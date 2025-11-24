import java.util.List;
import java.util.Objects;

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
        this.accountID = "name_" + System.currentTimeMillis();         // current time mills dh by7sb the time since January 1 1970 which will help us make a unique ID for each user

    }

    public void addProfile(UserProfiles profile) {
        profiles.add(profile);

    }

    public void removeProfile(UserProfiles profile) {
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).equals(profile)) {
                profiles.remove(i);
            }
        }


    }


    public void displayProfiles() {
        for (int i = 0; i < profiles.size(); i++) {
            System.out.println(profiles.get(i).getName());
            System.out.println(profiles.get(i).getAge());
            System.out.println(profiles.get(i).getHeight());
            System.out.println(profiles.get(i).getGender());
            System.out.println(profiles.get(i).getWeight());
            System.out.println("------------------------------");
        }

    }
}
