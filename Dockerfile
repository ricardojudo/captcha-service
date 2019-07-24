FROM openjdk:8-jre-alpine

ARG user=java
ARG group=java
ARG uid=1000
ARG gid=1000
ARG home=/usr/src/myapp
ARG app=captcha-service.jar

COPY target/${app} /usr/src/myapp/${app}
WORKDIR /usr/src/myapp

RUN adduser -h ${home} -u ${uid} -D -s /bin/ash ${user} && \
	chown -R ${user}:${gid} ${home}

USER ${user}
CMD ["java","-jar", "captcha-service.jar"] 