package Booking;

import java.util.*;
import java.util.concurrent.TimeUnit;
class Taxi {

	private int taxiId;
	private int currentPoint;
	private int pickupPoint;
	private int dropPoint;
	private long bookedTime;
	private double amount;
	
	public Taxi(int taxiId) {
		this.taxiId = taxiId;
		this.currentPoint = 0;
		this.pickupPoint = 0;
		this.dropPoint = 0;
		this.bookedTime = -1;
		this.amount = 0.0;
		
	}
	public int getTaxiId() {
		return taxiId;
	}
	public void setTaxiId(int taxiId) {
		this.taxiId = taxiId;
	}
	public int getCurrentPoint() {
		return currentPoint;
	}
	public void setCurrentPoint(int currentPoint) {
		this.currentPoint = currentPoint;
	}
	public long getBookedTime() {
		return bookedTime;
	}
	public void setBookedTime(long bookedTime) {
		this.bookedTime = bookedTime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount += amount;
	}
	public int getDropPoint() {
		return dropPoint;
	}
	public void setDropPoint(int dropPoint) {
		this.dropPoint = dropPoint;
	}
	public int getPickupPoint() {
		return pickupPoint;
	}
	public void setPickupPoint(int pickupPoint) {
		this.pickupPoint = pickupPoint;
	}
	public void bookingConfirmation() {
		// TODO Auto-generated method stub
		System.out.println("Taxi Id : " + this.taxiId);
		System.out.println("Pickup Point : " + this.pickupPoint);
		System.out.println("Destination : " + this.dropPoint);
		System.out.println("Total Amount : " + calcAmount(this.pickupPoint,this.dropPoint));
	}
	public static double calcAmount(int a,int b) {
		return ((b-a)-5)*10 + 5*100;
	}
	public boolean isAvailable(){
		if(bookedTime==-1) {
			//System.out.println("2" + Math.abs(TimeUnit.SECONDS.convert(bookedTime-System.nanoTime(),TimeUnit.NANOSECONDS)/60));
			System.out.println((int)Math.abs((bookedTime-System.nanoTime())/1_000_000_000)/60);
			return true;
		}
		else if((int)Math.abs((bookedTime-System.nanoTime())/1_000_000_000)<=Math.abs(pickupPoint-dropPoint)) {
			//System.out.println("2" + Math.abs(TimeUnit.SECONDS.convert(bookedTime-System.nanoTime(),TimeUnit.NANOSECONDS)/60));
			return true;
		}
		else {
			return false;
		}
	}
}

public class TaxiBook {
	static ArrayList<Taxi> taxi = new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for(int i=1;i<=4;i++) {
			Taxi t = new Taxi(i);
			taxi.add(t);
		}
		while(true) {
			System.out.println("Taxi Booking\n\n");
			System.out.println("Enter Your Name : ");
			String cname = sc.nextLine();
			System.out.println("Enter Pickup Point : ");
			int pp = sc.nextInt();
			System.out.println("Enter Drop Point : ");
			int dp = sc.nextInt();
			long bookingTime = System.currentTimeMillis();
			int flag=0;
			for(Taxi cab : taxi) {
				if(cab.isAvailable()) {
					bookingConfirmation(cab,pp,dp,bookingTime);
					System.out.println("Enter 1 to Confirm Booking : ");
					int temp = sc.nextInt();
					if(temp==1) {
						cab.setPickupPoint(pp);
						cab.setDropPoint(dp);
						cab.setCurrentPoint(dp);
						cab.setBookedTime(bookingTime);
						cab.setAmount(calcAmount(pp,dp));
						System.out.println("Booking Confirmed.");
						flag=1;
						break;
					}
					else {
						System.out.println("Your booking is Rejected.");
					}
				}
			}
			if(flag==0) {
				System.out.println("Due to heavy traffic no cabs are available at now. Please try again later.");
			}
			sc.nextLine();

		}

	}
	public static void bookingConfirmation(Taxi cab,int pp,int dp,long bt) {
		// TODO Auto-generated method stub
		System.out.println("Taxi Id : " + cab.getTaxiId());
		System.out.println("Pickup Point : " + pp);
		System.out.println("Destination : " + dp);
		System.out.println("Total Amount : " + calcAmount(pp,dp));
	}
	public static double calcAmount(int a,int b) {
		return (((b-a)*30)-5)*10 + 100;
	}
	public static Taxi getNearestCab(int pp) {
		int diff = Math.abs(taxi.get(0).getCurrentPoint()-pp);
		Taxi cab=null;
		for(Taxi t : taxi) {
			if(diff>Math.abs(t.getCurrentPoint()-pp)) {
				diff = Math.abs(t.getCurrentPoint()-pp);
				cab = t;
			}
		}
		return cab;
	}
	public static Taxi getLowEarnedTaxi(){
		double earned = taxi.get(0).getAmount();
		Taxi cab=null;
		for(Taxi t : taxi) {
			if(earned>t.getAmount()) {
				earned = t.getAmount();
				cab = t;
			}
		}
		return cab;
	}

}
