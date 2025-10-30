class PCB {

    String processName;

    int processId;

    int priority;

    PCB next;


    PCB(String name, int id, int prio) {

        processName = name;

        processId = id;

        priority = prio;

        next = null;

    }

}


class PCBList {

    PCB head;


    // Insert at end

    void insert(String name, int id, int prio) {

        PCB newNode = new PCB(name, id, prio);

        if (head == null) {

            head = newNode;

            return;

        }

        PCB temp = head;

        while (temp.next != null)

            temp = temp.next;

        temp.next = newNode;

    }


    // Delete by ID

    boolean delete(int id) {

        if (head == null)

            return false;

        if (head.processId == id) {

            head = head.next;

            return true;

        }

        PCB prev = head, curr = head.next;

        while (curr != null) {

            if (curr.processId == id) {

                prev.next = curr.next;

                return true;

            }

            prev = curr;

            curr = curr.next;

        }

        return false;

    }


    // Search by ID

    PCB search(int id) {

        PCB temp = head;

        while (temp != null) {

            if (temp.processId == id)

                return temp;

            temp = temp.next;

        }

        return null;

    }


    // Display all

    void display() {

        PCB temp = head;

        if (temp == null) {

            System.out.println("Process list empty");

            return;

        }

        while (temp != null) {

            System.out.print("[Name:" + temp.processName +

                ", ID:" + temp.processId +

                ", Priority:" + temp.priority + "] -> ");

            temp = temp.next;

        }

        System.out.println("NULL");

    }


    // Swap two nodes by IDs

    boolean swap(int id1, int id2) {

        if (id1 == id2)

            return true;

        PCB prevX = null, currX = head;

        PCB prevY = null, currY = head;


        while (currX != null && currX.processId != id1) {

            prevX = currX;

            currX = currX.next;

        }

        while (currY != null && currY.processId != id2) {

            prevY = currY;

            currY = currY.next;

        }


        if (currX == null || currY == null)

            return false;


        if (prevX != null)

            prevX.next = currY;

        else

            head = currY;

        if (prevY != null)

            prevY.next = currX;

        else

            head = currX;


        PCB temp = currX.next;

        currX.next = currY.next;

        currY.next = temp;


        return true;

    }

}


public class ProcessControlBlock_LinkedList {

    public static void main(String[] args) {

        PCBList list = new PCBList();


        list.insert("P1", 101, 1);

        list.insert("P2", 102, 2);

        list.insert("P3", 103, 3);


        System.out.println("Initial Process List:");

        list.display();


        System.out.println("\nDeleting process 102...");

        list.delete(102);

        list.display();


        System.out.println("\nSearching process 103...");

        PCB found = list.search(103);

        if (found != null)

            System.out.println("Found: " + found.processName);

        else

            System.out.println("Not found");


        System.out.println("\nSwapping process 101 and 103...");

        if (list.swap(101, 103))

            list.display();

        else

            System.out.println("Swap failed.");

    }

}