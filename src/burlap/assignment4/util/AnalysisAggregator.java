package burlap.assignment4.util;

import java.util.ArrayList;
import java.util.List;

public final class AnalysisAggregator {
	private static List<Integer> numIterations = new ArrayList<Integer>();
	private static List<Integer> stepsToFinishValueIteration = new ArrayList<Integer>();
	private static List<Integer> stepsToFinishPolicyIteration = new ArrayList<Integer>();
	private static List<Integer> stepsToFinishQLearning = new ArrayList<Integer>();
	
	private static List<Integer> millisecondsToFinishValueIteration = new ArrayList<Integer>();
	private static List<Integer> millisecondsToFinishPolicyIteration = new ArrayList<Integer>();
	private static List<Integer> millisecondsToFinishQLearning = new ArrayList<Integer>();

	private static List<Double> rewardsForValueIteration = new ArrayList<Double>();
	private static List<Double> rewardsForPolicyIteration = new ArrayList<Double>();
	private static List<Double> rewardsForQLearning = new ArrayList<Double>();
	
	public static void addNumberOfIterations(Integer numIterations1){
		numIterations.add(numIterations1);
	}
	public static void addStepsToFinishValueIteration(Integer stepsToFinishValueIteration1){
		stepsToFinishValueIteration.add(stepsToFinishValueIteration1);
	}
	public static void addStepsToFinishPolicyIteration(Integer stepsToFinishPolicyIteration1){
		stepsToFinishPolicyIteration.add(stepsToFinishPolicyIteration1);
	}
	public static void addStepsToFinishQLearning(Integer stepsToFinishQLearning1){
		stepsToFinishQLearning.add(stepsToFinishQLearning1);
	}
	public static void printValueIterationResults(){
		System.out.print("Value Iteration,");
		printList(stepsToFinishValueIteration);
	}
	public static void printPolicyIterationResults(){
		System.out.print("Policy Iteration,");
		printList(stepsToFinishPolicyIteration);
	}
	public static void printQLearningResults(){
		System.out.print("Q Learning,");
		printList(stepsToFinishQLearning);
	}

	public static void printValueIterationResultsLong(){
		System.out.print("Value Iteration,");
		printList(stepsToFinishValueIteration);
	}
	public static void printPolicyIterationResultsLong(){
		System.out.print("Policy Iteration,");
		printList(stepsToFinishPolicyIteration);
	}
	public static void printQLearningResultsLong(){
		System.out.print("Q Learning,");
		printList(stepsToFinishQLearning);
	}


	public static void addMillisecondsToFinishValueIteration(Integer millisecondsToFinishValueIteration1){
		millisecondsToFinishValueIteration.add(millisecondsToFinishValueIteration1);
	}
	public static void addMillisecondsToFinishPolicyIteration(Integer millisecondsToFinishPolicyIteration1){
		millisecondsToFinishPolicyIteration.add(millisecondsToFinishPolicyIteration1);
	}
	public static void addMillisecondsToFinishQLearning(Integer millisecondsToFinishQLearning1){
		millisecondsToFinishQLearning.add(millisecondsToFinishQLearning1);
	}
	public static void addValueIterationReward(double reward) {
		rewardsForValueIteration.add(reward);
	}
	public static void addPolicyIterationReward(double reward) {
		rewardsForPolicyIteration.add(reward);
	}
	public static void addQLearningReward(double reward) {
		rewardsForQLearning.add(reward);
	}
	public static void printValueIterationTimeResults(){
		System.out.print("Value Iteration,");	
		printList(millisecondsToFinishValueIteration);
	}
	public static void printPolicyIterationTimeResults(){
		System.out.print("Policy Iteration,");
		printList(millisecondsToFinishPolicyIteration);
	}

	public static void printQLearningTimeResults(){
		System.out.print("Q Learning,");	
		printList(millisecondsToFinishQLearning);
	}

	public static void printValueIterationRewards(){
		System.out.print("Value Iteration Rewards,");
		printDoubleList(rewardsForValueIteration);
	}

	public static void printPolicyIterationRewards(){
		System.out.print("Policy Iteration Rewards,");
		printDoubleList(rewardsForPolicyIteration);
	}

	public static void printQLearningRewards(){
		System.out.print("Q Learning Rewards,");
		printDoubleList(rewardsForQLearning);
	}

	public static void printNumIterations(){
		System.out.print("Iterations,");	
		printList(numIterations);
	}
	private static void printList(List<Integer> valueList){
		int counter = 0;
		for(long value : valueList){
			System.out.print(String.valueOf(value));
			if(counter != valueList.size()-1){
				System.out.print(",");
			}
			counter++;
		}
		System.out.println();
	}
	private static void printListLong(List<Integer> valueList, List<Integer> iterationList, String algorithm, String valueName){
<<<<<<< HEAD
=======
		int counter = 0;
>>>>>>> ac500841eeb87df0ec3672c64ffec3c2345c36a3
		for (int i = 0; i < iterationList.size(); i++){
			System.out.println(iterationList.get(i) + "," + valueList.get(i) + "," + algorithm + "," + valueName);
		}
	}

<<<<<<< HEAD
	private static void printListLong(List<Integer> valueList, List<Integer> iterationList, String algorithm, String valueName, String tag){
		for (int i = 0; i < iterationList.size(); i++){
			System.out.println(iterationList.get(i) + "," + valueList.get(i) + "," + algorithm + "," + valueName + "," + tag);
		}
	}

=======
>>>>>>> ac500841eeb87df0ec3672c64ffec3c2345c36a3
	private static void printDoubleList(List<Double> valueList){
		int counter = 0;
		for(double value : valueList){
			System.out.print(String.valueOf(value));
			if(counter != valueList.size()-1){
				System.out.print(",");
			}
			counter++;
		}
		System.out.println();
	}

<<<<<<< HEAD
	private static void printDoubleListLong(List<Double> valueList, List<Integer> iterationList, String algorithm, String valueName, String tag){
		for (int i = 0; i < iterationList.size(); i++){
			System.out.println(iterationList.get(i) + "," + valueList.get(i) + "," + algorithm + "," + valueName + "," + tag);
		}
	}

	private static void printDoubleListLong(List<Double> valueList, List<Integer> iterationList, String algorithm, String valueName){
=======
	private static void printDoubleListLong(List<Double> valueList, List<Integer> iterationList, String algorithm, String valueName){
		int counter = 0;
>>>>>>> ac500841eeb87df0ec3672c64ffec3c2345c36a3
		for (int i = 0; i < iterationList.size(); i++){
			System.out.println(iterationList.get(i) + "," + valueList.get(i) + "," + algorithm + "," + valueName);
		}
	}

	public static void printAggregateAnalysis(){
		System.out.println("//Aggregate Analysis//\n");
		System.out.println("The data below shows the number of steps/actions the agent required to reach \n"
				+ "the terminal state given the number of iterations the algorithm was run.");
		printNumIterations();
		printValueIterationResults();
		printPolicyIterationResults();
		printQLearningResults();
		System.out.println();
		System.out.println("The data below shows the number of milliseconds the algorithm required to generate \n"
				+ "the optimal policy given the number of iterations the algorithm was run.");
		printNumIterations();
		printValueIterationTimeResults();
		printPolicyIterationTimeResults();
		printQLearningTimeResults();

		System.out.println("\nThe data below shows the total reward gained for \n"
				+ "the optimal policy given the number of iterations the algorithm was run.");
		printNumIterations();
		printValueIterationRewards();
		printPolicyIterationRewards();
		printQLearningRewards();
	}

<<<<<<< HEAD
	public static void printAggregateAnalysisLongForm() {

		System.out.println("iterations, value, algorithm, type");
		if (millisecondsToFinishValueIteration.size() > 0) {
			printListLong(millisecondsToFinishValueIteration, numIterations, "VI", "msToOptimum");
			printListLong(stepsToFinishValueIteration, numIterations, "VI", "stepsToOptimum");
			printDoubleListLong(rewardsForValueIteration, numIterations, "VI", "totalReward");
		}
		if (millisecondsToFinishPolicyIteration.size() > 0) {
			printListLong(millisecondsToFinishPolicyIteration, numIterations, "VI", "msToOptimum");
			printListLong(stepsToFinishPolicyIteration, numIterations, "VI", "stepsToOptimum");
			printDoubleListLong(rewardsForPolicyIteration, numIterations, "VI", "totalReward");

		}
		if (millisecondsToFinishQLearning.size() > 0) {
			printListLong(millisecondsToFinishQLearning, numIterations, "QL", "msToOptimum");
			printListLong(stepsToFinishQLearning, numIterations, "QL", "stepsToOptimum");
			printDoubleListLong(rewardsForQLearning, numIterations, "QL", "totalReward");
		}
	}

	public static void printAggregateAnalysisLongForm(String tag) {

		System.out.println("iterations, value, algorithm, type, tag");
		if (millisecondsToFinishValueIteration.size() > 0) {
			printListLong(millisecondsToFinishValueIteration, numIterations, "VI", "msToOptimum", tag);
			printListLong(stepsToFinishValueIteration, numIterations, "VI", "stepsToOptimum", tag);
			printDoubleListLong(rewardsForValueIteration, numIterations, "VI", "totalReward", tag);
		}
		if (millisecondsToFinishPolicyIteration.size() > 0) {
			printListLong(millisecondsToFinishPolicyIteration, numIterations, "VI", "msToOptimum", tag);
			printListLong(stepsToFinishPolicyIteration, numIterations, "VI", "stepsToOptimum", tag);
			printDoubleListLong(rewardsForPolicyIteration, numIterations, "VI", "totalReward", tag);

		}
		if (millisecondsToFinishQLearning.size() > 0) {
			printListLong(millisecondsToFinishQLearning, numIterations, "QL", "msToOptimum", tag);
			printListLong(stepsToFinishQLearning, numIterations, "QL", "stepsToOptimum", tag);
			printDoubleListLong(rewardsForQLearning, numIterations, "QL", "totalReward", tag);
		}
=======
	public static void printAggregateAnalysisLongForm(){

		System.out.println("iterations, value, algorithm, type");
		printListLong(millisecondsToFinishValueIteration, numIterations, "VI", "msToOptimum" );
		printListLong(millisecondsToFinishPolicyIteration, numIterations, "PI", "msToOptimum" );
		printListLong(millisecondsToFinishQLearning, numIterations, "QL", "msToOptimum" );
		printListLong(stepsToFinishValueIteration, numIterations, "VI", "stepsToOptimum" );
		printListLong(stepsToFinishPolicyIteration, numIterations, "PI", "stepsToOptimum" );
		printListLong(stepsToFinishQLearning, numIterations, "QL", "stepsToOptimum" );
		printDoubleListLong(rewardsForValueIteration, numIterations, "VI", "totalReward");
		printDoubleListLong(rewardsForPolicyIteration, numIterations, "PI", "totalReward");
		printDoubleListLong(rewardsForQLearning, numIterations, "QL", "totalReward");

>>>>>>> ac500841eeb87df0ec3672c64ffec3c2345c36a3
	}
}
