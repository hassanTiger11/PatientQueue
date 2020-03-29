import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
class Tests {

    @Test
    void JustPrinttest() {
        PatientQueue q = new PatientQueue();
        System.out.println(q);
    }

    // ............enqueue........................
    @Test
    void EnqueueSpectest1() {
        PatientQueue q = new PatientQueue();
        q.enqueue(new Patient("Anat", 4));
        q.enqueue(new Patient("Ben", 9));
        q.enqueue(new Patient("Sasha", 8));
        q.enqueue(new Patient("Wu", 7));
        q.enqueue(new Patient("Rein", 6));
        q.enqueue(new Patient("Ford", 2));
        System.out.println("Spec 1: " + q);
        assertEquals(
                "{Ford (2), Rein (6), Anat (4), Ben (9), Wu (7), Sasha (8)}",
                q.toString());
    }
    @Test
    void EnqueueSpectest2() {
        PatientQueue q = new PatientQueue();
        q.enqueue(new Patient("Anat", 4));
        q.enqueue(new Patient("Ben", 9));
        q.enqueue(new Patient("Sasha", 8));
        q.enqueue(new Patient("Wu", 7));
        q.enqueue(new Patient("Rein", 6));
        q.enqueue(new Patient("Eve", 10));
        System.out.println("Spec 2: " + q);
        assertEquals(
                "{Anat (4), Rein (6), Sasha (8), Ben (9), Wu (7), Eve (10)}",
                q.toString());
    }

    // ................dequeue.............................
    @Test
    void DenqueueSpectest1() {
        PatientQueue q = new PatientQueue();
        q.enqueue(new Patient("Anat", 4));
        q.enqueue(new Patient("Ben", 9));
        q.enqueue(new Patient("Sasha", 8));
        q.enqueue(new Patient("Wu", 7));
        q.enqueue(new Patient("Rein", 6));
        q.enqueue(new Patient("Ford", 2));
        q.enqueue(new Patient("Eve", 3));

        System.out.println("Spec 1, B4 dequeue:..........\n " + q
                + "\nwith size: " + q.size());
        try {
            System.out.println("Dequeued: " + q.dequeue());
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        System.out.println(
                "Spec 1, After dequeue: " + q + "\nwith size: " + q.size());
        assertEquals(
                "{Eve (3), Rein (6), Anat (4), Ben (9), Wu (7), Sasha (8)}",
                q.toString());
    }

    @Test
    void DenqueueMinetest1() {
        PatientQueue q = new PatientQueue();
        q.enqueue(new Patient("Jake", 1));
        q.enqueue(new Patient("Zane", 5));
        q.enqueue(new Patient("Timothy", 5));
        q.enqueue(new Patient("Joe", 8));

        System.out.println("Spec 1, B4 dequeue:..........\n " + q
                + "\nwith size: " + q.size());
        try {
            System.out.println("Dequeued: " + q.dequeue());
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        System.out.println(
                "Spec 1, After dequeue: " + q + "\nwith size: " + q.size());
        assertEquals(
                "{Eve (3), Rein (6), Anat (4), Ben (9), Wu (7), Sasha (8)}",
                q.toString());
    }
    // ..........................clear...........................
    @Test
    void clearQueueTest() {
        PatientQueue q = new PatientQueue();
        q.enqueue(new Patient("Anat", 4));
        q.enqueue(new Patient("Ben", 9));
        q.enqueue(new Patient("Sasha", 8));
        q.enqueue(new Patient("Wu", 7));
        q.enqueue(new Patient("Rein", 6));
        q.enqueue(new Patient("Ford", 2));
        q.enqueue(new Patient("Eve", 3));
        q.clear();
        assertEquals("{}", q.toString());
    }
    // ..........................peek...........................
    @Test
    void peekTest() {
        PatientQueue q = new PatientQueue();
        q.enqueue(new Patient("Anat", 4));
        q.enqueue(new Patient("Ben", 9));
        q.enqueue(new Patient("Sasha", 8));
        q.enqueue(new Patient("Wu", 7));
        q.enqueue(new Patient("Rein", 6));
        q.enqueue(new Patient("Ford", 2));
        q.enqueue(new Patient("Eve", 3));
        try {
            assertEquals("Ford", q.peek());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void peekPriorityTest() {
        PatientQueue q = new PatientQueue();
        q.enqueue(new Patient("Anat", 4));
        q.enqueue(new Patient("Ben", 9));
        q.enqueue(new Patient("Sasha", 8));
        q.enqueue(new Patient("Wu", 7));
        q.enqueue(new Patient("Rein", 6));
        q.enqueue(new Patient("Ford", 2));
        q.enqueue(new Patient("Eve", 3));
        try {
            assertEquals(2, q.peekPriority());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    // ..........................changePriority..................
    @Test
    void ChangePrioritySpectest1() {
        PatientQueue q = new PatientQueue();
        q.enqueue(new Patient("Anat", 4));
        q.enqueue(new Patient("Ben", 9));
        q.enqueue(new Patient("Sasha", 8));
        q.enqueue(new Patient("Wu", 7));
        q.enqueue(new Patient("Rein", 6));
        q.enqueue(new Patient("Ford", 2));
        q.enqueue(new Patient("Eve", 3));
        try {
            q.dequeue();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        System.out.println("Spec 1, b4 changePriority:..........\n " + q
                + "\nwith size: " + q.size());
        q.changePriority("Eve", 10);
        System.out.println("Spec 1, after changePriority:..........\n " + q
                + "\nwith size: " + q.size());
        assertEquals(
                "{Anat (4), Rein (6), Sasha (8), Ben (9), Wu (7), Eve (10)}",
                q.toString());
    }

    @Test
    void ChangePriorityMinetest1() {
        PatientQueue q = new PatientQueue();
        q.enqueue(new Patient("Anat", 4));
        q.enqueue(new Patient("Ben", 9));
        q.enqueue(new Patient("Sasha", 8));
        q.enqueue(new Patient("Wu", 7));
        q.enqueue(new Patient("Rein", 6));
        q.enqueue(new Patient("Ford", 2));
        q.enqueue(new Patient("Eve", 3));
        try {
            q.dequeue();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        System.out.println("Mine, b4 changePriority:..........\n " + q
                + "\nwith size: " + q.size());
        q.changePriority("Ben", 1);
        System.out.println("Mine, after changePriority:..........\n " + q
                + "\nwith size: " + q.size());
        assertEquals(
                "{Ben (1), Eve (3), Anat (4), Rein (6), Wu (7), Sasha (8)}",
                q.toString());
    }

    @Test
    void ChangePriorityMinetest2() {
        PatientQueue q = new PatientQueue();
        q.enqueue(new Patient("Anat", 4));
        q.enqueue(new Patient("Ben", 9));
        q.enqueue(new Patient("Sasha", 8));
        q.enqueue(new Patient("Wu", 7));
        q.enqueue(new Patient("Rein", 6));
        q.enqueue(new Patient("Ford", 2));
        q.enqueue(new Patient("Eve", 3));
        q.enqueue(new Patient("Sasha", 7));
        q.enqueue(new Patient("Sasha", 6));
        q.enqueue(new Patient("Sasha", 5));
        try {
            q.dequeue();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        System.out.println("Mine, b4 changePriority:..........\n " + q
                + "\nwith size: " + q.size());
        q.changePriority("Sasha", 1);
        System.out.println("Mine, after changePriority:..........\n " + q
                + "\nwith size: " + q.size());
        assertEquals(
                "{Ben (1), Eve (3), Anat (4), Rein (6), Wu (7), Sasha (8)}",
                q.toString());
    }


}
