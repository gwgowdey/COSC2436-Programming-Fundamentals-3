import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphDriver {
    public static void main(String[] args) {
        //processRawData();
        //writeRandomEdgeData(109, 0.45);
        //UnweightedGraph<City> cityGraph = new UnweightedGraph<>();
        List<City> cities = readVertexData();
        //new DisplayUSMap(cities, new ArrayList<AbstractGraph.Edge>());

        List<AbstractGraph.Edge> edges = readEdgeData();

        UnweightedGraph<City> cityGraph = new UnweightedGraph<>(cities, edges);
        List<City> bfsResult = cityGraph.bfs(new City("Fort Worth", "TX", 32.71666666666667,97.31666666666666));
        System.out.println("BFS traversal order when starting with Fort Worth");
        for (City city : bfsResult) {
            System.out.println(city.getCity() + ", " + city.getState());
        }
        System.out.println();

        List<City> dfsResult = cityGraph.dfs(new City("Fort Worth", "TX", 32.71666666666667,97.31666666666666));
        System.out.println("DFS traversal order when starting with Fort Worth");
        for (City city : dfsResult) {
            System.out.println(city.getCity() + ", " + city.getState());
        }

    }

    static List<AbstractGraph.Edge> readEdgeData() {

        List<AbstractGraph.Edge> edges = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(new File("city_edges.txt"));
            while (myReader.hasNextLine()) {
                int v = myReader.nextInt();
                int u = myReader.nextInt();
                edges.add(new AbstractGraph.Edge(v, u));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return edges;
    }

    static List<City> readVertexData() {

        List<City> cities = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(new File("us_cities.txt"));
            myReader.useDelimiter("\t");

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, "\t");
                String city = tokenizer.nextToken();
                String state = tokenizer.nextToken();
                //System.out.println(city + " " + state);
                double lat = Double.valueOf(tokenizer.nextToken());
                double lon = Double.valueOf(tokenizer.nextToken());

                cities.add(new City(city, state, lat, lon));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return cities;
    }

    static void processRawData() {
        // Open text file
        String line;
        double R = 6371.0;
        List<String> processedData = new ArrayList<>();
        List<City> cities = new ArrayList<>();

        try {
            Scanner myReader = new Scanner(new File("cities.txt"));
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                String[] str = line.split("\t");
                //System.out.println(line);
                if (str.length != 5) continue;
                String[] cityState = str[0].split(",");
                cityState[0] = cityState[0].trim();
                cityState[1] = cityState[1].trim();
                if (cityState[1].equals("AK") || cityState[1].equals("HI")) continue;
                double lat = Double.valueOf(str[1]) + (Double.valueOf(str[2]) / 60.0);
                double lon = Double.valueOf(str[3]) + (Double.valueOf(str[4]) / 60.0);

                cities.add(new City(cityState[0], cityState[1], lat, lon));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("us_cities.txt");
            for (City city : cities) {
                String s = city.getCity() + "\t" + city.getState() + "\t" + city.getLatitude() + "\t" + city.getLongitude() + "\n";
                myWriter.write(s);
            }
            myWriter.close();
            System.out.println("Done processing file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static void writeRandomEdgeData(int numVertices, double edgeProb) {
        Random random = new Random();
        Set<Integer> usedVertices = new HashSet<>();
        double contProb = 0.0;

        try {
            FileWriter myWriter = new FileWriter("city_edges.txt");
            for (int i = 0; i < numVertices; ++i) {
                usedVertices.clear();
                do {
                    int u = random.nextInt(numVertices);
                    if (!usedVertices.contains(u) && i != u) {
                        String s = "" + i + " " + u + "\n";
                        myWriter.write(s);
                        usedVertices.add(u);
                    }
                    contProb = random.nextDouble();
                } while (contProb < edgeProb);
            }

            myWriter.close();
            System.out.println("Done processing file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

