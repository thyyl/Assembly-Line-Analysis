class MainCounters {
    static int NUM_LINE = 2;
    static int NUM_STATION;
    static int counter = 0;

    static int min(int a, int b) {
        counter += 2;
        return Math.min(a, b);
    }

    static int AssemblyLine(int[][] stationCost, int[][] transferCost, int[] entryCost, int[] exitCost) {
        int[] T1 = new int [NUM_STATION];
        int[] T2 = new int[NUM_STATION] ;

        T1[0] = entryCost[0] + stationCost[0][0];
        counter += 5;
        T2[0] = entryCost[1] + stationCost[1][0];
        counter += 5;

        counter++;
        for (int i = 1; i < NUM_STATION; ++i, counter += 2) {
            counter++;
            T1[i] = min(T1[i - 1] + stationCost[0][i],T2[i - 1] + transferCost[1][i] + stationCost[0][i]);
            counter += 13;
            T2[i] = min(T2[i - 1] + stationCost[1][i],T1[i - 1] + transferCost[0][i] + stationCost[1][i]);
            counter += 13;
        }
        counter++;

        counter += 10;
        return min(T1[NUM_STATION - 1] + exitCost[0],T2[NUM_STATION - 1] + exitCost[1]);
    }

    static void setNumStation(int numStation) {
        counter++;
        NUM_STATION = numStation;
    }

    public static void main (String[] args) {
        int upperBound = 50;
        int lowerBound = 1;
        int maxStation = 10;
        int minStation = 3;
        counter += 4;
        int intRandom = (int)(Math.random() * (maxStation - minStation + 1) + minStation);
        counter += 7;

        setNumStation(3);
        counter++;

        int[][] stationCost = new int[NUM_LINE][NUM_STATION];
        int[][] transferCost = new int[NUM_LINE][NUM_STATION];
        int[] entryCost = new int [NUM_LINE];
        int[] exitCost = new int[NUM_LINE];

        counter++;
        for (int i = 0; i < NUM_LINE; i++, counter += 2) {
            counter++;
            entryCost[i] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            counter += 8;
            exitCost[i] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            counter += 8;
        }
        counter++;

        counter++;
        for (int j = 0; j < NUM_STATION; j++, counter += 2) {
            counter++;
            stationCost[0][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            counter += 8;
            stationCost[1][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            counter += 8;
            transferCost[0][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            counter += 8;
            transferCost[1][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            counter += 8;
        }
        counter++;

        System.out.println(AssemblyLine(stationCost, transferCost, entryCost, exitCost));
        counter += 2;
        System.out.println("Counter: " + counter);
        System.out.println("Station: " + NUM_STATION);
    }
}
