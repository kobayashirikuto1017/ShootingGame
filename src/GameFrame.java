import java.util.Vector;

public class GameFrame extends MyFrame{
	public void run() {
		GameWorld.player=new Player(100,300,0,0);
		addKeyListener(GameWorld.player);
		GameWorld.stage=1;
		GameWorld.score=0;
		while(true) {
			GameWorld.player.x=100;
			GameWorld.player.y=300;

			GameWorld.playerBullets= new Vector<PlayerBullet>();
			GameWorld.enemies= new Vector<Enemy>();
			GameWorld.enemies.add(new EnemyBase(100,50,GameWorld.stage,0));
			GameWorld.enterPressed=false;
			while(true){
				clear();
				repaint();
				drawString("stage="+GameWorld.stage,300,50,15);
				drawString("score="+GameWorld.score,300,80,15);
				GameWorld.player.draw(this);
				GameWorld.player.move();
				movePlayerBullets();
				moveEnemies();
				CheckPlayerAndEnemies();
				CheckPlayerBulletsAndEnemies();
				if(GameWorld.enemies.size()==0) {
					setColor(0,0,0);
					drawString("クリア！！",100,200,40);
					if(GameWorld.enterPressed) {
						GameWorld.stage++;
						break;
					}
				}else if(GameWorld.player.y<0){
					setColor(0,0,0);
					drawString("ゲームオーバー！！",50,200,40);
					if(GameWorld.enterPressed) {
						GameWorld.stage=1;
						GameWorld.score=0;
						break;
					}
				}

				sleep(0.03);
				GameWorld.enemies.addAll(GameWorld.toAdd);
				GameWorld.enemies.removeAll(GameWorld.toRemove);
				GameWorld.toAdd.clear();
				GameWorld.toRemove.clear();

			}
		} 

	}

	public void movePlayerBullets() {
		int i=0;
		while(i<GameWorld.playerBullets.size()) {
			PlayerBullet b=GameWorld.playerBullets.get(i);
			b.draw(this);
			b.move();
			if(b.y<0) {
				GameWorld.playerBullets.remove(i);

			}else {
				i++;
			}
		}
	}
	public void moveEnemies() {
		for(int i=0; i<GameWorld.enemies.size();i++) {
			Enemy e=GameWorld.enemies.get(i);
			e.draw(this);
			e.move();
		}
		int i=0;
		while (i<GameWorld.enemies.size()) {
			Enemy e=GameWorld.enemies.get(i);
			if(e.y>400) {
				GameWorld.enemies.remove(i);
			}else {
				i++;
			}
		}
	}
	public void CheckPlayerAndEnemies() {
	    if (GameWorld.player.isInvincible()) return; // 無敵中は当たり判定をスキップ！

	    for (int i = 0; i < GameWorld.enemies.size(); i++) {
	        Enemy e = GameWorld.enemies.get(i);
	        if (cheakHit(GameWorld.player, e)) {
	            GameWorld.player.damage(1);
	            break; // 多段ヒット防止
	        }
	    }
	}
	public void CheckPlayerBulletsAndEnemies() {
		int i=0;
		while(i<GameWorld.playerBullets.size()) {
			PlayerBullet b=GameWorld.playerBullets.get(i);
			int j=0;
			int hits=0;
			while(j<GameWorld.enemies.size()) {
				Enemy e=GameWorld.enemies.get(j);
				//衝突判定
				if(cheakHit(e,b)) { 
					System.out.println("あたり");


					if (e instanceof SplittingEnemy) {
						hits++;

						((SplittingEnemy) e).damage(1);
					}else {
						hits++;
						e.life--;
					}

				}
				if(e.life<=0) { 
					GameWorld.score+=e.score;
					GameWorld.enemies.remove(j);
				}else {
					j++;
				}

			}
			if(hits>0) {
				GameWorld.playerBullets.remove(i);

			}else {
				i++;
			}

		}

	}
	public boolean cheakHit(Character a,Character b) {
		if(Math.abs(a.x-b.x)<=20 && Math.abs(a.y-b.y)<=20) {
			return true;
		}else {
			return false;
		}
	}

}
