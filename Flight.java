public class Flight {
    int pld;
    int depart_time;
    flight next;

    public flight(int pld,int depart_time) {
        this.pld=pld;
        this.depart_time=depart_time;
    }

    public int getPld() {
        return pld;
    }

    public void setPld(int pld) {
        this.pld = pld;
    }

    public int getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(int depart_time) {
        this.depart_time = depart_time;
    }

    public flight getNext() {
        return next;
    }

    public void setNext(flight next) {
        this.next = next;
    }
}
