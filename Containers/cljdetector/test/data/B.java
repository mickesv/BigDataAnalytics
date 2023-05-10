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
	package se.bth.serl.clony.processors;
	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.stream.Collectors;
	import se.bth.serl.clony.chunks.BaseChunkCollection;
	import se.bth.serl.clony.chunks.Chunk;
	import se.bth.serl.clony.transformers.IContentTransformer;
	import se.bth.serl.clony.transformers.TransformerChain;
	public class SourceProcessor {
		public static final int DEFAULT_CHUNKSIZE = 5;
		private TransformerChain tchain;
		private BaseChunkCollection chunkCollection;
		private List<Path> listOfJavaFiles; 
		private int chunkSize;
		private int totalFilesToProcess;
		private int currentFilesProcessed;
		private int totalLinesProcessed;		
		public SourceProcessor(Path rootFolder, int chunkSize, BaseChunkCollection chunkCollection) {
			this.chunkSize = chunkSize > 0 ? chunkSize : DEFAULT_CHUNKSIZE;
			this.chunkCollection = chunkCollection;
			this.totalFilesToProcess = 0;
			this.currentFilesProcessed = 0;
			this.totalLinesProcessed = 0;
			this.tchain = new TransformerChain();			
			try {
				this.listOfJavaFiles = Files.walk(rootFolder, Integer.MAX_VALUE).filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".java")).collect(Collectors.toList());
				this.totalFilesToProcess = listOfJavaFiles.size();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}		
		public void addTransformer(IContentTransformer t) {
			tchain.addTransformer(t);
		}
	public int getFirstUnitIndex() {
		return firstUnitIndex;
	}
	public int getFirstRawLineNumber() {
		return firstRawLineNumber;
	}
	public SourceProcessor(Path rootFolder, int chunkSize, BaseChunkCollection chunkCollection) {
		this.chunkSize = chunkSize > 0 ? chunkSize : DEFAULT_CHUNKSIZE;
		this.chunkCollection = chunkCollection;
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
}