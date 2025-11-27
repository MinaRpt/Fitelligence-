import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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


    public void saveAccounts(ArrayList<Account> accounts) {
        try (FileWriter writer = new FileWriter("accounts.txt")) {
            for (Account acc : accounts) {
                writer.write(acc.toString() + "\n");
            }
            System.out.println("Accounts saved to file successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void saveProfiles(ArrayList<UserProfiles> profiles) {
        try (FileWriter writer = new FileWriter("profiles.txt")) {
            for (UserProfiles profile : profiles) {
                writer.write(profile.toString() + "\n");
            }
            System.out.println("Profiles saved to file successfully.");
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

        public ArrayList<UserProfiles> loadProfiles () {
            ArrayList<UserProfiles> profiles = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("profiles.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    String email = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    Gender gender = Gender.valueOf(parts[3]);
                    double height = Double.parseDouble(parts[4]);
                    double weight = Double.parseDouble(parts[5]);
                    HealthCondition healthCondition = HealthCondition.valueOf(parts[6]);
                    profiles.add(new UserProfiles(email, name, age, gender, height, weight, healthCondition));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return profiles;
        }
    }
