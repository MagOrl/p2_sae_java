
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.skin.ButtonSkin;
import javafx.util.Duration;

// code prit de https://stackoverflow.com/questions/49831755/javafx-animation-on-button-hover
public class MyButtonSkin extends ButtonSkin {
 
    public MyButtonSkin(Button control) {
        super(control);

        final FadeTransition fadeIn = new FadeTransition(Duration.millis(100));
        fadeIn.setNode(control);
        fadeIn.setToValue(0.6);
        control.setOnMouseEntered(e -> fadeIn.playFromStart());

        final FadeTransition fadeOut = new FadeTransition(Duration.millis(100));
        fadeOut.setNode(control);
        fadeOut.setToValue(1);
        control.setOnMouseExited(e -> fadeOut.playFromStart());

        control.setOpacity(1);
    }

}