pipeline {
  agent {
    label 'compose-enabled'
  }
  stages {
    stage("Verify tooling") {
       steps{
         sh '''
          docker version
          docker info
          docker compose version
          git version
          '''
       }
    }
    stage("Inform Status I") {
      steps{
        sh 'echo OK'
      }
    }
    stage("Down docker data") {
      steps {
        sh 'docker compose -f compose.yml down'
      }
    }
    stage("Prune docker data") {
      steps {
        sh 'docker system prune -a --volumes -f'
      }
    }
    stage("Inform Status II") {
      steps{
        sh 'echo Deleted'
      }
    }
    stage("Start project") {
      steps {
        sh 'rm -rf * && git clone https://github.com/nclsbayona/design_patterns-project && cd design_patterns-project && docker compose -f compose.yml up -d'
      }
    }
    stage("Inform Status III") {
      steps{
        sh 'echo Up'
        sh 'docker compose -f compose.yml ps'
      }
    }
  }
}
