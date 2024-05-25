package SeranganBeruang;

import javafx.application.Platform;
import org.example.MainController;

public class Timer extends Thread {
    private int milisecond;
    private MainController mainController;
    public Timer(int milisecond, MainController mainController) {
        this.milisecond = milisecond;
        this.mainController = mainController;
    }

    @Override
    public void run() {
        while (milisecond > 0) {
            try {
                // System.out.println(milisecond);
                Platform.runLater(() -> {
                    mainController.updateTimerSeranganBeruang(milisecond);
                });
                Thread.sleep(100);
                milisecond -= 100;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Platform.runLater(() -> {
            mainController.get_pane_ladang().getChildren().remove(mainController.get_container_waktu_serangan());
        });
    }

    public int getMiliSecond() {
        return milisecond;
    }
}
