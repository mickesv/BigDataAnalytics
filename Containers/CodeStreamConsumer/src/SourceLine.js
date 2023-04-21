class SourceLine {
    constructor(lineNumber, content) {
        this.myLineNumber = lineNumber;
        this.myContent = content;
    }

    // Both JS getters and regular methods, for historical reasons
    get lineNumber() { return this.getLineNumber(); }
    get content() { return this.getContent(); }
	  
	  getLineNumber() { return this.myLineNumber; }
	  getContent() { return this.myContent; }
	  hasContent() { return this.myContent.length > 0; }
    equals(otherLine) { return this.content == otherLine.content; }
}

module.exports=SourceLine;
