import javafx.application.Platform;
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
import java.util.ArrayList;
import java.util.Optional;

public class AdvicePage {

    private UserProfiles userProfiles;
    private FileHandling fileHandler;
    private ArrayList<UserProfiles> profileList;

    public AdvicePage(UserProfiles userProfiles, Stage stage) {
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
            // Already on advice page
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

                    /*\\d+(\\.\\d+)? means: "one or more digits, optionally followed by a decimal point and more digits"

                    \\d+ = one or more digits

                     (\\.\\d+)? = optional group: decimal point followed by one or more digits

                     Examples that PASS: "150", "175.5", "180.25"

E                    xamples that FAIL: "abc", "175.", ".5", ""

                     */

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
                popup.initModality(Modality.APPLICATION_MODAL); // This blocks the user from fliping interacting with anytthing else so he doesn't break me
                popup.setTitle("Edit Profile");
                popup.setResizable(false);
                popup.setScene(popupScene);

                popup.showAndWait();

            }
        });

        Button tracking = new Button("Tracking");
        tracking.getStyleClass().add("menu-btn");
        tracking.setPrefWidth(190);

        menu.getChildren().addAll(dashboardBtn, progressBtn, tracking, Advice, Profile);
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

        Text topTitle = new Text("Health & Nutrition Advice");
        topTitle.setFont(Font.font(32));
        topTitle.getStyleClass().add("top-title");
        topTitle.setTranslateX(350);
        topBar.getChildren().add(topTitle);

        // Main content area that will contain the cards
        VBox contentArea = new VBox();
        contentArea.setPadding(new Insets(20));
        contentArea.setSpacing(20);
        VBox.setVgrow(contentArea, Priority.ALWAYS);

        // Create a grid for cards
        GridPane cardsGrid = new GridPane();
        cardsGrid.setHgap(20);
        cardsGrid.setVgap(20);
        cardsGrid.setPadding(new Insets(10));

        // Allow grid to expand
        GridPane.setHgrow(cardsGrid, Priority.ALWAYS);
        GridPane.setVgrow(cardsGrid, Priority.ALWAYS);
        cardsGrid.setMaxWidth(Double.MAX_VALUE);
        cardsGrid.setMaxHeight(Double.MAX_VALUE);

        // Get user's condition from enum
        ConditionHealth userCondition = userProfiles.getConditionHealth();
        if (userCondition == null) {
            userCondition = ConditionHealth.NONE;
        }

        // Create the appropriate condition object
        ConditionsHealth conditionObject = null;

        switch (userCondition) {
            case DIABETES:
                conditionObject = new ConditionDiabetes("Diabetes condition", "Diabetes", "Metabolic");
                break;
            case Heart_Disease:
                conditionObject = new ConditionHeartDisease("Heart Disease condition", "Heart Disease", "Cardiovascular");
                break;
            case Kidney_Disease:
                conditionObject = new ConditionKidneyDisease("Kidney Disease condition", "Kidney Disease", "Renal");
                break;
            case ConditionColon:
                conditionObject = new ConditionColon("Colon condition", "Colon Issues", "Digestive");
                break;
            case ConditionGlutenTolerance:
                conditionObject = new ConditionGlutenTolerance("Gluten Tolerance condition", "Gluten Intolerance", "Digestive");
                break;
            case ConditionLactoseTolerance:
                conditionObject = new ConditionLactoseTolerance("Lactose Tolerance condition", "Lactose Intolerance", "Digestive");
                break;
            case NONE:
            default:
                // No specific condition - will use default cards
                conditionObject = new ConditionNone("No specific condition", "General Health", "General");
                break;
        }

        // Cast to FoodAdvice if needed (since all condition classes implement both)
        FoodAdvice foodAdvice = (FoodAdvice) conditionObject;

        // Create cards using both ConditionsHealth and FoodAdvice methods
        VBox card1 = createProgressCard("Recommended Foods",
                formatAsBulletList(foodAdvice.getRecommendations()));

        VBox card2 = createProgressCard("Foods to Avoid",
                formatAsBulletList(foodAdvice.getRestrictions()));

        VBox card3 = createProgressCard("General Advice",
                conditionObject.getDietTips());

        VBox card4 = createProgressCard("Daily Routine Tips",
                conditionObject.getLifestyleTips());

        VBox card5 = createProgressCard("Warnings",
                conditionObject.getImportantTips());

        // Add cards to grid in 2x3 layout
        cardsGrid.add(card1, 0, 0);
        cardsGrid.add(card2, 1, 0);
        cardsGrid.add(card3, 2, 0);
        cardsGrid.add(card4, 0, 1);
        cardsGrid.add(card5, 1, 1);

        // Make all columns equal width
        for (int i = 0; i < 3; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / 3);
            colConst.setHgrow(Priority.ALWAYS);
            cardsGrid.getColumnConstraints().add(colConst);
        }

        // Make rows equal height
        for (int i = 0; i < 2; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(50);
            rowConst.setVgrow(Priority.ALWAYS);
            cardsGrid.getRowConstraints().add(rowConst);
        }

        contentArea.getChildren().add(cardsGrid);
        mainContent.getChildren().addAll(topBar, contentArea);
        root.getChildren().addAll(sidebar, mainContent);

        Scene scene = new Scene(root, 1400, 700);
        URL cssUrl = getClass().getResource("/style.css");
        if (cssUrl != null) scene.getStylesheets().add(cssUrl.toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Fitelligence - Health Advice");
        stage.setResizable(false);
        stage.show();
    }

    private UserProfiles findProfileByEmail(String email) {
        for (UserProfiles profile : profileList) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        return null;
    }

    private VBox createProgressCard(String title, String content) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.TOP_LEFT);
        card.getStyleClass().add("card");

        // Allow card to expand
        card.setMaxWidth(Double.MAX_VALUE);
        card.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(card, Priority.ALWAYS);
        HBox.setHgrow(card, Priority.ALWAYS);

        Text titleLabel = new Text(title);
        titleLabel.setFont(Font.font(22));
        titleLabel.setFill(Color.WHITE);
        titleLabel.getStyleClass().add("card-title");
        titleLabel.wrappingWidthProperty().bind(card.widthProperty().subtract(40));

        Text contentText = new Text(content);
        contentText.setFill(Color.LIGHTGRAY);
        contentText.setFont(Font.font(14));
        contentText.wrappingWidthProperty().bind(card.widthProperty().subtract(40));

        // Allow content text to expand
        VBox.setVgrow(contentText, Priority.ALWAYS);

        card.getChildren().addAll(titleLabel, contentText);

        return card;
    }

    private String formatAsBulletList(String[] items) {
        if (items == null || items.length == 0) {
            return "• No specific recommendations";
        }

        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append("• ").append(item.trim()).append("\n");
        }
        return sb.toString().trim();
    }
}