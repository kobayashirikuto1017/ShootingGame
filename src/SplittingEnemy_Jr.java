
public class SplittingEnemy_Jr extends Enemy{
	public SplittingEnemy_Jr(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		life = 1;
	    score = 2;
	}
	public void move() {
		super.move();



		
	}
	public void draw(MyFrame f) {
	    f.setColor(100, 100, 100); // 頭（センサー）
	    f.fillRect(x + 5, y, 8, 4);

	    f.setColor(80, 80, 200); // 胴体（タンク本体）
	    f.fillRect(x + 3, y + 4, 12, 8);

	    f.setColor(150, 100, 50); // キャタピラ足
	    f.fillRect(x, y + 12, 5, 4);
	    f.fillRect(x + 15, y + 12, 5, 4);
	}
}
