//package com.company.assignment6;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the GUI Application to process movie ticket purchases
 * @author Gabriel I Feliz
 */
public class MovieTicketManagerGUI extends Application {

    /**
     * Application header
     */
    Label lb1 = new Label("XYZ Movie Theater");

    /**
     * Application image header
     */
    ImageView image = new ImageView("xyzSmall.png");

    /**
     * Movie name prompt
     */
    Label lb2 = new Label("Movie Name: ");

    /**
     * Day the movie is to be watched prompt
     */
    Label lb3 = new Label("Day (1-31): ");

    /**
     * Time the movie is to be watched prompt
     */
    Label lb4 = new Label("Time (8-23): ");

    /**
     * Employee or MoviePass id prompt
     */
    Label lb5 = new Label();

    /**
     * Movie name
     */
    TextField tf1 = new TextField();

    /**
     * Day the movie is to be watched
     */
    TextField tf2 = new TextField();

    /**
     * Time the movie is to be watched
     */
    TextField tf3 = new TextField();

    /**
     * Employee or MoviePass ID
     */
    TextField tf4 = new TextField();

    /**
     * Movie rating prompt
     */
    Label lb6 = new Label("Select One: ");

    /**
     * Movie feature prompt
     */
    Label lb7 = new Label("Select One: ");

    /**
     * Toggle group of movie type radio buttons
     */
    ToggleGroup rbGroup1 = new ToggleGroup();
    /**
     * Toggle group of movie rating radio buttons
     */
    ToggleGroup rbGroup2 = new ToggleGroup();
    /**
     * Toggle group of movie feature radio buttons
     */
    ToggleGroup rbGroup3 = new ToggleGroup();

    /**
     * Adult ticket
     */
    RadioButton rb1 = new RadioButton("ADULT");
    /**
     * Child ticket
     */
    RadioButton rb2 = new RadioButton("CHILD");
    /**
     * Employee ticket
     */
    RadioButton rb3 = new RadioButton("EMPLOYEE");
    /**
     * MoviePass ticket
     */
    RadioButton rb4 = new RadioButton("MOVIEPASS");

    /**
     * G Rating
     */
    RadioButton rb5 = new RadioButton("G");
    /**
     * PG Rating
     */
    RadioButton rb6 = new RadioButton("PG");
    /**
     * PG13 Rating
     */
    RadioButton rb7 = new RadioButton("PG13");
    /**
     * R Rating
     */
    RadioButton rb8 = new RadioButton("R");
    /**
     * NR Rating
     */
    RadioButton rb9 = new RadioButton("NR");

    /**
     * IMAX feature
     */
    RadioButton rb10 = new RadioButton("IMAX");
    /**
     * 3D feature
     */
    RadioButton rb11 = new RadioButton("3D");
    /**
     * No feature
     */
    RadioButton rb12 = new RadioButton("None");

    /**
     * Ticket purchase button
     */
    Button bt1 = new Button("Purchase Ticket");
    /**
     * Clear user input button
     */
    Button bt2 = new Button("Clear");

    /**
     * Ticket price and monthly ticket sales label
     */
    Label lb8 = new Label("Ticket Price:        Monthly Ticket Sales:    $0.00");

    /**
     * Read file button
     */
    Button bt3 = new Button("Read File");
    /**
     * Print Monthly Sales Report button
     */
    Button bt4 = new Button("Print Monthly Sales Report");
    /**
     * Print All Tickets button
     */
    Button bt5 = new Button("Print All Tickets");
    /**
     * Print 3D Tickets button
     */
    Button bt6 = new Button("Print 3D Tickets");
    /**
     * Print MoviePass Tickets button
     */
    Button bt7 = new Button("Print MoviePass Tickets");
    /**
     * Exit button
     */
    Button bt8 = new Button("Exit");

    /**
     * Pane for mage and label header
     */
    HBox topPane = new HBox(image, lb1);
    /**
     * Pane for movie type
     */
    HBox midPane1 = new HBox(rb1, rb2, rb3, rb4);
    /**
     * Pane for movie name
     */
    HBox midPane2 = new HBox(lb2, tf1);
    /**
     * Pane for day the movie is to be watched
     */
    HBox midPane3 = new HBox(lb3, tf2);
    /**
     * Pane for time the movie is to be watched
     */
    HBox midPane4 = new HBox(lb4, tf3);
    /**
     * Pane for employee or MoviePass id
     */
    HBox midPane5 = new HBox();
    /**
     * Pane for movie rating
     */
    HBox midPane6 = new HBox(lb6, rb5, rb6, rb7, rb8, rb9);
    /**
     * Pane for movie feature
     */
    HBox midPane7 = new HBox(lb7, rb10, rb11, rb12);
    /**
     * Pane for buttons to purchase ticket and clear input
     */
    HBox midPane8 = new HBox(bt1, bt2);
    /**
     * Pane for ticket price and monthly ticket sales
     */
    HBox midPane9 = new HBox(lb8);
    /**
     * Pane for buttons to read file, print monthly sales report,
     * and print all tickets
     */
    HBox bottomPane1 = new HBox(bt3, bt4, bt5);
    /**
     * Pane for buttons to print 3D tickets, print MoviePass tickets,
     * and exit the application
     */
    HBox bottomPane2 = new HBox(bt6, bt7, bt8);

    /**
     * Movie ticket manager
     */
    MovieTicketManager ticketManager = new MovieTicketManager();

    /**
     * This method builds and displays the GUI application
     * @param primaryStage application stage
     */
    @Override
    public void start(Stage primaryStage) {

        topNodes();

        middleNodes();

        bottomNodes();

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(new VBox(topPane));
        VBox midPane = new VBox(midPane1, midPane2, midPane3, midPane4,
                midPane5, midPane6, midPane7, midPane8, midPane9);
        midPane.setStyle("-fx-border-color: black");
        mainPane.setCenter(midPane);
        mainPane.setBottom(new VBox(bottomPane1, bottomPane2));

        primaryStage.setScene(new Scene(mainPane, 400, 600));
        primaryStage.setTitle("Movie Ticket Manager");
        primaryStage.show();
    }

    /**
     * This method has the image and label headers
     */
    private void topNodes() {

        topPane.setAlignment(Pos.CENTER);
        lb1.setFont(new Font("Arial", 20));
        for (Node node : topPane.getChildren()) {
            HBox.setMargin(node, new Insets(5));
        }
        VBox.setMargin(lb1, new Insets(5, 0, 0,0));

    }

    /**
     * This method contains all the components so that the user selects movie type,
     * enters movie name, enters day the movie is to be watched, enters time the movie
     * is to be watched, selects movie rating, selects movie feature, purchases tickets,
     * and clears information entered. It also display ticket price and monthly ticket sales
     */
    private void middleNodes() {

        rb1.setOnAction(event -> { midPane5.getChildren().setAll(new HBox());
                midPane6.getChildren().setAll(lb6, rb5, rb6, rb7, rb8, rb9);
        });
        rb2.setOnAction(event -> { midPane5.getChildren().setAll(new HBox());
                midPane6.getChildren().setAll(lb6, rb5, rb6);}
        );
        rb3.setOnAction(event -> {
            lb5.setText("Employee Id: ");
            midPane5.getChildren().setAll(lb5, tf4);
            midPane6.getChildren().setAll(lb6, rb5, rb6, rb7, rb8, rb9);
        });
        rb4.setOnAction(event -> {
            lb5.setText("MoviePass Id: ");
            midPane5.getChildren().setAll(lb5, tf4);
            midPane6.getChildren().setAll(lb6, rb5, rb6, rb7, rb8, rb9);
        });

        ArrayList<HBox> hBoxes = new ArrayList<>(Arrays.asList(midPane1, midPane2, midPane3,
                midPane4, midPane5, midPane6, midPane7, midPane8, midPane9));
        for (HBox hBox : hBoxes) {
            hBox.setAlignment(Pos.CENTER);
        }

        ArrayList<RadioButton> radioButtons1 = new ArrayList<>(Arrays.asList(rb1, rb2, rb3, rb4));
        ArrayList<RadioButton> radioButtons2 = new ArrayList<>(Arrays.asList(rb5, rb6, rb7, rb8, rb9));
        ArrayList<RadioButton> radioButtons3 = new ArrayList<>(Arrays.asList(rb10, rb11, rb12));
        for (RadioButton radioButton: radioButtons1) { radioButton.setToggleGroup(rbGroup1); }
        for (RadioButton radioButton: radioButtons2) { radioButton.setToggleGroup(rbGroup2); }
        for (RadioButton radioButton: radioButtons3) { radioButton.setToggleGroup(rbGroup3); }

        for (Node node : midPane1.getChildren()) {
            HBox.setMargin(node, new Insets(5, 5, 20, 5));
        }

        HBox.setMargin(lb2, new Insets(5, 5, 15, 5));
        HBox.setMargin(tf1, new Insets(5, 5, 15, 5));

        HBox.setMargin(lb3, new Insets(5, 18, 15, 5));
        HBox.setMargin(tf2, new Insets(5, 5, 15, 5));

        HBox.setMargin(lb4, new Insets(5, 15, 15, 5));
        HBox.setMargin(tf3, new Insets(5, 5, 15, 5));

        HBox.setMargin(lb5, new Insets(5, 8, 15, 5));
        HBox.setMargin(tf4, new Insets(5, 5, 15, 5));

        for (Node node : midPane6.getChildren()) {
            HBox.setMargin(node, new Insets(5, 5, 20, 5));
        }

        for (Node node : midPane7.getChildren()) {
            HBox.setMargin(node, new Insets(5, 5, 20, 5));
        }

        HBox.setMargin(bt1, new Insets(5, 10,10,5));
        HBox.setMargin(bt2, new Insets(5, 5,10,10));

        bt1.setOnAction(event -> {

            boolean readyToPurchase = true;

            String movie = tf1.getText();
            if (movie.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please enter movie name.");
                alert.showAndWait();
                readyToPurchase = false;
            }

            int day = 0;
            try {
                day = Integer.parseInt(tf2.getText());
                if (day < 1 || day > 31) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Please enter a number between 1 and 31 " +
                            "inclusively for day.");
                    alert.showAndWait();
                    readyToPurchase = false;
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please enter an integer for day.");
                alert.showAndWait();
                readyToPurchase = false;
            }

            int time = 0;
            try {
                time = Integer.parseInt(tf3.getText());
                if (time < 8 || time > 23) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Please enter a number between 8 and 23 " +
                            "inclusively for time.");
                    alert.showAndWait();
                    readyToPurchase = false;
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please enter an integer for time.");
                alert.showAndWait();
                readyToPurchase = false;
            }

            int id = 0;
            try {
                if (rb3.isSelected()) {
                    String empId = tf4.getText();
                    if (empId.equals("")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Please enter employee ID.");
                        alert.showAndWait();
                        readyToPurchase = false;
                    }
                    id = Integer.parseInt(empId);
                } else if (rb4.isSelected()) {
                    String moviePassId = tf4.getText();
                    if (moviePassId.equals("")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Please enter MoviePass ID.");
                        alert.showAndWait();
                        readyToPurchase = false;
                    }
                    id = Integer.parseInt(moviePassId);
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please enter integers for ID.");
                alert.showAndWait();
                readyToPurchase = false;
            }

            String type = "";
            for (RadioButton rb : radioButtons1) {
                if (rb.isSelected()) {
                    type = rb.getText();
                }
            }
            String rating = "";
            for (RadioButton rb : radioButtons2) {
                if (rb.isSelected()) {
                    rating = rb.getText();
                }
            }
            String feature = "";
            for (RadioButton rb : radioButtons3) {
                if (rb.isSelected()) {
                    feature = rb.getText();
                }
            }

            if (!type.equals("") && !rating.equals("")
                    && !feature.equals("") && readyToPurchase) {
                double price = ticketManager.addTicket(movie, rating, day, time, feature, type, id);
                Label lbCurrentPrice = new Label(String.format("$%.02f", price));
                lbCurrentPrice.setTextFill(Color.RED);
                midPane9.getChildren().setAll(new Label("Ticket Price:    " ) , lbCurrentPrice,
                        new Label("    Monthly Ticket Sales:    " +
                        String.format("$%.02f", ticketManager.totalSalesMonth())));
                HBox.setMargin(bt1, new Insets(5, 10,20,5));
                HBox.setMargin(bt2, new Insets(5, 5,20,10));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("All fields are required.");
                alert.showAndWait();
            }

        });

        bt2.setOnAction(event -> {
            midPane5.getChildren().setAll(new HBox());
            ArrayList<RadioButton> radioButtons =
                    new ArrayList<>(Arrays.asList(rb1, rb2, rb3, rb4, rb5,
                            rb6, rb7, rb8, rb9, rb10, rb11, rb12));
            for (RadioButton rb : radioButtons) {
                rb.setSelected(false);
            }
            ArrayList<TextField> textFields =
                    new ArrayList<>(Arrays.asList(tf1, tf2, tf3, tf4));
            for (TextField tf : textFields) {
                tf.setText("");
            }
        });

        HBox.setMargin(lb8, new Insets(10));

    }

    /**
     * This method contains the buttons for reading a file,
     * printing monthly sales report, printing all tickets,
     * printing 3D tickets, printing MoviePass tickets, and
     * exiting the application
     */
    private void bottomNodes() {

        bt3.setOnAction(event -> {
            File selectedFile;

            FileChooser chooser = new FileChooser();
            chooser.setTitle("Choose a file to read information about movie tickets");
            if ((selectedFile = chooser.showOpenDialog(null)) != null) {
                // Read the file
                try {
                    ticketManager.readFile(selectedFile);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("File successfully uploaded.");
                    alert.showAndWait();
                    midPane9.getChildren().setAll(new Label("Ticket Price:    " ),
                            new Label("    Monthly Ticket Sales:    " +
                                    String.format("$%.02f", ticketManager.totalSalesMonth())));
                    HBox.setMargin(bt1, new Insets(5, 10,20,5));
                    HBox.setMargin(bt2, new Insets(5, 5,20,10));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Upload was unsuccessful.\n" +
                            "File could not be found");
                    alert.showAndWait();
                }
            }
        });

        bt4.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(ticketManager.monthlySalesReport());
            alert.showAndWait();
        });

        bt5.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String allTickets = "";
            for (String ticket : ticketManager.getAllTickets()) {
                allTickets += ticket + "\n";
            }
            alert.setHeaderText(allTickets.toString());
            alert.showAndWait();
        });

        bt6.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String all3DTickets = "";
            for (String ticket : ticketManager.get3DTickets()) {
                all3DTickets += ticket + "\n";
            }
            alert.setHeaderText(all3DTickets);
            alert.showAndWait();
        });

        bt7.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String allMoviePassTickets = "";
            for (String ticket : ticketManager.getMoviePassTickets()) {
                allMoviePassTickets += ticket + "\n";
            }
            alert.setHeaderText(allMoviePassTickets);
            alert.showAndWait();
        });

        bt8.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        bottomPane1.setAlignment(Pos.CENTER);
        for (Node node : bottomPane1.getChildren()) {
            HBox.setMargin(node, new Insets(5, 5, 5, 5));
        }
        bottomPane2.setAlignment(Pos.CENTER);
        for (Node node : bottomPane2.getChildren()) {
            HBox.setMargin(node, new Insets(5, 5, 10, 5));
        }
    }
}
