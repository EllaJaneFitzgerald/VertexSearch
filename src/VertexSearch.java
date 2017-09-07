import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class VertexSearch {

    Graph gr;
    ArrayList<Integer[]> cleanedEdgesTheGreat;
    ArrayList<Integer> cleanedVerticesTheGreat;

    VertexSearch(String fileName) throws IOException {
        gr = new Graph(fileName);
        cleanedEdgesTheGreat = new ArrayList<Integer[]>();
        cleanedVerticesTheGreat = new ArrayList<Integer>();
    }


    private ArrayList<Integer[]> subGraphToClanedEdges(Graph subGraph){
        ArrayList<Integer[]> cleanedEdges = new ArrayList<Integer[]>();
        for (int i=0; i<=gr.N; i++){
            for (int v: subGraph.graph[i]){
                Integer[] d = new Integer[2];
                subGraph.graph[v].remove(subGraph.graph[v].indexOf(i));
                d[0] = i;
                d[1] = v;
                cleanedEdges.add(d);
            }
        }
        return(cleanedEdges);
    }


    public ArrayList<Integer[]> task2_1(ArrayList<Integer[]> cleanedEdges, ArrayList<Integer> setZ) throws IOException {
    // В предположении, что указанное подмножество рёбер cleanedEdges
    // образует очищенное множество рёбер на графе, указать, каким будет очищенное множество,
    // если игроки занимают вершины множества Z. Подразумевается, что некоторые
    // рёбра из X при такой позиции игроков загрязняются. Если повторного загрязнения не
    // происходит, то считаем, что X0 = X.
        Graph subGraph = new Graph(cleanedEdges, gr.N);
        ArrayList<Integer> boundaryOfEdges = gr.task1_3(subGraph);

        ListIterator<Integer> iterator = boundaryOfEdges.listIterator();
        while (iterator.hasNext()) {
            int v = iterator.next();

            if (setZ.indexOf(v) == -1){
                Iterator<Integer> iterator2 = subGraph.graph[v].iterator();
                while (iterator2.hasNext()){
                    int u = iterator2.next();
                    iterator2.remove();
                    subGraph.graph[u].remove(subGraph.graph[u].indexOf(v));
                    iterator.add(u);
                    iterator.previous();
                }
            }
        }
        return subGraphToClanedEdges(subGraph);
    }


    public ArrayList<Integer> task2_2(ArrayList<Integer[]> cleanedEdges, ArrayList<Integer> setZ) throws IOException {
    // Составляет множество очищенных вершин по множеству очищенных ребер и множеству Z
        ArrayList<Integer> cleanedVertices = new ArrayList<Integer>();
        Graph subGraph = new Graph(cleanedEdges,gr.N);

        for (int i=0; i<=gr.N; i++){
            if (subGraph.graph[i].size() != 0){
                cleanedVertices.add(i);
            }
        }
        for (int v: setZ){
            if (cleanedVertices.indexOf(v) == -1){
                cleanedVertices.add(v);
            }
        }

        Collections.sort(cleanedVertices);
        return cleanedVertices;
    }


    public void task2_3(int v) throws IOException {
    // Определяет очищенное множество вершин после добавления вершины v

        if (cleanedVerticesTheGreat.indexOf(v) == -1){
            cleanedVerticesTheGreat.add(v);

            for (int j: gr.graph[v]){
                if (cleanedVerticesTheGreat.indexOf(j) != -1){
                    Integer[] d = new Integer[2] ;
                    d[0] = v;
                    d[1] = j;
                    cleanedEdgesTheGreat.add(d);
                }
            }

            Collections.sort(cleanedVerticesTheGreat);
        }
    }


    public void task2_4(int w, ArrayList<Integer> setZ) throws IOException {
    // Определяет очищенное множество ребер после снятия игрока с вершины w из множества Z
        ArrayList<Integer> setZ2 = new ArrayList<Integer>();
        for (int v: setZ){
            setZ2.add(v);
        }
        setZ2.remove(setZ2.indexOf(w));
        Graph subGraph = new Graph(cleanedEdgesTheGreat,gr.N);

        ArrayList<Integer> boundaryOfEdges = gr.task1_3(subGraph);
        ListIterator<Integer> iterator = boundaryOfEdges.listIterator();
        while (iterator.hasNext()) {
            int v = iterator.next();

            if (setZ2.indexOf(v) == -1){
                Iterator<Integer> iterator2 = subGraph.graph[v].iterator();
                while (iterator2.hasNext()){
                    int u = iterator2.next();
                    iterator2.remove();
                    subGraph.graph[u].remove(subGraph.graph[u].indexOf(v));
                    iterator.add(u);
                    iterator.previous();
                }
            }
        }

        cleanedEdgesTheGreat.clear();
        for (int i=0; i<=gr.N; i++){
            for (int v: subGraph.graph[i]){
                Integer[] d = new Integer[2];
                subGraph.graph[v].remove(subGraph.graph[v].indexOf(i));
                d[0] = i;
                d[1] = v;
                cleanedEdgesTheGreat.add(d);
            }
        }
    }


    public void task2(String fileNameIn, String fileNameOut) throws IOException {
        ArrayList<Integer> setZ = FileWorker.createList(1,fileNameIn);
        ArrayList<Integer[]> cleanedEdges = FileWorker.createListOfEdges(2, fileNameIn);

        cleanedEdgesTheGreat = task2_1(cleanedEdges,setZ);
        FileWorker.write(fileNameOut, StringBuilderWorker.makeStringBuilderOfEdges("#2.1", cleanedEdgesTheGreat));
        //#2.1

        cleanedVerticesTheGreat = task2_2(cleanedEdgesTheGreat,setZ);
        FileWorker.write(fileNameOut, StringBuilderWorker.makeStringBuilder("#2.2", cleanedVerticesTheGreat));
        //#2.2

        int p = FileWorker.createInt(5,fileNameIn);
        this.task2_3(p);
        FileWorker.write(fileNameOut, StringBuilderWorker.makeStringBuilder("#2.3", cleanedVerticesTheGreat));
        //#2.3

        setZ.add(p);
        int q = FileWorker.createInt(7,fileNameIn);
        this.task2_4(q,setZ);
        FileWorker.write(fileNameOut, StringBuilderWorker.makeStringBuilderOfEdges("#2.4", cleanedEdgesTheGreat));
        //#2.4
    }


    public int task3_1(ArrayList<ArrayList<Integer>> program){
    // Проверяет, является ли эта программа допустимой. Если да, то вычислиет наибольшее
    // число игроков единовременно размещенных в вершинах графа. Иначе возвращает -1
        ArrayList<Integer> pre = new ArrayList<Integer>();
        if (!(program.get(0).isEmpty())){
            return -1;
        }
        int maxNum = 0;
        int s;
        int dif;

        for (ArrayList<Integer> step: program){
            for (int v: step){
                if (v > gr.N){
                    return -1;
                }
                s = step.size();
                dif = s-pre.size();
                if (dif > 1){
                    return -1;
                }
                if (((dif==1)||(dif==0))&&(!checkInclusion(pre,step))){
                    return -1;
                }
                if ((dif<0)&&(!checkInclusion(step,pre))){
                    return -1;
                }
                if (s>maxNum){
                    maxNum = s;
                }
            }
            pre.clear();
            for (int v: step){
                pre.add(v);
            }
        }
        return maxNum;
    }


    public boolean checkInclusion(ArrayList<Integer> list1, ArrayList<Integer> list2){
    // Проверяет вхождение списка list1 в список list2
        for (int v: list1){
            if (list2.indexOf(v) == -1){
                return false;
            }
        }
        return true;
    }


    public void task3_2(ArrayList<ArrayList<Integer>> program) throws IOException {
    // Выполняет программу
        ArrayList<Integer> pre = new ArrayList<Integer>();

        for (ArrayList<Integer> step: program){
            if (step.size() > pre.size()){
                for (int v: step){
                    if (pre.indexOf(v) == -1){
                        task2_3(v);
                        break;
                    }
                }
            } else {
                cleanedEdgesTheGreat = task2_1(cleanedEdgesTheGreat, step);
                cleanedVerticesTheGreat = task2_2(cleanedEdgesTheGreat, step);
            }
            pre.clear();
            for (int v: step){
                pre.add(v);
            }
        }
    }


    public Graph createCleanedGraph(){
        Graph cleanedSubGraph = new Graph(cleanedEdgesTheGreat,gr.N);
        return cleanedSubGraph;
    }


    public boolean isInVertices(ArrayList<Integer> pre, ArrayList<Integer> cur){
        for (int v: pre){
            if (cur.indexOf(v) == -1){
                return false;
            }
        }
        return true;
    }

    public boolean isInEdges(ArrayList<Integer[]> pre, ArrayList<Integer[]> cur){
        for (Integer[] v: pre){
            if (cur.indexOf(v) == -1){
                return false;
            }
        }
        return true;
    }

    public boolean task3_3(ArrayList<ArrayList<Integer>> program) throws IOException {
    // Проверяет, является ли программа монотонной
        ArrayList<Integer> pre = new ArrayList<Integer>();
        ArrayList<Integer[]> preCleanedEdges = new ArrayList<Integer[]>();
        ArrayList<Integer> preCleanedVertices = new ArrayList<Integer>();

        for (ArrayList<Integer> step: program){
            if (step.size() > pre.size()){
                for (int v: step){
                    if (pre.indexOf(v) == -1){
                        task2_3(v);
                        break;
                    }
                }
            } else {
                cleanedEdgesTheGreat = task2_1(cleanedEdgesTheGreat, step);
                cleanedVerticesTheGreat = task2_2(cleanedEdgesTheGreat, step);
            }
            if (!(isInEdges(preCleanedEdges,cleanedEdgesTheGreat) && isInVertices(preCleanedVertices,cleanedVerticesTheGreat))){
                return false;
            }

            preCleanedEdges.clear();
            for (Integer[] v: cleanedEdgesTheGreat){
                preCleanedEdges.add(v);
            }
            preCleanedVertices.clear();
            for (int v:cleanedVerticesTheGreat){
                preCleanedVertices.add(v);
            }
            pre.clear();
            for (int v: step){
                pre.add(v);
            }
        }
        return true;
    }


    public void task3 (String fileNameIn, String fileNameOut) throws IOException {
        ArrayList<ArrayList<Integer>> program = FileWorker.createProgram(1,fileNameIn);
        int maxNum = task3_1(program);
        FileWorker.write(fileNameOut, StringBuilderWorker.makeStringBuilder("#3.1", maxNum));
        //#3.1

        if (maxNum != -1){
            task3_2(program);
            Graph subGraph = createCleanedGraph();
            if (subGraph.equals(gr)){
                FileWorker.write(fileNameOut,"#3.2\nY");
            } else{
                FileWorker.write(fileNameOut,StringBuilderWorker.makeCleanedGraph("#3.2", subGraph, cleanedVerticesTheGreat));
            }
            //#3.2

            if (task3_3(program)){
                FileWorker.write(fileNameOut,"#3.3\n1");
            } else {
                FileWorker.write(fileNameOut,"#3.3\n0");
            }
            //#3.3
        }
    }
}
