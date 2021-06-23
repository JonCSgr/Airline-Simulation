/******************************************************
 *                                                    *
 * file: Main.java                                    *
 *                                                    *
 * @Author: Iacovos G. Kolokasis                    *
 * @Version: 01-03-2020                              *
 * @email: kolokasis@csd.uoc.gr                    *
 *                                                    *
 * Source file for the needs of cs-240b project 2020  *
 *                                                    *
 ******************************************************
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class mainn {
    int max_tweets_g;
    static airlineList airlines = new airlineList();
    static flightList DESTINATIONS[] = new flightList[10];


    public static boolean initialize() {
        for (int i = 0; i < 10; i++) {
            DESTINATIONS[i] = new flightList();
        }
        return true;
    }

    public static boolean register_airline(int aId) {
        airline newAirline = new airline(aId);
        airlines.register_airline(newAirline);
        System.out.print("Airlines= ");
        airlines.simplePrint();
        return true;
    }

    public static boolean insert_airplanes(int aId, int pId, int dest, int depart_time) {
        airplane newPlane = new airplane(pId, dest, depart_time);
        airlines.insert_airplanes(aId, newPlane);

        flight newFlight = new flight(pId, depart_time);
        DESTINATIONS[dest].add(newFlight);

        airlines.print();
        return true;
    }

    public static boolean cancel_flight(int aId, int pId, int dest) {
        airlines.cancel_flight(aId, pId, dest);
        DESTINATIONS[dest].removeFlight(pId);
        airlines.print();
        return true;
    }

    public static boolean delete_airline(int aId) {

        airplane temp;
        temp = airlines.getPlanes(aId);
        flight tmp;
        while(temp!=null) {
            for (int i = 0; i < 10; i++) {
                tmp = DESTINATIONS[i].getHead();
                while (tmp != null) {
                    if (temp.getPid() == tmp.getPld()) {
                        DESTINATIONS[i].removeFlight(tmp.getPld());
                    }
                    tmp = tmp.getNext();
                }
            }
            temp = temp.getNext();
        }

        airlines.delete_airline(aId);
        airlines.print();
        return true;
    }

    public static boolean acquisition_airline(int aId1, int aId2) {
        airlines.Acquisition_airline(aId1, aId2);
        airlines.delete_airline(aId1);
        airlines.print();
        return true;
    }

    public static boolean subsidiary_airiline(int aId1, int aId2, int dest) {

            airplane helperEventS = airlines.helperEventS(aId1, dest);
            if (helperEventS == null){
                return false;
            }
            airplaneList list2 = null;
            airline head, tail;
            head = airlines.getHead();
            tail = airlines.getTail();

            while (head != tail) {
                if (head.getAid() == aId2) {
                    list2 = head.getAirplanes();
                }
                head = head.getNext();
            }
            if (head == tail && head.getAid() == aId2) {
                list2 = head.getAirplanes();
            }

            airplane finalList = new airplane(0, 0, 0);
            airplane merged = finalList;


            airplane headList2 = list2.getHead();

            while (helperEventS != null && headList2 != null) {
                if (helperEventS.getPid() < headList2.getPid()) {
                    merged.setNext(helperEventS);
                    helperEventS = helperEventS.getNext();
                } else {
                    merged.setNext(headList2);
                    headList2 = headList2.getNext();
                }
                merged = merged.getNext();
            }

            while (helperEventS != null) {
                merged.setNext(helperEventS);
                merged = merged.getNext();
                helperEventS = helperEventS.getNext();
            }

            while (headList2 != null) {
                merged.setNext(headList2);
                merged = merged.getNext();
                headList2 = headList2.getNext();
            }

            list2.setHead(finalList.getNext());

             airlines.print();
            return true;
        }

    public static boolean partition_airplanes(int aId1) {
        airlines.partition_airplanes(aId1);
        airlines.delete_airline(aId1);
        airlines.print();
        return true;
    }

    public static boolean travel(int dest, int timestamp) {
        flight temp = DESTINATIONS[dest].getHead();
        flight sentinel = DESTINATIONS[dest].getSentinel();
        System.out.print("Destination = ");
        while (temp != sentinel) {
            if (temp.getDepart_time() > timestamp) {
                System.out.print("<" + temp.getPld() + ":" + temp.getDepart_time() + ">, ");
            }
            temp = temp.getNext();
        }
        return true;
    }

    public static boolean print_airlines() {
        airlines.simplePrint();
        return true;
    }

    public static boolean print_destinations() {

        for (int i = 0; i < 10; i++) {
            System.out.print("Destination" + i + " = ");
            DESTINATIONS[i].print();
            System.out.println();
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        initialize();
        BufferedReader inputFile;       /* Input file                          */
        String line;                    /* Data for eachline of the input file */
        String[] params;               /* Event parameters arguments          */

        /* Check command buff arguments */
        if (args.length != 1) {
            System.err.println("Usage: <executable-name> <input_file>");
            System.exit(0);
        }

        /* Open input file */
        inputFile = new BufferedReader(new FileReader(args[0]));

        /* Read input file and handle the events */
        while ((line = inputFile.readLine()) != null) {

            if (line.length() == 0) continue;

            System.out.println(">>> Event: " + line);
            params = line.split(" ");
            char eventID = line.charAt(0);

            switch (eventID) {

                /* Comment */
                case '#':
                    break;

                /* Register airline
                 * R <aId> */
                case 'R': {
                    int aId = Integer.parseInt(params[1]);

                    if (register_airline(aId)) {
                        System.out.println("R succeeded");
                    } else {
                        System.err.println("R failed");
                    }

                    break;
                }

                /* Insert new airplane
                 * I <aId> <pId> <dest> <depart_time> */
                case 'I': {
                    int aId = Integer.parseInt(params[1]);
                    int pId = Integer.parseInt(params[2]);
                    int dest = Integer.parseInt(params[3]);
                    int depart_time = Integer.parseInt(params[4]);

                    if (insert_airplanes(aId, pId, dest, depart_time)) {
                        System.out.println("I succeeded");
                    } else {
                        System.err.println("I failed");
                    }

                    break;
                }

                /* Cancel flight
                 * C <aId> <pId> <dest> */
                case 'C': {
                    int aId = Integer.parseInt(params[1]);
                    int pId = Integer.parseInt(params[2]);
                    int dest = Integer.parseInt(params[3]);

                    if (cancel_flight(aId, pId, dest)) {
                        System.out.println("C succeeded");
                    } else {
                        System.err.println("C failed");
                    }

                    break;
                }

                /* Delete airline
                 * D <aId> */
                case 'D': {
                    int aId = Integer.parseInt(params[1]);

                    if (delete_airline(aId)) {
                        System.out.println("D succeeded");
                    } else {
                        System.err.println("D failed");
                    }

                    break;
                }

                /* Acquisition airline
                 * A <uid1> <uid2>*/
                case 'A': {
                    int aId1 = Integer.parseInt(params[1]);
                    int aId2 = Integer.parseInt(params[2]);

                    if (acquisition_airline(aId1, aId2)) {
                        System.out.println("A succeeded");
                    } else {
                        System.err.println("A failed");
                    }

                    break;
                }

                /* Subsidiary airline
                 * S <aId1> <aId2> <dest>
                 */
                case 'S': {
                    int aId1 = Integer.parseInt(params[1]);
                    int aId2 = Integer.parseInt(params[2]);
                    int dest = Integer.parseInt(params[3]);

                    if (subsidiary_airiline(aId1, aId2, dest)) {
                        System.out.println("S succeeded");
                    } else {
                        System.err.println("S failed");
                    }

                    break;
                }

                /* Partition airplanes
                 * P <aId> */
                case 'P': {
                    int aId = Integer.parseInt(params[1]);

                    if (partition_airplanes(aId)) {
                        System.out.println("P succeeded");
                    } else {
                        System.err.println("P failed");
                    }

                    break;
                }

                /* Travel
                 * T <dest> <timestamp> */
                case 'T': {
                    int dest = Integer.parseInt(params[1]);
                    int timestamp = Integer.parseInt(params[2]);

                    if (travel(dest, timestamp)) {
                        System.out.println("T succeeded");
                    } else {
                        System.err.println("T failed");
                    }

                    break;
                }

                /* Print airlines
                 * X */
                case 'X': {
                    if (print_airlines()) {
                        System.out.println("X succeeded");
                    } else {
                        System.err.println("X failed");
                    }

                    break;
                }

                /* Print destinations flights
                 * Y */
                case 'Y': {
                    if (print_destinations()) {
                        System.out.println("Y succeeded");
                    } else {
                        System.err.println("Y failed");
                    }

                    break;
                }

                /* Empty line */
                case '\n':
                    break;

                /* Ignore everything else */
                default:
                    System.out.println("Ignoring " + line);
                    break;
            }
        }
    }
}
