public class AirlineList {
    private airline head;
    private airline tail;

    public airline getHead() {
        return head;
    }

    public airline getTail() {
        return tail;
    }


    public void register_airline(airline company) {

        if (head == null) {
            tail = company;
            head = company;
            tail.setNext(head);
            head.setPrev(tail);
            return;
        }
        tail.setNext(company);
        company.setPrev(tail);
        company.setNext(head);
        tail = company;
        head.setPrev(tail);

    }

    public boolean insert_airplanes(int aId, airplane plane) {
        airline curr = head;
        while (curr != tail) {
            if (curr.getAid() == aId) {
                curr.getAirplanes().insertPlane(plane);
                return true;
            }
            curr = curr.getNext();
        }
        if (curr == tail) {
            curr.getAirplanes().insertPlane(plane);
            return true;
        }
        return false;
    }

    public boolean cancel_flight(int aId, int pId, int dest) {
        airline curr = head;
        while (curr != tail) {
            if (curr.getAid() == aId) {
                curr.getAirplanes().removePlane(pId);
                return true;
            }
            curr = curr.getNext();
        }
        if (curr == tail && curr.getAid() == aId) {
            curr.getAirplanes().removePlane(pId);
            return true;
        }
        return false;
    }

    public void delete_airline(int aId) {

        if (head.getAid() == aId) {
            head.emptyPlanes();
            head = head.getNext();
            head.setPrev(tail);
            tail.setNext(head);
            return;
        }

        airline curr, tmp;
        curr = head;
        tmp = curr;
        while (curr != tail) {
            if (curr.getAid() == aId) {
                curr.emptyPlanes();
                tmp.setNext(curr.getNext());
                curr.getNext().setPrev(tmp);
                return;
            }
            tmp = curr;
            curr = curr.getNext();
        }
        if (curr == tail && curr.getAid() == aId) {
            curr.emptyPlanes();
            tail = tmp;
            tmp.setNext(head);
            head.setPrev(tail);
        }

    }

    public void print() {
        airline curr = head;
        while (curr != tail) {
            System.out.print("Airline ");
            System.out.print(curr.getAid() + ", ");
            curr.getAirplanes().printPlanes();
            curr = curr.getNext();
        }
        if (curr == tail) {
            System.out.print("Airline ");
            System.out.print(curr.getAid() + ", ");
            curr.getAirplanes().printPlanes();

        }
    }

    public void simplePrint() {
        airline curr = head;
        while (curr != tail) {
            System.out.print("<" + curr.getAid() + ">,");
            curr = curr.getNext();
        }
        if (curr == tail) {
            System.out.print("<" + curr.getAid() + ">");
        }
    }

    public airplane getPlanes(int aId) {
        airline curr = head;
        airplane tmp = null;
        while (curr != tail) {
            if (curr.getAid() == aId) {
                tmp = curr.getAirplanes().getHead();
                return tmp;
            }
            if (curr == tail) {
                tmp = curr.getAirplanes().getHead();
                return tmp;
            }
            curr = curr.getNext();
        }
        return tmp;
    }

    public void Acquisition_airline(int id1, int id2) {
        airplaneList list1 = null, list2 = null;
        airline curr;
        curr = head;
        while (curr != tail) {
            if (curr.getAid() == id1) {
                list1 = curr.getAirplanes();
            } else if (curr.getAid() == id2) {
                list2 = curr.getAirplanes();
            }
            curr = curr.getNext();
        }
        if (curr == tail && curr.getAid() == id1) {
            list1 = curr.getAirplanes();
        } else if (curr == tail && curr.getAid() == id2) {
            list2 = curr.getAirplanes();
        }

        airplane finalList = new airplane(0, 0, 0);
        airplane merged = finalList;

        airplane headList1 = list1.getHead();
        airplane headList2 = list2.getHead();

        while (headList1 != null && headList2 != null) {
            if (headList1.getPid() < headList2.getPid()) {
                merged.setNext(headList1);
                headList1 = headList1.getNext();
            } else {
                merged.setNext(headList2);
                headList2 = headList2.getNext();
            }
            merged = merged.getNext();
        }

        while (headList1 != null) {
            merged.setNext(headList1);
            merged = merged.getNext();
            headList1 = headList1.getNext();
        }

        while (headList2 != null) {
            merged.setNext(headList2);
            merged = merged.getNext();
            headList2 = headList2.getNext();
        }

        list2.setHead(finalList.getNext());

    }

    public airplane helperEventS(int id1, int dest) {
        airplaneList list1 = null;
        airline curr;
        curr = head;

        while (curr != tail) {
            if (curr.getAid() == id1) {
                list1 = curr.getAirplanes();
            }
            curr = curr.getNext();
        }
        if (curr == tail && curr.getAid() == id1) {
            list1 = curr.getAirplanes();
        }

        if(list1==null){
            return null;
        }

        airplane headList1 = list1.getHead();

        airplane tmp = headList1;
        airplaneList list = new airplaneList();

        while (tmp != null) {
            if (tmp.getDest() == dest) {
                airplane plane = new airplane(tmp.getPid(), tmp.getDest(), tmp.getDepart_time());
                list1.removePlane(tmp.getPid());
                list.insertPlane(plane);
            }
            tmp = tmp.getNext();
        }

        airplane temp = list.getHead();

        return temp;

    }

    public void partition_airplanes(int id) {
        airline tmp = head;
        airplaneList deadAirline = null;
        while (tmp != null) {
            if (tmp.getAid() == id) {
                deadAirline = tmp.getAirplanes();
                break;
            }
            tmp = tmp.getNext();
        }
        airplane curr = deadAirline.getHead();
        airline right, left;

        right = tmp;
        left = tmp;

        while (curr != null) {
            airplane newPlane = new airplane(curr.getPid(), curr.getDest(), curr.getDepart_time());
            right.getNext().getAirplanes().insertPlane(newPlane);
            right = right.getNext();
            curr = curr.getNext();
            if(curr==null){
                return;
            }
            airplane newPlane1 = new airplane(curr.getPid(), curr.getDest(), curr.getDepart_time());
            left.getPrev().getAirplanes().insertPlane(newPlane1);
            left = left.getPrev();
            curr = curr.getNext();

        }

    }
}









