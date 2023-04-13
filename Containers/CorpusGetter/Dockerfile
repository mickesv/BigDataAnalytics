FROM alpine
RUN apk add --no-cache bash perl coreutils
RUN mkdir /QualitasCorpus
VOLUME /QualitasCorpus
WORKDIR /Scripts
COPY . .
ENTRYPOINT ["./qc-get.sh"]
