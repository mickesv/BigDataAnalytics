class FileStorage {
    static #myInstance = null;
    static getInstance() {
        FileStorage.#myInstance = FileStorage.#myInstance || new FileStorage();
        return FileStorage.#myInstance;
    }

    #myFiles = [];
    #myFileNames = [];
    #myNumberOfFiles = 0;

    constructor() {
    }

    get numberOfFiles() { return this.#myNumberOfFiles; }
    get filenames() { return this.#myFileNames; }

    isFileProcessed(fileName) {
        return false; // FIXME: sometimes this returns true even when it shouldn't. Probably a race condition.
        return this.#myFileNames.includes(fileName);
    }

    storeFile(file) {
        if (!this.isFileProcessed(file.name)) {
            //console.log('Adding file', file.name, 'to storage. Now containing', 1+this.#myNumberOfFiles, 'files.');
            this.#myFileNames.push(file.name);
            this.#myNumberOfFiles++;

            // FUTURE Use a database instead.
            this.#myFiles.push(file);
        }

        return file;
    }

    * getAllFiles() {
        // FUTURE Convert this to use this.#myFileNames to fetch each file from a database instead.
        // then use yield to release each file to where it is going to be used.
        for (let f of this.#myFiles) {
            yield f;
        }
    }
}

module.exports = FileStorage;
