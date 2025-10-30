import java.util.*;

class Passenger {

    String trainName;
    int trainNumber;
    String passengerName;

    int passengerAge;
    String passengerCoach;
    int passengerBerth;

    boolean passengerStatus;

    public Passenger(String trainName, int trainNumber, String passengerName, int passengerAge, boolean status,

            int berthNo, String coach) {

        this.trainName = trainName;
        this.trainNumber = trainNumber;

        this.passengerName = passengerName;
        this.passengerAge = passengerAge;

        this.passengerStatus = status;

        this.passengerBerth = berthNo;
        this.passengerCoach = coach;

    }

    public void printDetails() {

        System.out.println("Name: " + passengerName + ", Age: " + passengerAge +

                ", Coach: " + passengerCoach + ", Berth: " + passengerBerth +

                ", Status: " + (passengerStatus ? "Confirmed" : "Waiting"));

    }

}

class WaitingQueue {

    private Passenger[] queue;

    private int front, rear, size, capacity;

    public WaitingQueue(int capacity) {

        this.capacity = capacity;

        this.queue = new Passenger[capacity];

        this.front = 0;

        this.rear = -1;

        this.size = 0;

    }

    public boolean isFull() {

        return size == capacity;

    }

    public boolean isEmpty() {

        return size == 0;

    }

    public void enqueue(Passenger p) {

        if (isFull()) {

            System.out.println("Waiting list full. Cannot add " + p.passengerName);

            return;

        }

        rear = (rear + 1) % capacity;

        queue[rear] = p;

        size++;

    }

    public Passenger dequeue() {

        if (isEmpty())
            return null;

        Passenger removed = queue[front];

        front = (front + 1) % capacity;

        size--;

        return removed;

    }

    public Passenger peek() {

        return isEmpty() ? null : queue[front];

    }

    public void display() {

        if (isEmpty())
            return;

        for (int i = 0; i < size; i++) {

            int idx = (front + i) % capacity;

            queue[idx].printDetails();

        }

    }

    public boolean removeByName(String name) {

        if (isEmpty())
            return false;

        int idx = front;

        for (int i = 0; i < size; i++) {

            if (queue[idx].passengerName.equalsIgnoreCase(name)) {

                for (int j = i; j < size - 1; j++) {

                    int currIdx = (front + j) % capacity;

                    int nextIdx = (front + j + 1) % capacity;

                    queue[currIdx] = queue[nextIdx];

                }

                rear = (rear - 1 + capacity) % capacity;

                size--;

                return true;

            }

            idx = (idx + 1) % capacity;

        }

        return false;

    }

}

public class CircularQueue {

    static final int MAX_CONFIRMED = 5;

    static final int MAX_WAITING = 3;

    static List<Passenger> confirmedList = new ArrayList<>();

    static WaitingQueue waitingList = new WaitingQueue(MAX_WAITING);

    static int berthCounter = 1;

    public static void bookTicket(String trainName, int trainNumber, String name, int age) {

        if (confirmedList.size() < MAX_CONFIRMED) {

            String coach = "S1";

            confirmedList.add(new Passenger(trainName, trainNumber, name, age, true, berthCounter++, coach));

            System.out.println("Ticket Confirmed for " + name);

        } else if (!waitingList.isFull()) {

            Passenger p = new Passenger(trainName, trainNumber, name, age, false, -1, "WL");

            waitingList.enqueue(p);

            System.out.println("Added to Waiting List for " + name);

        } else {

            System.out.println("No tickets available for " + name);

        }

    }

    public static void cancelTicket(String name) {

        boolean found = false;

        for (int i = 0; i < confirmedList.size(); i++) {

            if (confirmedList.get(i).passengerName.equalsIgnoreCase(name)) {

                confirmedList.remove(i);

                found = true;

                System.out.println("Cancelled confirmed ticket of " + name);

                Passenger moved = waitingList.dequeue();

                if (moved != null) {

                    moved.passengerStatus = true;

                    moved.passengerCoach = "S1";

                    moved.passengerBerth = berthCounter++;

                    confirmedList.add(moved);

                    System.out.println("Moved from waiting to confirmed: " + moved.passengerName);

                }

                break;

            }

        }

        if (!found) {

            if (waitingList.removeByName(name)) {

                System.out.println("Cancelled waiting ticket of " + name);

                found = true;

            }

        }

        if (!found) {

            System.out.println("No ticket found under the name: " + name);

        }

    }

    public static void displayPassengers() {

        System.out.println("\n--- Confirmed List ---");

        for (Passenger p : confirmedList)

            p.printDetails();

        System.out.println("\n--- Waiting List ---");

        waitingList.display();

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n1. Book Ticket\n2. Cancel Ticket\n3. Display Passengers\n4. Exit");

            choice = sc.nextInt();

            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Train Name: ");

                    String tName = sc.nextLine();

                    System.out.print("Enter Train Number: ");

                    int tNo = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Passenger Name: ");

                    String pName = sc.nextLine();

                    System.out.print("Enter Age: ");

                    int age = sc.nextInt();

                    sc.nextLine();

                    bookTicket(tName, tNo, pName, age);

                    break;

                case 2:

                    System.out.print("Enter Passenger Name to Cancel: ");

                    String nameToCancel = sc.nextLine();

                    cancelTicket(nameToCancel);

                    break;

                case 3:

                    displayPassengers();

                    break;

                case 4:

                    System.out.println("Exiting...");

                    break;

                default:

                    System.out.println("Invalid choice.");

            }

        } while (choice != 4);

        sc.close();

    }

}