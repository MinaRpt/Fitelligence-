import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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

public class PageProgress {

    private UserProfiles userProfiles;
    private FileHandling fileHandler;
    private ArrayList<UserProfiles> profileList;

    public PageProgress(UserProfiles userProfiles, Stage stage) {
        this.userProfiles = userProfiles;
        this.fileHandler = new FileHandling();

        this.profileList = fileHandler.loadProfiles();
        if (this.profileList == null) {
            this.profileList = new ArrayList<>();
        }

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

        VBox sidebar = new VBox();
        sidebar.setPrefWidth(220);
        sidebar.setMinWidth(220);
        sidebar.setMaxWidth(220);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setAlignment(Pos.TOP_LEFT);

        // Sidebar header - FIXED
        AnchorPane sideAnchor = new AnchorPane();
        sideAnchor.setPrefHeight(100);
        sideAnchor.setPrefWidth(250);
        sideAnchor.getStyleClass().add("side_anker");

        Text username = new Text(userProfiles.getName());
        username.getStyleClass().add("username");
        username.setLayoutX(100);
        username.setLayoutY(60);
        sideAnchor.getChildren().add(username);

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
            PageAdvice pageAdvice = new PageAdvice(userProfiles, stage);
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
                card.getStyleClass().add("card");
                card.setMaxWidth(540);
                card.setMaxWidth(Double.MAX_VALUE);

                Text cardTitle = new Text("Edit Profile");
                cardTitle.setFont(Font.font(22));
                cardTitle.setFill(Color.WHITE);
                cardTitle.getStyleClass().add("card-title");

                GridPane form = new GridPane();
                form.setVgap(12);
                form.setHgap(12);
                form.setPadding(new Insets(15, 0, 0, 0));

                Label ageLabel = new Label("Age       " + userProfiles.getAge());
                ageLabel.getStyleClass().add("popup-label");

                TextField ageField = new TextField();
                ageField.setPromptText("Years");
                ageField.setAlignment(Pos.CENTER_LEFT);

                Label heightLabel = new Label("Height    " + userProfiles.getHeight());
                heightLabel.getStyleClass().add("popup-label");
                TextField heightField = new TextField();
                heightField.setPromptText("cm (number)");
                heightField.setAlignment(Pos.CENTER_LEFT);

                Label weightLabel = new Label("Weight   " + userProfiles.getWeight());
                weightLabel.getStyleClass().add("popup-label");

                TextField weightField = new TextField();
                weightField.setPromptText("kg (number)");

                Label goalLabel = new Label("Goal  " + userProfiles.getFitnessGoal());
                goalLabel.getStyleClass().add("popup-label");

                ComboBox<String> goalBox = new ComboBox<>();
                goalBox.getItems().addAll("WEIGHT_LOSS", "MUSCLE_GAIN", "MAINTAIN_WEIGHT");
                goalBox.setPromptText("Select goal");
                GridPane.setHgrow(goalBox, Priority.ALWAYS);
                GridPane.setHalignment(goalBox, HPos.CENTER);

                Label conditionLabel = new Label("Health Condition: " +
                        (userProfiles.getConditionHealth() != null ? userProfiles.getConditionHealth() : "None"));
                conditionLabel.getStyleClass().add("popup-label");

                ComboBox<String> conditionBox = new ComboBox<>();
                conditionBox.getItems().addAll(
                        "NONE",
                        "DIABETES",
                        "Heart_Disease",
                        "Kidney_Disease",
                        "ConditionGlutenTolerance",
                        "ConditionColon",
                        "ConditionLactoseTolerance"
                );
                conditionBox.setPromptText("Select Health Condition");
                GridPane.setHgrow(conditionBox, Priority.ALWAYS);
                GridPane.setHalignment(conditionBox, HPos.CENTER);

                if (userProfiles.getFitnessGoal() != null) {
                    goalBox.setValue(userProfiles.getFitnessGoal().toString());
                }
                if (userProfiles.getConditionHealth() != null) {
                    conditionBox.setValue(userProfiles.getConditionHealth().toString());
                }

                form.add(ageLabel, 0, 0);
                form.add(ageField, 1, 0);
                form.add(heightLabel, 0, 1);
                form.add(heightField, 1, 1);
                form.add(weightLabel, 0, 2);
                form.add(weightField, 1, 2);
                form.add(goalLabel, 0, 3);
                form.add(goalBox, 1, 3);
                form.add(conditionLabel, 0, 4);
                form.add(conditionBox, 1, 4);

                ColumnConstraints leftCol = new ColumnConstraints();
                leftCol.setPercentWidth(50);
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

                saveBtn.setOnAction(ev -> {
                    String ageText = ageField.getText().trim();
                    String heightText = heightField.getText().trim();
                    String weightText = weightField.getText().trim();
                    String goal = goalBox.getValue();
                    String condition = conditionBox.getValue();
                    boolean valid = true;
                    StringBuilder err = new StringBuilder();

                    if (!ageText.matches("\\d{1,3}")) { valid = false; err.append("Enter a valid age (numbers)\n"); }
                    if (!heightText.matches("\\d+(\\.\\d+)?")) { valid = false; err.append("Enter a valid height (number)\n"); }
                    if (!weightText.matches("\\d+(\\.\\d+)?")) { valid = false; err.append("Enter a valid weight (number)\n"); }
                    if (goal == null || goal.isEmpty()) { valid = false; err.append("Select a goal\n"); }
                    if (condition == null || condition.isEmpty()) { valid = false; err.append("Select a health condition\n"); }

                    if (!valid) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Validation error");
                        alert.setHeaderText("Invalid input");
                        alert.setContentText(err.toString());
                        alert.initOwner(stage);
                        alert.showAndWait();
                        return;
                    }

                    userProfiles.setAge(Integer.parseInt(ageText));
                    userProfiles.setHeight(Double.parseDouble(heightText));
                    userProfiles.setWeight(Double.parseDouble(weightText));
                    userProfiles.setFitnessGoal(FitnessGoal.valueOf(goal));
                    userProfiles.setConditionHealth(ConditionHealth.valueOf(condition));
                    fileHandler.saveProfiles(profileList);

                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Profile saved");
                    info.setHeaderText(null);
                    Label content = new Label(
                            "Profile updated:\nAge: " + ageText +
                                    "\nHeight: " + heightText +
                                    "\nWeight: " + weightText +
                                    "\nGoal: " + goal +
                                    "\nHealth Condition: " + condition
                    );
                    content.setFont(Font.font("Poppins SemiBold", 14));
                    content.setTextFill(Color.CYAN);
                    content.setWrapText(true);
                    info.getDialogPane().setContent(content);
                    info.initOwner(stage);
                    info.showAndWait();

                    ((Stage) saveBtn.getScene().getWindow()).close();

                    // Refresh page
                    PageProgress pageProgress = new PageProgress(userProfiles, stage);
                });

                cancelBtn.setOnAction(ev -> {
                    ((Stage) cancelBtn.getScene().getWindow()).close();
                });

                card.getChildren().addAll(cardTitle, form, buttons);
                popupContainer.getChildren().add(card);

                Scene popupScene = new Scene(popupContainer, 870, 650);
                URL cssUrl = getClass().getResource("/style.css");
                if (cssUrl != null) popupScene.getStylesheets().add(cssUrl.toExternalForm());

                Stage popup = new Stage();
                popup.initOwner(stage);
                popup.initModality(Modality.APPLICATION_MODAL);
                popup.setTitle("Edit Profile");
                popup.setResizable(false);
                popup.setScene(popupScene);

                popup.showAndWait();
            }
        });

        Button Exercise = new Button("Exercise");
        Exercise.getStyleClass().add("menu-btn");
        Exercise.setPrefWidth(190);
        Exercise.setOnAction(event -> {
            PageExercise pageExercise = new PageExercise(userProfiles, stage);
        });

        Button tracking = new Button("Food-Tracking");
        tracking.getStyleClass().add("menu-btn");
        tracking.setPrefWidth(190);
        tracking.setOnAction(event -> {
            PageTracking pageTracking = new PageTracking(userProfiles, stage);
        });

        menu.getChildren().addAll(dashboardBtn, progressBtn, tracking, Exercise, Advice, Profile);
        sidebar.getChildren().addAll(sideAnchor, menu);

        // Main Content
        VBox mainContent = new VBox();
        mainContent.setPrefWidth(1150);
        mainContent.setPrefHeight(700);
        mainContent.getStyleClass().add("main-content");

        // Top Bar
        HBox topBar = new HBox();
        topBar.setPrefHeight(60);
        topBar.setPadding(new Insets(10));
        topBar.getStyleClass().add("top-bar");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Text topTitle = new Text("Your Progress Dashboard");
        topTitle.setFont(Font.font(32));
        topTitle.getStyleClass().add("top-title");
        topTitle.setTranslateX(350);
        topBar.getChildren().add(topTitle);

        // Content Area - ONLY GOOD CARDS
        VBox contentArea = new VBox(30);
        contentArea.setPadding(new Insets(30));

        // Row 1: 4 BIG STAT CARDS
        HBox statsRow = new HBox(30);
        statsRow.setPrefHeight(220);

        // Current Weight Card
        VBox weightCard = createBigStatCard("Current Weight",
                String.format("%.1f kg", userProfiles.getWeight()),
                "Keep it steady! 💪", Color.CYAN, "⚖️");

        // BMI Card
        double bmi = userProfiles.calculateBMI(userProfiles.getWeight(), userProfiles.getHeight());
        VBox bmiCard = createBigStatCard("Your BMI",
                String.format("%.1f", bmi),
                getBMIDescription(bmi), getBMIColor(bmi), "📊");

        // Calories Card
        int calorieGoal = userProfiles.getMacroCalorieGoal(userProfiles);
        VBox caloriesCard = createBigStatCard("Daily Calories",
                userProfiles.getDailyCalories() + " / " + calorieGoal,
                "Stay on track! 🎯", getCalorieColor(userProfiles.getDailyCalories(), calorieGoal), "🔥");

        // Steps Card
        VBox stepsCard = createBigStatCard("Daily Steps",
                userProfiles.getTotalSteps() + " steps",
                getStepsMessage(userProfiles.getTotalSteps()),
                getStepsColor(userProfiles.getTotalSteps()), "👣");

        statsRow.getChildren().addAll(weightCard, bmiCard, caloriesCard, stepsCard);
        HBox.setHgrow(weightCard, Priority.ALWAYS);
        HBox.setHgrow(bmiCard, Priority.ALWAYS);
        HBox.setHgrow(caloriesCard, Priority.ALWAYS);
        HBox.setHgrow(stepsCard, Priority.ALWAYS);

        // Row 2: Health & Goal BIG CARDS
        HBox infoRow = new HBox(30);
        infoRow.setPrefHeight(280);

        // Health Conditions Card
        VBox healthCard = new VBox(20);
        healthCard.getStyleClass().add("card");
        healthCard.setPadding(new Insets(30));
        healthCard.setPrefWidth(550);

        Text healthTitle = new Text("Health Condition");
        healthTitle.setFont(Font.font(26));
        healthTitle.setFill(Color.WHITE);

        Text healthText = new Text(getFormattedHealthCondition(userProfiles.getConditionHealth()));
        healthText.setFont(Font.font(22));
        healthText.setFill(Color.LIGHTGREEN);

        Text healthTip = new Text(getHealthConditionTip(userProfiles.getConditionHealth()));
        healthTip.setFont(Font.font(16));
        healthTip.setFill(Color.LIGHTGRAY);
        healthTip.setWrappingWidth(500);

        healthCard.getChildren().addAll(healthTitle, healthText, healthTip);

        // Fitness Goal Card
        VBox goalCard = new VBox(20);
        goalCard.getStyleClass().add("card");
        goalCard.setPadding(new Insets(30));
        goalCard.setPrefWidth(550);

        Text goalTitle = new Text("Fitness Goal");
        goalTitle.setFont(Font.font(26));
        goalTitle.setFill(Color.WHITE);

        Text goalText = new Text(userProfiles.getFitnessGoal().toString().replace("_", " "));
        goalText.setFont(Font.font(22));
        goalText.setFill(Color.LIGHTCORAL);

        Text goalTip = new Text(getFitnessGoalTip(userProfiles.getFitnessGoal()));
        goalTip.setFont(Font.font(16));
        goalTip.setFill(Color.LIGHTGRAY);
        goalTip.setWrappingWidth(500);

        goalCard.getChildren().addAll(goalTitle, goalText, goalTip);

        infoRow.getChildren().addAll(healthCard, goalCard);
        HBox.setHgrow(healthCard, Priority.ALWAYS);
        HBox.setHgrow(goalCard, Priority.ALWAYS);

        contentArea.getChildren().addAll(statsRow, infoRow);
        mainContent.getChildren().addAll(topBar, contentArea);
        root.getChildren().addAll(sidebar, mainContent);

        Scene scene = new Scene(root, 1400, 700);
        URL cssUrl = getClass().getResource("/style.css");
        if (cssUrl != null) scene.getStylesheets().add(cssUrl.toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Fitelligence - Progress");
        stage.setResizable(false);
        stage.show();
    }

    // Helper method to create BIG stat cards
    private VBox createBigStatCard(String title, String value, String description, Color color, String emoji) {
        VBox card = new VBox(15);
        card.getStyleClass().add("card");
        card.setPadding(new Insets(25));
        card.setAlignment(Pos.CENTER);
        card.setPrefSize(250, 200);

        HBox titleBox = new HBox(15);
        titleBox.setAlignment(Pos.CENTER);

        Text emojiText = new Text(emoji);
        emojiText.setFont(Font.font(30));

        Text titleText = new Text(title);
        titleText.setFont(Font.font(18));
        titleText.setFill(Color.LIGHTGRAY);

        titleBox.getChildren().addAll(emojiText, titleText);

        Text valueText = new Text(value);
        valueText.setFont(Font.font(32));
        valueText.setFill(color);

        Text descText = new Text(description);
        descText.setFont(Font.font(14));
        descText.setFill(Color.LIGHTGRAY);
        descText.setWrappingWidth(200);

        card.getChildren().addAll(titleBox, valueText, descText);
        return card;
    }

    private Color getBMIColor(double bmi) {
        if (bmi < 18.5) return Color.LIGHTBLUE;    // Underweight
        else if (bmi < 25) return Color.LIGHTGREEN; // Normal
        else if (bmi < 30) return Color.ORANGE;     // Overweight
        else return Color.RED;                      // Obese
    }

    private String getBMIDescription(double bmi) {
        if (bmi < 18.5) return "Underweight - Eat more! 🍽️";
        else if (bmi < 25) return "Normal - Perfect! ✅";
        else if (bmi < 30) return "Overweight - Time to move! 🏃";
        else return "Obese - Let's work on it! 💪";
    }

    private Color getCalorieColor(int eaten, int goal) {
        double percentage = (double) eaten / goal;
        if (percentage < 0.7) return Color.LIGHTGREEN;  // Good
        else if (percentage < 0.9) return Color.YELLOW; // Moderate
        else if (percentage <= 1.0) return Color.ORANGE; // Near limit
        else return Color.RED;                          // Over limit
    }

    private Color getStepsColor(int steps) {
        if (steps >= 10000) return Color.LIGHTGREEN;  // Excellent
        else if (steps >= 5000) return Color.YELLOW;  // Good
        else return Color.LIGHTBLUE;                  // Could improve
    }

    private String getStepsMessage(int steps) {
        if (steps >= 10000) return "Awesome! Keep it up! 🏆";
        else if (steps >= 5000) return "Good job! Almost there! 👍";
        else return "Let's move more today! 🚶";
    }

    private String getFormattedHealthCondition(ConditionHealth health) {
        if (health == ConditionHealth.NONE) {
            return "No health conditions";
        } else {
            String name = health.toString();
            if (name.contains("_")) {
                String[] parts = name.split("_");
                return parts[0] + " " + parts[1].toLowerCase();
            } else {
                return name.substring(0, 1) + name.substring(1).toLowerCase();
            }
        }
    }

    private String getHealthConditionTip(ConditionHealth health) {
        switch (health) {
            case NONE: return "No restrictions - enjoy a balanced diet! 🍎";
            case DIABETES: return "Monitor sugar, eat whole grains, avoid sweets. 🚫🍭";
            case Heart_Disease: return "Low sodium, healthy fats, lots of veggies. ❤️🥦";
            case Kidney_Disease: return "Limit protein, watch potassium, stay hydrated. 💧";
            case ConditionGlutenTolerance: return "Avoid wheat, barley, rye. Go gluten-free! 🌾";
            case ConditionColon: return "High fiber, lots of water, avoid processed foods. 💦";
            case ConditionLactoseTolerance: return "Avoid dairy, try almond/soy alternatives. 🥛";
            default: return "Follow your doctor's advice! 👨‍⚕️";
        }
    }

    private String getFitnessGoalTip(FitnessGoal goal) {
        switch (goal) {
            case WEIGHT_LOSS: return "Calorie deficit + cardio. Focus on protein! 🥗🏃";
            case MUSCLE_GAIN: return "Protein surplus + strength training. Lift heavy! 💪🏋️";
            case MAINTAIN_WEIGHT: return "Balance calories, mix cardio & strength. ⚖️";
            default: return "Stay active every day! 🏋️‍♂️";
        }
    }

    private UserProfiles findProfileByEmail(String email) {
        for (UserProfiles profile : profileList) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        return null;
    }
}