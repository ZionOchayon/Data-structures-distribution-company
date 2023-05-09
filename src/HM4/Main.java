package HM4;

public class Main {

	public static void main(String[] args) {
		DeliveryCenter center = new DeliveryCenter(100,20,20,10,30);
		
		center.add(new Delivery(10, 28, 1));
		center.add(new Delivery(10, 31, 2));
		center.add(new Delivery(40, 30, 3));
		center.add(new Delivery(19, 22, 4));
		center.add(new Delivery(20, 21, 5));
		
		System.out.println("*******\n Init the delivery Center:\n");
		System.out.println(center);
		
		System.out.println("Find the closest from the 1st center");
		// O(1) for finding the closest delivery
		System.out.println(center.getClosest(1));
		
		// O(logn) for removing the closest delivery from the 2nd center
		Delivery deli = center.removeClosest(1);
		
		System.out.println("Find the closest from the 2nd center");
		// O(1) for finding the minimal
		System.out.println(center.getClosest(2));
		
		// O(logn) for removing the minimal from the second center
		deli= center.removeClosest(2);
		
		System.out.println("********\n After the changes:");
		System.out.println(center);
		}
}
