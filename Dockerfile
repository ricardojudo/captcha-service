#FROM openjdk:8-jre-alpine
FROM docker-repo.avl.sat.gob.mx/openjdk:8-jre

ARG user=java
ARG group=java
ARG uid=1000
ARG gid=1000
ARG home=/usr/src/app
ARG app=captcha-service.jar

RUN	mkdir -p ${home} && \
	adduser --system --home ${home} --uid ${uid} --shell /bin/ash ${user} && \
	chown -R ${user}:${gid} ${home} && \ 
	chmod g+s ${home}

COPY target/${app} ${home}/${app}
WORKDIR ${home}

USER ${user}
CMD ["java","-jar", "captcha-service.jar"] 
