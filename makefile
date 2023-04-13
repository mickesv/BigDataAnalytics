s1: corpusCheck

corpusVolume:
	@docker volume create qc-volume

corpusGet: corpusVolume
	@docker run -it -v qc-volume:/QualitasCorpus --name qc-getter corpusgetter ./qc-get.sh FETCH

corpusCheck: corpusVolume
	@docker run -it -v qc-volume:/QualitasCorpus --name qc-getter corpusgetter

clean:
	@docker rm -f qc-getter
