
public class RandomEnemy extends Enemy {
 public RandomEnemy(double x, double y, double vx, double vy) {
	 super(x,y,vx,vy);
	 life=2+GameWorld.stage;
	 score = 10;
 }
 public void move() {
	 super.move();
		 vx=Math.random()*(4+GameWorld.stage)-(2+GameWorld.stage);
	 }
 public void draw(MyFrame f) {
	 f.setColor(0,200,0);
	 f.fillRect(x, y+20, 10, 10);
	 f.setColor(0,200,0);
	 f.fillRect(x-10, y, 10, 20);
	 f.setColor(0,200,0);
	 f.fillRect(x+10, y, 10, 20);
	 
 }
 }

