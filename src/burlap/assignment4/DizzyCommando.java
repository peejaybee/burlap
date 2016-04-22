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


public class DizzyCommando {

    //These are some boolean variables that affect what will actually get executed
    private static boolean visualizeInitialGridWorld = false; //Loads a GUI with the agent, walls, and goal

    //runValueIteration, runPolicyIteration, and runQLearning indicate which algorithms will run in the experiment
    private static boolean runValueIteration = true;
    private static boolean runPolicyIteration = true;
    private static boolean runQLearning = true;

    //showValueIterationPolicyMap, showPolicyIterationPolicyMap, and showQLearningPolicyMap will open a GUI
    //you can use to visualize the policy maps. Consider only having one variable set to true at a time
    //since the pop-up window does not indicate what algorithm was used to generate the map.
    private static boolean showValueIterationPolicyMap = false;
    private static boolean showPolicyIterationPolicyMap = false;
    private static boolean showQLearningPolicyMap = false;

    private static Integer MAX_ITERATIONS = 100;
    private static Integer NUM_INTERVALS = 100;

    public static void main(String[] args) {
        int width = 15;
        int height = 15;

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

        if (visualizeInitialGridWorld) {
            visualizeInitialGridWorld(domain, gen, env);
        }
        AnalysisRunner runner = new AnalysisRunner(MAX_ITERATIONS,NUM_INTERVALS, -1);
        long startTime = System.nanoTime();
        if(runValueIteration){
            runner.runValueIteration(gen,domain,initialState, rf, tf, showValueIterationPolicyMap);
        }
        long viTime = System.nanoTime();
        if(runPolicyIteration){
            runner.runPolicyIteration(gen,domain,initialState, rf, tf, showPolicyIterationPolicyMap);
        }
        long piTime = System.nanoTime();
        if(runQLearning){
            runner.runQLearning(gen,domain,initialState, rf, tf, env, showQLearningPolicyMap);
        }
        long qlTime = System.nanoTime();
        AnalysisAggregator.printAggregateAnalysis();

        double[][] rewards = rf.getRewardMatrix();
        System.out.println("Reward matrix");
        for(int i = height - 1; i >= 0; i--){
            StringBuffer lineOut = new StringBuffer();
            for (int j = 0; j < width; j++){
                lineOut.append(Double.toString(rewards[j][i]));
                if (j < width - 1){
                    lineOut.append(", ");
                }
            }
            System.out.println(lineOut);
        }

        System.out.println("Value Iteration clock time: " + (viTime - startTime));
        System.out.println("Policy Iteration clock time: " + (piTime - viTime));
        System.out.println("Q learning clock time: " + (qlTime - piTime));

        AnalysisAggregator.printAggregateAnalysisLongForm();
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
    private static void visualizeInitialGridWorld(Domain domain,
                                                  BasicGridWorld gen, SimulatedEnvironment env) {
        Visualizer v = gen.getVisualizer();
        VisualExplorer exp = new VisualExplorer(domain, env, v);

        exp.addKeyAction("w", BasicGridWorld.ACTIONNORTH);
        exp.addKeyAction("s", BasicGridWorld.ACTIONSOUTH);
        exp.addKeyAction("d", BasicGridWorld.ACTIONEAST);
        exp.addKeyAction("a", BasicGridWorld.ACTIONWEST);

        exp.initGUI();

    }

}
