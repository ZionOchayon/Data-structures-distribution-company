package HM4;

public class DeliveryCenter {
	
	private Delivery Centraldistribution1;
	private Delivery Centraldistribution2;
	private Delivery MinHeap1[]; // close deliverers for Centraldistribution1
	private Delivery MinHeap2[]; // close deliverers for Centraldistribution2
	private int HeapLen;  // size in index of the heaps (heap size - 1) 

	
	public DeliveryCenter(int t,int x1,int y1,int x2,int y2) // Constructor
	{
		this.MinHeap1 = new Delivery[t];
		this.MinHeap2 = new Delivery[t];
		this.HeapLen = -1;
		this.Centraldistribution1 = new Delivery(x1,y1,1);
		this.Centraldistribution2 = new Delivery(x2,y2,2);
		// Time Complexity O(1)
	}
	
	public double distance(Delivery Shipment,int CenterId) // Calculate the distance for shipment to delivery center by center id
	{
		if(CenterId == Centraldistribution1.getDeliveryId()) 
		{
			return Math.sqrt(Math.pow(Shipment.getX()-Centraldistribution1.getX(),2)+Math.pow(Shipment.getY()-Centraldistribution1.getY(),2));
		}
		else if(CenterId == Centraldistribution2.getDeliveryId())
		{
			return Math.sqrt(Math.pow(Shipment.getX()-Centraldistribution2.getX(),2)+Math.pow(Shipment.getY()-Centraldistribution2.getY(),2));
		}
		return 0;
		// Time Complexity O(1)
	}
	
	private void MinHeapifyDown(Delivery[] heap,int headIndex,int CenterId) // ** Helper function** HeapifyDown get an id of heap and index to Heapify from
	{																		// and make the Heapify form him while updating the indexes of the of the heap
		int leftIndex = (2*headIndex+1);
		int RigthIndex = (2*headIndex+2);
		int smallest;
		if(leftIndex<=HeapLen && distance(heap[leftIndex],CenterId)<distance(heap[headIndex],CenterId))
		{
			smallest = leftIndex;
		}
		else 
		{
			smallest = headIndex;
		}
		if(RigthIndex<=HeapLen && distance(heap[RigthIndex],CenterId)<distance(heap[smallest],CenterId))
		{
			smallest = RigthIndex;
		}
		if(smallest != headIndex) 
		{
			Delivery tmp = heap[headIndex];
			heap[headIndex] = heap[smallest];
			heap[headIndex].setHeapIndex(headIndex, CenterId);
			heap[smallest] = tmp;
			heap[smallest].setHeapIndex(smallest, CenterId);
			MinHeapifyDown(heap,smallest,CenterId);
		}
		// Time Complexity O(Log(n))
	}
	
	private void MinHeapifyUp(Delivery[] heap,int elementIndex,int CenterId) // ** Helper function** HeapifyUp get an id of heap and index to heapify from														
	{																		// and make the Heapify form him while updating the indexes of the of the heap
		while(elementIndex > 0 && distance(heap[elementIndex],CenterId) < distance(heap[(elementIndex-1)/2],CenterId))
		{
			Delivery tmp = heap[elementIndex];
			heap[elementIndex] = heap[(elementIndex-1)/2];
			heap[elementIndex].setHeapIndex(elementIndex, CenterId);
			heap[(elementIndex-1)/2] = tmp;
			heap[(elementIndex-1)/2].setHeapIndex((elementIndex-1)/2, CenterId);
			elementIndex = (elementIndex-1)/2;
		}
		// Time Complexity O(Log(n))
	}
	
	public void add(Delivery data)  // add the delivery to the both of the heaps and update the indexes 
	{
		if(HeapLen < MinHeap1.length-1)
		{
			HeapLen++;
			MinHeap1[HeapLen] = data;
			MinHeap1[HeapLen].setHeapIndex(HeapLen,Centraldistribution1.getDeliveryId());
			MinHeap2[HeapLen] = data;
			MinHeap2[HeapLen].setHeapIndex(HeapLen,Centraldistribution2.getDeliveryId());
			MinHeapifyUp(MinHeap1,HeapLen,Centraldistribution1.getDeliveryId());
			MinHeapifyUp(MinHeap2,HeapLen,Centraldistribution2.getDeliveryId());
		}
		else 
		{
			System.out.println("Erorr, you rach the max number of elements");
		}
		// Time Complexity O(Log(n))
	}
	
	public Delivery getClosest(int centerNum) // get center id and return the closest to this center
	{
		if(HeapLen >=0) 
		{
			if(centerNum == Centraldistribution1.getDeliveryId()) 
			{
				return MinHeap1[0];
			}
			else if(centerNum == Centraldistribution2.getDeliveryId()) 
			{
				return MinHeap2[0];
			}
		}
		// Time Complexity O(1)
		return null;
	}

	public Delivery removeClosest(int i) // get center id and remove the closest delivery to him from the both of the heaps and update the indexes 
	{
		if(HeapLen < 0) 
		{
			return null;
		}
		else 
		{
			Delivery tmp = null;
			if(i == Centraldistribution1.getDeliveryId()) 
			{	
				// remove from Heap 1
				tmp = MinHeap1[0];
				MinHeap1[0] = MinHeap1[HeapLen];
				MinHeap1[0].setHeapIndex(0, i);
				HeapLen--;
				MinHeapifyDown(MinHeap1,0,i);
				
				// remove from Heap 2
				MinHeap2[tmp.getHeapIndex(Centraldistribution2.getDeliveryId())] = MinHeap2[HeapLen+1]; 
				MinHeap2[tmp.getHeapIndex(Centraldistribution2.getDeliveryId())].setHeapIndex(tmp.getHeapIndex(Centraldistribution2.getDeliveryId()), Centraldistribution2.getDeliveryId());
				MinHeapifyDown(MinHeap2,tmp.getHeapIndex(Centraldistribution2.getDeliveryId()),Centraldistribution2.getDeliveryId());
				MinHeapifyUp(MinHeap2,tmp.getHeapIndex(Centraldistribution2.getDeliveryId()),Centraldistribution2.getDeliveryId());
			}
			else if(i == Centraldistribution2.getDeliveryId()) 
			{
				// remove from Heap 2
				tmp = MinHeap2[0];
				MinHeap2[0] = MinHeap2[HeapLen];
				MinHeap2[0].setHeapIndex(0, i);
				HeapLen--;
				MinHeapifyDown(MinHeap2,0,i);
				
				// remove from Heap 1
				MinHeap1[tmp.getHeapIndex(Centraldistribution1.getDeliveryId())] = MinHeap1[HeapLen+1];
				MinHeap1[tmp.getHeapIndex(Centraldistribution1.getDeliveryId())].setHeapIndex(tmp.getHeapIndex(Centraldistribution1.getDeliveryId()), Centraldistribution1.getDeliveryId());
				MinHeapifyDown(MinHeap1,tmp.getHeapIndex(Centraldistribution1.getDeliveryId()),Centraldistribution1.getDeliveryId());
				MinHeapifyUp(MinHeap1,tmp.getHeapIndex(Centraldistribution1.getDeliveryId()),Centraldistribution1.getDeliveryId());
			}
			return tmp;
		}
		// Time Complexity O(Log(n))
	}

	public int size() 
	{
		return HeapLen+1;
		// Time Complexity O(1)
	}
	
	public String toString() // toString
	{
		String details = "";
		details = details.concat("Delivery locations. size: " + size() + "\n");
		for(int i=0;i<=HeapLen;i++) 
		{
			details = details.concat(MinHeap1[i] + " ");
		}
		details = details.concat("\n");
		return details;
	}
	// Time Complexity O(n)
}
