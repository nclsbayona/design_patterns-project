FROM docker.io/jenkins/jenkins:slim-jdk17

# No utilizar en un ambiente de producción
ENV JENKINS_USER=admin
ENV JENKINS_PASS=admin
ENV JAVA_OPTS=-Djenkins.install.runSetupWizard=false
###

ADD plugins.txt .
RUN jenkins-plugin-cli -f plugins.txt
USER root
ADD start.sh /start.sh
RUN chmod +x /start.sh
USER jenkins
ENTRYPOINT ["/start.sh" ]