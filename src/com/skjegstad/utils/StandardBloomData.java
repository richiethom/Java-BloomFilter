package com.skjegstad.utils;

import java.util.BitSet;

public class StandardBloomData implements BloomData {

	private BitSet bitset;
	
	public StandardBloomData(int bitSetSize) {
		this.bitset = new BitSet(bitSetSize);
	}
	
	@Override
	public void clear() {
		bitset.clear();
	}
	
	@Override
	public boolean getBit(int bit) {
		return bitset.get(bit);
	}
	
	@Override
	public void setBit(int bit, boolean value) {
		this.bitset.set(bit, value);
	}
	
	@Override
	public boolean setForHash(int abs) {
		return bitset.get(abs);
	}
	
	@Override
	public void set(int abs) {
		bitset.set(abs, true);		
	}
	
	@Override
	public BitSet toBitSet() {
		return bitset;
	}
	
	void setBitSet(final BitSet bitSet) {
		this.bitset = bitSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bitset == null) ? 0 : bitset.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardBloomData other = (StandardBloomData) obj;
		if (bitset == null) {
			if (other.bitset != null)
				return false;
		} else if (!bitset.equals(other.bitset))
			return false;
		return true;
	}
	
	
}
