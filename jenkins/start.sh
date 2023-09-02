#!/bin/bash

# while ! nc -z gitea 3000 ; do
#     echo "[Jenkins] - Waiting for the Gitea Server"
#     sleep 3
# done

# while ! nc -z django 8000 ; do
#     echo "[Jenkins] - Waiting for the Django Server"
#     sleep 3
# done
#
# if [ ! -f /var/jenkins_home/.ssh/id_rsa ]; then 
#     yes | ssh-keygen -t rsa -q -N "" -f /var/jenkins_home/.ssh/id_rsa
# fi

# cp -f ssh-config /var/jenkins_home/.ssh/config

/usr/local/bin/jenkins.sh