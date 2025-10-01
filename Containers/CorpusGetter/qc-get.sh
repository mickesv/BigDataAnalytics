#!/usr/bin/env bash

# This should already be done in the build now.
# --------------------
getCorpus_deprecated() {
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

diagnose() {
  echo "Diagnosing QualitasCorpus..."
  cd /QualitasCorpus
  ls -l
  ls -l QualitasCorpus-20130901r  
  echo "Number of tar files:"
  find . -name "*.tar*" -print | wc -l
  echo "Number of zip files:"
  find . -name "*.zip" -print | wc -l
  echo ""
  echo "--------------------"
  echo "Output of install log:"
  cat /Logs/install.log
}

reinstall() {
  echo "Attempting to reinstall QualitasCorpus..."

  mkdir /Logs
  cd /QualitasCorpus
  yes | QualitasCorpus-20130901r/bin/install.pl &> /Logs/install.log
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

# This should already be done in the build now.
# --------------------
# if [[ "$1" == "FETCH" ]]; then
#   echo "Started with FETCH argument, fetching corpus..."
#   getCorpus
#   printCorpusStats
# else
#   printCorpusStats
# fi

if [[ "$1" == "DIAGNOSE" ]]; then
    diagnose
fi

if [[ "$1" == "REINSTALL" ]]; then
    reinstall
fi



printCorpusStats

# Wait for keypress, then end container
# --------------------
read -n 1 -p "Press any key to end the container."
