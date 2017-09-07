import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Graph {

    ArrayList<Integer>[] graph;
    int N;

    Graph(String fileName) throws IOException {
    // Конструктор по файлу
        graph = FileWorker.createGraphAsList(fileName);
        N = graph.length - 1;
    }

    Graph(int n, int skip, String fileName) throws IOException {
        // Конструктор по файлу
        graph = FileWorker.createSubGraphAsList(n, skip, fileName);
        N = n;
    }

    Graph(ArrayList<Integer>[] ar){
    // Конструктор по списку
        graph = ar;
        N = ar.length - 1;
    }

    Graph(ArrayList<Integer[]> edges, int n){
    // Конструктор по списку ребер
        ArrayList<Integer>[] gr = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            gr[i] = new ArrayList<Integer>();
        }

        for (Integer[] edge: edges){
            gr[edge[0]].add(edge[1]);
            gr[edge[1]].add(edge[0]);
        }
        graph = gr;
        N = n;
    }


    public ArrayList<Integer> task1_1(int a){
    //Для данной вершины получает набор смежных с ней вершин (через пробел,
    // отсортированных в порядке возрастания их номеров).
        Collections.sort(graph[a]);
        return(graph[a]);
    }


    public int task1_2(){
    //Вычисляет число компонент связности графа
        int conComp = 0;
        boolean[] visited = new boolean[N+1];

        for(int i=0; i<=N; i++){
            if (!visited[i]){
                conComp++;
                explove(visited,i);
            }
        }
        return(conComp);
    }
    private void explove(boolean[] visited, int i){
        visited[i] = true;
        for (Integer x : graph[i]){
            if (!visited[x]){
                explove(visited,x);
            }
        }
    }


    public ArrayList<Integer> task1_3(Graph subGraph){
    // Проверяет, является ли subGraph подграфом. Если да, то возвращает границу
    // множества его ребер, иначе -1
        int size;
        ArrayList<Integer> theBPR = new ArrayList<Integer>();
        boolean flag = true;        //является подграфом

        for(int i=0; i<subGraph.graph.length; i++){
            for (Integer x : subGraph.graph[i]){
                if (this.graph[i].indexOf(x) == -1){
                    flag = false;   //не является подграфом
                    break;
                }
                if (subGraph.graph[x].indexOf(i) == -1){
                    flag = false;   //не является подграфом
                    break;
                }
            }

            size = subGraph.graph[i].size();
            if ((this.graph[i].size() > size) && (size != 0)){
                theBPR.add(i);
            }
        }

        if (!flag){
            theBPR.clear();
            theBPR.add(-1);
        }
        return(theBPR);
    }


    public void task1(String fileNameIn, String fileNameOut) throws IOException {
        File file = new File(fileNameIn);

        BufferedReader in = new BufferedReader(new FileReader(file));
        String s;
        in.readLine();
        s = in.readLine();
        int p = Integer.parseInt(s);
        FileWorker.write(fileNameOut, StringBuilderWorker.makeStringBuilder("#1.1", this.task1_1(p)));
        //#1.1

        in.readLine();
        FileWorker.write(fileNameOut, StringBuilderWorker.makeStringBuilder("#1.2", this.task1_2()));
        //#1.2
        in.close();
        Graph subGraph = new Graph(N, 4, fileNameIn);

        FileWorker.write(fileNameOut, StringBuilderWorker.makeStringBuilder("#1.3", this.task1_3(subGraph)));
        //#1.3
    }
}
