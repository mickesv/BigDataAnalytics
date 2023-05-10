cwd := $(shell pwd)

s1: cljDetector

corpusVolume:
	@docker volume create qc-volume

corpusGet: corpusVolume
	@docker run -it -v qc-volume:/QualitasCorpus --name qc-getter corpusgetter ./qc-get.sh FETCH

corpusCheck: corpusVolume
	@docker run -it -v qc-volume:/QualitasCorpus --name qc-getter corpusgetter

codeStream: corpusVolume
	@docker compose -f stream-of-code.yaml up

cljDetector: corpusVolume
	@docker compose -f all-at-once.yaml up

mongodb:
	@docker compose -f all-at-once.yaml up dbstorage

mongosh:
	@docker exec -it bigdataanalytics-dbstorage-1 mongosh

clean:
	@docker rm -f qc-getter cs-generator cs-consumer
