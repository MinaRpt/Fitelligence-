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

public class PageHOME {

    private UserProfiles userProfiles;
    private FileHandling fileHandler;
    private ArrayList<UserProfiles> profileList;

    public PageHOME(UserProfiles userProfiles , Stage stage) {
        this.userProfiles = userProfiles;
        createUI(stage);
        this.fileHandler = new FileHandling();

        this.profileList = fileHandler.loadProfiles();
        profileList.add(userProfiles);


        UserProfiles existingProfile = findProfileByEmail(userProfiles.getEmail());
        if (existingProfile != null) {
            this.userProfiles = existingProfile;
        } else {
            this.userProfiles = userProfiles;
            profileList.add(userProfiles);
            fileHandler.saveProfiles(profileList);
        }

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
                goalBox.getItems().addAll("WEIGHT_LOSS", "MUSCLE_GAINt", "MAINTAIN_WEIGHT");
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

                // show the popup immediately and wait; when it closes, exit the app (primary stage wasn't shown)
                popup.showAndWait();
            }

        });


        Button tracking = new Button("Tracking");
        tracking.getStyleClass().add("menu-btn");
        tracking.setPrefWidth(190);




        menu.getChildren().addAll(dashboardBtn, progressBtn  , tracking , Profile );


        sidebar.getChildren().addAll(sideAnchor, menu );

        // Main Content
        VBox mainContent = new VBox();
        mainContent.setPrefWidth(1150);
        mainContent.setPrefHeight(700);
        mainContent.getStyleClass().add("main-content");
        mainContent.setSpacing(18); // make main content spacing consistent


        // Top Bar
        HBox topBar = new HBox();
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
        // center the progress cards horizontally
        progressCards.setAlignment(Pos.CENTER);


        // Create progress cards: use numeric targets so bar can be bound to a property
        // Only Food and Exercise cards (Water and Sleep removed as requested)
        VBox foodCard = createProgressCard("Food", userProfiles.getMacroCalorieGoal(userProfiles), userProfiles.getDailyCalories(),  "kcal", "Add");

        VBox exerciseCard = createProgressCard("Exercise", 800, userProfiles.getTotalSteps() , "Steps", "Add Calories");

        progressCards.getChildren().addAll(foodCard, exerciseCard);

        // Bind each card width to the parent container so they scale responsively (two cards)
        foodCard.prefWidthProperty().bind(progressCards.widthProperty().multiply(0.48));
        exerciseCard.prefWidthProperty().bind(progressCards.widthProperty().multiply(0.48));

        HBox.setHgrow(foodCard, Priority.ALWAYS);
        HBox.setHgrow(exerciseCard, Priority.ALWAYS);


        HBox mealsContainer = new HBox(20);
        mealsContainer.setPadding(new Insets(20));
        mealsContainer.setPrefHeight(600);
        mealsContainer.setAlignment(Pos.TOP_LEFT); // align meals container to top so cards line up


        Text Greetings = new Text("Good Morning, " + userProfiles.getName() + " ! Let's make today count ");
        Greetings.setFont(Font.font(30));
        Greetings.setFill(Color.WHITE);
        Greetings.getStyleClass().add("top-title");
        VBox.setMargin(Greetings, new Insets(10, 0, 0, 20)); // use margin instead of translate so layout flows naturally


        // Breakfast
        VBox breakfast = createMealCard("🍳 Breakfast", "80g oatmeal + 150g Greek yogurt + 100g berries");
        breakfast.getStyleClass().add("Foodtitles");

        // Lunch
        VBox lunch = createMealCard("🥗 Lunch", "Grilled chicken breast + quinoa + mixed greens");
        lunch.getStyleClass().add("Foodtitles");

        // Dinner
        VBox dinner = createMealCard("🍛 Dinner", "Grilled salmon + steamed broccoli + brown rice");
        dinner.getStyleClass().add("Foodtitles");

        // Snacks
        VBox snacks = createMealCard("🥜 Snacks", "Protein shake + 20g almonds or rice cakes");
        snacks.getStyleClass().add("Foodtitles");


        mealsContainer.getChildren().addAll(breakfast, lunch, dinner, snacks);

        // Make meal cards share the mealsContainer width evenly and expand
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
        // guard stylesheet load to avoid NPE warning if resource path is wrong
        URL cssUrl = getClass().getResource("/style.css");
        if (cssUrl != null) scene.getStylesheets().add(cssUrl.toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Fitelligence");
        stage.setResizable(false);
        stage.show();
    }

    private VBox createMealCard(String title, String description) {
        VBox card = new VBox(10);
        HBox.setHgrow(card, Priority.NEVER); // prevents stretching
        card.setMinSize(270, 300); // these things prevent stetching and put them in thir fliping places
        card.setSpacing(12);
        card.setPadding(new Insets(16));
        card.setAlignment(Pos.TOP_LEFT);
//        card.setMaxSize(260, 360);
        card.setStyle("-fx-background-color: rgba(10,10,30,0.6); -fx-background-radius: 12; -fx-border-color: rgba(0,255,255,0.2); -fx-border-width: 0.5;");

        Text cardTitle = new Text(title);
        cardTitle.setFont(Font.font(25));
        cardTitle.setFill(Color.web("#00fff7"));
        cardTitle.getStyleClass().add("Foodtitles");
        Text cardDesc = new Text(description);
        cardDesc.setFont(Font.font(15));
        cardDesc.setFill(Color.WHITE);
        // make wrapping responsive to card width
        cardDesc.wrappingWidthProperty().bind(card.widthProperty().subtract(32));

        card.getChildren().addAll(cardTitle, cardDesc);
        return card;
    }

    // Modified createProgressCard: accept numeric target + unit + increment and bind ProgressBar to an IntegerProperty
    private VBox createProgressCard(String title, int target, int currentThing ,  String unit, String buttonText) {
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
        // make title wrap and center within the card
        titleLabel.wrappingWidthProperty().bind(card.widthProperty().subtract(32));
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        ProgressBar bar = new ProgressBar(66);
        // DON'T hardcode width if you want responsive scaling; bind it to the card width instead
        bar.setPrefHeight(16); // ensure visible height
        bar.setMinHeight(10);
        // ensure the accent color shows up even if CSS is changing defaults
        bar.setStyle("-fx-accent: #00fff7; -fx-control-inner-background: rgba(255,255,255,0.06);");


        // model for current total
        IntegerProperty total = new SimpleIntegerProperty(currentThing);
        // bind progress (0.0 - 1.0) safely and cap at 1.0
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
//        progText.setFill(Color.WHITE);
        progText.setFont(Font.font(16));

        Button button = new Button(buttonText);
        button.getStyleClass().add("buttonedit");
        button.setPrefWidth(120);

        // Always open a dialog to request name + numeric amount from the user (Food & Exercise behavior)
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

            // Convert user input to numeric value and update total on OK
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    // Basic validation: ensure name is not empty and value is a positive number
                    String name = nameField.getText();
                    double value = Double.parseDouble(valueField.getText());
                    if (name.isEmpty() || value <= 0) {
                        throw new NumberFormatException();
                    }
                    // For now, just log the output (replace with model update logic)
                    System.out.println("Added " + value + " to " + title + " (" + name + ")");
                    total.set(total.get() + (int)value);
                    if (title.equals("Steps")) {
                        userProfiles.setTotalSteps(total.get());
                        fileHandler.saveProfiles(profileList);
                    }
                     else {
                        userProfiles.setDailyCalories(total.get());
                        fileHandler.saveProfiles(profileList);
                    }

                } catch (NumberFormatException e) {
                    // Show error dialog on invalid input
                    Dialog<Void> errorDialog = new Dialog<>();
                    errorDialog.setTitle("Input Error");
                    errorDialog.setContentText("Please enter a valid name and a positive numeric value.");
                    errorDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    errorDialog.show();
                }
            }
            actionEvent.consume(); // reference the event so static analysis doesn't flag it as unused
        });



        // Bind the progress bar width to the card width minus padding so it scales
        bar.prefWidthProperty().bind(card.widthProperty().subtract(32));

        // center children and add spacing between elements
        VBox.setMargin(titleLabel, new Insets(4, 0, 8, 0));
        VBox.setMargin(bar, new Insets(6, 0, 6, 0));
        VBox.setMargin(button, new Insets(8, 0, 0, 0));

        // assemble children and return card
        card.getChildren().addAll(titleLabel, bar, progText, button);
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



