public class SplittingEnemy extends Enemy{
	boolean hasSplit = false;
	public SplittingEnemy(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		
		
		
		life = 5;
		score = 10;
	}
	public void move() {
		super.move();



		
	}
	// プレイヤーの弾から呼ばれるダメージ処理
	public void damage(int amount) {
		life -= amount;

		if (!hasSplit && life == 0) {
			hasSplit = true;

			

			// 子機を出現させる
			GameWorld.enemies.add(new SplittingEnemy_Jr(x+30,y,0,GameWorld.stage));
			GameWorld.enemies.add(new SplittingEnemy_Jr(x-30,y,0,GameWorld.stage));

			GameWorld.toRemove.add(this); 
			

		}
	}
	public void draw(MyFrame f) {
	    f.setColor(50, 200, 200);
	    f.fillRect(x + 8, y, 20, 20); // 頭

	    f.setColor(50, 150, 150);
	    f.fillRect(x, y + 20, 36, 20); // 体
	    f.fillRect(x, y + 40, 8, 10); // 左足
	    f.fillRect(x + 28, y + 40, 8, 10); // 右足
	}

	
}