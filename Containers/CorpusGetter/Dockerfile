FROM alpine
RUN apk add --no-cache bash perl coreutils
# RUN mkdir /QualitasCorpus
RUN mkdir /Logs
VOLUME /QualitasCorpus
ADD *.tar /QualitasCorpus/
WORKDIR /QualitasCorpus
RUN yes | QualitasCorpus-20130901r/bin/install.pl &> /Logs/install.log
WORKDIR /Scripts
COPY . .
ENTRYPOINT ["./qc-get.sh"]
