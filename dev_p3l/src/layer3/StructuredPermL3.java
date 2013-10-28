package layer3;

public class StructuredPermL3 {

	byte    [] permutTable;
	boolean [] reverses;
	int     bottomTurnCount;
	String  name;
	String  reverseName;
	
	
	public StructuredPermL3 (byte    [] pPermutTable,
									boolean [] pReverses,
									int     pBottomTurnCount,
									String pName, String rName) {
		permutTable		=pPermutTable;
		reverses		=pReverses;
		bottomTurnCount	=pBottomTurnCount;
		name			= pName;
		reverseName 	= rName;
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

	public State actInverse (State ini) {
		State ret = new State();
		
		for (int i=0; i<8; ++i) {
			// se j = permutTable[i]
			// significa que act leva i->j
			// logo actInverse leva j->i
			ret.state[i]=ini.state[permutTable[i]];
			// se j = permutTable[i]
			// significa que act leva i->j invertido se reverses[i]
			// logo actInverse leva j->i invertido ser reverses[i]
			ret.isReverse[i]=ini.isReverse[permutTable[i]]^reverses[i];
		}
		ret.bottomTurnCount = ini.bottomTurnCount - bottomTurnCount;
		ret.trace = ini.trace + "("+name+")-1";
		return ret;
	}
	
}
