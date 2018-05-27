job "messaging-service" {
  datacenters = ["dc1"]
  type = "service"
  group "messaging" {
    count = 1
    task "messaging-api" {
      driver = "docker"
      config {
        image = "punchat/messaging"
        network_mode = "punchat"
        volumes = [
          "/var/log/punchat/${appName}/:/logs/${appName}/"
        ]
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "messaging"
        clientSecret = "pass"
        appName = "messaging"
        DATABASE = "messaging"
      }
      resources {
        cpu = 300
        memory = 550
        network {
          port "http" {}
        }
      }
    }
    restart {
      attempts = 1
    }
  }
}