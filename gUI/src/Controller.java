import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

public static String test="ez";
    public void button(javafx.event.ActionEvent e) throws IOException {
        test ="2ez";
    Parent test2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Scene bla = new Scene(test2);
    Node n =  (Node)e.getSource();
    Stage s = (Stage)n.getScene().getWindow();
    s.setTitle(test);
    s.setScene(bla);
    s.show();




    }


}
