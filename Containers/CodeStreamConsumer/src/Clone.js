class Clone {

    constructor(sourceName, targetName, sourceChunk, targetChunk) {
        this.sourceName = sourceName;
        this.sourceStart = sourceChunk[0].lineNumber;
        this.sourceEnd = sourceChunk[sourceChunk.length -1].lineNumber;
        this.sourceChunk = sourceChunk;

        this.targets = [{ name: targetName, startLine: targetChunk[0].lineNumber }];
    }

    equals(clone) {
        return this.sourceName == clone.sourceName &&
            this.sourceStart == clone.sourceStart &&
            this.sourceEnd == clone.sourceEnd;
    }

    addTarget(clone) {
        this.targets = this.targets.concat(clone.targets);
    }

    isNext(clone) {
        return (this.sourceChunk[this.sourceChunk.length-1].lineNumber == 
                clone.sourceChunk[clone.sourceChunk.length-2].lineNumber);
    }

    maybeExpandWith(clone) {
        if (this.isNext(clone)) {
            this.sourceChunk = [...new Set([...this.sourceChunk, ...clone.sourceChunk])];
            this.sourceEnd = this.sourceChunk[this.sourceChunk.length-1].lineNumber;
            //console.log('Expanded clone, now starting at', this.sourceStart, 'and ending at', this.sourceEnd);
            return true;
        } else {
            return false;
        }
    }
}

module.exports = Clone;
