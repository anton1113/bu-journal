 pipeline {
     agent any

     stages {
         stage('Build') {
             steps {
                 echo 'Building..'
                 sh '/opt/apache-maven-3.6.3/bin/mvn clean install'
             }
         }
         stage('Test') {
             steps {
                 echo 'Testing..'
             }
         }
         stage('Deploy') {
             steps {
                 echo 'Deploying....'
             }
         }
     }
 }