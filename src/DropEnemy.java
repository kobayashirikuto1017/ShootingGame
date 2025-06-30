
public class DropEnemy extends Enemy{
	public DropEnemy(double x, double y, double vx, double vy) {
		 super(x,y,vx,vy);
		 life=1+GameWorld.stage;
		 score = 15;
	}
	public void move() {
		super.move();
		vy+=0.1+(GameWorld.stage*0.3);
	}
	public void draw(MyFrame f) {
		 f.setColor(0,200,0);
		 f.fillRect(x, y-10, 30, 10);
		 f.setColor(0,200,0);
		 f.fillRect(x+10, y, 10, 20);
		 
	 }
}
