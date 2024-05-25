package SeranganBeruang;

public class Timer extends Thread {
    private int milisecond;
    Timer(int milisecond) {
        this.milisecond = milisecond;
    }

    @Override
    public void run() {
        while (milisecond != 0) {
            try {
//                System.out.println(milisecond);
                Thread.sleep(100);
                milisecond--;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getMiliSecond() {
        return milisecond;
    }
}
