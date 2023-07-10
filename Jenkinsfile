 pipeline {
     agent any

     stages {
         stage('Mvn clean install') {
             steps {
                 sh '/opt/apache-maven-3.6.3/bin/mvn clean install'
             }
         }
         stage('Stop running container') {
             steps {
                 sh 'docker stop bu-journal'
                 sh 'docker rm -f bu-journal'
             }
         }
         stage('Rm old docker image') {
             steps {
                 sh 'docker image rm anton1113/bu-journal'
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