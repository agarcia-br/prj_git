package layer3;

public class State {

	byte      [] state;
	boolean   [] isReverse;
	
	int       bottomTurnCount;
	
	String	  trace;
	
	static int count = 0;

	public State() {
		state = new byte[8];
		isReverse = new boolean[8];
		bottomTurnCount = 0;
		trace = "";
	}

	public State( byte [] s, boolean[] b) {
		state = s;
		isReverse = b;
		bottomTurnCount = 0;
		trace = "";
	}
	
	static private State initialState;
	static public  State getInitialState() {
		if (null==initialState) {
			initialState = new State(new byte[] {0,1,2,3,4,5,6,7},
									 new boolean[] {false,false,false,false,false,false,false,false,false});
		}
		return initialState;
	}
	
	private boolean upperInPos() {
		for (int i=4; i<=7; ++i) {
			if (state[i]!=i) return false;
			if (isReverse[i]) return false;
		}
		return true;
	}
	public void test() {
		if ((bottomTurnCount%4)!=0) {
			//System.out.print("X:"+(bottomTurnCount%4));
		} else if (!upperInPos()) {
			//System.out.print("Y");
		} else {
			System.out.print("\t=>"+trace);
		}
		/*
		if ((++count %8)==0) {
			System.out.println("");
		} else {
			System.out.print("\t");
		}
		*/ 
		
	}
	
	
	
}
