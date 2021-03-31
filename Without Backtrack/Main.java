class Main {
    static int NUM_LINE = 2;
    static int NUM_STATION;
    static int counter = 0;

    public static int AssemblyLine(int[][] stationCost, int[][] transferCost, int[] entryCost, int[] exitCost) {
        int n = stationCost[0].length;

        int first = entryCost[0] + stationCost[0][0];
        int second = entryCost[1] + stationCost[1][0];

        for (int i = 1; i < n; i++) {
            int up = Math.min(first + stationCost[0][i], second + transferCost[1][i] + stationCost[0][i]),
                    down = Math.min(second + stationCost[1][i], first + transferCost[0][i] + stationCost[1][i]);

            first = up;
            second = down;
        }

        first += exitCost[0];
        second += exitCost[1];

        return Math.min(first, second);
    }

    static void setNumStation(int numStation) {
        NUM_STATION = numStation;
    }

    public static void main (String[] args) {
        int upperBound = 50;
        int lowerBound = 1;
        int maxStation = 10;
        int minStation = 3;
        int intRandom = (int)(Math.random() * (maxStation - minStation + 1) + minStation);

        setNumStation(intRandom);

        int[][] stationCost = new int[NUM_LINE][NUM_STATION];
        int[][] transferCost = new int[NUM_LINE][NUM_STATION];
        int[] entryCost = new int [NUM_LINE];
        int[] exitCost = new int[NUM_LINE];

        for (int i = 0; i < NUM_LINE; i++) {
            entryCost[i] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            exitCost[i] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
        }

        for (int j = 0; j < NUM_STATION; j++) {
            stationCost[0][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            stationCost[1][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            transferCost[0][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            transferCost[1][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
        }

        System.out.println(AssemblyLine(stationCost, transferCost, entryCost, exitCost));
    }
}
