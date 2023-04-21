class Timer {
    // Timer Management
    // --------------------
    static startTimer(file, timerName) {
        file.timers = file.timers || [];
        file.timers[timerName] = process.hrtime.bigint();
        return file;
    }

    static endTimer(file, timerName) {
        let end = process.hrtime.bigint();
        let start = file.timers[timerName] || end;
        file.timers[timerName] = end-start;
        return file;
    }

    static getTimers(file) { return file.timers; }
}


module.exports = Timer;
