public class FlightList {
    private flight head;
    private flight sentinel;
    public flight getHead() {
        return head;
    }

    public flight getSentinel() {
        return sentinel;
    }

    public flightList() {
    }

    public void add(flight flight){

        if(head==null){
            flight.setNext(null);
            head=flight;
            sentinel=head;
            return;
        }

        if(flight.getDepart_time() < head.getDepart_time()){
            flight.setNext(head);
            head=flight;
            return;
        }else if(flight.getDepart_time() == head.getDepart_time()) {
            if (flight.getPld() < head.getPld()) {
                flight.setNext(head);
                return;
            }
        }

        flight curr=head;
        flight tmp;
        while(curr.getNext()!=null){
            if(curr.getNext().getDepart_time() > flight.getDepart_time()){
                flight.setNext(curr.getNext());
                curr.setNext(flight);
                return;
            }else if(curr.getNext().getDepart_time() == flight.getDepart_time()){
               tmp=curr;
                while(tmp.getNext()!=null){
                    if(tmp.getNext().getDepart_time()==flight.getDepart_time() && tmp.getNext().getPld() > flight.getPld()){
                        flight.setNext(tmp.getNext());
                        tmp.setNext(flight);
                        return;
                    }
                    tmp=tmp.getNext();

                }

            }
            curr=curr.getNext();
        }
        curr.setNext(flight);
        sentinel= curr.getNext().getNext();
    }

    public void removeFlight(int pld) {
        flight curr = head;

        if (head.getPld() == pld) {
            head = head.getNext();
            return;
        }
        flight tmp = curr;
        while (curr != sentinel) {
            if (curr.getPld() == pld) {
                tmp.setNext(curr.getNext());
                return;
            }
            tmp = curr;
            curr = curr.getNext();
        }
    }

    public void test(){
        flight curr=head;
        while(curr!=sentinel){
            System.out.print(curr.getDepart_time()+ " ");
            curr=curr.getNext();
        }
    }

    public void print(){
        flight curr=head;
        while(curr!=null){
            System.out.print("<"+curr.getPld()+":"+curr.getDepart_time()+">,");
            curr=curr.getNext();
        }
    }

}
