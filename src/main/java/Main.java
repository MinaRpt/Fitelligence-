import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
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



    Text SignUpAndIn = new Text() ;       // just the button verification text

    Text userNameText = new Text("");;



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



        StackPane root1 = new StackPane();        // This scene is for the new user who signed up new !
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











                // yalla let's fliping create scene2 in a button because I can't do anything else and my brain stopped working flip this project
                Text userNameText = new Text();
                userNameText.setText("Welcome to Fitelligence!" );




                userNameText.setFont(Font.font("Verdana", 50 ));
                StackPane.setAlignment(userNameText , TOP_CENTER);
                userNameText.setTranslateY(20);



                Text AIWorkoutHelp = new Text();
                AIWorkoutHelp.setText("Hello,"+ currentUser.getName() + "Let's make Today count!!");
                AIWorkoutHelp.setFont(Font.font("Verdana", 30 ));
                StackPane.setAlignment(AIWorkoutHelp , TOP_CENTER);
                AIWorkoutHelp.setTranslateY(300);
                AIWorkoutHelp.setTranslateX(-200);


                AIChatBot.startChat(AIBotHelp)   ;      // this method is for the ai to tell me what the heck this user needs to fliping eat ef ef



















                root2.getChildren().addAll(userNameText ,AIWorkoutHelp );


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


                Text WelcomingTextSignIN = new Text();
                WelcomingTextSignIN.setText("Welcome Back" + currentUser.getName());
                WelcomingTextSignIN.setFont(Font.font("Verdana", 20));

                root3.getChildren().addAll(WelcomingTextSignIN);
                stage.setScene(scene3);



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
}