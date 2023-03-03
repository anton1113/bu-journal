 pipeline {
     agent any

     stages {

         stage('Git checkout') {
             checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
                       userRemoteConfigs: [[url: 'https://github.com/anton1113/bu-journal.git']]])
         }

         stage('Build') {
             steps {
                 echo 'Building..'
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