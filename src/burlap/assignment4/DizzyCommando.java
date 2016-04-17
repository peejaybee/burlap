package burlap.assignment4;

import burlap.behavior.policy.GreedyQPolicy;
import burlap.behavior.policy.Policy;
import burlap.behavior.singleagent.EpisodeAnalysis;
import burlap.behavior.singleagent.auxiliary.EpisodeSequenceVisualizer;
import burlap.behavior.singleagent.auxiliary.StateReachability;
import burlap.behavior.singleagent.auxiliary.performance.LearningAlgorithmExperimenter;
import burlap.behavior.singleagent.auxiliary.performance.PerformanceMetric;
import burlap.behavior.singleagent.auxiliary.performance.TrialMode;
import burlap.behavior.singleagent.auxiliary.valuefunctionvis.ValueFunctionVisualizerGUI;
import burlap.behavior.singleagent.auxiliary.valuefunctionvis.common.ArrowActionGlyph;
import burlap.behavior.singleagent.auxiliary.valuefunctionvis.common.LandmarkColorBlendInterpolation;
import burlap.behavior.singleagent.auxiliary.valuefunctionvis.common.PolicyGlyphPainter2D;
import burlap.behavior.singleagent.auxiliary.valuefunctionvis.common.StateValuePainter2D;
import burlap.behavior.singleagent.learning.LearningAgent;
import burlap.behavior.singleagent.learning.LearningAgentFactory;
import burlap.behavior.singleagent.learning.tdmethods.QLearning;
import burlap.behavior.singleagent.learning.tdmethods.SarsaLam;
import burlap.behavior.singleagent.planning.Planner;
import burlap.behavior.singleagent.planning.deterministic.DeterministicPlanner;
import burlap.behavior.singleagent.planning.deterministic.informed.Heuristic;
import burlap.behavior.singleagent.planning.deterministic.informed.astar.AStar;
import burlap.behavior.singleagent.planning.deterministic.uninformed.bfs.BFS;
import burlap.behavior.singleagent.planning.deterministic.uninformed.dfs.DFS;
import burlap.behavior.singleagent.planning.stochastic.valueiteration.ValueIteration;
import burlap.behavior.valuefunction.QFunction;
import burlap.behavior.valuefunction.ValueFunction;
import burlap.domain.singleagent.gridworld.*;
import burlap.oomdp.auxiliary.common.SinglePFTF;
import burlap.oomdp.auxiliary.stateconditiontest.StateConditionTest;
import burlap.oomdp.auxiliary.stateconditiontest.TFGoalCondition;
import burlap.oomdp.core.Domain;
import burlap.oomdp.core.TerminalFunction;
import burlap.oomdp.core.objects.ObjectInstance;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.RewardFunction;
import burlap.oomdp.singleagent.SADomain;
import burlap.oomdp.singleagent.common.GoalBasedRF;
import burlap.oomdp.singleagent.common.UniformCostRF;
import burlap.oomdp.singleagent.common.VisualActionObserver;
import burlap.oomdp.singleagent.environment.Environment;
import burlap.oomdp.singleagent.environment.EnvironmentServer;
import burlap.oomdp.singleagent.environment.SimulatedEnvironment;
import burlap.oomdp.statehashing.HashableStateFactory;
import burlap.oomdp.statehashing.SimpleHashableStateFactory;
import burlap.oomdp.visualizer.Visualizer;

import java.util.Arrays;


public class DizzyCommando {

    int width;
    int height;
    double probSucceed;

    GridWorldDomain gwdg;
    Domain domain;
    GridWorldRewardFunction rf;
    TerminalFunction tf;
    StateConditionTest goalCondition;
    State initialState;
    HashableStateFactory hashingFactory;
    Environment env;

    protected int[][] userMap;

    public DizzyCommando() {

        width = 21;
        height = 21;
        probSucceed = .8;

        userMap = new int[width][height];
        for (int[] row: userMap) {
            Arrays.fill(row,0);
        }

        gwdg = new GridWorldDomain(width, height);
        gwdg.setProbSucceedTransitionDynamics(probSucceed);
        domain = gwdg.generateDomain();

        rf = new GridWorldRewardFunction(width, height, -1);
        placeLightAt(11, 11, 5);

        tf = new GridWorldTerminalFunction(11, 20);
        goalCondition = new TFGoalCondition(tf);

        initialState = GridWorldDomain.getOneAgentOneLocationState(domain);
        GridWorldDomain.setAgent(initialState,11,0);

        hashingFactory = new SimpleHashableStateFactory();

        env = new SimulatedEnvironment(domain, rf, tf, initialState);

    }

    public void valueIterationExample(String outputPath){
        Planner planner = new ValueIteration(domain, rf, tf, 0.99, hashingFactory, .001, 100);
        Policy p = planner.planFromState(initialState);
        p.evaluateBehavior(initialState, rf, tf).writeToFile(outputPath + "vi");
    }

    public void visualize(String outputPath){
        Visualizer v = GridWorldVisualizer.getVisualizer(gwdg.getMap());
        new EpisodeSequenceVisualizer(v, domain, outputPath);
    }

    public static void main(String[] args) {

        DizzyCommando example = new DizzyCommando();
        String outputPath = "output/";

        example.valueIterationExample(outputPath);

        example.visualize(outputPath);
    }

    private void placeLightAt(int x, int y, int brightness) {
        //placing a light gives a reward of (taxicab distance from light - brightness)
        //with a maximum reward of zero.  Rewards from overlapping lights are added
        double[][] rewards = rf.getRewardMatrix();
        for (int dx = -brightness + 1; dx < brightness; dx++) {
            for (int dy = -brightness + 1; dy < brightness; dy++) {
                int calcX = dx + x;
                int calcY = dy + y;
                if (calcX > 0 && calcX < width && calcY > 0 && calcY < width) {
                    int taxicabDistance = Math.abs(dx) + Math.abs(dy);
                    rewards[calcX][calcY] += (double) Math.max(taxicabDistance - brightness, 0);
                }
            }
        }
    }

}
