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
          "/var/log/punchat/:/logs"
        ]
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "messaging"
        clientSecret = "pass"
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