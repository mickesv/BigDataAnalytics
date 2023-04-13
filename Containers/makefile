all: corpusGetter

corpusGetter:
	cd CorpusGetter && docker build -t corpusgetter .

clean:
	docker rmi --force corpusgetter
