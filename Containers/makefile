all: corpusGetter codeStreamGenerator codeStreamConsumer

corpusGetter:
	cd CorpusGetter && docker build -t corpusgetter .

codeStreamGenerator:
	cd CodeStreamGenerator && docker build -t csgenerator .

codeStreamConsumer:
	cd CodeStreamConsumer && docker build -t csconsumer .

clean:
	docker rmi --force corpusgetter csgenerator
