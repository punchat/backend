job "id-service" {
  datacenters = ["dc1"]
  type = "service"
  group "id" {
    count = 1
    task "id-api" {
      driver = "docker"
      config {
        image = "punchat/id"
        network_mode = "punchat"
        volumes = [
          "/var/log/punchat/:/logs"
        ]
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "id"
        clientSecret = "pass"
      }
      resources {
        cpu = 300
        memory = 400
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