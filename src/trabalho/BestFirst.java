package trabalho;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class BestFirst {
	protected Queue<State> abertos;
	private Map<Ilayout, State> fechados;
	private State actual;
	private Ilayout objective;

	static class State {
		private Ilayout layout;
		private State father;
		private double g;

		public State(Ilayout l, State n) {
			layout = l;
			father = n;
			if (father != null)
				g = father.g + l.getG();
			else
				g = 0.0;
		}

		public String toString() {
			return layout.toString();
		}

		public double getG() {
			return g;
		}

		public int hashCode() {
			return toString().hashCode();
		}

		public boolean equals(Object o) {
			if (o == null)
				return false;
			if (this.getClass() != o.getClass())
				return false;
			State n = (State) o;

			return this.layout.equals(n.layout);
		}
	}

	final private List<State> sucessores(State n) {
		List<State> sucs = new ArrayList<>();
		List<Ilayout> children = n.layout.children();
		for (Ilayout e : children) {
			if (n.father == null || !e.equals(n.father.layout)) {
				State nn = new State(e, n);
				sucs.add(nn);
			}
		}
		return sucs;
	}

	final public Iterator<State> solve(Ilayout s, Ilayout goal) {
		objective = goal;
		abertos = new PriorityQueue<>(10,

				(s1, s2) -> (int) Math.signum(s1.getG() - s2.getG()));

		fechados = new HashMap<>();
		abertos.add(new State(s, null));
		List<State> sucs;
		
		try {
			while(true) {
				if(abertos.isEmpty())
					return null;
				State atual = abertos.poll();
				if(atual.layout.isGoal(goal)) {
					List<State> solution = new ArrayList<State>();
					for(int i = 0; i < atual.layout.getG(); i++) {
						solution.add(atual.father);
					}
					Collections.reverse(solution);
					return solution.iterator();
				}					
				else {
					sucs = sucessores(atual);
					fechados.put(atual.layout, atual);
					for (int i = 0; i < sucs.size(); i++)
						if(!fechados.containsKey(sucs.get(i).layout))
							abertos.add(sucs.get(i));
				}
			}
		} catch (OutOfMemoryError error) {
			return null;
		}
	}
}