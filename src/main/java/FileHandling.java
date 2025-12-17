import java.io.*;
import java.util.ArrayList;

public class FileHandling {


//    public void saveAccounts(Account accounts) {
//        try (FileWriter writer = new FileWriter("accounts.txt")) {
//            writer.write(accounts.toString());
//            System.out.println("Accounts saved to file successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void addAccount(Account newAccount) {
        ArrayList<Account> accounts = loadAccounts(); // Load old list
        accounts.add(newAccount);                     // Add new account
        saveAccounts(accounts);                       // Save whole list back
    }

    public void editProfile(UserProfiles updatedProfile) {
        ArrayList<UserProfiles> profiles = loadProfiles();  // Load existing profiles

        boolean found = false;

        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getEmail().equals(updatedProfile.getEmail())) {
                profiles.set(i, updatedProfile);   // Replace old with updated
                found = true;
                break;
            }
        }

        if (found) {
            saveProfiles(profiles);   // Save updated list
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Profile not found, nothing updated.");
        }
    }


//    public void addProfile(UserProfiles newProfile) {
//        ArrayList<UserProfiles> profiles = loadProfiles();
//        profiles.add(newProfile);
//        saveProfiles(profiles);
//    }

    //    public void saveAccounts(ArrayList<Account> accounts) {
//        try (FileWriter writer = new FileWriter("accounts.txt")) {
//            for (Account acc : accounts) {
//                writer.write(acc.toString() + "\n");
//            }
//            System.out.println("Accounts saved to file successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
public void saveAccounts(ArrayList<Account> accounts) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.txt"))) {
        oos.writeObject(accounts);
        System.out.println("Accounts saved successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}   public ArrayList<Account> loadAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.txt"))) {
            accounts = (ArrayList<Account>) ois.readObject();
            System.out.println("Accounts loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No accounts file found, returning empty list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return accounts;
    }
    //    public void saveProfiles(UserProfiles profiles) {
//        try (FileWriter writer = new FileWriter("profiles.txt")) {
//            writer.write(profiles.toString());
//            System.out.println("Profiles saved to file successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//    public void saveProfiles(ArrayList<UserProfiles> profiles) {
//        try (FileWriter writer = new FileWriter("profiles.txt")) {
//            for (UserProfiles profile : profiles) {
//                writer.write(profile.toString() + "\n");
//            }
//            System.out.println("Profiles saved to file successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//
//
//        }
//    }

    public void saveProfiles(ArrayList<UserProfiles> profiles) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("profiles.txt"))) {
            oos.writeObject(profiles);
            System.out.println("Profiles saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<UserProfiles> loadProfiles() {
        ArrayList<UserProfiles> profiles = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("profiles.txt"))) {
            profiles = (ArrayList<UserProfiles>) ois.readObject();
            System.out.println("Profiles loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No profiles file found, returning empty list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return profiles;
    }
}
