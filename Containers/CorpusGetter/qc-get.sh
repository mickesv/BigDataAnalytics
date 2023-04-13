#!/usr/bin/env bash

getCorpus() {
  echo "Getting QualitasCorpus..."
  cd /QualitasCorpus
  wget ftp://custsrv1.bth.se/FTP/QualitasCorpus/QualitasCorpus-20130901r-pt1.tar
  wget ftp://custsrv1.bth.se/FTP/QualitasCorpus/QualitasCorpus-20130901r-pt2.tar
  tar xf QualitasCorpus-20130901r-pt1.tar
  tar xf QualitasCorpus-20130901r-pt2.tar
  yes | QualitasCorpus-20130901r/bin/install.pl
  rm QualitasCorpus-20130901r-pt1.tar
  rm QualitasCorpus-20130901r-pt2.tar  
}


printCorpusStats() {
  echo "Statistics for QualitasCorpus"
  echo "------------------------------"
  echo "Creation time       :" $( stat -c "%z" /QualitasCorpus/QualitasCorpus-20130901r )
  echo "Size on disk        :" $( du -hs /QualitasCorpus/QualitasCorpus-20130901r )
  cd /QualitasCorpus/QualitasCorpus-20130901r/Systems
  echo "Number of files     :" $( find -type f | wc -l )
  echo "Number of Java files:"  $( find -type f -name "*.java" | wc -l )
  echo "Size of Java files  :" $( find -type f -name "*.java" -print0 | du -hc --files0-from - | tail -n1 )
}

# Start here
# --------------------

echo "Staring Corpus-Getter..."
echo "Start command is:" $0 $@

if [[ "$1" == "FETCH" ]]; then
  echo "Started with FETCH argument, fetching corpus..."
  getCorpus
  echo ""
  printCorpusStats
else
  printCorpusStats
fi

# Wait for keypress, then end container
# --------------------
read -n 1 -p "Press any key to end the container."
