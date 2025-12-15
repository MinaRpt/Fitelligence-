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

public class PageTracking {

    private UserProfiles userProfiles;
    private FileHandling fileHandler;
    private ArrayList<UserProfiles> profileList;

    public PageTracking(UserProfiles userProfiles, Stage stage) {
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
                // Same profile edit popup as before
                showProfilePopup(stage);
            }
        });

        Button tracking = new Button("Food-Tracking");
        tracking.getStyleClass().add("menu-btn");
        tracking.setPrefWidth(190);

        Button Exercise = new Button("Exercise");
        Exercise.getStyleClass().add("menu-btn");
        Exercise.setPrefWidth(190);
        Exercise.setOnAction(event -> {
            PageExercise pageExercise = new PageExercise(userProfiles , stage);
        });

        menu.getChildren().addAll(dashboardBtn, progressBtn, tracking, Exercise, Advice, Profile);
        sidebar.getChildren().addAll(sideAnchor, menu);

        // Main Content - SIMPLIFIED
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

        Text topTitle = new Text("Food Tracking");
        topTitle.setFont(Font.font(32));
        topTitle.getStyleClass().add("top-title");
        topTitle.setTranslateX(350);
        topBar.getChildren().add(topTitle);

        // SIMPLE Food Tracking Area
        VBox trackingArea = new VBox(20);
        trackingArea.setPadding(new Insets(20));

        // 1. Add Food Card
        VBox addFoodCard = new VBox(15);
        addFoodCard.getStyleClass().add("card");
        addFoodCard.setPadding(new Insets(20));

        Text addTitle = new Text("Add Food");
        addTitle.setFont(Font.font(22));
        addTitle.setFill(Color.WHITE);

        // Simple form
        VBox form = new VBox(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Food Name (e.g., Apple)");
        nameField.setPrefWidth(300);

        HBox nutritionRow = new HBox(10);

        TextField caloriesField = new TextField();
        caloriesField.setPromptText("Calories");
        caloriesField.setPrefWidth(100);

        TextField proteinField = new TextField();
        proteinField.setPromptText("Protein (g)");
        proteinField.setPrefWidth(100);

        TextField carbsField = new TextField();
        carbsField.setPromptText("Carbs (g)");
        carbsField.setPrefWidth(100);

        TextField fatsField = new TextField();
        fatsField.setPromptText("Fats (g)");
        fatsField.setPrefWidth(100);

        nutritionRow.getChildren().addAll(caloriesField, proteinField, carbsField, fatsField);

        Button addButton = new Button("Add Food");
        addButton.getStyleClass().add("buttonedit");
        addButton.setPrefWidth(150);

        addButton.setOnAction(e -> {
            // Validate and add food
            if (nameField.getText().trim().isEmpty()) {
                showError("Please enter a food name");
                return;
            }

            try {
                double calories = Double.parseDouble(caloriesField.getText());
                double protein = Double.parseDouble(proteinField.getText());
                double carbs = Double.parseDouble(carbsField.getText());
                double fats = Double.parseDouble(fatsField.getText());

                // Create food tracker if doesn't exist
                if (userProfiles.getFoodTracker() == null) {
                    userProfiles.setFoodTracker(new FoodTracker());
                }

                // Create and add food
                Food newFood = new Food(
                        calories, fats,
                        nameField.getText().trim(),
                        protein,
                        LocalDateTime.now(),
                        carbs
                );

                userProfiles.getFoodTracker().addFood(newFood);

                // Update total calories on home page
                userProfiles.setDailyCalories(userProfiles.getDailyCalories() + (int)calories);
                fileHandler.saveProfiles(profileList);

                // Clear form
                nameField.clear();
                caloriesField.clear();
                proteinField.clear();
                carbsField.clear();
                fatsField.clear();

                showSuccess("Food added successfully!");
                PageTracking pageTracking = new PageTracking(userProfiles , stage);
            } catch (NumberFormatException ex) {
                showError("Please enter valid numbers for nutrition info");
            }
        });

        form.getChildren().addAll(nameField, nutritionRow, addButton);
        addFoodCard.getChildren().addAll(addTitle, form);

        // 2. Food List Card - FIXED TITLE
        VBox foodListCard = new VBox(15);
        foodListCard.getStyleClass().add("card");
        foodListCard.setPadding(new Insets(20));
        foodListCard.setPrefHeight(400);

        // Title stays fixed at top
        Text listTitle = new Text( "Foods");
        listTitle.setFont(Font.font(22));
        listTitle.setFill(Color.WHITE);

        // Center the title
        HBox titleContainer = new HBox();
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.getChildren().add(listTitle);

        // Scrollable area for food items
        ScrollPane foodScrollPane = new ScrollPane();
        foodScrollPane.setFitToWidth(true);
        foodScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        foodScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        foodScrollPane.setPrefHeight(320);

        VBox foodListArea = new VBox(10);
        foodListArea.setMinHeight(300);
        foodScrollPane.setContent(foodListArea);

        foodScrollPane.setStyle("-fx-background: transparent; " +
                "-fx-background-color: transparent; " +
                "-fx-control-inner-background: transparent;");

        // Add fixed title and scrollable content
        foodListCard.getChildren().addAll(titleContainer, foodScrollPane);

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

        refreshFoodList(foodListArea , stage);

        // Layout for cards
        HBox cardsRow = new HBox(20);
        cardsRow.getChildren().addAll(foodListCard, totalsCard);
        foodListCard.prefWidthProperty().bind(cardsRow.widthProperty().multiply(0.7));
        totalsCard.prefWidthProperty().bind(cardsRow.widthProperty().multiply(0.3));

        trackingArea.getChildren().addAll(addFoodCard, cardsRow);
        mainContent.getChildren().addAll(topBar, trackingArea);
        root.getChildren().addAll(sidebar, mainContent);

        Scene scene = new Scene(root, 1400, 700);
        URL cssUrl = getClass().getResource("/style.css");
        if (cssUrl != null) scene.getStylesheets().add(cssUrl.toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Fitelligence - Tracking");
        stage.setResizable(false);
        stage.show();
    }

    private void refreshFoodList(VBox foodListArea, Stage stage) {
        foodListArea.getChildren().clear();

        if (userProfiles.getFoodTracker() == null ||
                userProfiles.getFoodTracker().getFoodList().isEmpty()) {
            Text noFoodText = new Text("No foods added yet");
            noFoodText.setFill(Color.LIGHTGRAY);
            foodListArea.getChildren().add(noFoodText);
            return;
        }

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy 'at' HH:mm");

        for (Food food : userProfiles.getFoodTracker().getFoodList()) {
            HBox foodItem = new HBox(20);
            foodItem.setPadding(new Insets(10));
            foodItem.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 8;");

            // Food name and time
            VBox foodInfo = new VBox(5);
            Text foodName = new Text(food.getFoodName());
            foodName.setFill(Color.WHITE);
            foodName.setFont(Font.font(16));

            Text foodTime = new Text(food.getTimestamp().format(dateTimeFormat));
            foodTime.setFill(Color.LIGHTGRAY);
            foodTime.setFont(Font.font(12));

            foodInfo.getChildren().addAll(foodName, foodTime);

            // Nutrition info
            HBox nutritionInfo = new HBox(15);
            Text caloriesText = new Text(food.getCalories() + " kcal");
            caloriesText.setFill(Color.CYAN);

            Text proteinText = new Text("P: " + food.getProtein() + "g");
            proteinText.setFill(Color.LIGHTGREEN);

            Text carbsText = new Text("C: " + food.getCarbs() + "g");
            carbsText.setFill(Color.LIGHTBLUE);

            Text fatsText = new Text("F: " + food.getFat() + "g");
            fatsText.setFill(Color.LIGHTCORAL);

            nutritionInfo.getChildren().addAll(caloriesText, proteinText, carbsText, fatsText);

            // Delete button
            Button deleteBtn = new Button("X");
            deleteBtn.getStyleClass().add("buttonedit");
            deleteBtn.setOnAction(e -> {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Delete Food");
                confirm.setHeaderText("Delete " + food.getFoodName() + "?");
                confirm.setContentText("This will remove the food from your diary.");

                Optional<ButtonType> result = confirm.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Update calories
                    userProfiles.setDailyCalories(userProfiles.getDailyCalories() - (int)food.getCalories());

                    // Remove food
                    userProfiles.getFoodTracker().removeFood(food);
                    fileHandler.saveProfiles(profileList);

                    refreshFoodList(foodListArea, stage);
                    PageTracking pageTracking = new PageTracking(userProfiles , stage);
                }
            });

            HBox.setHgrow(foodInfo, Priority.ALWAYS);
            HBox.setHgrow(nutritionInfo, Priority.ALWAYS);

            foodItem.getChildren().addAll(foodInfo, nutritionInfo, deleteBtn);
            foodListArea.getChildren().add(foodItem);
        }
    }

    private void refreshTotals(VBox totalsArea) {
        totalsArea.getChildren().clear();

        if (userProfiles.getFoodTracker() == null) {
            Text noData = new Text("No data yet");
            noData.setFill(Color.LIGHTGRAY);
            totalsArea.getChildren().add(noData);
            return;
        }

        FoodTracker tracker = userProfiles.getFoodTracker();

        VBox totals = new VBox(10);

        if(userProfiles.getDailyCalories() == 0 ){
            tracker.clearAllFoods();
        }

        Text caloriesTotal = new Text("Calories: " + tracker.CalculateTotalCalories() + " kcal");
        caloriesTotal.setFill(Color.CYAN);
        caloriesTotal.setFont(Font.font(18));
        if (userProfiles.getDailyCalories() == 0) {

        }
        Text proteinTotal = new Text("Protein: " + tracker.calculatTotalprotein() + " g");
        proteinTotal.setFill(Color.LIGHTGREEN);

        Text carbsTotal = new Text("Carbs: " + tracker.calculatTotalcarb() + " g");
        carbsTotal.setFill(Color.LIGHTBLUE);

        Text fatsTotal = new Text("Fats: " + tracker.calculatTotalfat() + " g");
        fatsTotal.setFill(Color.LIGHTCORAL);

        totals.getChildren().addAll(caloriesTotal, proteinTotal, carbsTotal, fatsTotal);
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
        // Copy the profile popup code from before here
        // (Same as in your original code)
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