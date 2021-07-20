package gUI;

import AOC.AOC;
import Action.Action;
import GObject.GObject;
import Transformation.Transformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import mainFrameInterface.mainFrameImplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Controller {
        private boolean started = false;
        ArrayList<Action> actionsforobject = new ArrayList<Action>();
    ArrayList<Action> hotkeyActionsforobject = new ArrayList<Action>();
        int index = 0;
        @FXML
        private Button startbutton;
        @FXML
        private Button previebutton;
        @FXML
        private Button settings;
        @FXML
        private ChoiceBox<Action> action;
        @FXML
        private ChoiceBox<GObject> object;
        @FXML
        private ColorPicker dcolor;
        @FXML
        private Button actionadd;
        @FXML
        private ChoiceBox<Transformation> transformation;
        @FXML
        private Button transadd;
        @FXML
        private Button objectadd;
        @FXML
        private ListView translist;
        @FXML
        private ScrollPane scrollAction;
        @FXML
        private Button transremove;
        @FXML
        private Button removeobject;
        @FXML
        private TextField name;
        @FXML
        private TextField hotkey;
        @FXML
        void button(ActionEvent event) {
        }
        @FXML
        private ListView actionlist;
        @FXML
        private TextField av1;
        @FXML
        private TextField av2;
        @FXML
        private TextField av3;
        @FXML
        private TextField ov1;

        @FXML
        private TextField ov2;

        @FXML
        private TextField ov3;

        @FXML
        private TextField ov4;

        @FXML
        private TextField ov5;

        @FXML
        private TextField ov6;

        @FXML
        private TextField ov7;

        @FXML
        private TextField ov8;

        @FXML
        private TextField ov9;
        @FXML
        private ListView objectlist;
        @FXML
         private Button eddit;
        @FXML
        private Button edditAction;
        @FXML
        private ColorPicker color1;

        @FXML
        private ColorPicker color2;
        @FXML
        private AnchorPane mainwindow;

    AOC aoc;
    ArrayList<AOC> aocs;
    private boolean previewb;

//TODO just display action that the object can actually use
    public void initialize() {
            ObservableList<Action> al = FXCollections.observableArrayList(Main.actions);
            action.setItems(al);
            ObservableList<GObject> gl = FXCollections.observableArrayList(Main.gObjects);
            object.setItems(gl);
        ObservableList<Transformation> tf = FXCollections.observableArrayList(Main.transformations);
        transformation.setItems(tf);
            object.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                   setValuesforObject();
                }
            });
            action.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setValuesforAction();
                }
            });


            color1.setDisable(true);
            color2.setDisable(true);

            previewb = false;
            hotkey.setText("");
            aocs = new ArrayList<AOC>();
            //actionlist.getChildren().add(list);

    }
    public void start(){
        if(started){
            startbutton.setText("Start");
            started = false;
            if(previewb)
              aocs = Main.mainframe.getAOC();
            Main.mainframe.stopit();
            Main.mainframe =  null;
            previebutton.setDisable(false);
            previewb = false;
                  /*  Main.actions = new ArrayList<Action>();
                    Main.gObjects = new ArrayList<GObject>();*/
            // GObject.reset();
        }
        else  {
            if(Main.mainframe == null) {
                resetmainframe();
            }
            if(!previewb) {
                Main.mainframe.saveallAOC(aocs);
                previebutton.setDisable(true);
            }
            ArrayList<Transformation> transformations = new ArrayList<>();
            transformations.addAll(translist.getItems());
            Main.mainframe.setTransformation(transformations);
            Main.mainframe.startup();
            startbutton.setText("Stop");
            started = true;
        }


    }
    public void preview(){
        if(previewb){
            previewb = false;
            previebutton.setText("Preview");
            start();

        }

        else {
            previewb = true;
            previebutton.setDisable(previewb);
            start();
        }
        // startbutton.setDisable(previewb);
    }

    public void edditAOC(){
        aoc = (AOC)objectlist.getSelectionModel().getSelectedItems().get(0);
        System.out.println(aoc);
        name.setText(aoc.name);
        object.setValue(aoc.gObject);
        removeshit();
        ArrayList<Action> tmp = new ArrayList<>();
        tmp.addAll(aoc.actions);
        tmp.addAll(aoc.hotleyActions);
        actionlist.setItems(FXCollections.observableArrayList(tmp));
    }
    public void edditAction(){
        action.setValue((Action)actionlist.getSelectionModel().getSelectedItems().get(0));
        action.setDisable(true);
        actionadd.setText("save");
    }
    private void removeshit(){

        ArrayList<Action> tmp = new ArrayList<Action>(aoc.actions);

        tmp.forEach(action1 -> {
            if(action1.id == -1)
                aoc.actions.remove(action1);
        });
    }


    public void preparelistforeddit(ArrayList<String> nfv, ArrayList<Double> nfvd, TextField tf, int index){
        if(nfv.get(index) == "null")
            setTexttoField(nfv.get(index), tf);
        else if(nfvd.get(0) == null) {
            setTexttoField("i", tf);
            nfvd.remove(0);
        }else
        setTexttoField(nfvd.remove(0).toString(), tf);

    }
    public void setValuesforObject(){

        if(object.getValue() != null) {
            ArrayList<String> nfv = new ArrayList<>();
            Main.gObjects.forEach(gObject -> {
                if(gObject.name == object.getValue().name)
                    nfv.addAll(gObject.getNameforValues());
            });
            if(object.getValue().values == null || object.getValue().values.isEmpty()) {

                setTexttoField(nfv.get(0), ov1);
                setTexttoField(nfv.get(1), ov2);
                setTexttoField(nfv.get(2), ov3);
                setTexttoField(nfv.get(3), ov4);
                setTexttoField(nfv.get(4), ov5);
                setTexttoField(nfv.get(5), ov6);
                setTexttoField(nfv.get(6), ov7);
                setTexttoField(nfv.get(7), ov8);
                setTexttoField(nfv.get(8), ov9);
            }else{
                System.out.println(object.getValue());
                ArrayList<Double> nfvd = object.getValue().values;
                preparelistforeddit(nfv, nfvd, ov1, 0);
                preparelistforeddit(nfv, nfvd, ov2, 1);
                preparelistforeddit(nfv, nfvd, ov3, 2);
                preparelistforeddit(nfv, nfvd, ov4, 3);
                preparelistforeddit(nfv, nfvd, ov5, 4);
                preparelistforeddit(nfv, nfvd, ov6, 5);
                preparelistforeddit(nfv, nfvd, ov7, 6);
                preparelistforeddit(nfv, nfvd, ov8, 7);
                preparelistforeddit(nfv, nfvd, ov9, 8);

                            }
        }

    }

    public void setValuesforAction(){
        if(action.getValue() != null){
            ArrayList<String> nfv = new ArrayList<>();
            Main.actions.forEach(action1 -> {
                if(action1.id == action.getValue().getId())
                    nfv.addAll(action1.getNameForValues());
            });

        if(action.getValue().getValues() == null || action.getValue().getValues().isEmpty()) {
            setTexttoField(nfv.get(0), av1);
            setTexttoField(nfv.get(1), av2);
            setTexttoField(nfv.get(2), av3);
            if(nfv.get(0) == "color Picker"){
                color1.setDisable(false);
                av1.setDisable(true);
            }else color1.setDisable(true);
            if(nfv.get(1) == "color Picker"){
                color2.setDisable(false);
                av2.setDisable(true);

            }else color2.setDisable(true);
        }else{
            ArrayList<Double> valuesforeddit = action.getValue().getValues();
            if(nfv.get(1) == "color Picker"){
                preparelistforeddit(nfv,valuesforeddit, av3, 0);
                color1.setValue(action.getValue().getColers().get(0));
                color2.setValue(action.getValue().getColers().get(1));
            }else{

            preparelistforeddit(nfv,valuesforeddit, av1, 0);
            preparelistforeddit(nfv,valuesforeddit, av2, 1);
            preparelistforeddit(nfv,valuesforeddit, av3, 2);

        }}
        if(action.getValue().getHotkey() != null)
            hotkey.setText(action.getValue().getHotkey());
        else hotkey.setText("");
        }
    }
    public void setTexttoField(String text, TextField textField){
        if(text != "null") {
            textField.setText(text);
            textField.setEditable(true);
            textField.setDisable(false);
        }else {
            textField.setText("");
            textField.setEditable(false);
            textField.setDisable(true);
        }
    }
    public void addTransformation(){
        if(transformation.getValue() != null){
            Transformation newtrans = new Transformation(transformation.getValue().getTransformationName(), transformation.getValue().getId());
            translist.getItems().add(newtrans);

        }

    }
    public void removeTransformation(){
        if(!translist.getItems().isEmpty()){
            Transformation toberemoved = (Transformation) translist.getSelectionModel().getSelectedItems().get(0);
            translist.getItems().remove(toberemoved);


        }
    }
    public void addActiontoAOC(){

                if(action.getValue() != null && !actionlist.getItems().contains(action.getValue())) {
                    Action newaction = new Action(action.getValue().actionName, action.getValue().id);
                    ArrayList<Double> valuesforaction = new ArrayList<>();
                    if(!color1.isDisable()&& !color2.isDisable()) {
                        ArrayList<Color> tmp = new ArrayList<>();
                        tmp.add(color1.getValue());
                        tmp.add(color2.getValue());
                        newaction.setColers(tmp);
                        valuesforaction.add(Double.parseDouble(av3.getText()));
                        newaction.setValues(valuesforaction);
                    }else if(!av1.getText().isEmpty()) {
                        valuesforaction.add(Double.parseDouble(av1.getText()));
                        valuesforaction.add(Double.parseDouble(av2.getText()));
                        valuesforaction.add(Double.parseDouble(av3.getText()));
                        newaction.setValues(valuesforaction);
                    }
                        if(hotkey.getText() != "") {
                            newaction.setHotkey(hotkey.getText());
                        }else newaction.setHotkey("");

                        actionlist.getItems().add(newaction);
                }else if(actionadd.getText() == "save"){
                    ArrayList<Double> valuesforaction = new ArrayList<>();
                    if(!color1.isDisable()&& !color2.isDisable()) {

                        ArrayList<Color> tmp = new ArrayList<>();
                        tmp.add(color1.getValue());
                        tmp.add(color2.getValue());

                        action.getValue().setColers(tmp);
                        valuesforaction.add(Double.parseDouble(av3.getText()));
                        action.getValue().setValues(valuesforaction);
                    }else {
                        valuesforaction.add(Double.parseDouble(av1.getText()));
                        valuesforaction.add(Double.parseDouble(av2.getText()));
                        valuesforaction.add(Double.parseDouble(av3.getText()));
                        action.getValue().setValues(valuesforaction);
                    }
                    if(hotkey != null) {
                        action.getValue().setHotkey(hotkey.getText());
                    }else action.getValue().setHotkey("");
                    actionadd.setText("add");
                    action.setDisable(false);
                }
                hotkey.setText("");
    }

    private void resetmainframe(){
        Main.mainframe = new mainFrameImplementation();
        Main.mainframe.start();
        aocs.forEach(aoc1 -> Main.mainframe.saveAOC(aoc1));

    }



    public void saveAOC(ActionEvent e){
        if(Main.mainframe == null) {
            resetmainframe();

        }
        ArrayList<Action> InternActionsForObject = new ArrayList<>();
        InternActionsForObject.addAll(actionlist.getItems());
        actionsforobject.addAll(InternActionsForObject);
        InternActionsForObject.forEach(action1 -> {
            if(action1.getHotkey() != "") {
                hotkeyActionsforobject.add(action1);
                actionsforobject.remove(action1);
            }
        });
        ArrayList<Double> tmp = new ArrayList<>();
        if(!ov1.getText().isEmpty()) {
            if(ov1.getText().contains("i"))
                tmp.add(null);
            else
              //  System.out.println(ov1.getText());
            tmp.add(Double.parseDouble(ov1.getText()));
        }
        if(!ov2.getText().isEmpty()) {
            if(ov2.getText().contains("i"))
                tmp.add(null);
            else
            tmp.add(Double.parseDouble(ov2.getText()));
        }
        if(!ov3.getText().isEmpty()){
            if(ov3.getText().contains("i"))
                tmp.add(null);
            else
                    tmp.add(Double.parseDouble(ov3.getText()));
}
        if(!ov4.getText().isEmpty()){
            if(ov4.getText().contains("i"))
                tmp.add(null);
            else
                    tmp.add(Double.parseDouble(ov4.getText()));
}
        if(!ov5.getText().isEmpty()){
            if(ov5.getText().contains("i"))
                tmp.add(null);
            else
                    tmp.add(Double.parseDouble(ov5.getText()));
        }
        if(!ov6.getText().isEmpty()){
            if(ov6.getText().contains("i"))
                tmp.add(null);
            else
                    tmp.add(Double.parseDouble(ov6.getText()));
        }
        if(!ov7.getText().isEmpty()){
            if(ov7.getText() == "i")
                tmp.add(null);
            else
                    tmp.add(Double.parseDouble(ov7.getText()));
        }
        if(!ov8.getText().isEmpty()){
            if(ov8.getText().contains("i"))
                tmp.add(null);
            else
                    tmp.add(Double.parseDouble(ov8.getText()));
        }
        if(!ov9.getText().isEmpty()){
            if(ov9.getText().contains("i"))
                tmp.add(null);
            else
                    tmp.add(Double.parseDouble(ov9.getText()));
        }


        boolean dontsave = true;
if(aoc == null) {
    GObject gObject = new GObject(object.getValue().name);
    gObject.setValues(tmp);
    aoc = new AOC(name.getText(), gObject, actionsforobject);
    aoc.setHotleyActions(hotkeyActionsforobject);
    dontsave = false;
}
String tmp2 = name.getText();
if(!aoc.name.equals(tmp2)) {
    GObject gObject = new GObject(object.getValue().name);
    gObject.setValues(tmp);
    aoc = new AOC(name.getText(), gObject, actionsforobject);
    aoc.setHotleyActions(hotkeyActionsforobject);
    dontsave = false;
}
        if(previewb) {
            Action change = new Action("change", 3);
           actionsforobject.add(change);
        }
        aoc.actions = actionsforobject;
        aoc.setHotleyActions(hotkeyActionsforobject);
        aoc.gObject.setValues(tmp);
        if(!
                aocs.contains(aoc))
        aocs.add(aoc);
        //System.out.println(Main.mainframe.saveAOC(aoc));


        if(!dontsave)
        objectlist.getItems().add(aoc);

        name.clear();
       actionlist.getItems().removeAll(actionsforobject);
       actionlist.getItems().removeAll(hotkeyActionsforobject);
       if(previewb) {
           Main.mainframe.view();
           Main.mainframe.saveAOC(aoc);
       }
       actionsforobject = new ArrayList<>();
       hotkeyActionsforobject = new ArrayList<>();

    }
    public void removeAOC(){
      AOC toberemoved = (AOC)objectlist.getSelectionModel().getSelectedItems().get(0);

        Action remove = new Action("remove" , 5);
        toberemoved.actions.add(remove);

        objectlist.getItems().remove(toberemoved);

        object.setValue(null);
        aoc = null;
        if(Main.mainframe != null){
            Main.mainframe.view();
            Main.mainframe.removeAOC(toberemoved);
            aocs.remove(toberemoved);
    }else
        aocs.remove(toberemoved);
    }

    public void removeAction(){
        actionlist.getItems().remove(actionlist.getSelectionModel().getSelectedItems().get(0));

    }

    }
