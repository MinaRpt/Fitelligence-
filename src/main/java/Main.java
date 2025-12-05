import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
 import javafx.scene.text.FontWeight;
import javafx.scene.text.FontWeight;  
import javafx.scene.control.Label;
 import javafx.animation.PauseTransition;
 import javafx.util.Duration;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import jfx.incubator.scene.control.richtext.SelectionSegment;
import javafx.scene.text.Font;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;


import static javafx.geometry.Pos.BOTTOM_CENTER;
import static javafx.geometry.Pos.TOP_CENTER;

public class Main extends Application  {  // extends application gives us the functionality of the Application!

    String email;
    String password;
    UserProfiles currentUser = new UserProfiles("", "", 0, Gender.MALE, 0, 0, HealthCondition.NONE);
    ArrayList<Account> accountList = new ArrayList<>();
    ArrayList<UserProfiles> profileList = new ArrayList<>();
    FileHandling fileHandler = new FileHandling();
    ArrayList<Account> loadedAccounts = fileHandler.loadAccounts(); // load accounts from file
    AccountService accountService = new AccountService(loadedAccounts);
    ArrayList<UserProfiles> loadedProfiles = fileHandler.loadProfiles();  // I am lost between all this methods I need someone to explain them to me ef ef
    ArrayList<Account> accounts = fileHandler.loadAccounts();
    FoodTracker foodTracker = new FoodTracker();
    ExerciseTracker exerciseTracker = new ExerciseTracker();


    Text SignUpAndIn = new Text() ;       // just the button verification text




    public static void main(String[] args) {
        launch(args); // launch method from application class which sets up everything we need for JavaFX

    }


    /* The entire window is called "Stage" everything from the close button and minimize and the frame is called  "stage"
     *  The content inside the window is called the "scene" in there we will be button all the stuff like buttons and other stuff
     *  it is not called a window and something inside the window it is called a stage and what's inside is called the scene! */
    @Override               // primary Stage is our main window
    public void start(Stage stage) throws Exception { // overridden from application class // // The main JavaFX code is here!
        stage.setTitle("Mina's Window!!"); // set the title of the window like for example

        StackPane root = new StackPane(); // That thing helps us to center stuff in the window :D  while anchorpane helps us put stuff anywhere on the screen
        Scene scene = new Scene(root , 835 , 400); // create a scene object // width and height of the window
        // this scene is for the login page


        StackPane root2 = new StackPane();        // This scene is for the new user who signedIn new ! MAIN APP!!
        Scene scene2 = new Scene(root2 , 1400 , 700);



        StackPane root1 = new StackPane();        // This scene is for the new user who signed up new  ( creating profile ) !
        Scene scene1 = new Scene(root1 , 1000 , 700);



        StackPane root3 = new StackPane();        // This scene is for the new user who signedIn new ! MAIN APP!!
        Scene scene3 = new Scene(root3 , 1400 , 700);


        //* Scene for new user who signed up NEW NEW NEW using root1 ! *//
        Text WelcomingText = new Text();
        WelcomingText.setText("Welcome to Fitelligence! Please enter your details below:");
        WelcomingText.setFont(Font.font("Verdana", 30 ));
        StackPane.setAlignment(WelcomingText , TOP_CENTER);

        TextField nameField = new TextField( );
        nameField.setPromptText("Enter your Name ex. Minamanmon");
        nameField.setMaxWidth(400);
        nameField.setMaxHeight(40);
        nameField.setTranslateY(-250);

        TextField age = new TextField( );
        age.setPromptText("Enter your Age ex. 20");
        age.setMaxWidth(400);
        age.setMaxHeight(40);
        age.setTranslateY(-200);

        TextField heightField = new TextField( );
        heightField.setPromptText("Enter your Height ex. 170cm");
        heightField.setMaxWidth(400);
        heightField.setMaxHeight(40);
        heightField.setTranslateY(-150);


        TextField weightField = new TextField( );
        weightField.setPromptText("Enter your Weight ex. 99kg");
        weightField.setMaxWidth(400);
        weightField.setMaxHeight(40);
        weightField.setTranslateY(-100);


        ComboBox genderBox = new ComboBox();
        genderBox.setPromptText("Choose Gender");
        genderBox.getItems().addAll("MALE", "FEMALE");
        genderBox.setTranslateY( -50);


        ComboBox Healthconditionbox = new ComboBox();
        Healthconditionbox.setPromptText("Choose Health Condition");
        Healthconditionbox.getItems().addAll("DIABETES", "NONE");
        Healthconditionbox.setTranslateY(0);


        Button SubmitButton = new Button("Submit");
        SubmitButton.setFont(Font.font("Verdana", 20));
        SubmitButton.setTranslateY(100);
        SubmitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String name = nameField.getText();
                int userAge = Integer.parseInt(age.getText());
                double weight = (Double) Double.parseDouble(weightField.getText());
                double height = (Double) Double.parseDouble(heightField.getText());

                Gender selected =Gender.valueOf(genderBox.getValue().toString());


                HealthCondition healthConditionSelected = HealthCondition.valueOf(Healthconditionbox.getValue().toString());
                String chosenHealthCondition = healthConditionSelected.toString();
                String ChosenGender = selected.toString();

                currentUser.setName(name);
                currentUser.setAge(userAge);
                currentUser.setWeight(weight);
                currentUser.setHeight(height);
                currentUser.setGender(selected);
                currentUser.setHealthCondition(healthConditionSelected);
                currentUser.setEmail(email);

                profileList.add(currentUser);
                fileHandler.saveProfiles(profileList);


                String AIBotHelp = name + " " + userAge + " " + weight + " " + height + " " + ChosenGender + " " + chosenHealthCondition;
//                System.out.println(name + " " + userAge + " " + weight + " " + height + " " + ChosenGender + " " + chosenHealthCondition);


                stage.setScene(scene2);


                currentUser.setFoodTracker(foodTracker);
                currentUser.setExerciseTracker(exerciseTracker);











                // yalla let's fliping create scene2 in a button because I can't do anything else and my brain stopped working flip this project

                // ==== MY CLEANED CODE STARTS HERE - ADDED TO YOUR EXISTING CODE ====

                // --- HEADER SECTION ---
                Text userNameText = new Text("Welcome to Fitelligence!");
                userNameText.setFont(Font.font("Verdana", 50));
                StackPane.setAlignment(userNameText, TOP_CENTER);

                Text AIWorkoutHelp = new Text("Hello, " + currentUser.getName() + "! Let's make today count!!");
                AIWorkoutHelp.setFont(Font.font("Verdana", 30));
                StackPane.setAlignment(AIWorkoutHelp, TOP_CENTER);
                AIWorkoutHelp.setTranslateY(300);
                AIWorkoutHelp.setTranslateX(-200);

                AIChatBot.startChat(AIBotHelp); // your existing AI logic

                // --- FOOD SECTION (Left Side) ---
                Label foodLabel = new Label("FOOD TRACKING");
                foodLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
                foodLabel.setTranslateX(-400);
                foodLabel.setTranslateY(-200);

                TextField foodNameField = new TextField();
                foodNameField.setPromptText("Food Name (e.g., 'Apple')");
                foodNameField.setMaxWidth(250);
                foodNameField.setTranslateX(-400);
                foodNameField.setTranslateY(-150);

                TextField weightFood = new TextField();
                weightFood.setPromptText("Weight in grams (e.g., '100')");
                weightFood.setMaxWidth(250);
                weightFood.setTranslateX(-400);
                weightFood.setTranslateY(-100);

                Button addFoodBtn = new Button("Add Food");
                addFoodBtn.setPrefWidth(250);
                addFoodBtn.setTranslateX(-400);
                addFoodBtn.setTranslateY(-50);
                addFoodBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14;");

                Text foodDisplay = new Text();
                foodDisplay.setFont(Font.font("Verdana", 16));
                foodDisplay.setTranslateX(-400);
                foodDisplay.setTranslateY(50);
                foodDisplay.setText(updateFoodDisplay(currentUser.getFoodTracker()));

                // --- EXERCISE SECTION (Right Side) ---
                Label exerciseLabel = new Label("EXERCISE TRACKING");
                exerciseLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
                exerciseLabel.setTranslateX(400);
                exerciseLabel.setTranslateY(-200);

                TextField exNameField = new TextField();
                exNameField.setPromptText("Exercise Name (e.g., 'Running')");
                exNameField.setMaxWidth(250);
                exNameField.setTranslateX(400);
                exNameField.setTranslateY(-150);

                TextField exDurationField = new TextField();
                exDurationField.setPromptText("Duration in minutes (e.g., '30')");
                exDurationField.setMaxWidth(250);
                exDurationField.setTranslateX(400);
                exDurationField.setTranslateY(-100);

                TextField exCaloriesField = new TextField();
                exCaloriesField.setPromptText("Calories Burned (e.g., '300')");
                exCaloriesField.setMaxWidth(250);
                exCaloriesField.setTranslateX(400);
                exCaloriesField.setTranslateY(-50);

                Button addExerciseBtn = new Button("Add Exercise");
                addExerciseBtn.setPrefWidth(250);
                addExerciseBtn.setTranslateX(400);
                addExerciseBtn.setTranslateY(0);
                addExerciseBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14;");

                Text exDisplay = new Text();
                exDisplay.setFont(Font.font("Verdana", 16));
                exDisplay.setTranslateX(400);
                exDisplay.setTranslateY(100);
                exDisplay.setText(updateExerciseDisplay(currentUser.getExerciseTracker()));

                // --- BUTTON ACTIONS ---
                addFoodBtn.setOnAction(e -> {
                    try {
                        String foodName = foodNameField.getText().trim();
                        String weightStr = weightFood.getText().trim();

                        if (foodName.isEmpty() || weightStr.isEmpty()) {
                            showError("Please enter both food name and weight!", root2);
                            return;
                        }

                        double weightValue = Double.parseDouble(weightStr);

                        // Get nutrition info from AI
                        double calories = Double.parseDouble(AIChatBot.CalculationCalories(foodName + " " + weightValue + "g"));
                        double protein = Double.parseDouble(AIChatBot.CalculationProtein(foodName + " " + weightValue + "g"));
                        double fat = Double.parseDouble(AIChatBot.CalculationFat(foodName + " " + weightValue + "g"));
                        double carbs = Double.parseDouble(AIChatBot.CalculationCarbs(foodName + " " + weightValue + "g"));

                        // Create and add food
                        Food f = new Food(calories, fat, foodName, protein, java.time.LocalDateTime.now(), carbs);
                        currentUser.getFoodTracker().addFood(f);

                        // Update display
                        foodDisplay.setText(updateFoodDisplay(currentUser.getFoodTracker()));

                        // Save data
                        fileHandler.saveProfiles(profileList);

                        // Clear fields
                        foodNameField.clear();
                        weightFood.clear();

                        showSuccess("Food added successfully!", root2);

                    } catch (NumberFormatException ex) {
                        showError("Please enter a valid number for weight!", root2);
                    } catch (Exception ex) {
                        showError("Error adding food: " + ex.getMessage(), root2);
                    }
                });

                addExerciseBtn.setOnAction(e -> {
                    try {
                        String exerciseName = exNameField.getText().trim();
                        String durationStr = exDurationField.getText().trim();
                        String caloriesStr = exCaloriesField.getText().trim();

                        if (exerciseName.isEmpty() || durationStr.isEmpty() || caloriesStr.isEmpty()) {
                            showError("Please fill all exercise fields!", root2);
                            return;
                        }

                        int duration = Integer.parseInt(durationStr);
                        double caloriesBurned = Double.parseDouble(caloriesStr);

                        currentUser.getExerciseTracker().addExercise(exerciseName, duration, caloriesBurned);
                        exDisplay.setText(updateExerciseDisplay(currentUser.getExerciseTracker()));
                        fileHandler.saveProfiles(profileList);

                        // Clear fields
                        exNameField.clear();
                        exDurationField.clear();
                        exCaloriesField.clear();

                        showSuccess("Exercise logged successfully!", root2);

                    } catch (NumberFormatException ex) {
                        showError("Please enter valid numbers for duration and calories!", root2);
                    } catch (Exception ex) {
                        showError("Error adding exercise: " + ex.getMessage(), root2);
                    }
                });

                // --- ADD EVERYTHING TO ROOT ---
                root2.getChildren().addAll(
                        userNameText, AIWorkoutHelp,
                        foodLabel, foodNameField, weightFood, addFoodBtn, foodDisplay,
                        exerciseLabel, exNameField, exDurationField, exCaloriesField, addExerciseBtn, exDisplay
                );
            }
        });
        root1.getChildren().addAll(nameField , age , WelcomingText  , heightField , weightField, SubmitButton , genderBox, Healthconditionbox);     // add the button to the scene for it to appear on the screen















//















        Text text = new Text();
        text.setText("Welcome to Fitelligence!");
        text.setFont(Font.font("Verdana", 50 ));
        StackPane.setAlignment(text , TOP_CENTER);



        // Text Fields for the user to input data!
        TextField emailField = new TextField( );
        emailField.setPromptText("Enter your Email ex.Minamanmon5@gmail.com");
        emailField.setMaxWidth(400);
        emailField.setMaxHeight(40);
        emailField.setTranslateY(-50);


        TextField Password = new TextField( );
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
                password = Password.getText();
                Account account = new Account(email , password);
                boolean registeredAccount = accountService.SignUpCheck(account);
                if(registeredAccount) {
                    SignUpAndIn.setText("Signed Up Successfully!! Please SignIn");
                    SignUpAndIn.setFont(Font.font("Verdana", 20));
                    StackPane.setAlignment(SignUpAndIn, BOTTOM_CENTER);
                    stage.setScene(scene1);
                    accountList.add(account);
                    fileHandler.saveAccounts(accountList);
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


            Account account = new Account(email , password);
            boolean registeredAccount = accountService.SignInCheck(account);
            System.out.println(registeredAccount);
            if(registeredAccount) {
                SignUpAndIn.setText("Signed In Successfully!! Welcome Back");
                SignUpAndIn.setFont(Font.font("Verdana", 20 ));
                StackPane.setAlignment(SignUpAndIn, BOTTOM_CENTER);



                currentUser = findProfileByEmail(email);  // This saved it I need an explination of how it worked HELP SOMEONE PLEASE FF

                stage.setScene(scene3);
                Text userNameText = new Text();
                userNameText.setText("Welcome to Fitelligence!");
                userNameText.setFont(Font.font("Verdana", 50));
                StackPane.setAlignment(userNameText, TOP_CENTER);
                userNameText.setTranslateY(20);

                Text AIWorkoutHelp = new Text();
                AIWorkoutHelp.setText("Hello, " + currentUser.getName() + " Let's make today count!!");
                AIWorkoutHelp.setFont(Font.font("Verdana", 30));
                StackPane.setAlignment(AIWorkoutHelp, TOP_CENTER);
                AIWorkoutHelp.setTranslateY(300);
                AIWorkoutHelp.setTranslateX(-200);

                AIChatBot.startChat(currentUser.toString()); // your existing AI logic

// --- FOOD INPUTS ---
                TextField foodNameField = new TextField();
                foodNameField.setPromptText("Food Name");
                foodNameField.setMaxWidth(200);
                foodNameField.setTranslateX(-400);
                foodNameField.setTranslateY(-200);

                TextField caloriesField = new TextField();
                caloriesField.setPromptText("Calories");
                caloriesField.setMaxWidth(100);
                caloriesField.setTranslateX(-400);
                caloriesField.setTranslateY(-150);

                TextField proteinField = new TextField();
                proteinField.setPromptText("Protein");
                proteinField.setMaxWidth(100);
                proteinField.setTranslateX(-400);
                proteinField.setTranslateY(-100);

                TextField fatField = new TextField();
                fatField.setPromptText("Fat");
                fatField.setMaxWidth(100);
                fatField.setTranslateX(-400);
                fatField.setTranslateY(-50);

                TextField carbField = new TextField();
                carbField.setPromptText("Carbs");
                carbField.setMaxWidth(100);
                carbField.setTranslateX(-400);
                carbField.setTranslateY(0);

                Button addFoodBtn = new Button("Add Food");
                addFoodBtn.setTranslateX(-400);
                addFoodBtn.setTranslateY(50);

                Text foodDisplay = new Text();
                foodDisplay.setFont(Font.font("Verdana", 20));
                foodDisplay.setTranslateX(-400);
                foodDisplay.setTranslateY(150);
                foodDisplay.setText(updateFoodDisplay(currentUser.getFoodTracker()));

// --- EXERCISE INPUTS ---
                TextField exNameField = new TextField();
                exNameField.setPromptText("Exercise Name");
                exNameField.setMaxWidth(200);
                exNameField.setTranslateX(400);
                exNameField.setTranslateY(-200);

                TextField exDurationField = new TextField();
                exDurationField.setPromptText("Duration (min)");
                exDurationField.setMaxWidth(100);
                exDurationField.setTranslateX(400);
                exDurationField.setTranslateY(-150);

                TextField exCaloriesField = new TextField();
                exCaloriesField.setPromptText("Calories Burned");
                exCaloriesField.setMaxWidth(100);
                exCaloriesField.setTranslateX(400);
                exCaloriesField.setTranslateY(-100);

                Button addExerciseBtn = new Button("Add Exercise");
                addExerciseBtn.setTranslateX(400);
                addExerciseBtn.setTranslateY(-50);

                Text exDisplay = new Text();
                exDisplay.setFont(Font.font("Verdana", 20));
                exDisplay.setTranslateX(400);
                exDisplay.setTranslateY(50);
                exDisplay.setText(updateExerciseDisplay(currentUser.getExerciseTracker()));

// --- BUTTON ACTIONS ---
                addFoodBtn.setOnAction(e -> {
                    try {
                        String nameFood = foodNameField.getText();
                        double cal = Double.parseDouble(caloriesField.getText());
                        double protein = Double.parseDouble(proteinField.getText());
                        double fat = Double.parseDouble(fatField.getText());
                        double carbs = Double.parseDouble(carbField.getText());

                        Food f = new Food(cal, fat, nameFood, protein, java.time.LocalDateTime.now() ,carbs);
                        currentUser.getFoodTracker().addFood(f);
                        foodDisplay.setText(updateFoodDisplay(currentUser.getFoodTracker()));
                        fileHandler.saveProfiles(profileList);

                        foodNameField.clear();
                        caloriesField.clear();
                        proteinField.clear();
                        fatField.clear();
                        carbField.clear();
                    } catch (Exception ex) {
                        System.out.println("Invalid Food Input");
                    }
                });

                addExerciseBtn.setOnAction(e -> {
                    try {
                        String name10 = exNameField.getText();
                        int duration = Integer.parseInt(exDurationField.getText());
                        double calBurned = Double.parseDouble(exCaloriesField.getText());

                        currentUser.getExerciseTracker().addExercise(name10, duration, calBurned);
                        exDisplay.setText(updateExerciseDisplay(currentUser.getExerciseTracker()));
                        fileHandler.saveProfiles(profileList);

                        exNameField.clear();
                        exDurationField.clear();
                        exCaloriesField.clear();
                    } catch (Exception ex) {
                        System.out.println("Invalid Exercise Input");
                    }
                });

// --- ADD EVERYTHING TO ROOT ---
                root3.getChildren().addAll(
                        userNameText, AIWorkoutHelp,
                        foodNameField, caloriesField, proteinField, fatField, carbField, addFoodBtn, foodDisplay,
                        exNameField, exDurationField, exCaloriesField, addExerciseBtn, exDisplay

                );


// okie it should work now
            }
            else {
                SignUpAndIn.setText("Account doesnn't exists please Signup!!");
                SignUpAndIn.setFont(Font.font("Verdana", 20 ));
                StackPane.setAlignment(SignUpAndIn, BOTTOM_CENTER);
            }
        });



        root.getChildren().addAll(SignInButton , SignUpButton , text , emailField , Password);     // add the button to the scene for it to appear on the screen
//        root.getChildren().add(SignInButton );
//        root.getChildren().add(SignUpButton);
//        root.getChildren().add(text);
        root.getChildren().add(SignUpAndIn);




        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); // show the window

    }

    private UserProfiles findProfileByEmail(String email) {
        ArrayList<UserProfiles> profiles = fileHandler.loadProfiles();
        for (UserProfiles profile : profiles) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
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
     private void showError(String message, StackPane root) {
         Text errorText = new Text(message);
         errorText.setFont(Font.font("Verdana", 15));
         errorText.setFill(javafx.scene.paint.Color.RED);
         errorText.setTranslateY(400);

         if (!root.getChildren().contains(errorText)) {
             root.getChildren().add(errorText);

             PauseTransition pause = new PauseTransition(Duration.seconds(3));
             pause.setOnFinished(event -> root.getChildren().remove(errorText));
             pause.play();
         }
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
}