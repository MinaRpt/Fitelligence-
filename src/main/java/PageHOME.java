import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class PageHOME {

    private UserProfiles userProfiles;
    private FileHandling fileHandler;
    private ArrayList<UserProfiles> profileList;

    public PageHOME(UserProfiles userProfiles , Stage stage) {
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
        progressBtn.setOnAction(event -> {
            PageProgress pageProgress = new PageProgress(userProfiles, stage);
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

        Button tracking = new Button("Food-Tracking");
        tracking.getStyleClass().add("menu-btn");
        tracking.setPrefWidth(190);
        tracking.setOnAction(event -> {
            PageTracking pageTracking = new PageTracking(userProfiles , stage);
        });

        Button Exercise = new Button("Exercise");
        Exercise.getStyleClass().add("menu-btn");
        Exercise.setPrefWidth(190);
        Exercise.setOnAction(event -> {
            PageExercise pageExercise = new PageExercise(userProfiles , stage);
        });

        menu.getChildren().addAll(dashboardBtn, progressBtn  , tracking , Exercise ,  Advice , Profile  );
        sidebar.getChildren().addAll(sideAnchor, menu );

        // Main Content
        VBox mainContent = new VBox();
        mainContent.setPrefWidth(1150);
        mainContent.setPrefHeight(700);
        mainContent.getStyleClass().add("main-content");
        mainContent.setSpacing(18);

        // Top Bar
        HBox topBar = new HBox();
        CheckBox autoResetToggle = new CheckBox("Auto reset at 3 AM");
        autoResetToggle.setTextFill(Color.WHITE);
        autoResetToggle.setSelected(userProfiles.getAutoResetEnabled());

        autoResetToggle.setOnAction(e -> {
            userProfiles.setAutoResetEnabled(autoResetToggle.isSelected());
            fileHandler.saveProfiles(profileList);
        });

        HBox.setMargin(autoResetToggle, new Insets(0, 0, 0, 40));
        topBar.getChildren().add(autoResetToggle);
        topBar.setPrefHeight(60);
        topBar.setPadding(new Insets(10));
        topBar.getStyleClass().add("top-bar");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Text topTitle = new Text("Welcome to Fitelligence");
        topTitle.setFont(Font.font(32));
        topTitle.getStyleClass().add("top-title");
        topTitle.setTranslateX(350);
        topBar.getChildren().add(topTitle);

        HBox progressCards = new HBox(30);
        progressCards.setPadding(new Insets(15, 25, 10, 25));
        progressCards.setAlignment(Pos.CENTER);

        VBox foodCard = createProgressCard("Food", userProfiles.getMacroCalorieGoal(userProfiles), userProfiles.getDailyCalories(),  "kcal", "Add");
        VBox exerciseCard = createProgressCard("Exercise", 800, userProfiles.getTotalSteps() , "Steps", "Add Steps");

        progressCards.getChildren().addAll(foodCard, exerciseCard);
        foodCard.prefWidthProperty().bind(progressCards.widthProperty().multiply(0.48));
        exerciseCard.prefWidthProperty().bind(progressCards.widthProperty().multiply(0.48));

        HBox.setHgrow(foodCard, Priority.ALWAYS);
        HBox.setHgrow(exerciseCard, Priority.ALWAYS);

        HBox mealsContainer = new HBox(20);
        mealsContainer.setPadding(new Insets(20));
        mealsContainer.setPrefHeight(600);
        mealsContainer.setAlignment(Pos.TOP_LEFT);

        Text Greetings = new Text("Good Morning, " + userProfiles.getName() + " ! Let's make today count ");
        Greetings.setFont(Font.font(30));
        Greetings.setFill(Color.WHITE);
        Greetings.getStyleClass().add("top-title");
        VBox.setMargin(Greetings, new Insets(10, 0, 0, 20));


        String  currentUser = "age : " + userProfiles.getAge() + "  weight : " +  userProfiles.getWeight() + " height : " + userProfiles.getHeight() + " health condition :  " + userProfiles.getHealthCondition() + "fitness goal : " +  userProfiles.getFitnessGoal() + "";
        VBox breakfast = createMealCard("🍳 Breakfast", AIChatBot.suggestBreakfast(currentUser));
        System.out.println(AIChatBot.suggestBreakfast(currentUser));
        breakfast.getStyleClass().add("Foodtitles");
        VBox lunch = createMealCard("🥗 Lunch", AIChatBot.suggestLunch(currentUser));
        lunch.getStyleClass().add("Foodtitles");
        VBox dinner = createMealCard("🍛 Dinner", AIChatBot.suggestDinner(currentUser));
        dinner.getStyleClass().add("Foodtitles");
        VBox snacks = createMealCard("🥜 Snacks", AIChatBot.suggestSnacks(currentUser));
        snacks.getStyleClass().add("Foodtitles");

        mealsContainer.getChildren().addAll(breakfast, lunch, dinner, snacks);
        breakfast.prefWidthProperty().bind(mealsContainer.widthProperty().multiply(0.23));
        lunch.prefWidthProperty().bind(mealsContainer.widthProperty().multiply(0.23));
        dinner.prefWidthProperty().bind(mealsContainer.widthProperty().multiply(0.23));
        snacks.prefWidthProperty().bind(mealsContainer.widthProperty().multiply(0.23));
        HBox.setHgrow(breakfast, Priority.ALWAYS);
        HBox.setHgrow(lunch, Priority.ALWAYS);
        HBox.setHgrow(dinner, Priority.ALWAYS);
        HBox.setHgrow(snacks, Priority.ALWAYS);

        mainContent.getChildren().addAll(topBar, progressCards, Greetings, mealsContainer);
        root.getChildren().addAll(sidebar, mainContent);

        Scene scene = new Scene(root, 1400, 700);
        URL cssUrl = getClass().getResource("/style.css");
        if (cssUrl != null) scene.getStylesheets().add(cssUrl.toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Fitelligence");
        stage.centerOnScreen();
        stage.setResizable(false);
        Timeline autoResetTimeline = new Timeline(
                new KeyFrame(javafx.util.Duration.minutes(1), e -> {
                    if (userProfiles != null) {
                        userProfiles.autoResetAt3AM();
                        fileHandler.saveProfiles(profileList);
                    }
                })
        );
        autoResetTimeline.setCycleCount(Timeline.INDEFINITE);
        autoResetTimeline.play();

        stage.show();
        stage.centerOnScreen();
    }

    private VBox createMealCard(String title, String description) {
        VBox card = new VBox(10);
        HBox.setHgrow(card, Priority.NEVER);
        card.setMinSize(270, 300);
        card.setSpacing(12);
        card.setPadding(new Insets(16));
        card.setAlignment(Pos.TOP_LEFT);
        card.setStyle("-fx-background-color: rgba(10,10,30,0.6); -fx-background-radius: 12; -fx-border-color: rgba(0,255,255,0.2); -fx-border-width: 0.5;");

        Text cardTitle = new Text(title);
        cardTitle.setFont(Font.font(25));
        cardTitle.setFill(Color.web("#00fff7"));
        cardTitle.getStyleClass().add("Foodtitles");

        Text cardDesc = new Text(description);
        cardDesc.setFont(Font.font(15));
        cardDesc.setFill(Color.WHITE);
        cardDesc.wrappingWidthProperty().bind(card.widthProperty().subtract(32));

        card.getChildren().addAll(cardTitle, cardDesc);
        return card;
    }

    private VBox createProgressCard(String title, int target, int currentThing ,  String unit, String buttonText ) {
        VBox card = new VBox(10);
        card.setPrefSize(200, 150);
        card.setMinWidth(150);
        card.setMaxWidth(400);
        card.setPadding(new Insets(14));
        card.setAlignment(Pos.TOP_CENTER);
        card.getStyleClass().add("card");

        Text titleLabel = new Text(title);
        titleLabel.setFont(Font.font(22));
        titleLabel.setFill(Color.WHITE);
        titleLabel.getStyleClass().add("card-title");
        titleLabel.wrappingWidthProperty().bind(card.widthProperty().subtract(32));
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        ProgressBar bar = new ProgressBar(66);
        bar.setPrefHeight(16);
        bar.setMinHeight(10);
        bar.setStyle("-fx-accent: #00fff7; -fx-control-inner-background: rgba(255,255,255,0.06);");

        IntegerProperty total = new SimpleIntegerProperty(currentThing);
        bar.progressProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(1.0, total.get() / (double) target),
                total
        ));

        Text progText = new Text();
        progText.getStyleClass().add("card-title");
        progText.textProperty().bind(Bindings.createStringBinding(
                () -> total.get() + "/" + target + " " + unit,
                total
        ));
        progText.setFont(Font.font(16));

        Button button = new Button(buttonText);
        button.getStyleClass().add("buttonedit");
        button.setPrefWidth(120);
        Button resetBtn = new Button("Reset Today");
        resetBtn.getStyleClass().add("buttonedit");

        resetBtn.setOnAction(e -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Reset Confirmation");
            confirm.setHeaderText("Reset today’s data?");
            confirm.setContentText("Calories and exercises will be saved to history.");

            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                userProfiles.manualReset();
                fileHandler.saveProfiles(profileList);
                total.set(0);
            }
        });


        button.setOnAction(actionEvent -> {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Add " + title);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nameField = new TextField();
            nameField.setPromptText(title + " name");
            TextField valueField = new TextField();
            valueField.setPromptText(unit);

            grid.add(new Label(title + " name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label(unit + " value:"), 0, 1);
            grid.add(valueField, 1, 1);

            dialog.getDialogPane().setContent(grid);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    String name = nameField.getText();
                    double value = Double.parseDouble(valueField.getText());
                    if (name.isEmpty() || value <= 0) throw new NumberFormatException();

                    System.out.println("Added " + value + " to " + title + " (" + name + ")");
                    total.set(total.get() + (int)value);
                    if (title.equals("Exercise")) {
                        userProfiles.setTotalSteps(total.get());
                        double caloriesBurned = value * 0.04 * userProfiles.getWeight();

                        if (userProfiles.getExerciseTracker() == null) userProfiles.setExerciseTracker(new ExerciseTracker());
                        userProfiles.getExerciseTracker().addExercise("Steps", (int)value, caloriesBurned);



                    } else {
                        userProfiles.setDailyCalories(userProfiles.getDailyCalories() + (int)value);
                        if (userProfiles.getFoodTracker() == null) {
                            userProfiles.setFoodTracker(new FoodTracker());
                        }

                        Food newFood = new Food(
                                value, // calories
                                0,     // fat (default 0 since not entered)
                                name.trim(),
                                0,     // protein (default 0)
                                LocalDateTime.now(),
                                0      // carbs (default 0)
                        );
                        userProfiles.getFoodTracker().addFood(newFood);
                        total.set(userProfiles.getDailyCalories());

                    }
                    fileHandler.saveProfiles(profileList);

                } catch (NumberFormatException e) {
                    Dialog<Void> errorDialog = new Dialog<>();
                    errorDialog.setTitle("Input Error");
                    errorDialog.setContentText("Please enter a valid name and a positive numeric value.");
                    errorDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    errorDialog.show();
                }
            }
            actionEvent.consume();
        });

        bar.prefWidthProperty().bind(card.widthProperty().subtract(32));
        VBox.setMargin(titleLabel, new Insets(4, 0, 8, 0));
        VBox.setMargin(bar, new Insets(6, 0, 6, 0));
        VBox.setMargin(button, new Insets(8, 0, 0, 0));

        card.getChildren().addAll(titleLabel, bar, progText, button,resetBtn);
        return card;
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