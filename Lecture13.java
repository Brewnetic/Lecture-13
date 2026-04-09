// ============================================
// LECTURE 13: Collections Framework & HashSet & ArrayBlockingQueue
// Social Media Live Stream System
// ============================================

/* HashSet stores unique elements (no duplicates allowed).
   Part of java.util, the standard utility collections package. */
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Lecture13 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("   SOCIAL MEDIA LIVE STREAM SYSTEM");
        System.out.println("========================================\n");
        
        // ============================================
        // PART 1: Tracking Unique Viewers with HashSet
        // ============================================
        
        /* new HashSet<>() creates an empty set.
           The <String> on the left declares what type the set holds.
           The diamond <> on the right lets Java infer the type automatically.
           No capacity argument is needed because HashSet grows as elements are added. */
        HashSet<String> activeViewers = new HashSet<>();
        
        System.out.println("--- Stream Starting ---\n");
        
        /* .add() inserts an element into the set.
           Returns true if the element was new, false if it was already present.
           Duplicates are silently ignored (no exception is thrown). */
        activeViewers.add("alex_gaming");
        
        activeViewers.add("luna_tech");
        
        activeViewers.add("stream_pro");
        
        /* "alex_gaming" is already in the set, so .add() returns false
           and the set stays at 3 elements. wasNewViewer will be false. */
        boolean wasNewViewer = activeViewers.add("alex_gaming");
        
        System.out.println("✓ Stream started! First viewers joined.\n");
        
        System.out.println("--- Current Viewers in Stream ---");
        
        /* .size() returns the number of unique elements currently in the set.
           Even though .add() was called 4 times, size is 3 because the duplicate did not count. */
        System.out.println("Unique viewers: " + activeViewers.size());
        
        System.out.println("\nViewer List:");
        
        /* The enhanced for-each loop works on any Iterable, and HashSet qualifies.
           The loop variable (viewer) takes the type of the set's elements (String).
           Iteration order is unpredictable with HashSet, so output is not necessarily in insertion order. */
        int viewerNumber = 1;
        for (String viewer : activeViewers) {
            System.out.println("  " + viewerNumber + ". " + viewer);
            viewerNumber++;
        }
        
        System.out.println();
        
        // ============================================
        // PART 2: Processing Chat Messages with ArrayBlockingQueue
        // ============================================
        
        System.out.println("--- Starting Live Chat ---\n");
        
        /* new ArrayBlockingQueue<>(5) creates a queue with a fixed capacity of 5.
           Unlike HashSet, capacity must be specified at creation and cannot grow.
           The <String> type parameter means this queue holds String messages. */
        ArrayBlockingQueue<String> chatQueue = new ArrayBlockingQueue<>(5);
        
        /* .offer() adds an element to the END of the queue (the tail).
           Returns true if added successfully, false if the queue is already full.
           It does not block or throw an exception when the queue is full. */
        chatQueue.offer("@alex_gaming: This stream is awesome!");
        
        chatQueue.offer("@luna_tech: Love the content!");
        
        chatQueue.offer("@stream_pro: When's the next episode?");
        
        System.out.println("✓ First chat messages received!\n");
        
        System.out.println("--- Chat Queue Status ---");
        
        /* .size() on ArrayBlockingQueue returns the current number of elements,
           not the capacity. Capacity is always 5; size starts at 0 and grows with each .offer(). */
        System.out.println("Messages in queue: " + chatQueue.size());
        
        System.out.println("\nPending Messages:");
        
        /* Iterating with for-each does NOT remove elements from the queue.
           ArrayBlockingQueue maintains FIFO order, so iteration goes front to back.
           The loop variable (message) holds each String element in turn. */
        int messageNum = 1;
        for (String message : chatQueue) {
            System.out.println("  " + messageNum + ". " + message);
            messageNum++;
        }
        
        System.out.println();
        
        // ============================================
        // PART 3: Host Reading and Responding to Chat
        // ============================================
        
        System.out.println("--- Host Reading Chat ---");
        
        System.out.print("Host: I'll respond to a chat message. Reveal it? (y/n): ");
        String readChat = scanner.nextLine().toLowerCase();
        
        if (readChat.equals("y")) {
            /* .poll() removes and returns the element at the FRONT of the queue (the head).
               This is what makes ArrayBlockingQueue a true FIFO structure:
               the first message added is the first message retrieved.
               Returns null if the queue is empty (never throws an exception). */
            String firstMessage = chatQueue.poll();
            
            if (firstMessage != null) {
                System.out.println("\n✓ Host reading: " + firstMessage);
                System.out.println("✓ Message removed from queue!\n");
            } else {
                System.out.println("✓ No messages in queue!\n");
            }
        }
        
        System.out.println("--- Updated Chat Queue ---");
        
        /* After .poll() removed one element, size decreased by 1.
           If the user skipped reading (entered "n"), size is still 3. */
        System.out.println("Messages remaining: " + chatQueue.size());
        
        if (!chatQueue.isEmpty()) {
            System.out.println("\nRemaining Messages:");
            
            /* Same for-each pattern as before (iterates without removing).
               The queue now has 2 elements if a message was polled, or 3 if not. */
            int remainingNum = 1;
            for (String message : chatQueue) {
                System.out.println("  " + remainingNum + ". " + message);
                remainingNum++;
            }
        } else {
            System.out.println("Chat queue is EMPTY!");
        }
        
        System.out.println("\n========================================");
        System.out.println("    Live Stream Session Complete!");
        System.out.println("========================================");
        System.out.println("Unique viewers who watched: " + activeViewers.size());
        System.out.println("Messages processed: " + (3 - chatQueue.size()));
        
        scanner.close();
    }
}

// ============================================
// KEY HASHSET METHODS REFERENCE
// ============================================
/*
 * HashSet Methods Used in This Example:
 *
 * .add(E element)
 *     Adds a unique element to the set.
 *     Returns true if the element was new, false if it was a duplicate.
 *     Duplicates are automatically ignored (no exception thrown).
 *
 * .contains(E element)
 *     Checks whether an element exists in the set.
 *     Returns true if found, false if not.
 *     Very fast: O(1) average time.
 *
 * .size()
 *     Returns the number of unique elements currently in the set.
 *     Very fast: O(1).
 *
 * .remove(E element)
 *     Removes a specific element from the set.
 *     Returns true if found and removed, false if not present.
 *     Very fast: O(1) average time.
 *
 * .isEmpty()
 *     Returns true if the set contains no elements, false otherwise.
 *     Very fast: O(1).
 *
 *
 * Why HashSet for Live Stream Viewers?
 * ✓ Automatically prevents counting the same viewer twice
 * ✓ Very fast lookup to check "is this viewer already watching?"
 * ✓ Efficient for frequently changing membership (viewers join/leave/rejoin)
 * ✓ No ordering needed; the goal is tracking UNIQUE viewers only
 *
 *
 * HashSet Key Concepts:
 *     UNIQUE DATA         Each element can only appear once.
 *     NO DUPLICATES       Trying to add a duplicate returns false and does nothing.
 *     NO ORDERING         Elements are stored in an unpredictable order (unlike ArrayList).
 *     FAST OPERATIONS     Add/remove/check all run in O(1) constant time.
 *
 *
 * Real-World Uses of HashSet:
 *     Live stream unique viewer tracking (this example)
 *     Social media unique followers (prevent double-counting)
 *     Email contact lists (no duplicate emails)
 *     Server unique IP addresses (each IP counted once)
 *     Game achievements (each achievement earned only once)
 *     Dictionary of unique words (spell checker)
 */


// ============================================
// KEY ARRAYBLOCKINGQUEUE METHODS REFERENCE
// ============================================
/*
 * ArrayBlockingQueue Methods Used in This Example:
 *
 * .offer(E element)
 *     Adds an element to the END of the queue (the tail).
 *     Returns true if added, false if the queue is already at capacity.
 *     Does NOT block or wait; simply rejects the element if full.
 *
 * .poll()
 *     Removes and returns the element at the FRONT of the queue (the head).
 *     Returns null if the queue is empty (no exception thrown).
 *     Processes elements in FIFO order: first in, first out.
 *
 * .peek()
 *     Returns the element at the front without removing it.
 *     Returns null if the queue is empty.
 *     Useful for checking the next item before deciding to remove it.
 *
 * .size()
 *     Returns the current number of elements in the queue (not the capacity).
 *     Very fast: O(1).
 *
 * .isEmpty()
 *     Returns true if the queue contains no elements, false otherwise.
 *     Very fast: O(1).
 *
 *
 * Why ArrayBlockingQueue for Chat Messages?
 * ✓ Fixed capacity prevents memory overflow (max 5 messages)
 * ✓ FIFO ordering ensures messages are read in the exact order they arrived
 * ✓ Thread-safe for real streaming systems with multiple threads
 * ✓ Coordinates the producer (viewers sending chat) and consumer (host reading)
 * ✓ Fair processing so no message is skipped or prioritized unfairly
 *
 *
 * ArrayBlockingQueue Key Concepts:
 *     QUEUE BEHAVIOR     First In, First Out (FIFO)
 *     FIXED CAPACITY     Set when created and does not grow (prevents overflow)
 *     THREAD-SAFE        Multiple threads can safely add/remove simultaneously
 *     NON-BLOCKING       .offer() returns false instead of waiting when full
 *
 *
 * Real-World Uses of ArrayBlockingQueue:
 *     Live chat message processing (this example)
 *     Server request handling with limited worker threads
 *     Game server player action queues (process actions in order)
 *     Email queue systems (limited batch processing)
 *     Print job queues (manage printing in order)
 *
 *
 * Important Distinction:
 *     .offer() returns a boolean and does not wait if full.
 *     .put() would block and wait until space is available.
 *     This course uses .offer() because the non-blocking behavior is easier to reason about.
 */


// ============================================
// HASHSET vs ARRAYBLOCKINGQUEUE COMPARISON
// ============================================
/*
 * Both are Collections Framework classes but serve different purposes:
 *
 *                        HashSet                    ArrayBlockingQueue
 * Purpose:               Unique value tracking       Ordered processing queue
 * Order:                 Random/unordered            FIFO (First In, First Out)
 * Duplicates:            Prevented automatically     Allowed
 * Capacity:              Unlimited (grows freely)    Fixed at creation
 * Best for:              Membership tracking         Message/task processing
 * Thread-safe:           No (single-thread use)      Yes (multi-thread safe)
 *
 *
 * When to use HashSet:
 *     Need unique/distinct items
 *     Order does not matter
 *     Fast lookup or duplicate checking is important
 *     Single-threaded application
 *
 * When to use ArrayBlockingQueue:
 *     Need FIFO processing (tasks or messages)
 *     Want to limit capacity to prevent overflow
 *     Multiple threads need safe coordination
 *     Order of processing matters
 */