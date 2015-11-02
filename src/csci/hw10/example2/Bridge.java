package csci.hw10.example2;

public class Bridge {
	int maxWeight;
	Direction currentDirection;
	int maxNumberOfVehicles;
		
	public Bridge(int maxNumberOfVehicles, int maxWeight) {
		this.maxWeight = maxWeight;
		this.maxNumberOfVehicles = maxNumberOfVehicles;
	}
}
