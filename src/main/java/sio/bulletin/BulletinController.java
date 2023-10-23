package sio.bulletin;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sio.bulletin.Model.Etudiant;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BulletinController implements Initializable {
    DecimalFormat df;
    HashMap<String, HashMap<String, ArrayList<Etudiant>>> lesBulletins;
    @FXML
    private AnchorPane apBulletin;
    @FXML
    private ListView lvMatieres;
    @FXML
    private ListView lvDevoirs;
    @FXML
    private ComboBox cboTrimestres;
    @FXML
    private TextField txtNomEtudiant;
    @FXML
    private TextField txtNote;
    @FXML
    private Button btnValider;
    @FXML
    private AnchorPane apMoyenne;
    @FXML
    private Button btnMenuBulletin;
    @FXML
    private Button btnMenuMoyenne;
    @FXML
    private ListView lvMatieresMoyenne;
    @FXML
    private TreeView tvMoyennesParDevoirs;
    @FXML
    private TextField txtMajor;
    @FXML
    private TextField txtNoteMaxi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apBulletin.toFront();
        df = new DecimalFormat("#.##");
        lesBulletins = new HashMap<String, HashMap<String, ArrayList<Etudiant>>>();
        lvMatieres.getItems().addAll("Maths", "Anglais", "Economie");
        lvDevoirs.getItems().addAll("Devoir n°1", "Devoir n°2", "Devoir n°3", "Devoir n°4");
        cboTrimestres.getItems().addAll("Trim 1", "Trim 2", "Trim 3");
        cboTrimestres.getSelectionModel().selectFirst();
    }

    @FXML
    public void btnMenuClicked(Event event) {
        if (event.getSource() == btnMenuBulletin) {
            apBulletin.toFront();
        } else if (event.getSource() == btnMenuMoyenne) {
            apMoyenne.toFront();
        }
    }

    @FXML
    public void btnValiderClicked(Event event) {
        if (lvMatieres.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix de la Matière");
            alert.setHeaderText("");
            alert.setContentText("Sélectionner une matiere");
            alert.showAndWait();
        } else if (lvDevoirs.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix du Devoir");
            alert.setHeaderText("");
            alert.setContentText("Sélectionner un Devoir");
            alert.showAndWait();
        } else if (txtNomEtudiant.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix d'un étudiant");
            alert.setHeaderText("");
            alert.setContentText("Saisir un étudiant");
            alert.showAndWait();
        } else if (txtNote.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Choix d'une note");
            alert.setHeaderText("");
            alert.setContentText("Saisir une note");
            alert.showAndWait();
        } else {
            Etudiant etudiant = new Etudiant(txtNomEtudiant.getText(), Integer.parseInt(txtNote.getText()));
            if (!lesBulletins.containsKey(lvMatieres.getSelectionModel().getSelectedItem())) {
                ArrayList<Etudiant> lesEtudiants = new ArrayList<>();
                lesEtudiants.add(etudiant);


                HashMap<String, ArrayList<Etudiant>> lesDevoirs = new HashMap<>();
                lesDevoirs.put(lvDevoirs.getSelectionModel().getSelectedItem().toString(), lesEtudiants);
                lesBulletins.put(lvMatieres.getSelectionModel().getSelectedItem().toString(), lesDevoirs);
            } else if (!lesBulletins.get(lvMatieres.getSelectionModel().getSelectedItem().toString()).containsKey(lvDevoirs.getSelectionModel().getSelectedItem().toString())) {

                ArrayList<Etudiant> lesDevoirs = new ArrayList<>();
                lesDevoirs.add(etudiant);
                lesBulletins.get(lvMatieres.getSelectionModel().getSelectedItem().toString()).put(lvDevoirs.getSelectionModel().getSelectedItem().toString(), lesDevoirs);


            } else {
                lesBulletins.get(lvMatieres.getSelectionModel().getSelectedItem().toString()).get(lvDevoirs.getSelectionModel().getSelectedItem().toString()).add(etudiant);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Affectation réussi");
            alert.setHeaderText("");
            alert.setContentText("Montant enregistrer");
            alert.show();


        }
        // RemplirTreeView();

        // A vous de jouer

    }

    @FXML
    public void lvMatieresMoyenneClicked(Event event) {
        String matSelec = lvMatieresMoyenne.getSelectionModel().getSelectedItem().toString();
        for (Etudiant etudiant : lesBulletins.get(matSelec).get(tvMoyennesParDevoirs.getSelectionModel().getSelectedItem().toString())) {
          /*  if ()
            {
                ArrayList<Etudiant> letudiant = new ArrayList<>();
                letudiant.add(etudiant);

            }
            else
            {

            }

        } */


        }
        // A vous de jouer
        // RemplirTreeViewDevoir();



    }
}



