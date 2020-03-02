package pl.com.bohdziewicz.javaFxAndSpring;

import org.springframework.beans.factory.annotation.Value;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

@org.springframework.stereotype.Controller
public class Controller {

    @FXML
    private WebView myWebView;

    @Value(("${my.url}"))
    private String myUrl;

    @FXML
    private void initialize(){
        myWebView.getEngine().load(myUrl);
    }

}
