class Main {
    static int NUM_LINE = 2;
    static int NUM_STATION;
    static int[][] bestPathway;
    static int bestExit;

    static int min(int a, int b, int assemblyLine, int station) {
        if (assemblyLine == 0 ) {
            bestPathway[assemblyLine][station] = (a < b) ? 0 : 1;
        } else if (assemblyLine == 1) {
            bestPathway[assemblyLine][station] = (a < b) ? 1 : 0;
        }
        return Math.min(a, b);
    }

    static int minExit(int a, int b) {
        bestExit = (a < b) ? 0 : 1;
        return Math.min(a, b);
    }

    static int AssemblyLine(int[][] stationCost, int[][] transferCost, int[] entryCost, int[] exitCost) {
        int[] T1 = new int [NUM_STATION];
        int[] T2 = new int[NUM_STATION];
        int i;

        T1[0] = entryCost[0] + stationCost[0][0];
        T2[0] = entryCost[1] + stationCost[1][0];

        for (i = 1; i < NUM_STATION; ++i) {
            T1[i] = min(T1[i - 1] + stationCost[0][i],T2[i - 1] + transferCost[1][i] + stationCost[0][i], 0, i);
            T2[i] = min(T2[i - 1] + stationCost[1][i],T1[i - 1] + transferCost[0][i] + stationCost[1][i],1, i);
        }

        return minExit(T1[NUM_STATION - 1] + exitCost[0],T2[NUM_STATION - 1] + exitCost[1]);
    }

    static void printPathway() {
        int i = bestExit;
        System.out.println("Exit: Assembly Line " + (bestExit + 1) + " Station " + NUM_STATION);

        for (int j = NUM_STATION - 1; j > 0; j--) {
            i = bestPathway[i][j];
            System.out.println("Assembly Line " + (i + 1) + " Station " + j);
        }

        i = bestPathway[i][0];
        System.out.println("Enter: Assembly Line " + (i + 1));
    }

    static void setNumStation(int numStation) {
        NUM_STATION = numStation;
    }

    public static void main (String[] args) {
        int upperBound = 50;
        int lowerBound = 0;
        int maxStation = 5;
        int minStation = 3;
        int intRandom = (int)(Math.random() * (maxStation - minStation + 1) + minStation);
        bestPathway = new int[2][intRandom];

        setNumStation(intRandom);

        int[][] stationCost = new int[NUM_LINE][NUM_STATION];
        int[][] transferCost = new int[NUM_LINE][NUM_STATION];
        int[] entryCost = new int [NUM_LINE];
        int[] exitCost = new int[NUM_LINE];


        for (int i = 0; i < NUM_LINE; i++) {
            entryCost[i] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            exitCost[i] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
        }

        for (int j = 0; j < intRandom; j++) {
            stationCost[0][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            stationCost[1][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            transferCost[0][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            transferCost[1][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
        }

        System.out.println(AssemblyLine(stationCost, transferCost, entryCost, exitCost));
        printPathway();
    }
}