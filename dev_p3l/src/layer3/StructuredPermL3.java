package layer3;

public class StructuredPermL3 {

	byte    [] permutTable;
	boolean [] reverses;
	int     bottomTurnCount;
	String  name;
	
	
	public StructuredPermL3 (byte    [] pPermutTable,
									boolean [] pReverses,
									int     pBottomTurnCount,
									String pName) {
		permutTable		=pPermutTable;
		reverses		=pReverses;
		bottomTurnCount	=pBottomTurnCount;
		name			= pName;
	}

	public State act (State ini) {
		State ret = new State();
		
		for (int i=0; i<8; ++i) {
			ret.state[permutTable[i]]=ini.state[i];
			ret.isReverse[permutTable[i]]=ini.isReverse[i]^reverses[i];
		}
		ret.bottomTurnCount = ini.bottomTurnCount+bottomTurnCount;
		ret.trace = ini.trace +name;
		return ret;
	}
	
}
