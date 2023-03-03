 pipeline {
     agent any

     stages {
              stage('Who am I?') {
                  steps {
                      sh 'whoami'
                  }
              }
         stage('Mvn clean install') {
             steps {
                 sh '/opt/apache-maven-3.6.3/bin/mvn clean install'
             }
         }
         stage('Build docker image') {
             steps {
                 sh 'docker build -t anton1113/bu-journal -f Dockerfile target'
             }
         }
         stage('Stop running container') {
             steps {
                 sh 'docker rm -f bu-journal'
             }
         }
         stage('Run new image') {
             steps {
                  sh 'docker run -d --name=bu-journal --net=host anton1113/bu-journal'
             }
         }
     }
 }