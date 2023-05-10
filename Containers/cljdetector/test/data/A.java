package org.conqat.engine.code_clones.index;
import org.conqat.lib.commons.digest.MD5Digest;
public class Chunk {
	private final String originId;
	private final MD5Digest chunkHash;
	private final int firstUnitIndex;
	private final int firstRawLineNumber;
	private final int lastRawLineNumber;
	private final int rawStartOffset;
	private final int rawEndOffset;
	private final int elementUnits;
	public Chunk(String originId, MD5Digest chunkHash, int firstUnitIndex,
			int firstRawLineNumber, int lastRawLineNumber, int rawStartOffset,
			int rawEndOffset) {
		this.originId = originId;
		this.chunkHash = chunkHash;
		this.firstUnitIndex = firstUnitIndex;
		this.firstRawLineNumber = firstRawLineNumber;
		this.lastRawLineNumber = lastRawLineNumber;
		this.rawStartOffset = rawStartOffset;
		this.rawEndOffset = rawEndOffset;
		this.elementUnits = -1;
	}
	public Chunk(String originId, MD5Digest chunkHash, int firstUnitIndex,
			int firstRawLineNumber, int lastRawLineNumber, int rawStartOffset,
			int rawEndOffset, int unitCount) {
		this.originId = originId;
		this.chunkHash = chunkHash;
		this.firstUnitIndex = firstUnitIndex;
		this.firstRawLineNumber = firstRawLineNumber;
		this.lastRawLineNumber = lastRawLineNumber;
		this.rawStartOffset = rawStartOffset;
		this.rawEndOffset = rawEndOffset;
		this.elementUnits = unitCount;
	}
	public String getOriginId() {
		return originId;
	}
	public MD5Digest getChunkHash() {
		return chunkHash;
	}
	public int getFirstUnitIndex() {
		return firstUnitIndex;
	}
	public int getFirstRawLineNumber() {
		return firstRawLineNumber;
	}
	public int getLastRawLineNumber() {
		return lastRawLineNumber;
	}
	public int getRawStartOffset() {
		return rawStartOffset;
	}
	public int getRawEndOffset() {
		return rawEndOffset;
	}
	public int getElementUnits() {
		return elementUnits;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Chunk)) {
			return false;
		}
		Chunk other = (Chunk) obj;
		return other.originId.equals(originId)
				&& other.chunkHash.equals(chunkHash)
				&& other.firstUnitIndex == firstUnitIndex
				&& other.firstRawLineNumber == firstRawLineNumber
				&& other.lastRawLineNumber == lastRawLineNumber
				&& other.rawStartOffset == rawStartOffset
				&& other.rawEndOffset == rawEndOffset;
	}
	@Override
	public int hashCode() {
		return originId.hashCode() + 13 * chunkHash.hashCode() + 413
				* firstUnitIndex;
	}
}