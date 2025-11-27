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
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.ser"))) {
        oos.writeObject(accounts);
        System.out.println("Accounts saved successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}   public ArrayList<Account> loadAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.ser"))) {
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("profiles.ser"))) {
            oos.writeObject(profiles);
            System.out.println("Profiles saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<UserProfiles> loadProfiles() {
        ArrayList<UserProfiles> profiles = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("profiles.ser"))) {
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
