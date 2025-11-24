pipeline {
    agent any

    stages {
        stage('Preparación') {
            steps {
                git branch: 'main', url: 'https://github.com/NatalyAvila20/Examen.git'
            }
        }

        stage('Construcción') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Pruebas') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
