package SeranganBeruang;

import org.example.Board;
import org.example.Player;
import org.example.card.Hewan.Beruang;

import java.util.ArrayList;
import java.util.Random;
public class SeranganBeruang extends Thread{
    private final int time;
    private final int[] ukuran = new int[2];
    private final int[] firstIdx = new int[2];
    private Player player;
    public SeranganBeruang(Player player){
        this.player = player;
        int[][] pilihan = {{1,1}, {1,2}, {2,1}, {1,3}, {3,1}, {2,2}, {1,4}, {4,1}, {2,3}, {3,2}};
        Random random = new Random();
        time = random.nextInt(10,11); // dalam milisecond
        int idx = random.nextInt(pilihan.length); // milih index random (misal 5)
        ukuran[0] = pilihan[idx][0]; // tinggi
        ukuran[1] = pilihan[idx][1]; // lebar
        firstIdx[0] = random.nextInt(4 - ukuran[0]); // y
        firstIdx[1] = random.nextInt(5 - ukuran[1]); // x
        System.out.println(ukuran[0] + " " + ukuran[1]);
        System.out.println(time);
    }

    public int getTime() {
        return time;
    }

    @Override
    public void run() {
        try {
            synchronized (player.getLahan()) {
                if(isTrapHere()){
                    synchronized (player.getDeck_aktif()){
                        player.add_Deck_active(new Beruang());
                    }
                } else {
                    player.serangan(ukuran[0],ukuran[1],firstIdx[0],firstIdx[1]);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Boolean isTrapHere() {
        String trap = "Trap";
        for (int i = firstIdx[0]; i < player.getLahan().size(); i++) {
            for (int j = firstIdx[1]; j < player.getLahan().get(i).size(); j++) {
                if (player.getLahan().get(i).get(j) != null) {
                    if (player.getLahan().get(i).get(j).getName().equals(trap)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Player p = new Player("A", 100);
        SeranganBeruang a = new SeranganBeruang(p);
        Timer t = new Timer(a.time*10);
        t.start();
        System.out.println(t.getMiliSecond()*1000);
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
                System.out.println(t.getMiliSecond());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        try {
            Thread.sleep(a.time + 5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        a.start();
        System.out.println("Success");
    }
}
