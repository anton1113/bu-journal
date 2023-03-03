 pipeline {
     agent any

     stages {
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
                  sh 'docker run -e BU_JOURNAL_DB_URL=${BU_JOURNAL_DB_URL} -e BU_JOURNAL_DB_USER=${BU_JOURNAL_DB_USER} -e BU_JOURNAL_DB_PASSWORD=${BU_JOURNAL_DB_PASSWORD} --restart=always ${runEnv} --name=bu-journal --net=host anton1113/bu-journal'
             }
         }
     }
 }