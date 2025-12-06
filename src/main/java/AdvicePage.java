import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class AdvicePage {

    private UserProfiles userProfiles;
    private FileHandling fileHandler;
    private ArrayList<UserProfiles> profileList;

    public AdvicePage(UserProfiles userProfiles , Stage stage) {
        this.userProfiles = userProfiles;
        this.fileHandler = new FileHandling();

        // SAFE LOADING: avoid NullPointerException
        this.profileList = fileHandler.loadProfiles();
        if (this.profileList == null) {
            this.profileList = new ArrayList<>();
        }

        // Check if user profile already exists
        UserProfiles existingProfile = findProfileByEmail(userProfiles.getEmail());

        if (existingProfile != null) {
            this.userProfiles = existingProfile;
        } else {
            this.userProfiles = userProfiles;
            profileList.add(userProfiles);
            fileHandler.saveProfiles(profileList);
        }

        createUI(stage);
    }

    private void createUI(Stage stage) {
        HBox root = new HBox();
        root.setPrefSize(1400, 700);

        // Sidebar
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(250);
        sidebar.setPrefHeight(700);
        sidebar.getStyleClass().add("sidebar");

        // Profile Section
        AnchorPane sideAnchor = new AnchorPane();
        sideAnchor.setPrefHeight(100);
        sideAnchor.setPrefWidth(250);
        sideAnchor.getStyleClass().add("side_anker");

        Text username = new Text(userProfiles.getName());
        username.getStyleClass().add("username");
        username.setLayoutX(100);
        username.setLayoutY(60);
        sideAnchor.getChildren().add(username);

        // Menu Buttons
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(20, 0, 0, 30));

        Button dashboardBtn = new Button("Home");
        dashboardBtn.getStyleClass().add("menu-btn");
        dashboardBtn.setPrefWidth(190);
        dashboardBtn.setOnAction(event -> {
            PageHOME home = new PageHOME(userProfiles, stage);
        });

        Button progressBtn = new Button("Progress");
        progressBtn.getStyleClass().add("menu-btn");
        progressBtn.setPrefWidth(190);

        Button Advice = new Button("Advice");
        Advice.getStyleClass().add("menu-btn");
        Advice.setPrefWidth(190);
        Advice.setOnAction(event -> {

            AdvicePage  advicePage = new AdvicePage(userProfiles, stage);


        });


        Button Profile = new Button("Profile");
        Profile.getStyleClass().add("menu-btn");
        Profile.setPrefWidth(190);

        Profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox popupContainer = new VBox();
                popupContainer.setAlignment(Pos.CENTER);
                popupContainer.setPadding(new Insets(18));

                VBox card = new VBox(14);
                card.setPadding(new Insets(20));
                card.setAlignment(Pos.TOP_LEFT);
                card.getStyleClass().add("card"); // reuse card style from CSS
                card.setMaxWidth(540);
                card.setMaxWidth(Double.MAX_VALUE); // let it grow horizontally

                Text cardTitle = new Text("Edit Profile");
                cardTitle.setFont(Font.font(22));
                cardTitle.setFill(Color.WHITE);
                cardTitle.getStyleClass().add("card-title");

                GridPane form = new GridPane();
                form.setVgap(12);
                form.setHgap(12);
                form.setPadding(new Insets(6, 0, 0, 0));

                Label ageLabel = new Label("Age       " + userProfiles.getAge());
                ageLabel.getStyleClass().add("popup-label");

                TextField ageField = new TextField();
                ageField.setPromptText("Years");

                Label heightLabel = new Label("Height    " + userProfiles.getHeight());
                heightLabel.getStyleClass().add("popup-label");
                TextField heightField = new TextField();
                heightField.setPromptText("cm (number)");

                Label weightLabel = new Label("Weight   " + userProfiles.getWeight());
                weightLabel.getStyleClass().add("popup-label");
                TextField weightField = new TextField();
                weightField.setPromptText("kg (number)");

                Label goalLabel = new Label("Goal  " + userProfiles.getFitnessGoal());
                goalLabel.getStyleClass().add("popup-label");
                ComboBox<String> goalBox = new ComboBox<>();
                goalBox.getItems().addAll("WEIGHT_LOSS", "MUSCLE_GAIN", "MAINTAIN_WEIGHT");
                goalBox.setPromptText("Select goal");

                form.add(ageLabel, 0, 0);
                form.add(ageField, 1, 0);
                form.add(heightLabel, 0, 1);
                form.add(heightField, 1, 1);
                form.add(weightLabel, 0, 2);
                form.add(weightField, 1, 2);
                form.add(goalLabel, 0, 3);
                form.add(goalBox, 1, 3);

                ColumnConstraints leftCol = new ColumnConstraints();
                leftCol.setPercentWidth(35);
                ColumnConstraints rightCol = new ColumnConstraints();
                rightCol.setPercentWidth(65);
                form.getColumnConstraints().addAll(leftCol, rightCol);

                HBox buttons = new HBox(12);
                buttons.setAlignment(Pos.CENTER_RIGHT);
                Button saveBtn = new Button("Save");
                saveBtn.getStyleClass().add("buttonedit");
                Button cancelBtn = new Button("Cancel");
                cancelBtn.getStyleClass().add("buttonedit");
                buttons.getChildren().addAll(cancelBtn, saveBtn);

                // Save validation
                saveBtn.setOnAction(ev -> {
                    String ageText = ageField.getText().trim();
                    String heightText = heightField.getText().trim();
                    String weightText = weightField.getText().trim();
                    String goal = goalBox.getValue();

                    boolean valid = true;
                    StringBuilder err = new StringBuilder();
                    if (!ageText.matches("\\d{1,3}")) { valid = false; err.append("Enter a valid age (numbers)\\n"); }
                    if (!heightText.matches("\\d+(\\.\\d+)?")) { valid = false; err.append("Enter a valid height (number)\\n"); }
                    if (!weightText.matches("\\d+(\\.\\d+)?")) { valid = false; err.append("Enter a valid weight (number)\\n"); }
                    if (goal == null || goal.isEmpty()) { valid = false; err.append("Select a goal\\n"); }

                    if (!valid) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Validation error");
                        alert.setHeaderText("Invalid input");
                        alert.setContentText(err.toString());
                        System.out.println(err.toString());
                        alert.initOwner(stage);
                        alert.showAndWait();
                        return;
                    }

                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Profile saved");
                    info.setHeaderText(null);

                    Label content = new Label(
                            "Profile updated:\nAge: " + ageText +
                                    "\nHeight: " + heightText +
                                    "\nWeight: " + weightText +
                                    "\nGoal: " + goal
                    );
                    content.setFont(Font.font("Poppins SemiBold", 14)); // choose a nicer font and size
                    content.setTextFill(Color.CYAN); // optional: match your theme
                    content.setWrapText(true);
                    info.getDialogPane().setContent(content);
                    info.initOwner(stage);
                    info.showAndWait();

                    userProfiles.setAge(Integer.parseInt(ageText));
                    userProfiles.setHeight(Double.parseDouble(heightText));
                    userProfiles.setWeight(Double.parseDouble(weightText));
                    FitnessGoal fitnessGoal = FitnessGoal.valueOf(goal.toString());
                    userProfiles.setFitnessGoal(fitnessGoal);
                    fileHandler.saveProfiles(profileList);

                    // close the popup after successful save
                    ((Stage) saveBtn.getScene().getWindow()).close();
                });

                cancelBtn.setOnAction(ev -> {
                    // close the popup without saving
                    ((Stage) cancelBtn.getScene().getWindow()).close();
                });

                card.getChildren().addAll(cardTitle, form, buttons);
                popupContainer.getChildren().add(card);

                Scene popupScene = new Scene(popupContainer, 870, 500);
                URL cssUrl = getClass().getResource("/style.css");
                if (cssUrl != null) popupScene.getStylesheets().add(cssUrl.toExternalForm());

                Stage popup = new Stage();
                popup.initOwner(stage);
                popup.initModality(Modality.APPLICATION_MODAL);
                popup.setTitle("Edit Profile");
                popup.setResizable(false);
                popup.setScene(popupScene);

                // show the popup immediately and wait
                popup.showAndWait();
            }
        });

        Button tracking = new Button("Tracking");
        tracking.getStyleClass().add("menu-btn");
        tracking.setPrefWidth(190);

        menu.getChildren().addAll(dashboardBtn, progressBtn  , tracking , Advice , Profile  );
        sidebar.getChildren().addAll(sideAnchor, menu );
    }


    private UserProfiles findProfileByEmail(String email) {
        for (UserProfiles profile : profileList) {
            if (profile.getEmail().equals(email)) { // idk how that thing fixes everything but it works so I am not gonna touch that thing bye.
                return profile;
            }
        }
        return null;
    }
    }