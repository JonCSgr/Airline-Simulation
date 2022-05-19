public class Airline {
    private int aid;
    private airplaneList airplanes;
    private airline next;
    private airline prev;

    public airline(int aid) {
        this.aid = aid;
        airplanes = new airplaneList();
    }

    public airplaneList getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(airplaneList airplanes) {
        this.airplanes = airplanes;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public airline getNext() {
        return next;
    }

    public void setNext(airline next) {
        this.next = next;
    }

    public airline getPrev() {
        return prev;
    }

    public void setPrev(airline prev) {
        this.prev = prev;
    }

    public void emptyPlanes() {
        airplane curr = airplanes.getHead();
        while (curr != null) {
            airplanes.removePlane(curr.getPid());
            curr = curr.getNext();
        }
    }


}
