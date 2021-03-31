class Main {
    static int NUM_LINE = 2;
    static int NUM_STATION;
    static int counter = 0;
    static int[][] bestPathway;
    static int bestExit;

    public static int AssemblyLine(int[][] stationCost, int[][] transferCost, int[] entryCost, int[] exitCost) {
        int n = stationCost[0].length;

        int first = entryCost[0] + stationCost[0][0];
        counter += 4;
        int second = entryCost[1] + stationCost[1][0];
        counter += 4;

        counter++;
        for (int i = 1; i < n; i++, counter += 2) {
            counter++;

            int up = Math.min(first + stationCost[0][i], second + transferCost[1][i] + stationCost[0][i]),
                    down = Math.min(second + stationCost[1][i], first + transferCost[0][i] + stationCost[1][i]);
            counter += 16;

            first = up;
            counter++;
            second = down;
            counter++;
        }
        counter++;

        first += exitCost[0];
        counter += 2;
        second += exitCost[1];
        counter += 2;

        counter += 2;
        return Math.min(first, second);
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
        int intRandom = (int)(Math.random() * (maxStation - minStation + 1) + minStation);
        bestPathway = new int[2][intRandom];
        counter += 11;

        setNumStation(intRandom);
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
        counter += 3;
        System.out.println("Counter: " + counter);
    }
}
