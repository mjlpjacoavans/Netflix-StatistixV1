package GUI;

import Database.ProfileDAO;
import Domain.Profile;
import javafx.scene.Scene;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Profiles {
    public Scene profileList(Stage stage, String email){
        AnchorPane mainPane = new AnchorPane();
        mainPane.prefHeight(800.0);
        mainPane.prefWidth(1600.0);
        mainPane.setStyle("-fx-background-color: #545454;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(407);
        scrollPane.setLayoutY(50);
        scrollPane.setMinWidth(1140);
        scrollPane.setMinHeight(700);
        scrollPane.setMaxHeight(700);

        VBox verticalBox = new VBox();
        verticalBox.setMinWidth(1160);
        verticalBox.setMinHeight(Region.USE_COMPUTED_SIZE);

        scrollPane.setStyle("-fx-background: #383838;");

        ProfileDAO profileDAO = new ProfileDAO();
        ArrayList<Profile> profileList = profileDAO.getAllProfiles(email);


        int count = 0;
        for (Profile profile: profileList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblName = new Label(profile.getName());
            lblName.getStyleClass().add("accountEmail");
            lblName.setLayoutX(65);
            lblName.setLayoutY(14);

            Label lblAge = new Label(Integer.toString(profile.getAge()));
            lblAge.getStyleClass().add("accountLabels");
            lblAge.setLayoutY(37);
            lblAge.setLayoutX(65);


            Button btnEdit = new Button("Edit");
            btnEdit.setLayoutX(1089);
            btnEdit.setLayoutY(55);
            btnEdit.setMinWidth(80);
            btnEdit.getStyleClass().add("accountButtons");

            Button btnDelete = new Button("Delete");
            btnDelete.setLayoutX(1089);
            btnDelete.setLayoutY(13);
            btnDelete.setMinWidth(80);
            btnDelete.getStyleClass().add("accountButtons");

            Button btnWatch = new Button("Bekijk programma");
            btnWatch.setLayoutX(910);
            btnWatch.setLayoutY(13);
            btnWatch.setMinWidth(142);
            btnWatch.getStyleClass().add("accountButtons");



            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255,255,255));

            accountPane.getChildren().addAll(lblName, lblAge);
            accountPane.getChildren().addAll(btnEdit, btnDelete, btnWatch);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);
        }

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        Button btnAddNewProfile = new Button("New Profile");
        btnAddNewProfile.getStyleClass().add("accountButtons");
        btnAddNewProfile.getStyleClass().add("newButton");
        btnAddNewProfile.setLayoutY(2);
        btnAddNewProfile.setLayoutX(407);

        mainPane.getChildren().addAll(menu.getMenu(stage),scrollPane, btnAddNewProfile);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }
}
