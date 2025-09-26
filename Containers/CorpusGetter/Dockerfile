FROM alpine
RUN apk add --no-cache bash perl coreutils
RUN mkdir /QualitasCorpus
VOLUME /QualitasCorpus
ADD *.tar /QualitasCorpus/
WORKDIR /QualitasCorpus
RUN yes | QualitasCorpus-20130901r/bin/install.pl
WORKDIR /Scripts
COPY . .
ENTRYPOINT ["./qc-get.sh"]
