FROM alpine AS base
RUN apk add --no-cache bash perl coreutils
# RUN mkdir /QualitasCorpus
RUN mkdir /Logs
VOLUME /QualitasCorpus
ADD *.tar /QualitasCorpus/
WORKDIR /QualitasCorpus
RUN yes | QualitasCorpus-20130901r/bin/install.pl &> /Logs/install.log
WORKDIR /Scripts
COPY qc-get.sh qc-get.sh
RUN echo "Built base"

FROM base AS entry
ENTRYPOINT ["./qc-get.sh"]

FROM base AS alternative
COPY *.tar /Download/
ENTRYPOINT ["./qc-get.sh"]
