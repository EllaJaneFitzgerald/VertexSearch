import java.util.ArrayList;
import java.util.Collections;

public class StringBuilderWorker {

    public static String makeStringBuilder(String num, ArrayList<Integer> response){
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        sb.append("\n");

        for (Integer x : response){
            sb.append(x).append(" ");
        }

        sb.append("\n");
        return(sb.toString());
    }

    public static String makeStringBuilder(String num, int response){
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        sb.append("\n");
        sb.append(response);

        sb.append("\n");
        return(sb.toString());
    }

    public static String makeStringBuilderOfEdges(String num, ArrayList<Integer[]> edges){
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        sb.append("\n");
        for (Integer[] d: edges){
                sb.append(d[0]).append("-").append(d[1]).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }

    public static String makeStringBuilder(String num, Graph response){
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        sb.append("\n");

        for(int i=0; i<=response.N; i++){
            sb.append(i);
            sb.append(": ");
            for (int v: response.graph[i]){
                sb.append(v);
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return(sb.toString());
    }

    public static String makeCleanedGraph(String num, Graph subGraph, ArrayList<Integer> cleanedVertices){
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        sb.append("\n");
        sb.append(cleanedVertices.size());
        sb.append("\n");

        for(int i=0; i<=subGraph.N; i++){
            Collections.sort(subGraph.graph[i]);
            if ((subGraph.graph[i].size() == 0) && (cleanedVertices.indexOf(i) ==  -1)){
                continue;
            }
            sb.append(i);
            sb.append(": ");
            for (int v: subGraph.graph[i]){
                sb.append(v);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return(sb.toString());
    }
}
