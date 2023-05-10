all: corpusGetter codeStreamGenerator codeStreamConsumer cljDetector

corpusGetter:
	cd CorpusGetter && docker build -t corpusgetter .

codeStreamGenerator:
	cd CodeStreamGenerator && docker build -t csgenerator .

codeStreamConsumer:
	cd CodeStreamConsumer && docker build -t csconsumer .

cljDetector:
	cd cljdetector && docker build -t cljdetector .

clean:
	docker rmi --force corpusgetter csgenerator
