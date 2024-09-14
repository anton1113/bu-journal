 pipeline {
     agent any

     stages {
         stage('Mvn clean install') {
             steps {
                 sh 'mvn clean install'
             }
         }
         stage('Stop running container') {
             steps {
                 sh 'docker ps -a -q --filter name=bu-journal | xargs -r docker stop'
                 sh 'docker ps -a -q --filter name=bu-journal | xargs -r docker rm -f'
             }
         }
         stage('Rm old docker image') {
             steps {
                 sh 'docker images -q bu-journal | xargs -r docker image rm'
             }
         }
         stage('Build docker image') {
             steps {
                 sh 'docker build -t anton1113/bu-journal -f Dockerfile target'
             }
         }
         stage('Run new container') {
             steps {
                  sh 'docker run -d --env-file /var/lib/jenkins/env/bu-journal.env --restart=always ${runEnv} --name=bu-journal --net=host anton1113/bu-journal'
             }
         }
     }
 }