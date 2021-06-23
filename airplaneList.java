public class airplaneList {
    private airplane head;

    public airplane getHead(){
        return head;
    }

    public void setHead(airplane head) {
        this.head = head;
    }

    public void airplaneList() {
    }

    public void insertPlane(airplane plane) {

        if (head == null) {
            plane.setNext(null);
            head = plane;
            return;
        }

        if (plane.getPid() < head.getPid()) {
            plane.setNext(head);
            head = plane;
            return;
        }

        airplane curr = head;
        while (curr.getNext() != null) {
            if (curr.getNext().getPid() > plane.getPid()) {
                plane.setNext(curr.getNext());
                curr.setNext(plane);
                return;
            }
            curr = curr.getNext();
        }
        curr.setNext(plane);
    }

    public void removePlane(int pld) {
        airplane curr = head;

        if (head.getPid() == pld) {
            head = head.getNext();
            return;
        }
        airplane tmp = curr;
        while (curr != null) {
            if (curr.getPid() == pld) {
                tmp.setNext(curr.getNext());
                return;
            }
            tmp = curr;
            curr = curr.getNext();
        }

    }

    public void printPlanes() {
        airplane curr = head;
        System.out.print("Planes= ");
        while (curr != null) {
            System.out.print("<" + curr.getPid() + ">");
            curr = curr.getNext();
        }
        System.out.println();
    }
}
