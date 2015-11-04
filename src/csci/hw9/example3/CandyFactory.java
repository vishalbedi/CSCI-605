package csci.hw9.example3;

import java.util.LinkedList;

public class CandyFactory {
	LinkedList<String> candyStore = new LinkedList<String>();
	LinkedList<String> wrapperStore = new LinkedList<String>();
	LinkedList<String> wrappedCandyStore = new LinkedList<String>();
	LinkedList<String> boxStore = new LinkedList<String>();
	LinkedList<String> finalCandyStore = new LinkedList<String>();
	
	private void createCandy(){
		Producer candyProducer = new Producer("Candy Produced: ", candyStore, finalCandyStore);
		candyProducer.start();
	}
	
	private void createWrapper(){
		Producer wrapperProducer = new Producer("Wrapper Produced: ",wrapperStore, 
				finalCandyStore);
		wrapperProducer.start();
	}
	
	private void createBox(){
		Producer boxProducer = new Producer("Boxes Produced: ",boxStore, finalCandyStore);
		boxProducer.start();
	}
	
	private void wrapCandy(){
		WrappingConsumer wrapConsumer = new WrappingConsumer(candyStore, 
				wrapperStore, wrappedCandyStore, finalCandyStore);
		wrapConsumer.setPriority(Thread.MAX_PRIORITY);
		wrapConsumer.start();
	}
	
	private void boxCandy(){
		BoxConsumer boxConsumer = new BoxConsumer(boxStore,
				wrappedCandyStore, finalCandyStore);
		boxConsumer.setPriority(Thread.MAX_PRIORITY);
		boxConsumer.start();
	}
	
	
	private void startProduction(){
		createCandy();
		createWrapper();
		createBox();
		wrapCandy();
		boxCandy();
	}
	
	public static void main(String args[]){
		new CandyFactory().startProduction();
	}
}
