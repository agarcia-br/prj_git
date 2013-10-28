package layer3;

import java.util.HashSet;
import java.util.Set;


public class FindSequence {
	
	int depthLimit;
	
	StructuredPermL3  identity;
	StructuredPermL3  initialRotation;
	Set<StructuredPermL3> perms;
	Set<StructuredPermL3> bottomTurns;
	
	static Set<StructuredPermL3> finalPatterns;
	
	public FindSequence(int i) {
		depthLimit = i;
		perms = new HashSet<StructuredPermL3>();
		bottomTurns = new HashSet<StructuredPermL3>();
		initPerms();
		if (null == finalPatterns) {
			finalPatterns = new HashSet<StructuredPermL3>();
			initSearchedPatterns();
		}
	}
	
	public void initSearchedPatterns() {
		
		//String [] permname = new String[]{"ident","r_adj","l_adj","oposite","cicle"};
		String [] permname = new String[]{"I","DR","DL","X","C","C2"};
		byte [][] perm = {
			// 1/12
			/* byte [] ident =*/ new    byte[] {    0,    1,     2,    3,     4,     5,     6,     7},
			// 1/12 
			/* byte [] r_adj =*/ new    byte[] {    1,    0,     3,    2,     4,     5,     6,     7},
			// 1/12 
			/* byte [] l_adj =*/ new    byte[] {    3,    2,     1,    0,     4,     5,     6,     7},
			// 1/12
			/* byte [] oposite =*/ new    byte[] {    2,    3,     0,    1,     4,     5,     6,     7},
			// 1/12 * 4 * 2 = 8/12
			/* byte [] cicle =*/ new    byte[] {    0,    3,     1,    2,     4,     5,     6,     7},
			/* byte [] cicle =*/ new    byte[] {    0,    2,     3,    1,     4,     5,     6,     7}
		};
		//String [] flipname = new String[]{"noflip","allflip","flip_01","flip_12","flip_23","flip_30","vert_flip","hori_flip"};
		String [] flipname = new String[]{"O","A","F_01","F_12","F_23","F_30","H_VERT","H_HORI"};
		boolean [][] flip = {
		// 1/8
		/*boolean [] noflip =*/  new boolean[] { false, false, false, false, false, false, false, false},
		// 1/8
		/*boolean [] allflip =*/  new boolean[] { true, true, true, true, false, false, false, false},
		// 4/8
		/*boolean [] flip_01 =*/  new boolean[] { true, true, false, false, false, false, false, false},
		/*boolean [] flip_12 =*/  new boolean[] { false, true, true, false, false, false, false, false},
		/*boolean [] flip_23 =*/  new boolean[] { false, false, true, true, false, false, false, false},
		/*boolean [] flip_30 =*/  new boolean[] { true, false, false, true, false, false, false, false},
		// 1/8 
		/*boolean [] vert_flip =*/  new boolean[] { true, false, true, false, false, false, false, false},
		// 1/8 
		/*boolean [] hori_flip =*/  new boolean[] { false, true, false, true, false, false, false, false}
		};
		StructuredPermL3 p;
		for (int i=0; i<perm.length;++i) {
			for (int j=0; j<flip.length;++j) {
				p = new StructuredPermL3(perm[i],flip[j], 0,"",permname[i]+";"+flipname[j]);
				finalPatterns.add(p);
			}
		}
		/* FIX NAMES */
		p1_loop: for (StructuredPermL3 p1:finalPatterns) {
			for (StructuredPermL3 p2:finalPatterns) {
				if (match(p1.actInverse(State.getInitialState()),p2.act(State.getInitialState()))) {
					p1.name = p2.reverseName;
					continue p1_loop;
				}
			}
		}
		
	}
	
	public void initPerms() {
		
		identity =  new StructuredPermL3(new    byte[] {    0,    1,     2,    3,     4,     5,     6,     7},
			 	 new boolean[] { false, false, false, false, false, false, false, false},
				0, "","");
		initialRotation =  new StructuredPermL3(new    byte[] {    3,    0,     1,    2,     4,     5,     6,     7},
			 	 new boolean[] { false, false, false, false, false, false, false, false},
				0, ">","");
 
		
		StructuredPermL3 p1;
		p1 = new StructuredPermL3(new    byte[] {    3,    0,     2,    4,     1,     5,     6,     7},
									 	 new boolean[] { true, true, false, false, false, false, false, false},
										+1, "+","");
		perms.add(p1);
		StructuredPermL3 p2;
		p2 = new StructuredPermL3(new    byte[] {    1,    4,     2,    0,     3,     5,     6,     7},
										 new boolean[] { true, false, false, true, false, false, false, false},
										-1, "-","");
		perms.add(p2);
		
		StructuredPermL3 p3;
		p3 = new StructuredPermL3(new    byte[]{   4,      3,      2,     1,    0,     5,     6,     7},
										new boolean[]{ true,  false,  false, false, true, false, false, false},
										+2, "2","");
		perms.add(p3);

		StructuredPermL3 q1;
		q1 = new StructuredPermL3(new    byte[]{    1,     2,     3,     0,     4,     5,     6,     7},
										new boolean[]{false, false, false, false, false, false, false, false},
										+1, "(+)","");
		bottomTurns.add(q1);

		StructuredPermL3 q2;
		q2 = new StructuredPermL3(new    byte[]{   3,     0,     1,    2,     4,     5,     6,     7},
										new boolean[]{false, false, false, false, false, false, false, false},
										-1, "(-)","");
		bottomTurns.add(q2);
		
		StructuredPermL3 q3;
		q3 = new StructuredPermL3(new    byte[]{   2,     3,     0,    1,     4,     5,     6,     7},
										new boolean[]{false, false, false, false, false, false, false, false},
										+2, "(2)","");
		bottomTurns.add(q3);

		/*
		System.out.println("@@@@@@="+match(p1.actInverse(p1.act(State.getInitialState())),State.getInitialState()));
		System.out.println("@@@@@@="+match(p2.actInverse(p2.act(State.getInitialState())),State.getInitialState()));
		System.out.println("@@@@@@="+match(p3.actInverse(p3.act(State.getInitialState())),State.getInitialState()));
		System.out.println("@@@@@@="+match(q1.actInverse(q1.act(State.getInitialState())),State.getInitialState()));
		System.out.println("@@@@@@="+match(q2.actInverse(q2.act(State.getInitialState())),State.getInitialState()));
		System.out.println("@@@@@@="+match(q3.actInverse(q3.act(State.getInitialState())),State.getInitialState()));
		*/ 
		//p2.act(p1.act(State.getInitialState())).test();
		//p1.act(q3.act(p2.act(State.getInitialState()))).test();
		//p2.act(q3.act(p1.act(State.getInitialState()))).test();
	}

	public boolean match(State s, State s2) {
		for (int i = 0; i<8; ++i) {
			if ((s.bottomTurnCount%4)!=0) {
				//System.out.println("r1 "+s.bottomTurnCount);
				return false;
			}
			if (s.state[i]!=s2.state[i]) {
				//System.out.println("r2");
				return false;
			}
			if (s.isReverse[i]!= s2.isReverse[i]) {
				//System.out.println("r3");
				return false;
			}
		}
		return true;
	}
	
	public boolean match(State s, StructuredPermL3 l) {
		for (int i = 0; i<8; ++i) {
			if ((s.bottomTurnCount%4)!=0) return false;
			if (s.state[i]!=l.permutTable[i]) return false;
			if (s.isReverse[i]!= l.reverses[i]) return false;
		}
		return true;
	}


	public void findBegin(int depth, State s) {
		find(depth,s);
		for(StructuredPermL3 p : bottomTurns) {
			State novo = p.act(s);
			find(depth,novo);
		}
	}
	
	static Set<StructuredPermL3> found;
	public void find(int depth, State s) {
		if (depth == depthLimit ) {
			//s.test();
		} else {
			Set<StructuredPermL3> operations = (depth%2)==0?perms:bottomTurns;
			
				//State novo = identity.act(s);
				for(StructuredPermL3 p : operations) {
					State novo = p.act(s);
					for(StructuredPermL3 fp :finalPatterns) {
						if (match(novo,fp)) {
							System.out.println("found "+("".equals(fp.name)?("INV+"+fp.reverseName):fp.name)+"="+novo.trace);
							found.add(fp);
						}
					}
					find(depth+1,novo);
				}
		}
	}


	public static void main (String []args) {
		
		System.out.println("Achadas:");
		for (int j=3; j<=10 /*10*/; ++j) {
			found  = new HashSet<StructuredPermL3>();
			System.out.println("\n Achando seq de tamanho "+j);
			FindSequence fs = new FindSequence(j);
			fs.findBegin(0,State.getInitialState());
			finalPatterns.removeAll(found);
		}
		System.out.println("Nao achadas:");
		for(StructuredPermL3 fp :finalPatterns) {
			System.out.println("not found "+fp.name);
		}
	}
	

}
