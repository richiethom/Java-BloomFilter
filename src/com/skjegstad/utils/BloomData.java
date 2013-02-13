package com.skjegstad.utils;

import java.util.BitSet;

public interface BloomData {
	
	void clear();

	boolean getBit(int bit);
	
	void setBit(int bit, boolean value);

	boolean setForHash(int abs);

	void set(int abs);
	
	BitSet toBitSet();

}
