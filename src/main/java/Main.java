import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.util.Duration;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static javafx.geometry.Pos.BOTTOM_CENTER;
import static javafx.geometry.Pos.TOP_CENTER;

public class Main extends Application {  // extends application gives us the functionality of the Application!

    String email;
    String password;

    UserProfiles currentUser = new UserProfiles("", "", 0, Gender.MALE, 0, 0, ConditionHealth.NONE, FitnessGoal.MAINTAIN_WEIGHT);
    ArrayList<Account> accountList = new ArrayList<>();
    ArrayList<UserProfiles> profileList = new ArrayList<>();
    FileHandling fileHandler = new FileHandling();

    AccountService accountService;
    FoodTracker foodTracker = new FoodTracker();
    ExerciseTracker exerciseTracker = new ExerciseTracker();

    Text SignUpAndIn = new Text(); // just the button verification text

    public static void main(String[] args) {
        launch(args); // launch method from application class which sets up everything we need for JavaFX
    }

    @Override
    public void start(Stage stage) throws Exception {

        // --- SAFE INITIALIZATION ---
        ArrayList<Account> loadedAccounts = fileHandler.loadAccounts();
        if (loadedAccounts == null) loadedAccounts = new ArrayList<>();
        accountService = new AccountService(loadedAccounts);


        Image icon = new Image(getClass().getResourceAsStream("/logo.jpeg"));
        stage.getIcons().add(icon);

        ArrayList<UserProfiles> loadedProfiles = fileHandler.loadProfiles();
        if (loadedProfiles == null) loadedProfiles = new ArrayList<>();
        profileList = loadedProfiles;

        // --- PRIMARY SCENES ---
        StackPane root = new StackPane(); // That thing helps us to center stuff in the window :D
        Scene scene = new Scene(root, 835, 400); // create a scene object // width and height of the window

        StackPane root1 = new StackPane(); // Scene for new user signup / creating profile
        Scene scene1 = new Scene(root1, 1000, 700);

        StackPane root2 = new StackPane(); // Scene for new user signed in MAIN APP!!
        Scene scene2 = new Scene(root2, 1400, 700);

        StackPane root3 = new StackPane(); // Another main app scene
        Scene scene3 = new Scene(root3, 1400, 700);

        //* Scene for new user who signed up using root1 *//
        Text WelcomingText = new Text();
        WelcomingText.setText("Welcome to Fitelligence! Please enter your details below:");
        WelcomingText.setFont(Font.font("Verdana", 30));
        StackPane.setAlignment(WelcomingText, TOP_CENTER);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your Name ex. Minamanmon");
        nameField.setMaxWidth(400);
        nameField.setMaxHeight(40);
        nameField.setTranslateY(-250);

        Text namePlace = new Text();
        namePlace.setText("Name");
        namePlace.setFont(Font.font("Verdana", 20));
        namePlace.setTranslateX(-250);
        namePlace.setTranslateY(-250);

        TextField age = new TextField();
        age.setPromptText("Enter your Age ex. 20");
        age.setMaxWidth(400);
        age.setMaxHeight(40);
        age.setTranslateY(-200);

        Text agePlace = new Text();
        agePlace.setText("age");
        agePlace.setFont(Font.font("Verdana", 20));
        agePlace.setTranslateX(-250);
        agePlace.setTranslateY(-200);

        TextField heightField = new TextField();
        heightField.setPromptText("Enter your Height ex. 170cm");
        heightField.setMaxWidth(400);
        heightField.setMaxHeight(40);
        heightField.setTranslateY(-150);

        Text heightPlace = new Text();
        heightPlace.setText("Height");
        heightPlace.setFont(Font.font("Verdana", 20));
        heightPlace.setTranslateX(-250);
        heightPlace.setTranslateY(-150);


        TextField weightField = new TextField();
        weightField.setPromptText("Enter your Weight ex. 99kg");
        weightField.setMaxWidth(400);
        weightField.setMaxHeight(40);
        weightField.setTranslateY(-100);

        Text weightPlace = new Text();
        weightPlace.setText("Weight");
        weightPlace.setFont(Font.font("Verdana", 20));
        weightPlace.setTranslateX(-250);
        weightPlace.setTranslateY(-100);

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.setPromptText("Choose Gender");
        genderBox.getItems().addAll("MALE", "FEMALE");
        genderBox.setTranslateY(-50);

        Text genderText = new Text();
        genderText.setText("Gender");
        genderText.setFont(Font.font("Verdana", 20));
        genderText.setTranslateX(-250);

        ComboBox<String> Healthconditionbox = new ComboBox<>();
        Healthconditionbox.setPromptText("Choose Health Condition");
        Healthconditionbox.getItems().addAll("NONE" , "DIABETES" , "ConditionLactoseTolerance" , "ConditionColon" , "ConditionGlutenTolerance");
        Healthconditionbox.setTranslateY(0);

        ComboBox<String> goalBox = new ComboBox<>();
        goalBox.setPromptText("Choose Your Goal!");
        goalBox.getItems().addAll("WEIGHT_LOSS", "MUSCLE_GAIN", "MAINTAIN_WEIGHT");
        goalBox.setTranslateY(50);

        Button SubmitButton = new Button("Submit");
        SubmitButton.setFont(Font.font("Verdana", 20));
        SubmitButton.setTranslateY(100);
        SubmitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override

            /* Name hehight weight things to not break the application validations >:c */
            public void handle(ActionEvent actionEvent) {
                if (!nameField.getText().matches("[A-Za-z]+") || nameField.getText().length() > 7 ||
                        !age.getText().matches("\\d+") ||
                        !weightField.getText().matches("\\d+(\\.\\d+)?") ||
                        !heightField.getText().matches("\\d+(\\.\\d+)?")) {

                    showError("Invalid input. Please check:\n" +
                            "- Name: letters only, max 7 chars\n" +
                            "- Age: numbers only\n" +
                            "- Weight: numbers only\n" +
                            "- Height: numbers only");
                    return;
                }

                String name = nameField.getText();

                
                int userAge = Integer.parseInt(age.getText());

                double weight = Double.parseDouble(weightField.getText());

                double height = Double.parseDouble(heightField.getText());


                if (userAge < 1 || userAge > 100 ||
                        height < 50 || height > 250 ||
                        weight < 10 || weight > 400) {

                    showError("Please enter realistic values:\n\nAge: 1–100\nHeight: 50–250 cm\nWeight: 10–400 kg" );
                    return;
                }
                else {
                    Gender selected = Gender.valueOf(genderBox.getValue().toString());
                    ConditionHealth conditionHealthSelected = ConditionHealth.valueOf(Healthconditionbox.getValue().toString());
                    FitnessGoal ChosenGoal = FitnessGoal.valueOf(goalBox.getValue().toString());

                    currentUser.setName(name);
                    currentUser.setAge(userAge);
                    currentUser.setWeight(weight);
                    currentUser.setHeight(height);
                    currentUser.setGender(selected);
                    currentUser.setHealthCondition(conditionHealthSelected);
                    currentUser.setEmail(email);
                    currentUser.setFoodTracker(foodTracker);
                    currentUser.setExerciseTracker(exerciseTracker);
                    currentUser.setFitnessGoal(ChosenGoal);

                    profileList.add(currentUser);
                    fileHandler.saveProfiles(profileList);

                    // Open the main app scene after signup
                    PageHOME home = new PageHOME(currentUser, stage);
                }
            }
        });

        root1.getChildren().addAll(namePlace , weightPlace , heightPlace ,   agePlace , nameField, age, WelcomingText, heightField, weightField, SubmitButton, genderBox, goalBox, Healthconditionbox);

        // --- LOGIN SCENE ---
        Text text = new Text();
        text.setText("Welcome to Fitelligence!");
        text.setFont(Font.font("Verdana", 50));
        StackPane.setAlignment(text, TOP_CENTER);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your Email ex.Minamanmon5@gmail.com");
        emailField.setMaxWidth(400);
        emailField.setMaxHeight(40);
        emailField.setTranslateY(-50);

        TextField Password = new TextField();
        Password.setPromptText("Enter your password ex. Minamanmon123");
        Password.setMaxWidth(400);
        Password.setMaxHeight(40);
        Password.setTranslateY(-100);

        Button SignUpButton = new Button("SignUp");
        SignUpButton.setFont(Font.font("Verdana", 20));
        SignUpButton.setTranslateX(-100);
        SignUpButton.setTranslateY(100);
        SignUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                email = emailField.getText();
                EmailValidator validator = EmailValidator.getInstance();
//                if (!validator.isValid(email)) {}     This also can be used but I prefer the one that you did
                if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                    Text Error = new Text("Invalid Email Address!");
                    Error.setFont(Font.font("Verdana", 20));
                    Error.setTranslateY(5);
                    root.getChildren().add(Error);
                } else {

                    password = Password.getText();
                    Account account = new Account(email, password);
                    boolean registeredAccount = accountService.SignUpCheck(account);
                    if (registeredAccount) {
                        SignUpAndIn.setText("Signed Up Successfully!! Please SignIn");
                        SignUpAndIn.setFont(Font.font("Verdana", 20));
                        StackPane.setAlignment(SignUpAndIn, BOTTOM_CENTER);
                        stage.setScene(scene1);
                        accountList.add(account);
                        fileHandler.addAccount(account);
                    }
                }
            }
        });

        Button SignInButton = new Button("SignIn");
        SignInButton.setFont(Font.font("Verdana", 20));
        SignInButton.setTranslateX(100);
        SignInButton.setTranslateY(100);
        SignInButton.setOnAction(event -> {
            email = emailField.getText();
            password = Password.getText();

            Account account = new Account(email, password);
            boolean registeredAccount = accountService.SignInCheck(account);
            System.out.println(registeredAccount);
            if (registeredAccount) {
                SignUpAndIn.setText("Signed In Successfully!! Welcome Back");
                SignUpAndIn.setFont(Font.font("Verdana", 20));
                StackPane.setAlignment(SignUpAndIn, BOTTOM_CENTER);
                fileHandler.loadAccounts();
                fileHandler.loadProfiles();
                currentUser = findProfileByEmail(email); // safely retrieve user profile

                PageHOME home = new PageHOME(currentUser, stage);
            } else {
                SignUpAndIn.setText("Account doesnn't exists please Signup!!");
                SignUpAndIn.setFont(Font.font("Verdana", 20));
                StackPane.setAlignment(SignUpAndIn, BOTTOM_CENTER);
            }
        });

        root.getChildren().addAll(SignInButton, SignUpButton, text, emailField, Password, SignUpAndIn);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); // show the window
    }

    private UserProfiles findProfileByEmail(String email) {
        if (profileList == null) return null; // extra safety
        for (UserProfiles profile : profileList) {
            if (profile.getEmail().equals(email)) return profile;
        }
        return null;
    }

    private String updateFoodDisplay(FoodTracker tracker) {
        return "Food Tracker:\n" +
                "Total Calories: " + String.format("%.1f", tracker.CalculateTotalCalories()) +
                "\nProtein: " + String.format("%.1f", tracker.calculatTotalprotein()) +
                "\nFat: " + String.format("%.1f", tracker.calculatTotalfat()) +
                "\nCarbs: " + String.format("%.1f", tracker.calculatTotalcarb());
    }

    private String updateExerciseDisplay(ExerciseTracker tracker) {
        return "Exercise Tracker:\n" +
                "Total Calories Burned: " + tracker.getTotalCaloriesBurned() +
                "\nTotal Duration (minutes): " + tracker.getTotalDurationMinutes();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void showSuccess(String message, StackPane root) {
        Text successText = new Text(message);
        successText.setFont(Font.font("Verdana", 15));
        successText.setFill(javafx.scene.paint.Color.GREEN);
        successText.setTranslateY(400);

        if (!root.getChildren().contains(successText)) {
            root.getChildren().add(successText);

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> root.getChildren().remove(successText));
            pause.play();
        }

    }
    public void checkAutoReset(UserProfiles user){



        if(user!=null){
           user.autoResetAt3AM() ;
        }
    }

}
