package layer3;

import java.util.HashSet;
import java.util.Set;


public class FindSequence {
	
	int depthLimit;
	Set<StructuredPermL3> perms;
	Set<StructuredPermL3> bottomTurns;
	
	public FindSequence(int i) {
		depthLimit = i;
		perms = new HashSet<StructuredPermL3>();
		bottomTurns = new HashSet<StructuredPermL3>();
		initPerms();
	}
	
	public void initPerms() {
		
		StructuredPermL3 p1;
		p1 = new StructuredPermL3(new    byte[] {    3,    0,     2,    4,     1,     5,     6,     7},
									 	 new boolean[] { true, true, false, false, false, false, false, false},
										+1, "+");
		perms.add(p1);
		StructuredPermL3 p2;
		p2 = new StructuredPermL3(new    byte[] {    1,    4,     2,    0,     3,     5,     6,     7},
										 new boolean[] { true, false, false, true, false, false, false, false},
										-1, "-");
		perms.add(p2);
		
		StructuredPermL3 p3;
		p3 = new StructuredPermL3(new    byte[]{   4,      3,      2,     1,    0,     5,     6,     7},
										new boolean[]{ true,  false,  false, false, true, false, false, false},
										+2, "2");
		perms.add(p3);

		StructuredPermL3 q1;
		q1 = new StructuredPermL3(new    byte[]{    1,     2,     3,     0,     4,     5,     6,     7},
										new boolean[]{false, false, false, false, false, false, false, false},
										+1, "(+)");
		bottomTurns.add(q1);

		StructuredPermL3 q2;
		q2 = new StructuredPermL3(new    byte[]{   3,     0,     1,    2,     4,     5,     6,     7},
										new boolean[]{false, false, false, false, false, false, false, false},
										-1, "(-)");
		bottomTurns.add(q2);
		
		StructuredPermL3 q3;
		q3 = new StructuredPermL3(new    byte[]{   2,     3,     0,    1,     4,     5,     6,     7},
										new boolean[]{false, false, false, false, false, false, false, false},
										+2, "(2)");
		bottomTurns.add(q3);

		//p1.act(p2.act(State.getInitialState())).test();
		//p2.act(p1.act(State.getInitialState())).test();
		//p1.act(q3.act(p2.act(State.getInitialState()))).test();
		//p2.act(q3.act(p1.act(State.getInitialState()))).test();
	}
	
/*
	public void initPermsWreversePermut() {
		
		StructuredPermutationL3 p1;
		p1 = new StructuredPermutationL3(new    byte[]{   1,     4,     2,    0,     3,     5,     6,     7},
										new boolean[]{true, false, false, true, false, false, false, false},
										+1, "+");
		perms.add(p1);
		StructuredPermutationL3 p2;
		p2 = new StructuredPermutationL3(new    byte[]{   1,     0,      2,     4,     3,     5,     6,     7},
										new boolean[]{true,  true,  false, false, false, false, false, false},
										-1, "-");
		perms.add(p2);
		
		StructuredPermutationL3 p3;
		p3 = new StructuredPermutationL3(new    byte[]{   4,      3,      2,     1,    0,     5,     6,     7},
										new boolean[]{true,  false,  false, false, true, false, false, false},
										+2, "D");
		//perms.add(p3);

		StructuredPermutationL3 q1;
		q1 = new StructuredPermutationL3(new    byte[]{    3,     0,     1,     2,     4,     5,     6,     7},
										new boolean[]{false, false, false, false, false, false, false, false},
				+1, "(+)");
		//bottomTurns.add(q1);

		StructuredPermutationL3 q2;
		q2 = new StructuredPermutationL3(new    byte[]{   1,     2,     3,    0,     4,     5,     6,     7},
										new boolean[]{false, false, false, false, false, false, false, false},
										-1, "(-)");
		//bottomTurns.add(q2);
		
		StructuredPermutationL3 q3;
		q3 = new StructuredPermutationL3(new    byte[]{   2,     3,     0,    1,     4,     5,     6,     7},
										new boolean[]{false, false, false, false, false, false, false, false},
				+2, "(D)");
		bottomTurns.add(q3);

		//p1.act(p2.act(State.getInitialState())).test();
		p2.act(p1.act(State.getInitialState())).test();
		//p1.act(q3.act(p2.act(State.getInitialState()))).test();
		//p2.act(q3.act(p1.act(State.getInitialState()))).test();
	}
	*/ 
	
	public void find(int depth, State s) {
		if (depth == depthLimit ) {
			s.test();
		} else {
			Set<StructuredPermL3> operations = (depth%2)==0?perms:bottomTurns;
			
			for(StructuredPermL3 p : operations) {
				State novo = p.act(s);
				find(depth+1,novo);
			}
		}
	}


	public static void main (String []args) {
		System.out.println("###="+(-4 %4));
		for (int j=3; j<=7; ++j) {
			System.out.println("\n Achando seq de tamanho "+j);
			FindSequence fs = new FindSequence(j);
			fs.find(0,State.getInitialState());
		}
	}
	

}
