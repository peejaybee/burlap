package burlap.behavior.singleagent.auxiliary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import burlap.behavior.statehashing.StateHashFactory;
import burlap.behavior.statehashing.StateHashTuple;
import burlap.oomdp.auxiliary.common.NullTermination;
import burlap.oomdp.core.State;
import burlap.oomdp.core.TerminalFunction;
import burlap.oomdp.core.TransitionProbability;
import burlap.oomdp.singleagent.Action;
import burlap.oomdp.singleagent.GroundedAction;
import burlap.oomdp.singleagent.SADomain;

public class StateReachability {
	public static List <State> getReachableStates(State from, SADomain inDomain, StateHashFactory usingHashFactory){
		return getReachableStates(from, inDomain, usingHashFactory, new NullTermination());
	}
	
	public static List <State> getReachableStates(State from, SADomain inDomain, StateHashFactory usingHashFactory, TerminalFunction tf){
		Set <StateHashTuple> hashedStates = getReachableHashedStates(from, inDomain, usingHashFactory, tf);
		List <State> states = new ArrayList<State>(hashedStates.size());
		for(StateHashTuple sh : hashedStates){
			states.add(sh.s);
		}
		
		return states;
	}
	
	public static Set <StateHashTuple> getReachableHashedStates(State from, SADomain inDomain, StateHashFactory usingHashFactory){
		return getReachableHashedStates(from, inDomain, usingHashFactory, new NullTermination());
	}
	
	public static Set <StateHashTuple> getReachableHashedStates(State from, SADomain inDomain, StateHashFactory usingHashFactory, TerminalFunction tf){
		
		Set<StateHashTuple> hashedStates = new HashSet<StateHashTuple>();
		StateHashTuple shi = usingHashFactory.hashState(from);
		List <Action> actions = inDomain.getActions();
		
		LinkedList <StateHashTuple> openList = new LinkedList<StateHashTuple>();
		openList.offer(shi);
		while(openList.size() > 0){
			StateHashTuple sh = openList.poll();
			
			if(hashedStates.contains(sh)){
				continue;
			}
			
			hashedStates.add(sh);
			
			if(tf.isTerminal(sh.s)){
				continue; //don't expand
			}
			
			List <GroundedAction> gas = sh.s.getAllGroundedActionsFor(actions);
			for(GroundedAction ga : gas){
				List <TransitionProbability> tps = ga.action.getTransitions(sh.s, ga.params);
				for(TransitionProbability tp : tps){
					StateHashTuple nsh = usingHashFactory.hashState(tp.s);
					if(!hashedStates.contains(nsh)){
						openList.offer(nsh);
					}
				}
			}
			
		}
		
		return hashedStates;
	}
}
