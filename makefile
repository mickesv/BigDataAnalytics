cwd := $(shell pwd)

s1: codeStreamConsumer

corpusVolume:
	@docker volume create qc-volume

corpusGet: corpusVolume
	@docker run -it -v qc-volume:/QualitasCorpus --name qc-getter corpusgetter ./qc-get.sh FETCH

corpusCheck: corpusVolume
	@docker run -it -v qc-volume:/QualitasCorpus --name qc-getter corpusgetter

codeStream: corpusVolume
	@docker compose -f stream-of-code.yaml up


clean:
	@docker rm -f qc-getter cs-generator cs-consumer
