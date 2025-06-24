import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GameWorld {
    static Player player;
    static Vector<PlayerBullet> playerBullets;
    static Vector<Enemy> enemies;
    static List<Enemy> toRemove = new ArrayList<>();
    static List<Enemy> toAdd = new ArrayList<>();
    static boolean enterPressed;
    static int stage;
    static int score;
}
