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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class PageExercise {

    private UserProfiles userProfiles;
    private FileHandling fileHandler;
    private ArrayList<UserProfiles> profileList;

    public PageExercise(UserProfiles userProfiles, Stage stage) {
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

        // Sidebar (same as before)
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(250);
        sidebar.setPrefHeight(700);
        sidebar.getStyleClass().add("sidebar");
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
        progressBtn.setOnAction(event -> {
            PageProgress progress = new PageProgress(userProfiles, stage);
        });
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
                GridPane.setHalignment(goalBox, HPos.CENTER); // Align to right side of cell

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
                GridPane.setHalignment(conditionBox, HPos.CENTER); // Align to right side of cell

                // Set current values
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

                // Save validation
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
                });

                cancelBtn.setOnAction(ev -> {
                    ((Stage) cancelBtn.getScene().getWindow()).close();
                });

                card.getChildren().addAll(cardTitle, form, buttons);
                popupContainer.getChildren().add(card);

                Scene popupScene = new Scene(popupContainer, 870, 650);  // Increased to 650
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

        Button foodTracking = new Button("Food-Tracking");
        foodTracking.getStyleClass().add("menu-btn");
        foodTracking.setPrefWidth(190);
        foodTracking.setOnAction(event -> {
            PageTracking pageTracking = new PageTracking(userProfiles, stage);
        });

        Button exerciseTracking = new Button("Exercise");
        exerciseTracking.getStyleClass().add("menu-btn");
        exerciseTracking.setPrefWidth(190);

        menu.getChildren().addAll(dashboardBtn, progressBtn, foodTracking, exerciseTracking, Advice, Profile);
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

        Text topTitle = new Text("Exercise Tracking");
        topTitle.setFont(Font.font(32));
        topTitle.getStyleClass().add("top-title");
        topTitle.setTranslateX(350);
        topBar.getChildren().add(topTitle);

        // Exercise Tracking Area
        VBox trackingArea = new VBox(20);
        trackingArea.setPadding(new Insets(20));

        // 1. Add Exercise Card
        VBox addExerciseCard = new VBox(15);
        addExerciseCard.getStyleClass().add("card");
        addExerciseCard.setPadding(new Insets(20));

        Text addTitle = new Text("Add Exercise");
        addTitle.setFont(Font.font(22));
        addTitle.setFill(Color.WHITE);

        // Simple form
        VBox form = new VBox(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Exercise Name (e.g., Running)");
        nameField.setPrefWidth(300);

        HBox detailsRow = new HBox(10);

        TextField durationField = new TextField();
        durationField.setPromptText("Duration (min)");
        durationField.setPrefWidth(120);

        TextField caloriesField = new TextField();
        caloriesField.setPromptText("Calories Burned");
        caloriesField.setPrefWidth(120);

        TextField stepsField = new TextField();
        stepsField.setPromptText("Steps (optional)");
        stepsField.setPrefWidth(120);

        detailsRow.getChildren().addAll(durationField, caloriesField, stepsField);

        Button addButton = new Button("Add Exercise");
        addButton.getStyleClass().add("buttonedit");
        addButton.setPrefWidth(150);

        addButton.setOnAction(e -> {
            // Validate and add exercise
            if (nameField.getText().trim().isEmpty()) {
                showError("Please enter an exercise name");
                return;
            }

            try {
                int duration = Integer.parseInt(durationField.getText());
                double calories = Double.parseDouble(caloriesField.getText());

                if (duration <= 0) {
                    showError("Duration must be positive");
                    return;
                }
                if (calories <= 0) {
                    showError("Calories must be positive");
                    return;
                }

                // Create exercise tracker if doesn't exist
                if (userProfiles.getExerciseTracker() == null) {
                    userProfiles.setExerciseTracker(new ExerciseTracker());
                }

                // Add exercise
                userProfiles.getExerciseTracker().addExercise(
                        nameField.getText().trim(),
                        duration,
                        calories
                );

                // Update steps if provided
                if (!stepsField.getText().trim().isEmpty()) {
                    try {
                        int steps = Integer.parseInt(stepsField.getText());
                        if (steps > 0) {
                            userProfiles.setTotalSteps(userProfiles.getTotalSteps() + steps);
                        }
                    } catch (NumberFormatException ex) {
                        // Ignore if steps is not a valid number
                    }
                }

                fileHandler.saveProfiles(profileList);

                // Clear form
                nameField.clear();
                durationField.clear();
                caloriesField.clear();
                stepsField.clear();

                showSuccess("Exercise added successfully!");
                PageExercise pageExercise = new PageExercise(userProfiles, stage);
            } catch (NumberFormatException ex) {
                showError("Please enter valid numbers for duration and calories");
            }
        });

        form.getChildren().addAll(nameField, detailsRow, addButton);
        addExerciseCard.getChildren().addAll(addTitle, form);

        // 2. Exercise List Card - FIXED TITLE
        VBox exerciseListCard = new VBox(15);
        exerciseListCard.getStyleClass().add("card");
        exerciseListCard.setPadding(new Insets(20));
        exerciseListCard.setPrefHeight(400);

        // Title stays fixed at top
        Text listTitle = new Text( "Exercises");
        listTitle.setFont(Font.font(22));
        listTitle.setFill(Color.WHITE);

        // Center the title
        HBox titleContainer = new HBox();
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.getChildren().add(listTitle);

        // Scrollable area for exercise items
        ScrollPane exerciseScrollPane = new ScrollPane();
        exerciseScrollPane.setFitToWidth(true);
        exerciseScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        exerciseScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        exerciseScrollPane.setPrefHeight(320);

        VBox exerciseListArea = new VBox(10);
        exerciseListArea.setMinHeight(300);
        exerciseScrollPane.setContent(exerciseListArea);

        exerciseScrollPane.setStyle("-fx-background: transparent; " +
                "-fx-background-color: transparent; " +
                "-fx-control-inner-background: transparent;");

        // Add fixed title and scrollable content
        exerciseListCard.getChildren().addAll(titleContainer, exerciseScrollPane);

        // 3. Totals Card
        VBox totalsCard = new VBox(15);
        totalsCard.getStyleClass().add("card");
        totalsCard.setPadding(new Insets(20));

        Text totalsTitle = new Text("Daily Totals");
        totalsTitle.setFont(Font.font(22));
        totalsTitle.setFill(Color.WHITE);

        VBox totalsArea = new VBox(10);
        refreshTotals(totalsArea);

        totalsCard.getChildren().addAll(totalsTitle, totalsArea);

        // Initial load
        refreshExerciseList(exerciseListArea, stage);

        // Layout for cards
        HBox cardsRow = new HBox(20);
        cardsRow.getChildren().addAll(exerciseListCard, totalsCard);
        exerciseListCard.prefWidthProperty().bind(cardsRow.widthProperty().multiply(0.7));
        totalsCard.prefWidthProperty().bind(cardsRow.widthProperty().multiply(0.3));

        trackingArea.getChildren().addAll(addExerciseCard, cardsRow);
        mainContent.getChildren().addAll(topBar, trackingArea);
        root.getChildren().addAll(sidebar, mainContent);

        Scene scene = new Scene(root, 1400, 700);
        URL cssUrl = getClass().getResource("/style.css");
        if (cssUrl != null) scene.getStylesheets().add(cssUrl.toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Fitelligence - Exercise Tracking");
        stage.setResizable(false);
        stage.show();
    }

    private void refreshExerciseList(VBox exerciseListArea, Stage stage) {
        exerciseListArea.getChildren().clear();

        if (userProfiles.getExerciseTracker() == null ||
                userProfiles.getExerciseTracker().getExerciseEntries().isEmpty()) {
            Text noExerciseText = new Text("No exercises added yet");
            noExerciseText.setFill(Color.LIGHTGRAY);
            exerciseListArea.getChildren().add(noExerciseText);
            return;
        }

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy 'at' HH:mm");

        for (Exercise exercise : userProfiles.getExerciseTracker().getExerciseEntries()) {
            HBox exerciseItem = new HBox(20);
            exerciseItem.setPadding(new Insets(10));
            exerciseItem.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 8;");

            // Exercise name and time
            VBox exerciseInfo = new VBox(5);
            Text exerciseName = new Text(exercise.getExerciseName());
            exerciseName.setFill(Color.WHITE);
            exerciseName.setFont(Font.font(16));

            Text exerciseTime = new Text(exercise.getTimestamp().format(dateTimeFormat));
            exerciseTime.setFill(Color.LIGHTGRAY);
            exerciseTime.setFont(Font.font(12));

            exerciseInfo.getChildren().addAll(exerciseName, exerciseTime);

            // Exercise details
            HBox detailsInfo = new HBox(15);
            Text durationText = new Text(exercise.getDurationMinutes() + " min");
            durationText.setFill(Color.CYAN);

            Text caloriesText = new Text(exercise.getCalories() + " kcal");
            caloriesText.setFill(Color.LIGHTGREEN);

            detailsInfo.getChildren().addAll(durationText, caloriesText);

            // Delete button
            Button deleteBtn = new Button("X");
            deleteBtn.getStyleClass().add("buttonedit");
            deleteBtn.setOnAction(e -> {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Delete Exercise");
                confirm.setHeaderText("Delete " + exercise.getExerciseName() + "?");
                confirm.setContentText("This will remove the exercise from your diary.");

                Optional<ButtonType> result = confirm.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Remove exercise
                    // Note: ExerciseTracker doesn't have remove metho
                    recreateTrackerWithoutExercise(exercise);
                    fileHandler.saveProfiles(profileList);

                    refreshExerciseList(exerciseListArea, stage);
                    PageExercise pageExercise = new PageExercise(userProfiles, stage);
                }
            });

            HBox.setHgrow(exerciseInfo, Priority.ALWAYS);
            HBox.setHgrow(detailsInfo, Priority.ALWAYS);

            exerciseItem.getChildren().addAll(exerciseInfo, detailsInfo, deleteBtn);
            exerciseListArea.getChildren().add(exerciseItem);
        }
    }

    private void recreateTrackerWithoutExercise(Exercise exerciseToRemove) {
        if (userProfiles.getExerciseTracker() == null) return;

        ExerciseTracker oldTracker = userProfiles.getExerciseTracker();
        ExerciseTracker newTracker = new ExerciseTracker();

        for (Exercise exercise : oldTracker.getExerciseEntries()) {
            if (!exercise.equals(exerciseToRemove)) {
                newTracker.addExercise(
                        exercise.getExerciseName(),
                        exercise.getDurationMinutes(),
                        exercise.getCalories()
                );
            }
        }
        userProfiles.setTotalSteps(0); // will need to check on this soon just tesitng
        userProfiles.setExerciseTracker(newTracker);
    }

    private void refreshTotals(VBox totalsArea) {
        totalsArea.getChildren().clear();

        VBox totals = new VBox(10);

        // Exercise totals
        if (userProfiles.getExerciseTracker() != null) {
            ExerciseTracker tracker = userProfiles.getExerciseTracker();

            Text caloriesTotal = new Text("Calories Burned: " +
                    String.format("%.0f", tracker.getTotalCaloriesBurned()) + " kcal");
            caloriesTotal.setFill(Color.CYAN);
            caloriesTotal.setFont(Font.font(18));

            Text durationTotal = new Text("Total Duration: " +
                    tracker.getTotalDurationMinutes() + " min");
            durationTotal.setFill(Color.LIGHTGREEN);

            Text exercisesTotal = new Text("Exercises: " +
                    tracker.getExerciseEntries().size());
            exercisesTotal.setFill(Color.LIGHTBLUE);

            totals.getChildren().addAll(caloriesTotal, durationTotal, exercisesTotal);
        }

        // Steps total (from UserProfiles)
        Text stepsTotal = new Text("Total Steps: " + userProfiles.getTotalSteps());
        stepsTotal.setFill(Color.LIGHTCORAL);

        totals.getChildren().add(stepsTotal);
        totalsArea.getChildren().add(totals);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showProfilePopup(Stage stage) {
        // Copy the profile popup code from your PageTracking class
        // Same as in your original PageTracking code
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