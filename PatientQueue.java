/*
 * This class is a priority queue backed by a min heap. Such a priority 
 * queue is used widely in hospitals and emergency rooms. As discussed in class, a min a heap
 * is a sort of tree where our root is minimum value."

 In other words each parent has to be smaller than its children. This is the only rule
 we are thinking of when we write this class's functions.
 
 An example for this priority queue will be constructing the following
 if I have the following 6 patients
Anat (4)
Ben (9)
Sasha (8)
Wu (7)
Rein (6)
Ford (2)
I will end up with a priority queue: 
------------------------------------------------------------------------------------------
index   |0      |1      |2      |3      |4      |5      |6      |7      |8      |9      |
name    |       |Ford   |Rien   |Anat   |Ben    |Wu     |Sasha  |       |       |       |
value   |       |2      |6      |4      |9      |7      |8      |       |       |       |

When we peek we are always looking at index 1 which is the one with the lowest priority numerical
value. which is "Ford" when we call peek() and "2" when we call peekPriority. 
The representation for this queue should be:
"{Ford (2), Rein (6), Eve (3), Ben (9), Wu (7), Sasha (8), Anat (4)}"


IMPORTANT NOTE: dequeue and peek access the front most value, which will be the minimum.
IMPORTANT NOTE: leave index 0 unfilled

    *FIELDS:
    +size: int
    +queue: Patient[]
    
    *METHODS:
    *..........................enqueue......................................................
    +enqueu(Patient p), enqueue(String name, int priority): the enqueue method follows the following structure:
                add to the end of the array-> compare it with its parent (index/2)-> 
                swap it with its parent if its parent is larger. compare again. I can use a
                recursive function with a base case: 
                parent < child.
                IMPORTANT ANOTHER BASE CASE:
                if parent.priority == child.priority: then compare them by name(alphabetically)
                IMPORTANT: the heap has to grow up in capacity. if (size%10 == 0 ){growPQ()}
    -bubbleUp(int currIndex, Patient p): this function is a recursive helper function.
    I start by adding at the end of the queue then compare the valu
                
    -growPQ(): this function is a helper function for enqueue. when we reach full capacity.
    *................................dequeue................................................
    +dequeue(): return a String (the name). This method follows the following structure:
    Directly swap index 1 with the last index. then bubble down the index 1, i.e compare it
    with its children if one of them is smaller then swap.
    IMPORTANT: if this.isEmpty(), throws an exception
    IMPORTANT OTHERWISE: swap index 1 with last index. bubbleDown()
    then return last index's name.
    -bubbleDown(): this is a helper function for dequeue. It bubbles down the first index.
    base case: parent.priority < child.priority(compare both children): return. always check that children exist
    recurse case: parent.priority == child.priority: compare Strings 
    recurse case: parent.priority > child.priority: swap then recurse. 
    *..............................peek (two types)....................................................
    +peek(): String, This function returns the value of the front most patient.
    if the queue is empty, it throws an exception.           
    +peekPriority(): int, this function return the priority of the front most patient.
    Throws an exception if the queue is empty
    *................................toString..............................................
    +toString(): this is an override of the Object class. developing this early on is recommeneded
    'it should follow the heap order': {Anat (4), Rein (6), Sasha (8), Ben (9), Wu (7), Eve (10)}
    *..................................changePriority....................................
    +changePriority(String name, int newPriority): this method changes a patients priority then bubbles it up or down.
    The structure of the method:
        search for first occurence of patient.
        Reason whether I need to bubble it up or down.
        bubble it up or down
        
     *..........................................clear..................................
     +clear(): This function traverse the queue and remove every element of the queue.
     and sets the size back to 0;
     *..........................................size....................................
     +size(): int, this returns the size of the queue. the number of patients.
     
                

*/
public class PatientQueue {
    private Patient[] PQ;
    private int size;
    private int capacity;

    /*
     * class constructor:
     * 
     * @param:
     * 
     * @return:
     */
    public PatientQueue() {
        capacity = 10;
        PQ = new Patient[capacity];
        size = 0;

    }
    // ..........enqueue.............................................................
    /*
     * this function has to add to the queue to the queue at the end and bubble
     * up.
     * When I have an empty queue I need to directly add to index 1
     * 
     * otherwise I need to add to the end of the queue and bubble up
     * (bubbleUp(int index)), but I need to
     * check for the size first(growPQ())
     * 
     * @param: Patient patient
     * 
     * @return: none
     */

    public void enqueue(Patient p) {
        if (PQ[1] == null) {
            PQ[1] = new Patient(p.name, p.priority);
            size += 1;
        }
        // add to the end and bubble up
        else {
            // myQueue needs to grow
            if (size == capacity - 1) {
                growPQ();
            }
            size += 1;
            PQ[size] = new Patient(p.name, p.priority);
            bubbleUp(size, PQ[size]);
        }
    }

    /*
     * This function is the exact duplicate of the previos one but the params.
     * since
     * I have written this previous one first I am chaining by it.
     * 
     * @param: String name, int priority
     */
    public void enqueue(String name, int priority) {
        enqueue(new Patient(name, priority));
    }

    /*
     * this is a helper function for enqueue above. when we add to the end of
     * the queue we need to bubble up
     * then.
     * the patient has to be compared to its parent (i/2): child.prioriy<
     * parent.priority: swap then compare again
     * other: child.priority == parent priority: compare using names. then
     * compare again.
     * other (child.priority> parent priority): keep at its place
     * 
     * @param: int index, patient
     * 
     * @return: none
     */
    public void bubbleUp(int index, Patient p) {
        if (index == 1) {
            // base case
            return;
        }
        if (p.priority > PQ[index / 2].priority) {
            // base case
            return;
        } else if (p.priority == PQ[index / 2].priority) {
            // compare strings, recursive case!
            if (p.name.compareTo(PQ[index / 2].name) < 0) {
                // p.name < PQ[index/2].name
                // swipe
                Patient temp = new Patient(PQ[index / 2].name,
                        PQ[index / 2].priority);
                PQ[index / 2] = new Patient(p.name, p.priority);
                PQ[index] = new Patient(temp.name, temp.priority);
                // recurse
                bubbleUp(index / 2, PQ[index / 2]);
            }
        } else {
            // swipe
            Patient temp = new Patient(PQ[index / 2].name,
                    PQ[index / 2].priority);
            PQ[index / 2] = new Patient(p.name, p.priority);
            PQ[index] = new Patient(temp.name, temp.priority);
            // recurse
            bubbleUp(index / 2, PQ[index / 2]);
        }
    }

    /*
     * this is a helper function for the enqueue above. it creates a new
     * Patients array that is
     * 10 indexes more than the previous one. copies all the values of the old
     * one. increase the capacity.
     * 
     * @param: none
     * 
     * @return: none
     */
    private void growPQ() {
        // increase capacity
        capacity += 10;
        Patient[] largerPQ = new Patient[capacity];
        // copies elements of PQ
        for (int index = 0; index < PQ.length; index++) {
            // to avoid nullPointerException
            if (PQ[index] != null) {
                largerPQ[index] = new Patient(PQ[index].name,
                        PQ[index].priority);
            }
        }
        PQ = largerPQ;
    }

    // ........................toString....................................................
    /*
     * This should return a string of the format {Anat (4), Rein (6), Sasha (8),
     * Ben (9), Wu (7), Eve (10)}
     * the order should follows from PQ order.
     * consider that index[0] is empty so skip
     * 
     * @param:
     * 
     * @return: String
     */
    public String toString() {
        String myPQ = "{";
        for (int index = 0; index < PQ.length; index++) {
            if (PQ[index] != null && index != 0) {
                // I can remove condition: index != 0. but I am keeping it for
                // debugging purposes
                if (index == size) {
                    myPQ += PQ[index].name + " (" + PQ[index].priority + ")";
                } else {
                    myPQ += PQ[index].name + " (" + PQ[index].priority + "), ";
                }
            }
        }
        myPQ += "}";
        return myPQ;
    }

    // ........................clear....................................................
    /*
     * As described on Piazza and the spec, I can't just reference a new Arrat.
     * Thus I will change all the references of each element to null
     * 
     * @param:
     * 
     * @return:
     */
    public void clear() {
        this.size = 0;
        for (int index = 0; index < capacity; index++) {
            PQ[index] = null;
        }
    }

    // ......................size................................
    /*
     * In this function you should return the number of elements in
     * your patient queue.*
     * 
     * @param:
     * 
     * @return:
     */
    public int size() {
        return this.size;
    }

    // .......................isEmpty........................
    public boolean isEmpty() {
        return this.size == 0;
    }

    // ............................dequeue.............................
    /*
     * This function is used to dequeue the patient with the highest priority.
     * the function has to consider whether the queue isEmpty or not. if
     * everything is
     * good to go, I need to swap the first index with the last, remove
     * "the new last",
     * and bubble down the "the new first".
     * 
     * @param: none
     * 
     * @return: String name
     */
    public String dequeue() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("Empty Queue. No patients at this time.");
        } else {
            // swap
            Patient temp = new Patient(PQ[1].name, PQ[1].priority);
            PQ[1] = new Patient(PQ[size].name, PQ[size].priority);
            PQ[size] = null;
            bubbleDown(1);
            size -= 1;
            return temp.name;

        }
    }

    /*
     * since we are bound to reach to a case where both children priorities
     * are less than the parent. I have to decide which one of them
     * to use for bubbling
     * 
     * @param: int index
     * 
     * @return none:
     */
    private void bubbleDown(int index) {
        if (index * 2 >= PQ.length || index * 2 + 1 >= PQ.length) {
            // Avoid outOfBoundsException
            return;
        }
        if (PQ[index * 2] != null && PQ[index * 2 + 1] != null) {
            if (PQ[index * 2].priority < PQ[index * 2].priority) {
                // left < right

                bubbleDownLeft(index);
            } else if (PQ[index * 2 + 1].priority == PQ[index * 2].priority) {
                // compare with Strings
                if (PQ[index * 2].name.compareTo(PQ[index * 2 + 1].name) < 0) {
                    bubbleDownLeft(index);
                } else {
                    bubbleDownRight(index);
                }
            } else {
                // left> right
                bubbleDownRight(index);
            }
        }
        if (PQ[index * 2] != null) {
            bubbleDownLeft(index);

        } else {
            return;
        }
    }
    /*
     * This function is a helper function for dequeue. This function is a
     * recursive function that
     * takes a patient compare it to its children and swap.
     * base case: p.priority < child.priority
     * recursive case (special): p.priority== child.priority compare names
     * recursive case: p.priority > child.priority: swap and recurse
     */
    private void bubbleDownLeft(int index) {
        if (PQ[index * 2] != null) {
            if (PQ[index].priority < PQ[index * 2].priority) {
                // base case
                return;
            } else if (PQ[index].priority == PQ[index * 2].priority) {
                // recursive case (special)
                if (PQ[index].name.compareTo(PQ[index * 2].name) > 0) {
                    // if name1 > name2 then swap and recurse
                    Patient temp = new Patient(PQ[index].name,
                            PQ[index].priority);
                    PQ[index] = new Patient(PQ[index * 2].name,
                            PQ[index * 2].priority);
                    PQ[index * 2] = new Patient(temp.name, temp.priority);
                }
                bubbleDown(index * 2);

            } else {
                // recursive case p.priority > child.priority.
                // swap and recurse
                Patient temp = new Patient(PQ[index].name, PQ[index].priority);
                PQ[index] = new Patient(PQ[index * 2].name,
                        PQ[index * 2].priority);
                PQ[index * 2] = new Patient(temp.name, temp.priority);
                bubbleDown(index * 2);
            }
        }
        return;

    }

    private void bubbleDownRight(int index) {

        if (PQ[index * 2 + 1] != null) {
            if (PQ[index].priority < PQ[index * 2 + 1].priority) {
                // base case
                return;
            } else if (PQ[index].priority == PQ[index * 2 + 1].priority) {
                // recursive case special
                if (PQ[index].name.compareTo(PQ[index * 2 + 1].name) > 0) {
                    // if name1 > name2 then swap and recurse
                    Patient temp = new Patient(PQ[index].name,
                            PQ[index].priority);
                    PQ[index] = new Patient(PQ[index * 2 + 1].name,
                            PQ[index * 2 + 1].priority);
                    PQ[index * 2] = new Patient(temp.name, temp.priority);
                }
                bubbleDown(index * 2 + 1);

            } else {
                // recursive case p.priority > child.priority.
                // swap and recurse
                Patient temp = new Patient(PQ[index].name, PQ[index].priority);
                PQ[index] = new Patient(PQ[index * 2 + 1].name,
                        PQ[index * 2 + 1].priority);
                PQ[index * 2 + 1] = new Patient(temp.name, temp.priority);
                bubbleDown(index * 2 + 1);
            }

        }
        return;
    }
    // .................Peek............................................
    /*
     * This function returns the name of the front most patient. if the
     * queue is empty it throws an exception
     * 
     * @param: none
     * 
     * @return: String
     */
    public String peek() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("Empty Queue. No Patients At This Time.");
        }
        return PQ[1].name;
    }

    // ........................peekPriority...............................
    public int peekPriority() throws Exception {
        /*
         * This function returns the priority of the front most patient. if the
         * queue is empty it throws an exception.
         * 
         * @param: none
         * 
         * @return: int
         */
        if (this.isEmpty()) {
            throw new Exception("Empty Queue. No Patients At This Time.");
        }
        return PQ[1].priority;
    }

    // .........................changePriority..............................
    /*
     * This function modify the priority of a patient which causes the patient
     * to move
     * forward(end of the queue), backwards(front of the queue) or stay at its
     * place.
     * forward: newPriority > oldPriority
     * backward: newPriority < oldPriority
     * stays: newPriority = oldPriority.
     * IMPORTANT: use tie breaker for patients comparisons
     * 
     * @param: String patientName, int newPriority
     * 
     * @return: none
     */
    public void changePriority(String name, int newPriority) {
        // search doe patient
        int index = searchPatient(name);
        // couldn't find patient
        if (index == -1) {
            return;
        }
        // bubbling up or down.
        // Those functions already have tie breakers built in them.
        if (newPriority > PQ[index].priority) {
            PQ[index] = new Patient(name, newPriority);
            bubbleDown(index);
        } else if ((newPriority < PQ[index].priority)) {
            PQ[index] = new Patient(name, newPriority);
            bubbleUp(index, PQ[index]);
        } else {
            // nothing will happen
        }

    }

    /*
     * This function is a helper function for change Priority.
     * it searches for the patient's index
     * 
     * @param: String name.
     * 
     * @return: index
     */
    private int searchPatient(String name) {
        for (int i = 0; i < PQ.length; i++) {
            if (PQ[i] != null) {
                if (PQ[i].name.equals(name)) {
                    return i;
                }
            }
        }
        return -1;
    }
}

