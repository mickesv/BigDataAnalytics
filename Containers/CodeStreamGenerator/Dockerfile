FROM alpine
RUN apk add --no-cache bash perl coreutils curl
RUN mkdir /QualitasCorpus
VOLUME /QualitasCorpus
WORKDIR /Scripts
COPY . .
ENTRYPOINT ["./generate-code-stream.sh"]
