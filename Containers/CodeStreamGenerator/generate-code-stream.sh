#!/usr/bin/env bash

createFileList() {
  echo "Creating initial list of files..."
  find /QualitasCorpus/QualitasCorpus-20130901r/Systems -type f -name "*.java" | sort -R > ~/files.txt
}

sendFile() {
  # echo "Sending file" "$1" to "$TARGET"
  curl -s -F "name=$1" -F "data=@$1" "$TARGET"
  sleep 0.01  # A slight delay is necessary here to not overrun buffers in the consumer
}


if [[ "$DELAY" == "" ]]; then
 DELAY=0
fi

echo "Stream-of-Code generator."
echo "Delay (seconds) between each file is:" $DELAY
echo "files are sent to                   :" $TARGET

echo "Waiting 5 seconds to give consumer time to get started..."
sleep 5

if [[ "$1" == "TEST" ]]; then
  echo "Started with TEST argument, first sending test files..."
  sendFile ./test/A.java
  sendFile ./test/B.java
  echo "Sent test files. Sleeping before continuing..."
  sleep 10
fi


createFileList

while read LINE; do
  sendFile $LINE
  sleep $DELAY
done < ~/files.txt

echo "No more files to send. Exiting."
