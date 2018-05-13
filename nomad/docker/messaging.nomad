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
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "messaging"
        clientSecret = "pass"
      }
      resources {
        cpu = 300
        memory = 450
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