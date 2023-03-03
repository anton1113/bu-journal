 pipeline {
     agent any

     stages {
         stage('Echo ls') {
             steps {
                 sh 'ls'
             }
         }
         stage('Build') {
             steps {
                 echo 'Building..'
                 sh 'mvn clean install'
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