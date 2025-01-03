pipeline {
    agent any
    tools {
        maven 'sonarmaven' // Ensure this matches the Maven configuration in Jenkins
    }
    environment {
        SONAR_TOKEN = credentials('sonar-token') // Replace with your credentials ID for the SonarQube token
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Install ChromeDriver') {
            steps {
                script {
                    // If ChromeDriver is not installed yet, you can download it dynamically
                    // This is an example, make sure to adjust for your environment
                    if (!fileExists("${env.WORKSPACE}/chromedriver")) {
                        echo 'Downloading ChromeDriver...'
                        sh '''
                            curl -o chromedriver.zip https://chromedriver.storage.googleapis.com/131.0.6778.205/chromedriver_linux64.zip
                            unzip chromedriver.zip
                            mv chromedriver /usr/local/bin/
                        '''
                    }
                }
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean verify' // Run tests and generate JaCoCo reports
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') { // Ensure the name matches your SonarQube configuration
                    bat """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=mavensonar \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.tests=src/test/java \
                        -Dsonar.junit.reportPaths=target/surefire-reports \
                        -Dsonar.jacoco.reportPaths=target/site/jacoco/jacoco.xml \
                        -Dsonar.pmd.reportPaths=target/pmd-duplicates.xml \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.login=%SONAR_TOKEN%
                    """
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
