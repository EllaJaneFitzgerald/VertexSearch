import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //task1
        String graphFile = "input/ltd01.graph";
        String inputFile = "ltd01.in";
        String outputFile = "output/ltd01.out";

        FileWorker.delete(outputFile);
        Graph gr1 = new Graph(graphFile);
        gr1.task1(inputFile, outputFile);
        // System.out.println(FileWorker.read(outputFile));

        //task2
        graphFile = "input/ltd11.graph";
        inputFile = "ltd11.in";
        outputFile = "output/ltd11.out";

        FileWorker.delete(outputFile);
        VertexSearch gr2 = new VertexSearch(graphFile);
        gr2.task2(inputFile, outputFile);

        //task3
        graphFile = "input/ltd21.graph";
        inputFile = "ltd21.in";
        outputFile = "output/ltd21.out";
        FileWorker.delete(outputFile);
        VertexSearch gr3 = new VertexSearch(graphFile);
        gr3.task3(inputFile, outputFile);

    }
}
