FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1
WORKDIR /nochmal
ADD . /nochmal
CMD sbt run