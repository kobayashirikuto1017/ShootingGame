
public class CurveEnemy extends Enemy{
	public CurveEnemy(double x, double y, double vx, double vy) {
		 super(x,y,vx,vy);
		 life=1+GameWorld.stage;
		 score=5;
	}
	public void move() {
		super.move();
		if(x<GameWorld.player.x) {
			x+=GameWorld.stage;
			
		}
		if(x>GameWorld.player.x) {
			x-=GameWorld.stage;
			
		}
	
		
	}
	public void draw(MyFrame f) {
		f.setColor(0, 0, 0);
		f.fillOval(x, y, 30, 30);
		
		f.setColor(256,256, 256);
		  f.fillOval(x+5, y, 20, 30);
	}
	
}
