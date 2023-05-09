package HM4;

public class Delivery {

	private double x;
	private double y;
	private int deliveryId;
	private int Heap1Index; // the index of element in central distribution 1 
	private int Heap2Index; // the index of element in central distribution 1 
	
	public Delivery(double x,double y, int deliveryId) {
		this.x = x;
		this.y = y;
		this. deliveryId = deliveryId;
	}
	
	public int getDeliveryId () {
		return deliveryId;
	}
	
	public void setDeliveryId (int deliveryId) {
		this. deliveryId = deliveryId;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public String toString() {
		return String.format("Delivery: [x= %4.2f y= %4.2f Id = %d]", x, y, deliveryId);
	}
	
	public double getDistanceFrom(double x, double y) {
		return Math.sqrt(Math.pow((this.x-x), 2) + Math.pow((this.y-y), 2));
	}

	public void setHeapIndex(int heapIndex,int heapId) { // get Heap id and update the right index
		if(heapId == 1) 
		{
			Heap1Index = heapIndex;
		}
		else if (heapId == 2) 
		{
			Heap2Index = heapIndex;
		}
		
	}

	public int getHeapIndex(int heapId) { // get Heap id and return the right index
		if(heapId == 1) 
		{
			return Heap1Index;
		}
		else if (heapId == 2) 
		{
			return Heap2Index;
		}
		return 0;
	}
}
