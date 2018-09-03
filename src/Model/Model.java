package Model;
import java.util.ArrayList;

import Observer.Observer;


public class Model {

	ArrayList<Observer> obs;
	
	public Model(){
		obs = new ArrayList<Observer>();
	}
	public void addObs(Observer view) {
		// TODO Auto-generated method stub
		this.obs.add(view);
	}
	public void init() {
		// TODO Auto-generated method stub
		for(Observer o: obs){
			o.init();
		}
	}

}
