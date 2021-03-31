class Main {
    static int NUM_LINE = 2;
    static int NUM_STATION;
    static int counter = 0;
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

    public static int AssemblyLine(int[][] stationCost, int[][] transferCost, int[] entryCost, int[] exitCost) {
        int n = stationCost[0].length;

        int first = entryCost[0] + stationCost[0][0];
        int second = entryCost[1] + stationCost[1][0];

        for (int i = 1; i < n; i++) {
            int up = min(first + stationCost[0][i], second + transferCost[1][i] + stationCost[0][i], 0, i),
                    down = min(second + stationCost[1][i], first + transferCost[0][i] + stationCost[1][i], 1, i);

            first = up;
            second = down;
        }
        counter++;

        first += exitCost[0];
        second += exitCost[1];
        
        return Math.min(first, second);
    }

    static void printPathway() {
        int i = bestExit;
        counter++;
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
        int lowerBound = 1;
        int maxStation = 10;
        int minStation = 3;
        int intRandom = (int)(Math.random() * (maxStation - minStation + 1) + minStation);
        bestPathway = new int[2][intRandom];

        setNumStation(intRandom);

        int[][] stationCost = new int[NUM_LINE][NUM_STATION];
        int[][] transferCost = new int[NUM_LINE][NUM_STATION];
        int[] entryCost = new int [NUM_LINE];
        int[] exitCost = new int[NUM_LINE];

        for (int i = 0; i < NUM_LINE; i++, counter += 2) {
            entryCost[i] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            exitCost[i] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
        }

        for (int j = 0; j < NUM_STATION; j++, counter += 2) {
            stationCost[0][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            stationCost[1][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            transferCost[0][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
            transferCost[1][j] = (int)(Math.random() * (upperBound - lowerBound + 1) + lowerBound);
        }

        System.out.println(AssemblyLine(stationCost, transferCost, entryCost, exitCost));
        printPathway();
        System.out.println("Counter: " + counter);
        System.out.println("Station: " + NUM_STATION);
    }
}
