import java.io.*;
import java.util.ArrayList;

public class FileWorker {

    public static void write(String fileName, String text) throws IOException {
    // Записывает в файл с названием fileName строку text
    // Если файла с таким именем не существует, создает его и записывает строку text
        File file = new File(fileName);

        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter out = new FileWriter(file, true);
        out.write(text);
        out.close();
    }


    private static void exists(String fileName) throws FileNotFoundException {
    // Проверяет существование файла с именем fileName
    // Если такого файла не существует, вызывает исключение
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }


    public static String read(String fileName) throws IOException {
        // Считывает файл с именем fileName
        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        exists(fileName);

        BufferedReader in = new BufferedReader(new FileReader(file));
        String s;
        while ((s = in.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }

        in.close();
        return sb.toString();
    }


    public static void delete(String fileName) throws FileNotFoundException {
        // Удаляем файл с именем fileName
        File file = new File(fileName);
        if (file.exists()){
            file.delete();
        }
    }


    public static boolean[][] createGraphAsMatrix(String fileName) throws IOException {
    // Считывает файл-граф и сохраняет его матрицу инциденций
        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        exists(fileName);
        int a,b,c;

        BufferedReader in = new BufferedReader(new FileReader(file));
        String s;
        s = in.readLine();
        int N = Integer.parseInt(s);
        boolean[][] graph = new  boolean[N][N];

        while ((s = in.readLine()) != null) {
            sb = sb.append(s);
            a = sb.indexOf(":");
            b = Integer.parseInt(sb.substring(0, a));  //строчка
            sb.delete(0,a+2);

            while(sb.length()>0){
                a = sb.indexOf(" ");
                if (a==-1){
                    a = sb.length();
                    c = Integer.parseInt(sb.substring(0,a));
                    sb.delete(0,a);
                } else {
                    c = Integer.parseInt(sb.substring(0,a));
                    sb.delete(0,a+1);
                }
                graph[b][c] = true;
            }
        }

        return graph;
    }


    public static int createInt(int skip, String fileName) throws IOException {
        File file = new File(fileName);
        exists(fileName);

        BufferedReader in = new BufferedReader(new FileReader(file));
        for (int j=1; j<=skip; j++){
            in.readLine();
        }
        String s = in.readLine();
        return Integer.parseInt(s);
    }


    public static ArrayList<Integer> createList(int skip, String fileName) throws IOException {
        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        exists(fileName);

        BufferedReader in = new BufferedReader(new FileReader(file));
        for (int j=1; j<=skip; j++){
            in.readLine();
        }
        String s = in.readLine();
        sb = sb.append(s);
        int a,c;
        ArrayList <Integer> list = new ArrayList<Integer>();

        while(sb.length()>0){
            a = sb.indexOf(" ");
            if (a==-1){
                a = sb.length();
                c = Integer.parseInt(sb.substring(0,a));
                sb.delete(0,a);
            } else {
                c = Integer.parseInt(sb.substring(0,a));
                sb.delete(0, a + 1);
            }
            list.add(c);
        }

        return list;
    }


    public static ArrayList<Integer[]> createListOfEdges(int skip, String fileName) throws IOException {
    // Возвращает список ребер
        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        exists(fileName);

        BufferedReader in = new BufferedReader(new FileReader(file));
        for (int j=1; j<=skip; j++){
            in.readLine();
        }
        String s;
        s = in.readLine();
        sb = sb.append(s);
        ArrayList<Integer[]> edges = new ArrayList<Integer[]>();
        int a;
        while(sb.length()>0){
            Integer[] d = new Integer[2];
            a = sb.indexOf("-");
            if (a==-1){
                a = sb.length();
                sb.delete(0,a);
            } else {
                d[0] = Integer.parseInt(sb.substring(0,a));
                sb.delete(0, a + 1);
            }
            a = sb.indexOf(" ");
            if (a==-1){
                a = sb.length();
                d[1] = Integer.parseInt(sb.substring(0,a));
                sb.delete(0,a);
            } else {
                d[1] = Integer.parseInt(sb.substring(0,a));
                sb.delete(0, a + 1);
            }
            edges.add(d);
        }
        return edges;
    }


    public static ArrayList<ArrayList<Integer>> createProgram(int skip, String fileName) throws IOException {
        ArrayList<ArrayList<Integer>> prog = new ArrayList<ArrayList<Integer>>();

        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        String s;
        exists(fileName);

        BufferedReader in = new BufferedReader(new FileReader(file));
        for (int j=1; j<=skip; j++){
            in.readLine();
        }

        while((s = in.readLine()).length()>0){
            if (s.indexOf('[') == -1){
                break;
            }

            sb = sb.append(s);
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length()-1);
            int a;
            ArrayList<Integer> d = new ArrayList<Integer>();

            while(sb.length()>0){
                a = sb.indexOf(",");
                if (a==-1){
                    a = sb.length();
                    d.add(Integer.parseInt(sb.substring(0,a)));
                    sb.delete(0,a);
                } else {
                    d.add(Integer.parseInt(sb.substring(0,a)));
                    sb.delete(0, a + 2);
                }
            }
            prog.add(d);
        }
        return prog;
    }


    public static ArrayList<Integer>[] createGraphAsList(String fileName) throws IOException {
    // Считывает файл-граф и сохраняет его список смежности, пропускает n строчек
        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        exists(fileName);
        int a,b,c;

        BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        String s;
        s = in.readLine();
        int N = Integer.parseInt(s);
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<Integer>();
        }


        while ((s = in.readLine()) != null) {
            sb = sb.append(s);
            a = sb.indexOf(":");
            b = Integer.parseInt(sb.substring(0, a));
            sb.delete(0,a+2);

            while(sb.length()>0){
                a = sb.indexOf(" ");
                if (a==-1){
                    a = sb.length();
                    c = Integer.parseInt(sb.substring(0,a));
                    sb.delete(0,a);
                } else {
                    c = Integer.parseInt(sb.substring(0,a));
                    sb.delete(0,a+1);
                }
                graph[b].add(c);
            }
        }
        return(graph);
    }


    public static ArrayList<Integer>[] createSubGraphAsList(int n, int skip, String fileName) throws IOException {
    // Считывает файл-граф и сохраняет его список смежности, пропускает n строчек
        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        exists(fileName);
        int a,b,c;

        BufferedReader in = new BufferedReader(new FileReader(file));
        for (int j=1; j<=skip; j++){
            in.readLine();
        }
        String s;
        s = in.readLine();
        int N = Integer.parseInt(s);
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<Integer>();
        }


        for (int j=1; j<=N; j++) {
            s = in.readLine();
            sb = sb.append(s);
            a = sb.indexOf(":");
            b = Integer.parseInt(sb.substring(0, a));
            sb.delete(0,a+2);

            while(sb.length()>0){
                a = sb.indexOf(" ");
                if (a==-1){
                    a = sb.length();
                    c = Integer.parseInt(sb.substring(0,a));
                    sb.delete(0,a);
                } else {
                    c = Integer.parseInt(sb.substring(0,a));
                    sb.delete(0,a+1);
                }
                graph[b].add(c);
            }
        }
        return(graph);
    }
}