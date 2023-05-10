FROM clojure
WORKDIR /usr/src/app/
COPY project.clj /usr/src/app

RUN lein deps
COPY . /usr/src/app

ENTRYPOINT ["lein", "run"]
