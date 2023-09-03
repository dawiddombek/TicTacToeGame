import javax.swing.*;
import java.awt.*;

public class FieldButton extends JButton {

    FieldButton(String text) {
        this.setSize(100, 100);
        this.setFont(new Font("", Font.PLAIN, 70));
        this.setText(text);
    }
}
