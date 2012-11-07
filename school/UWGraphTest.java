public class UWGraphTest {
    public static void main(String[] args) {
        try{ runTest1(); }
        catch(RuntimeException e) {
            System.err.println("Test 1 failed: " + e);
        }
        try{ runTest2(); }
        catch(RuntimeException e) {
            System.err.println("Test 2 failed: " + e);
        }
        try{ runTest3(); }
        catch(RuntimeException e) {
            System.err.println("Test 3 failed: " + e);
        }
    }
    
    public static void runTest1() {
        UWGraph test = new UndirectedWeightedGraph(10);
        test.addEdge(0,1,41);
        test.addEdge(0,2,12);
        test.addEdge(0,3,27);
        test.addEdge(0,4,19);
        test.addEdge(1,5,3);
        test.addEdge(1,7,14);
        test.addEdge(1,8,21);
        test.addEdge(1,9,20);
        test.addEdge(2,3,5);
        test.addEdge(3,4,7);
        test.addEdge(3,5,15);
        test.addEdge(3,6,16);
        test.addEdge(4,6,10);
        test.addEdge(5,6,5);
        test.addEdge(5,8,25);
        test.addEdge(6,8,16);
        test.addEdge(7,9,14);
        test.addEdge(8,9,30);
        System.out.println("Running test 1");
        System.out.println(test);
        System.out.println(test.breadthFirstSearch(7));
        System.out.println(test.depthFirstSearch(5));
        System.out.println(test.minimumSpanningTree(8));
        System.out.println(test.singleSourceShortestPath(0));
        boolean connected = test.isConnected(3,7);
        System.out.println("Vertices 3 and 7 are " + (connected ? "" : "not ") + "connected");
        boolean tree = test.isTree();
        System.out.println("Graph 1 is " + (tree ? "" : "not ") + "a tree");
    }
    
    public static void runTest2() {
        UWGraph test = new UndirectedWeightedGraph(3);
        test.addEdge(0,1,35);
        test.addEdge(1,2,23);
        test.addEdge(2,0,-12);
        System.out.println("Running test 2");
        System.out.println(test);
        try{ System.out.println(test.breadthFirstSearch(7)); }
        catch(IndexOutOfBoundsException r) { System.out.println("PASS: " + r.getMessage()); }
        try{ System.out.println(test.depthFirstSearch(5)); }
        catch(IndexOutOfBoundsException r) { System.out.println("PASS: " + r.getMessage()); }
        try{ System.out.println(test.minimumSpanningTree(8)); }
        catch(IndexOutOfBoundsException r) { System.out.println("PASS: " + r.getMessage()); }
        try{ System.out.println(test.singleSourceShortestPath(0)); }
        catch(UnsupportedOperationException r) { System.out.println("PASS: " + r.getMessage()); }
        try{ 
            int distance = test.distance(0,1);
            if(distance > 0) System.out.println("Shortest distance between 0 and 1: " + distance);
            else System.out.println("Vertices 0 and 1 are not connected");
        } catch(UnsupportedOperationException r) { System.out.println("PASS: " + r.getMessage()); }
        try{
            boolean connected = test.isConnected(3,7);
            System.out.println("Vertices 3 and 7 are " + (connected ? "" : "not ") + "connected");
        } catch(IndexOutOfBoundsException r) { System.out.println("PASS: " + r.getMessage()); }
        try{
            boolean tree = test.isTree();
            System.out.println("Graph 2 is " + (tree ? "" : "not ") + "a tree");
        } catch(IndexOutOfBoundsException r) { System.out.println("PASS: " + r.getMessage()); }
    }
    
    public static void runTest3() {
        UWGraph test = new UndirectedWeightedGraph(7);
        test.addEdge(0,1,35);
        test.addEdge(1,2,23);
        test.addEdge(2,3,12);
        test.addEdge(3,0,27);
        test.addEdge(0,2,22);
        test.addEdge(3,1,65);
        test.addEdge(4,5,27);
        test.addEdge(4,6,37);
        test.addEdge(6,5,10);
        System.out.println("Running test 3");
        System.out.println(test);
        System.out.println(test.breadthFirstSearch(1));
        System.out.println(test.depthFirstSearch(6));
        System.out.println(test.minimumSpanningTree(3));
        System.out.println(test.singleSourceShortestPath(1));
        int distance = test.distance(3,5);
        if(distance > 0) System.out.println("Shortest distance between 3 and 5: " + distance);
        else System.out.println("Vertices 3 and 5 are not connected");
        distance = test.distance(4,6);
        if(distance > 0) System.out.println("Shortest distance between 4 and 6: " + distance);
        else System.out.println("Vertices 4 and 6 are not connected");
        boolean connected = test.isConnected(1,6);
        System.out.println("Vertices 1 and 6 are " + (connected ? "" : "not ") + "connected");
        boolean tree = test.isTree();
        System.out.println("Graph 3 is " + (tree ? "" : "not ") + "a tree");
    }
}