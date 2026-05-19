
import java.util.ArrayList;
import java.util.Collections;

import enums.MonitorType;
import model.AudioPlayer;
import model.MoviePlayer;
import model.Product;
import model.Screen;

public class App {

    public static void main(String[] args) {
        AudioPlayer nugget = new AudioPlayer("Ipod Touch 3", "mp3");
        System.out.println(nugget.toString());

        Screen screen = new Screen("640x400", 40, 22);
        MoviePlayer moviePlayer = new MoviePlayer("Rexus Monitor 3000", screen, MonitorType.LCD);
        System.out.println(moviePlayer.toString());

        ArrayList<Product> products = new ArrayList<>();
        products.add(nugget);
        products.add(moviePlayer);
        Collections.sort(products);

        print(products);
    }

    static void print(ArrayList<Product> products) {
        for (Product p1 : products) {
            System.out.println(p1.toString());
        }
    }
}
