public class airplane {
    private int pid;
    private int dest;
    private int depart_time;
    private airplane next;
    private airlineList s_airline;


    public airplane(int pid, int dest, int depart_time) {
        this.pid = pid;
        this.dest = dest;
        this.depart_time = depart_time;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public int getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(int depart_time) {
        this.depart_time = depart_time;
    }

    public airplane getNext() {
        return next;
    }

    public void setNext(airplane next) {
        this.next = next;
    }
}
