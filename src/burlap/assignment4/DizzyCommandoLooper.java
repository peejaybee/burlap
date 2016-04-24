package burlap.assignment4;

import burlap.assignment4.util.AnalysisAggregator;
import burlap.assignment4.util.AnalysisRunner;
import burlap.assignment4.util.BasicTerminalFunction;
import burlap.assignment4.util.MapPrinter;
import burlap.domain.singleagent.gridworld.GridWorldRewardFunction;
import burlap.oomdp.core.Domain;
import burlap.oomdp.core.TerminalFunction;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.environment.SimulatedEnvironment;
import burlap.oomdp.singleagent.explorer.VisualExplorer;
import burlap.oomdp.visualizer.Visualizer;

import java.util.Arrays;


public class DizzyCommandoLooper {

    private static boolean showQLearningPolicyMap = false;

    private static Integer MAX_ITERATIONS = 10;
    private static Integer NUM_INTERVALS = 10;



    public static void main(String[] args) {
        int width = 15;
        int height = 15;

//        Double[] epsilons = {0.1, 0.2, 0.3, 0.4, 1.0};
        Double[] epsilons = {0.1, 0.2};

        int [][] userMap = new int[width][height];
        for (int[] row: userMap) {
            Arrays.fill(row,0);
        }

        int[][] map = MapPrinter.mapToMatrix(userMap);
        int maxX = map.length-1;
        int maxY = map[0].length-1;

        BasicGridWorld gen = new BasicGridWorld(userMap,maxX,maxY);
        Domain domain = gen.generateDomain();

        State initialState = BasicGridWorld.getExampleState(domain);

        GridWorldRewardFunction rf = new GridWorldRewardFunction(width, height, -1);
        placeLightAt(rf, 7, 7, 4);
        placeLightAt(rf, 0, 14, 4);
        placeLightAt(rf, 14, 0, 4);

        TerminalFunction tf = new BasicTerminalFunction(maxX,maxY); //Goal is at the top right grid

        SimulatedEnvironment env = new SimulatedEnvironment(domain, rf, tf,
                initialState);

        AnalysisRunner runner = new AnalysisRunner(MAX_ITERATIONS,NUM_INTERVALS, -1);

        for (Double epsilon: epsilons) {
            long startTime = System.nanoTime();

            runner.runQLearning(gen, domain, initialState, rf, tf, env, showQLearningPolicyMap, epsilon);
            long qlTime = System.nanoTime();

//            System.out.println("Q learning clock time: " + (qlTime - startTime));

//            AnalysisAggregator.printAggregateAnalysisLongForm(epsilon.toString());
        }
    }

    private static void placeLightAt(GridWorldRewardFunction rf, int x, int y, int brightness) {
        //placing a light gives a reward of (taxicab distance from light - brightness)
        //with a maximum reward of zero.  Rewards from overlapping lights are added
        double[][] rewards = rf.getRewardMatrix();
        int width = rewards.length;
        int height = rewards[0].length;
        for (int dx = -brightness + 1; dx <= brightness; dx++) {
            for (int dy = -brightness + 1; dy <= brightness; dy++) {
                int calcX = dx + x;
                int calcY = dy + y;
                if (calcX >= 0 && calcX < width && calcY >= 0 && calcY < height) {
                    int taxicabDistance = Math.abs(dx) + Math.abs(dy);
                    rewards[calcX][calcY] += (double) Math.min(taxicabDistance - brightness, 0);
                }
            }
        }
    }
}
