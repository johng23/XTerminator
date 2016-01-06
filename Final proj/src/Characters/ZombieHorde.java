package Characters;

import java.util.ArrayList;

public class ZombieHorde implements Runnable {
	
	public ArrayList<Zombie> zombies;
	public int numZPassed;
	
	public ZombieHorde(ArrayList<Zombie> z, int num){
		zombies=z;
		numZPassed=num;
	}
	
	public void run(){
		if(Math.random() > .95) {
			zombies.add(new Zombie(1000,(int) (Math.random() * 200 + 130)));
		}
		for(int i = 0; i < zombies.size(); i++) {
			Zombie z = zombies.get(i);
			z.move();
			if(z.x < -5) {
				zombies.remove(i);
				System.out.println("swag");
				numZPassed++;
				i--;
			}
			else if(z.health <= 0) {
				zombies.remove(i);
				i--;
			}
		}
	}
}
